package com.group4.app.model.dungeon;

public interface IWorldContainer extends ITileContainer{
    void add(World world);
    void remove(World world);
    void remove(String worldId);
    World getWorld(String worldId);
}
