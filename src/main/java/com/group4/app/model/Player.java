package com.group4.app.model;

import java.util.List;

public class Player extends Entity implements IAttackable, ICanAttack, IMovable {
  private HealthBar hp;
  private Weapon weapon;

  public Player(String id, int hp, Weapon weapon, Tile tile) {
    super(id, tile);
    this.hp = new HealthBar(hp);
    this.weapon = weapon;
  }

  public Player(String id, int hp) {
    this(id, hp, null, null);
  }

  public Player(String id, int hp, Weapon weapon) {
    this(id, hp, weapon, null);
  }

  public Player(String id, int hp, Tile tile) {
    this(id, hp, null, tile);
  }

  /**
   * Return copy of HealthBar object 
   */
  @Override
  public void move(Tile tile) {
    // TODO: Add restraints to where player can move
    Tile currentTile = getTile();
    currentTile.removeOccupant(this);
    tile.addOccupant(this);
    setTile(tile);
  }

  @Override
  public List<Tile> getLegalMoves() {
    // TODO: Implement logic for getting legal moves
    throw new UnsupportedOperationException("Method 'getLegalMoves()' not implemented");
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