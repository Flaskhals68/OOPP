package com.group4.app.model.dungeon;

public class Position {
    private int x;
    private int y;
    private String floor;

    public Position(int x, int y, String floor) {
        this.x = x;
        this.y = y;
        this.floor = floor;
    }

    public Position(Position pos) {
        this.x = pos.getX();
        this.y = pos.getY();
    }

    public int getX() { return x; }

    public int getY() { return y; }

    public String getFloor() { return floor; }

    /**
     * @return Copy of coordinate
     */
    public Position getPos() { return new Position(this); }

    public void setPos(Position cord) { 
        this.x = cord.getX();
        this.y = cord.getY(); 
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
        Position other = (Position) obj;
        if (x != other.x)
            return false;
        if (y != other.y)
            return false;
        return true;
    }
}
