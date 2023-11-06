package model;

public class World {
    private Tile[][] tileMatrix;

    public World(Tile origin, int size){
        this.tileMatrix = new Tile[size][size];
        this.tileMatrix[size / 2 + 1][size / 2 + 1] = origin;
    }

    public Tile[][] getTileMatrix(){
        return this.tileMatrix;
    }
}
