package com.group4.app.model.dungeon;

public interface ITileContainer {
    void add(Tile tile);
    void remove(Tile tile);
    void remove(Position pos);
    Tile getTile(Position pos);
}
