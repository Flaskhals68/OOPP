package com.group4.app.model;

public class Coordinate {
    private int x;
    private int y;

    public Coordinate(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Coordinate(Coordinate cord) {
        this.x = cord.getX();
        this.y = cord.getY();
    }

    public int getX() { return x; }

    public int getY() { return y; }

    /**
     * @return Copy of coordinate
     */
    public Coordinate getCoordinate() { return new Coordinate(this); }

    public void setCoordinate(Coordinate cord) { 
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
        Coordinate other = (Coordinate) obj;
        if (x != other.x)
            return false;
        if (y != other.y)
            return false;
        return true;
    }
}
