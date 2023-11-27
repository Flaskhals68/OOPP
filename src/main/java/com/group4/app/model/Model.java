package com.group4.app.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Model {
    private static Model instance = null;
    private List<IModelObserver> observers;
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
        world.addTile(new Tile("stone", world.getId(), new Position(0, 0, world.getId())));
        for (int x = 0; x<size; x++) {
            for (int y = 0; y<size; y++) {
                double r = Math.random();
                if(r> emptyChance){
                    world.addTile(new Tile("stone", world.getId(), new Position(x, y, world.getId())));
                    r = Math.random();
                    if(r > 0.98){
                        Enemy e = EnemyFactory.createZombie();
                        addEntity(e, world.getId(), new Position(x, y, world.getId()));
                    }
                }
            }
        }
        this.player = new Player(PLAYER_ID, 3, null, world.getId(), new Position(0, 0, world.getId()));
        addEntity(player, world.getId(), player.getPos());
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

    public int getPlayerX(){
        return this.player.getXPos();
    }

    public int getPlayerY(){
        return this.player.getYPos();
    }

    public Tile getTile(String floorId, Position pos){
        return this.getWorld(floorId).getTile(pos);
    }

    public Set<Entity> getEntities(String floorId, Position pos){
        return getTile(floorId, pos).getEntities();
    }

    public List<IDrawable> getDrawables(String floorId, Position pos){
        IDrawable[] entities = getEntities(floorId, pos).toArray(new IDrawable[0]);
        IDrawable tile = getTile(floorId, pos);
        ArrayList<IDrawable> drawables = new ArrayList<IDrawable>();
        drawables.add(tile);
        for (IDrawable entity : entities){
            drawables.add(entity);
        }
        return drawables;
    }

    public void addEntity(Entity entity, String floorId, Position pos){
        this.getWorld(floorId).addEntity(entity, pos);
    }

    public void removeEntity(Entity entity){
        this.getWorld(entity.getFloor()).removeEntity(entity);
    }

    public void startPlayerTurn(){
        this.isPlayerTurn = true;
    }

    public void endPlayerTurn(){
        this.isPlayerTurn = false;
        this.endTurn();
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

    /**
     * Checks if the given coordinates are valid.
     * @param x
     * @param y
     * @return Returns true if the given x and y positions are within the bounds of the current world, and
     * if a tile exists at the given position. If not, return false.
     */
    public boolean isValidCoordinates(Position pos){
        if(pos.getX() < 0 || pos.getX() > getWorld(getCurrentWorldId()).getWorldWidth()-1 || pos.getY() <0 || pos.getY() > getWorld(getCurrentWorldId()).getWorldHeight() - 1){
            return false;
        }
        else{
            if(getTile(getCurrentWorldId(), pos) == null){
                return false;
            }
            return true;
        }
    }

    public void removeFromTurnOrder(ITurnTaker turnTaker){
        this.turnHandler.remove(turnTaker);
    }

    public void startTurn(){
        this.turnHandler.startTurn();
    }

    public void endTurn(){
        this.turnHandler.endTurn();
    }

    public void movePlayer(Position pos){
        this.player.move(pos);
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

    /**
     * Only implemented for melee weapons currently,
     * but should be relatively simple to adapt for ranged as well in the future
     * @param attacker the entity doing the attacking
     * @param victim the entity getting hit
     */
    public void performAttackAction(ICanAttack attacker, IAttackable victim) {
        int xDiff = Math.abs(attacker.getXPos() - victim.getXPos());
        int yDiff = Math.abs(attacker.getYPos() - victim.getYPos());

        if(!attacker.getFloor().equals(victim.getFloor())) {
            throw new IllegalArgumentException("Attacker and victim are on different floors/worlds");
        } else if(xDiff <= 1 && yDiff <= 1) {
            attacker.attack(victim);
        } else {
            throw new IllegalArgumentException("Attacker is out of range");
        }
    }

    public Set<Position> getSurrounding(Position pos, int steps) {
        return PathfindingHelper.getSurrounding(getTile(currentWorld.getId(), pos), steps);
    }

    public void giveExperience(int xp) {
        player.giveXP(xp);
    }
}