package com.group4.app.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.group4.app.controller.ActionController;
import com.group4.app.model.creatures.AttributeType;
import com.group4.app.model.creatures.Enemy;
import com.group4.app.model.creatures.EnemyFactory;
import com.group4.app.model.creatures.Entity;
import com.group4.app.model.creatures.IAttackable;
import com.group4.app.model.creatures.Player;
import com.group4.app.model.dungeon.DungeonEntitySpawner;
import com.group4.app.model.dungeon.DungeonWorldGenerator;
import com.group4.app.model.dungeon.IDrawable;
import com.group4.app.model.dungeon.IPositionable;
import com.group4.app.model.dungeon.IWorldContainer;
import com.group4.app.model.dungeon.PathfindingHelper;
import com.group4.app.model.dungeon.Position;
import com.group4.app.model.dungeon.Tile;
import com.group4.app.model.dungeon.World;
import com.group4.app.model.input.ActionInput;
import com.group4.app.model.items.WeaponFactory;
import com.group4.app.model.turns.ITurnTaker;
import com.group4.app.model.turns.TurnHandler;

public class Model implements IWorldContainer, IPlayerManager, IEnemyManager, IModel {
    private static Model instance = null;
    private List<IModelObserver> observers;
    private IController controller;
    private Player player;
    private TurnHandler turnHandler;
    private Boolean isPlayerTurn;
    private Map<String, World> floors;
    private World currentWorld;
    private boolean restartQueued;
    private boolean dead;

    private static final String PLAYER_ID = "player";
    
    public static Model getInstance(){
        if (instance == null) {
            instance = new Model();
            return instance;
        }
        else {
            return Model.instance;
        }
    }

    private Model(){
        this.observers = new ArrayList<IModelObserver>();
        this.floors = new HashMap<String, World>();
        this.isPlayerTurn = false;
        this.turnHandler = new TurnHandler();
        this.dead = false;
    }

    public void addBasicMap(int size, double emptyChance){
        World world = new World(100);
        currentWorld = world;
        this.add(currentWorld);
        world.add(new Tile("stone", new Position(0, 0, world.getId())));
        for (int x = 0; x<size; x++) {
            for (int y = 0; y<size; y++) {
                double r = Math.random();
                if(r> emptyChance){
                    try {
                        world.add(new Tile("stone", new Position(x, y, world.getId())));
                    } catch (IllegalArgumentException e) {
                        System.out.println("Tile already exists at position: " + x + ", " + y);
                    }
                    r = Math.random();
                    if(r > 0.995){
                        r = Math.random();
                        Enemy e;
                        if(r > 0.5){
                            e = EnemyFactory.createZombie(new Position(x, y, world.getId()), this);

                        } else {
                            e = EnemyFactory.createSkeleton(new Position(x, y, world.getId()), this);
                        }
                        add(e);
                        addToTurnOrder(e);
                    }
                }
            }
        }
        this.player = new Player(PLAYER_ID, 3, WeaponFactory.createSword(), new Position(0, 0, world.getId()), this);
        add(player);
        addToTurnOrder(player);
    }

    public void addBasicMap(int size) {
        addBasicMap(size, 0.1);
    }

    public void addRandomMap(int size) {
        World world = DungeonWorldGenerator.generate(size, this);
        this.player = new Player(PLAYER_ID, 3, WeaponFactory.createSword(), new Position(27, 27, world.getId()), this);
        add(player);
        addToTurnOrder(player);
        List<Creature> enemies = DungeonEntitySpawner.spawnEnemies(world, 0.01, this);
        for (Creature e : enemies) {
            addToTurnOrder(e);
        }
    }

    public void add(World world){
        this.floors.put(world.getId(), world);
        if (this.currentWorld == null){
            this.currentWorld = world;
        }
    }

    public void remove(World world){
        this.remove(world.getId());
    }

    public void remove(String floorId){
        this.floors.remove(floorId);
    }

    public World getWorld(String floorId){
        return this.floors.get(floorId);
    }

    public String getPlayerFloor(){
        return player.getFloor();
    }

    public Player getPlayer(){
        return this.player;
    }

    public Position getPlayerPos() {
        return this.player.getPos();
    }

    public Tile getTile(Position pos){
        return this.getWorld(pos.getFloor()).getTile(pos);
    }

    public Set<IPositionable> getEntities(Position pos){
        return getTile(pos).getEntities();
    }

    public List<IDrawable> getDrawables(String floorId, Position pos){
        IDrawable[] entities = getEntities(pos).toArray(new IDrawable[0]);
        IDrawable tile = getTile(pos);
        ArrayList<IDrawable> drawables = new ArrayList<IDrawable>();
        drawables.add(tile);
        for (IDrawable entity : entities){
            drawables.add(entity);
        }
        return drawables;
    }

    @Override
    public ITileContainer getTileContainer() {
        return this;
    }

    public void add(Tile tile){
        this.getWorld(tile.getFloor()).add(tile);
    }

    public void remove(Tile tile){
        this.remove(tile.getPos());;
    }

    public void remove(Position pos){
        this.getWorld(pos.getFloor()).remove(pos);
    }

    public void add(Entity entity){
        this.getWorld(entity.getFloor()).add(entity);
    }

    public void add(IPositionable positionable){
        this.getWorld(positionable.getFloor()).add(positionable);
    }

