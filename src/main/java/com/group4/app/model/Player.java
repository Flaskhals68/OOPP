package com.group4.app.model;

import java.awt.Point;
import java.awt.geom.Point2D;
import java.util.List;
import java.util.Set;

public class Player extends Entity implements IAttackable, ICanAttack, IMovable {
  private HealthBar hp;
  private Weapon weapon;

  public Player(String id, int hp, Weapon weapon, String floorId, int xPos, int yPos) {
    super(id, floorId, xPos, yPos);
    this.hp = new HealthBar(hp);
    this.weapon = weapon;
  }

  @Override
  public void move(int xPos, int yPos) {
    Tile target = Model.getInstance().getTile(getFloor(), xPos, yPos);
    Set<Point2D> legalMoves = getLegalMoves();
    if (!legalMoves.contains(new Point(target.getXPos(), target.getYPos()))) {
      throw new IllegalArgumentException("Illegal move");
    }

    Model.getInstance().removeEntity(this);
    this.setPosition(getFloor(), xPos, yPos);
    Model.getInstance().addEntity(this, getFloor(), xPos, yPos);
  }

  @Override
    public Set<Point2D> getLegalMoves() {
      // TODO: Change to use players actionpoints instead of static value
      return PathfindingHelper.getSurrounding(Model.getInstance().getTile(getFloor(), getYPos(), getYPos()), 5);
    }

  public void setWeapon(Weapon weapon) {
    this.weapon = weapon;
  }

  @Override
  public void attack(IAttackable other) {
    other.takeHit(this.getDamage());
  }

  @Override
  public int getDamage() {
    return weapon.getAttack();
  }

  @Override
  public void takeHit(int damage) {
    hp.reduceCurrent(damage);
  }

  @Override
  public int getHitPoints() { return hp.getCurrent(); }
}