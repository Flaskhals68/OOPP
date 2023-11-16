package com.group4.app.model;

import java.util.List;

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
    // TODO: Add restraints to where player can move
    Model.getInstance().removeEntity(this);
    this.setPosition(getFloor(), xPos, yPos);
    Model.getInstance().addEntity(this, getFloor(), xPos, yPos);
  }

  @Override
  public List<Tile> getLegalMoves() {
    // TODO: Implement logic for getting legal moves
    
    throw new UnsupportedOperationException("Method 'getLegalMoves()' not implemented");
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