package com.group4.app.model.creatures;

public class ResourceBar {
    private int max;
    private int current;

    public ResourceBar(int max) {
        this.max = max;
        this.current = max;
    }

    public void setCurrent(int current) {
        this.current = Math.min(current, this.max);
    }

    public void setMax(int max) { this.max = max; }

    public int getMax() { return max; }

    public int getCurrent() { return current; }

    /**
     * Reduce the current amount by the given amount
     * @param reduction amount to be reduced
     */
    public void reduceCurrent(int reduction) {
        current -= reduction;
        if (current < 0) current = 0;
    }

    /**
     * Increase current hp up to the given max
     * @param increase amount to be increased
     */
    public void increaseCurrent(int increase) {
        current += increase;
        if (current > max) current = max;
    }
}
