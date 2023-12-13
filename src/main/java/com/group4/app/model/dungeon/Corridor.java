package com.group4.app.model.dungeon;

import com.group4.app.model.dungeon.DungeonGraphGenerator.Direction;

/**
* Represents a corridor connecting two rooms in a dungeon.
*/
public class Corridor {
    private Room room1;
    private Room room2;
    private Direction direction;

    public Corridor(Room room1, Room room2) {
        if (room1 == null || room2 == null) {
            throw new IllegalArgumentException("Rooms cannot be null");
        }
        this.room1 = room1;
        this.room2 = room2;
        calculateDirection();
    }

    private void calculateDirection() {
        if (room1.getX() == room2.getX()) {
            if (room1.getY() < room2.getY()) {
                this.direction = Direction.SOUTH;
            } else {
                this.direction = Direction.NORTH;
            }
        } else if (room1.getY() == room2.getY()) {
            if (room1.getX() < room2.getX()) {
                this.direction = Direction.EAST;
            } else {
                this.direction = Direction.WEST;
            }
        } else {
            throw new IllegalArgumentException("Rooms must be adjacent");
        }
    }

    public Direction getDirection() {
        return this.direction;
    }

    public Room getRoom1() {
        return this.room1;
    }

    public Room getRoom2() {
        return this.room2;
    }

    public boolean contains(Room room) {
        return room1 == room || room2 == room;
    }

    public Room getOther(Room room) {
        if (room1 == room) {
            return room2;
        } else if (room2 == room) {
            return room1;
        } else {
            throw new IllegalArgumentException("Room is not connected to this corridor");
        }
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((room1 == null) ? 0 : room1.hashCode());
        result = prime * result + ((room2 == null) ? 0 : room2.hashCode());
        int result2 = 1;
        result2 = prime * result2 + ((room2 == null) ? 0 : room2.hashCode());
        result2 = prime * result2 + ((room1 == null) ? 0 : room1.hashCode());
        
        return result + result2;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Corridor other = (Corridor) obj;
        if (room1 == null) {
            if (other.room1 != null)
                return false;
        } else if (!(room1.equals(other.room1) || room1.equals(other.room2)))
            return false;
        if (room2 == null) {
            if (other.room2 != null)
                return false;
        } else if (!(room2.equals(other.room2) || room2.equals(other.room1)))
            return false;
        return true;
    }
}