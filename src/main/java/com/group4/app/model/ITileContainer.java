package com.group4.app.model;

import java.util.Set;

public interface ITileContainer {
    void add(Tile tile);
    void remove(Tile tile);
    void remove(Position pos);
    Tile getTile(Position pos);
}
