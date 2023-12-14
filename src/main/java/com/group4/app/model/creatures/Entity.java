package com.group4.app.model.creatures;

import com.group4.app.model.dungeon.IDrawable;
import com.group4.app.model.dungeon.Position;

public class Entity implements IDrawable {
    private String id;
    private Position pos;
    private final IEntityManager manager;

    public Entity(String id, IEntityManager manager) {
        this.id = id;
        this.manager = manager;
    }

    public Entity(String id, Position pos, IEntityManager manager) {
        this.id = id;
        this.pos = pos;
        this.manager = manager;
        manager.add(this);
    }

    public void setPosition(Position pos) {
        if(getFloor() != null) {
            manager.remove(this);
        }
        manager.add(this);
        this.pos = pos;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFloor() {
        return pos.getFloor();
    }

    public void setFloor(String floorId) {
        this.pos = new Position(this.pos.getX(), this.pos.getY(), floorId);
    }

    public Position getPos() {
        return this.pos;
    }

    public void setPos(Position pos) {
        this.pos = pos;
    }
}
