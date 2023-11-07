package model;

class Enemy implements Fightable{
    private final String name;
    private final Weapon weapon;
    private int hp;
    Enemy(String name, Weapon weapon, int hp){
        this.name = name;
        this.weapon = weapon;
        this.hp = hp;
    }
    @Override
    public int getAttackDamage() {
        return weapon.getAttack();
    }

    @Override
    public int getHitPoints() {
        return hp;
    }

    @Override
    public void takeDamage(int damage) {
        // Should probably add some logic for what happens if they die :p
        hp -= hp;
    }
}
