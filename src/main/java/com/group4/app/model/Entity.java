package com.group4.app.model;

public class Entity {
    private String id;
    private Tile tile;

    public Entity(String id) {
        this.id = id;
        this.tile = null;
    }

    public Entity(String id, Tile tile) {
        this.id = id;
        this.tile = tile;
    }

    public String getId() {
        return this.id;
    }

    public int getXPos() {
        return tile.getXPos();
    }

    public int getYPos() {
        return tile.getYPos();
    }

    /**
     * Returns a copy of the entity's tile
     * @return
     */
    public Tile getTile() {
        return this.tile;
    }

    public void setTile(Tile tile) {
        tile.removeEntity(this);
        this.tile = tile;
    }
}
