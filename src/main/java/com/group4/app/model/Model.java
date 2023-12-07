package com.group4.app.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.group4.app.model.actions.ActionInput;
import com.group4.app.model.creatures.AttributeType;
import com.group4.app.model.creatures.Enemy;
import com.group4.app.model.creatures.EnemyFactory;
import com.group4.app.model.creatures.Entity;
import com.group4.app.model.creatures.IPositionable;
import com.group4.app.model.creatures.Player;
import com.group4.app.model.dungeon.IWorldContainer;
import com.group4.app.model.dungeon.Tile;
import com.group4.app.model.dungeon.World;
import com.group4.app.model.items.WeaponFactory;

public class Model implements IWorldContainer {
    private static Model instance = null;
    private List<IModelObserver> observers;
    private IController controller;
    private Player player;
    private TurnHandler turnHandler;
    private Boolean isPlayerTurn;
    private Map<String, World> floors;
    private World currentWorld;

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
                    world.add(new Tile("stone", new Position(x, y, world.getId())));
                    r = Math.random();
                    if(r > 0.995){
                        r = Math.random();
                        Enemy e;
                        if(r > 0.5){
                            e = EnemyFactory.createZombie(new Position(x, y, world.getId()));

                        } else {
                            e = EnemyFactory.createSkeleton(new Position(x, y, world.getId()));
                        }
                        add(e);
                        addToTurnOrder(e);
                    }
                }
            }
        }
        this.player = new Player(PLAYER_ID, 3, WeaponFactory.createSword(), new Position(0, 0, world.getId()));
        add(player);
        addToTurnOrder(player);
    }

    public void addBasicMap(int size) {
        addBasicMap(size, 0.1);
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
    }

    public void remove(IPositionable positionable){
        this.getWorld(positionable.getFloor()).remove(positionable);
    }

    public void startPlayerTurn(){
        System.out.println("Player turn started");
        this.isPlayerTurn = true;
    }

    public void endPlayerTurn(){
        System.out.println("Player turn ended");
        this.isPlayerTurn = false;
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
        this.turnHandler.nextTurn();
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

    public void giveExperience(int xp) {
        player.giveXP(xp);
    }

    public Map<AttributeType, Integer> getPlayerAttributes() {
        return player.getAttributesMap();
    }

    public void performPlayerAction(ActionInput<?> input) {
        player.performAction(input);
    }

    public ActionInput<?> getActionInput() {
        return controller.getActionInput();
    }

    public List<String> getAvailableActions() {
        return player.getAvailableActions();
    }

    public void enterGameLoop() {
        while (true) {
            nextTurn();
            updateObservers();
            if(dead) {
                break;
            }
        }
    }

    public void setPlayerDied() {
        dead = true;
    }

    public List<Position> getPathFromTo(Position startPos, Position targetPos){
        return PathfindingHelper.getShortestPath(startPos, targetPos, this);
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
        return getSurrounding(pos, 1).contains(player.getPos());
    }
}