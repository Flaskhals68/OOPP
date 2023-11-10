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

    /**
     * Reduce the current hp by the given damage
     * @param damage to be taken
     */
    public void reduceCurrent(int damage) {
        current -= damage;
        if (current < 0) current = 0;
    }

    /**
     * Increase current hp up to the given max
     * @param amount
     */
    public void increaseCurrent(int amount) {
        current += amount;
        if (current > max) current = max;
    }
}
