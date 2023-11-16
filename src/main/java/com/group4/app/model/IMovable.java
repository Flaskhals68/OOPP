package com.group4.app.model;

import java.awt.geom.Point2D;
import java.util.List;
import java.util.Set;

public interface IMovable {
    /**
     * Move entity to tile
     * @param tile
     */
    void move(int xPos, int yPos);

    /**
     * Return list of all legal tiles entity can move to
     */
    Set<Point2D> getLegalMoves();
}
