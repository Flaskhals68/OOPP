package com.group4.app.model;

public class HealthBar {
    private int max;
    private int current;

    public HealthBar(int max) {
        this.max = max;
        this.current = max;
    }

    public void setCurrent(int current) {
        this.current = Math.max(current, this.max); 
    }

    public void setMax(int max) { this.max = max; }

    public int getMax() { return max; }

    public int getCurrent() { return current; }

    public void reduceCurrent(int val) {
        current -= val;
        if (current < 0) current = 0;
    }

    @Override
    public int hashCode() {
        return max;
    }
}
