package com.group4.app.model;

public class Entity {
    private Tile tile;
    private World world;

    public Entity() {
        this(null);
    }

    public Entity(Tile tile) {
        this.tile = tile;
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
        return new Tile(this.world, getXPos(), getYPos());
    }
}
