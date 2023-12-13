package com.group4.app.model.dungeon;

import java.util.HashSet;
import java.util.Set;

/**
* Represents a room in the dungeon.
*/
public class Room {
    int x;
    int y;
    private Set<Room> adjacent;
    private Set<Corridor> corridors;

    public Room(int x, int y) {
        this.x = x;
        this.y = y;
        this.adjacent = new HashSet<>();
        this.corridors = new HashSet<>();
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public Set<Room> getAdjacent() {
        return this.adjacent;
    }

    public Set<Corridor> getCorridors() {
        return this.corridors;
    }

    void updateAdjacent(Room[][] matrix) {
        this.adjacent.clear();

        if (x - 1 >= 0 && matrix[x - 1][y] != null) {
            this.adjacent.add(matrix[x - 1][y]);
            matrix[x - 1][y].addAdjacent(this);
        }
        if (x + 1 < matrix.length && matrix[x + 1][y] != null) {
            this.adjacent.add(matrix[x + 1][y]);
            matrix[x + 1][y].addAdjacent(this);
        }
        if (y - 1 >= 0 && matrix[x][y - 1] != null) {
            this.adjacent.add(matrix[x][y - 1]);
            matrix[x][y - 1].addAdjacent(this);
        }
        if (y + 1 < matrix[x].length && matrix[x][y + 1] != null) {
            this.adjacent.add(matrix[x][y + 1]);
            matrix[x][y + 1].addAdjacent(this);
        }
    }

    private void addAdjacent(Room room) {
        if (room == null) {
            throw new IllegalArgumentException("Room cannot be null");
        }
        this.adjacent.add(room);
    }

    void addCorridor(Corridor corridor) {
        if (corridor == null) {
            throw new IllegalArgumentException("Corridor cannot be null");
        }
        this.corridors.add(corridor);
    }

    void removeCorridor(Corridor corridor) {
        if (corridor == null) {
            throw new IllegalArgumentException("Corridor cannot be null");
        }
        this.corridors.remove(corridor);
    }

    boolean isAdjacent(Room room) {
        return this.adjacent.contains(room);
    }

    boolean isConnected(Room room) {
        for (Corridor corridor : this.corridors) {
            if (corridor.contains(room)) {
                return true;
            }
        }
        return false;
    }

    public Set<Room> getConnected() {
        Set<Room> connected = new HashSet<>();
        for (Corridor corridor : this.corridors) {
            connected.add(corridor.getOther(this));
        }
        return connected;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + x;
        result = prime * result + y;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Room other = (Room) obj;
        if (x != other.x)
            return false;
        if (y != other.y)
            return false;
        return true;
    }
}