package com.group4.app.model;

import java.util.List;

public interface IMovable {
    /**
     * Move entity to tile
     * @param tile
     */
    void move(int xPos, int yPos);

    /**
     * Return list of all legal tiles entity can move to
     */
    List<Tile> getLegalMoves();
}