    public void remove(Entity entity){
        this.getWorld(entity.getFloor()).remove(entity);
        updateObservers();
    }

    @Override
    public void setDeadTile(Position position) {
        getTile(position).setId("deadEnemy");
    }

    public void remove(IPositionable positionable){
        this.getWorld(positionable.getFloor()).remove(positionable);
    }

    public void startPlayerTurn(){
        System.out.println("Player turn started");
        this.isPlayerTurn = true;
        updateObservers();
    }

    public void endPlayerTurn(){
        System.out.println("Player turn ended");
        this.isPlayerTurn = false;
        updateObservers();
    }

    public boolean isPlayerTurn(){
        return this.isPlayerTurn;
    }

    public void addToTurnOrder(ITurnTaker turnTaker){
        this.turnHandler.add(turnTaker);
    }

    /**
     * 
     * @return the id of the current world.
     */
    public String getCurrentWorldId(){
        return this.currentWorld.getId();
    }

    public void setCurrentWorld(String floorId){
        this.currentWorld = this.getWorld(floorId);
    }

    /**
     * Checks if the given coordinates are valid.
     * @param pos the position to check
     * @return Returns true if the given x and y positions are within the bounds of the current world, and
     * if a tile exists at the given position. If not, return false.
     */
    public boolean isValidCoordinates(Position pos){
        if(pos.getX() < 0 || pos.getX() > getWorld(getCurrentWorldId()).getWorldWidth()-1 || pos.getY() <0 || pos.getY() > getWorld(getCurrentWorldId()).getWorldHeight() - 1){
            return false;
        }
        else{
            if(getTile(pos) == null){
                return false;
            }
            return true;
        }
    }

    public void removeFromTurnOrder(ITurnTaker turnTaker){
        this.turnHandler.remove(turnTaker);
    }

    public void nextTurn(){
        if(!dead){this.turnHandler.nextTurn();}
    }

    public void addObserver(IModelObserver observer){
        this.observers.add(observer);
    }

    public void removeObserver(IModelObserver observer){
        this.observers.remove(observer);
    }

    public void updateObservers(){
        for (IModelObserver observer : this.observers){
            observer.update();
        }
    }

    public void setController(IController inputGetter){
        this.controller = inputGetter;
    }

    public Set<Position> getSurrounding(Position pos, int steps) {
        return PathfindingHelper.getSurrounding(pos, steps, this);
    }

    public Set<Position> getEntitiesInRange(Position pos, int steps) {
        return PathfindingHelper.getEntitiesInRange(pos, steps, this);
    }

    public Set<Position> getLegalMoves(){
        return player.getTargetPositions("move");
    }

    public Set<Position> getAttackablePositions(){
        return player.getTargetPositions("attack");
    }

    //FIXME not random
    public IAttackable getAttackedAtPosition(Position targetPos){
        Set<IPositionable> targets = new HashSet<>();
        if(getAttackablePositions().contains(targetPos)){
            targets = getEntities(targetPos);
        }
        else{
            throw new IllegalStateException();
        }
        List<IPositionable> targetsList = new ArrayList<>(targets);
        return (IAttackable)targetsList.get(0);
    }

    public void giveExperienceToPlayer(int xp) {
        player.giveXP(xp);
    }

    public Map<AttributeType, Integer> getPlayerAttributes() {
        return player.getAttributesMap();
    }

    public void performPlayerAction(ActionInput<?> input) {
        player.performAction(input);
        updateObservers();
    }

    public ActionInput<?> getActionInput() {
        return controller.getActionInput();
    }

    public List<String> getAvailableActions() {
        return player.getAvailableActions();
    }

    public void enterGameLoop() {
        while (true) {
            updateObservers();
            nextTurn();
            if(dead) {
                break;
            }
        }
        updateObservers();


    }

    public boolean isPlayerDead(){
        return this.dead;
    }

    public void setPlayerDied() {
        getTile(getPlayerPos()).setId("playerDead");
        dead = true;
    }

    public List<Position> getPathFromTo(Position startPos, Position targetPos){
        if(getEntities(targetPos).isEmpty()){
            return PathfindingHelper.getShortestPath(startPos, targetPos, this);
        } else {
            return PathfindingHelper.getPathNextTo(startPos, targetPos, this);
        }
    }

    public int getPlayerHealth() {
        return player.getHitPoints();
    }

    public int getPlayerMaxHealth() {
        return player.getMaxHitPoints();
    }

    public int getPlayerAp(){
        return player.getAp();
    }

    public boolean nextToPlayer(Position pos){
        Set<Position> surrounding = getEntitiesInRange(pos, 1);
        return surrounding.contains(player.getPos());
    }

    @Override
    public int getPlayerStealthBonus() {
        return player.getDexBonus();
    }

    /**
     * Required for tests
     * @param player the player object to be set as current player
     */
    public void setPlayer(Player player) {
        this.player = player;
    }

    public void queueRestart() {
        System.out.println("Restart queued");
        restartQueued = true;
    }

    public void start() {
        while (true) {
            enterGameLoop();

            // Wait for restart to be queued
            while (!restartQueued) {
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            reset();
            restartQueued = false;
        }
    }

    public boolean restartQueued(){
        return restartQueued;
    }

    private void reset() {
        System.out.println("Resetting game");
        dead = false;
        turnHandler = new TurnHandler();
        floors.clear();
        currentWorld = null;
        addRandomMap(10);
        updateObservers();
    }
}