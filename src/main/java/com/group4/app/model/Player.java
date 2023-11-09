package com.group4.app.model;

import java.util.List;

public class Player extends Entity implements IAttackable, ICanAttack, IMovable {
  private int hp;

  public Player(String id, int hp, Tile tile) {
    super(id, tile);
    this.hp = hp;
  }

  public Player(String id, int hp) {
    this(id, hp, null);
  }

  public int getHp() { return this.hp; }

  public World getWorld(){
    return this.getTile().getWorld();
  }

  @Override
  public void move(Tile tile) {
    throw new UnsupportedOperationException("Unimplemented method 'takeHit'");
  }

  @Override
  public List<Tile> getLegalMoves() {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'getLegalMoves'");
  }

  @Override
  public void attack(IAttackable entity) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'attack'");
  }

  @Override
  public int getDamage() {
    // TODO Implement method when weapon support exists
    throw new UnsupportedOperationException("Unimplemented method 'getDamage'");
  }

  @Override
  public void takeHit(int damage) {
    // TODO Implement method when weapon support exists
    throw new UnsupportedOperationException("Unimplemented method 'takeHit'");
  }

  @Override
  public int getHitPoints() { return hp; }
}