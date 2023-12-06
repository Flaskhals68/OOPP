package com.group4.app.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Model {
    private static Model instance = null;
    private List<IModelObserver> observers;
    private IController controller;
    private Player player;
    private TurnHandler turnHandler;
    private Boolean isPlayerTurn;
    private Map<String, World> floors;
    private World currentWorld;

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
    }

    public void addBasicMap(int size, double emptyChance){
        World world = new World(100);
        currentWorld = world;
        this.addWorld(currentWorld);
        world.add(new Tile("stone", new Position(0, 0, world.getId())));
        for (int x = 0; x<size; x++) {
            for (int y = 0; y<size; y++) {
                double r = Math.random();
                if(r> emptyChance){
                    world.add(new Tile("stone", new Position(x, y, world.getId())));
                    r = Math.random();
                    if(r > 0.98){
                        Enemy e = EnemyFactory.createZombie(new Position(x, y, world.getId()));
                        add(e, new Position(x, y, world.getId()));
                    }
                }
            }
        }
        this.player = new Player(PLAYER_ID, 3, WeaponFactory.createSword(), new Position(0, 0, world.getId()));
        add(player, player.getPos());
    }

    public void addBasicMap(int size) {
        addBasicMap(size, 0.1);
    }

    public void addWorld(World world){
        this.floors.put(world.getId(), world);
        if (this.currentWorld == null){
            this.currentWorld = world;
        }
    }

    private World getWorld(String floorId){
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

    public void add(Entity entity, Position pos){
        this.getWorld(pos.getFloor()).add(entity, pos);
    }

    public void add(IPositionable positionable, Position pos){
        this.getWorld(pos.getFloor()).add(positionable, pos);
    }

    public void remove(Entity entity){
        this.getWorld(entity.getFloor()).remove(entity);
    }

    public void remove(IPositionable positionable){
        this.getWorld(positionable.getFloor()).remove(positionable);
    }

    public void startPlayerTurn(){
        this.isPlayerTurn = true;
    }

    public void endPlayerTurn(){
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
        return PathfindingHelper.getSurrounding(getTile(pos), steps);
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

    public int getPlayerHealth() {
        return player.getHitPoints();
    }

    public int getPlayerMaxHealth() {
        return player.getMaxHitPoints();
    }

    public int getPlayerAp(){
        return player.getAp();
    }

    public int getMaxPlayerAp(){
        return player.getAp();
    }
}