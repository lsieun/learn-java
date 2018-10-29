package com.lsieun.tank.logic;

import com.lsieun.tank.action.Fireable;
import com.lsieun.tank.ancillary.Group;
import com.lsieun.tank.ancillary.WeaponType;
import com.lsieun.tank.geometry.Geometry;

/**
 *
 */
public class Weapon extends LifelessElement implements Fireable {

    public static final int DEFAULT_SPEED = 10;
    public static final int DEFAULT_DAMAGE = 20;


    protected WeaponType type;
    protected int missileCount;
    protected double speedScalar;
    protected int damage;

    protected Tank tank;

    public Weapon(String name, WeaponType type, int missileCount) {
        this(name, type, missileCount, DEFAULT_SPEED, DEFAULT_DAMAGE);
    }

    public Weapon(String name, WeaponType type, int missileCount, double speedScalar, int damage) {
        super(name, null, Group.NEUTRAL);

        this.type = type;
        this.missileCount = missileCount;
        this.speedScalar = speedScalar;
        this.damage = damage;

        this.tank = null;
    }

    public WeaponType getType() {
        return type;
    }

    public void setType(WeaponType type) {
        this.type = type;
    }

    public int getMissileCount() {
        return missileCount;
    }

    public void setMissileCount(int missileCount) {
        this.missileCount = missileCount;
    }

    public double getSpeedScalar() {
        return speedScalar;
    }

    public void setSpeedScalar(double speedScalar) {
        this.speedScalar = speedScalar;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public Tank getTank() {
        return tank;
    }

    public void setTank(Tank tank) {
        this.tank = tank;
    }

    @Override
    public String toString() {
        return "Weapon{" +
                "\n    type=" + type +
                ", \n    missileCount=" + missileCount +
                ", \n    speedScalar=" + speedScalar +
                ", \n    damage=" + damage +
                "\n} \n" + super.toString();
    }

    public Missile fire() {
        Missile missile = this.newMissile();
        return missile;
    }

    public Geometry getOutline() {
        return null;
    }

    protected Missile newMissile() {
        if (this.type == WeaponType.LIMITED) this.missileCount--;
        Missile missile = new Missile("默认炮弹", this);
        return missile;
    }

}
