package com.group4.app.model.items;

public abstract class Potion implements IInventoriable{
    private final String name;

    public Potion(String name){
        this.name = name;
    }
    public String getName() {
        return name;
    }

}
