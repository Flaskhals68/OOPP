package model;

import java.util.ArrayList;

public class Tile {
    private World world;
    private ArrayList<Tile> neighbors;
    private int xPos;
    private int yPos;
    private ArrayList<Entity> occupants;

    public Tile(World world, int Xpos, int Ypos){
        this.world = world;
        this.xPos = Xpos;
        this.yPos = Ypos;
        this.occupants = new ArrayList<Entity>();
        this.neighbors = this.calculateNeighbors();
    }

    public void setXPos(int next){
        this.xPos = next;
    }

    public int getXPos(){
        return xPos;
    }

    public void setYPos(int next){
        this.yPos = next;
    }

    public int getYPos(){
        return yPos;
    }

    public void addOccupant(Entity occupant){
        this.occupants.add(occupant);
    }

    public void removeOccupant(Entity occupant){
        this.occupants.remove(occupant);
    }

    public ArrayList<Tile> getNeighbors(){
        return neighbors;
    }

    public ArrayList<Tile> calculateNeighbors(){
        ArrayList<Tile> neighbors = new ArrayList<Tile>();
        for (int x=-1; x==1; x++){
            for (int y=-1; y==1; y++){
                Tile tile = this.world.getTileMatrix()[this.xPos+x][this.yPos+y];
                if (tile != null){
                    neighbors.add(tile);
                }
            }
        }
        return neighbors;
    }
}
