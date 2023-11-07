package model;

import java.util.List;

public class Player implements Fightable, Movable {
  private int hitpoints;
  private int movement;
  private int maxActions;
  private int remainingActions;

  public Player(int hitpoints, int movement, int maxActions) {
    this.hitpoints = hitpoints;
    this.movement = movement;
    this.maxActions = maxActions;
    this.remainingActions = maxActions;
  }

  public int getHitpoints() { return this.hitpoints; }

  public int getMovement() { return this.movement; }

  @Override
  public void move(int x, int y) {}

  @Override
  public int getAttackDamage() {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'getAttackDamage'");
  }

  @Override
  public int getHitPoints() {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'getHitPoints'");
  }

  @Override
  public List<List<Integer>> getLegalPositions() {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'getLegalPositions'");
  }


}