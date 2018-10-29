package com.lsieun.tank.logic;

import com.lsieun.tank.action.Attackable;
import com.lsieun.tank.action.Moveable;
import com.lsieun.tank.ancillary.Direction;
import com.lsieun.tank.ancillary.Speed;
import com.lsieun.tank.geometry.Circle;
import com.lsieun.tank.geometry.Geometry;
import com.lsieun.tank.geometry.Point;
import com.lsieun.tank.util.GameUtil;

public class Missile extends LivingElement implements Moveable, Attackable {

    public static final int DEFAULT_HEALTH = 1;
    public static final int DEFAULT_MAX_HEALTH = 1;
    public static final int RADIUS = 10;

    protected Weapon weapon;
    protected String tankId;
    protected Speed speed;
    protected int damage;

    public Missile(String name, Weapon weapon) {
        super(name, new Point(weapon.getTank().getCenter()), weapon.getTank().getGroup(), DEFAULT_HEALTH);

        this.weapon = weapon;
        this.tankId = weapon.getTank().getId();
        this.damage = weapon.getDamage();

        double speedScalar = weapon.getSpeedScalar();
        Direction direction = weapon.getTank().getDirection();
        this.speed = GameUtil.scalar2Speed(speedScalar, direction);
    }



    public String getTankId() {
        return tankId;
    }

    public void setTankId(String tankId) {
        this.tankId = tankId;
    }

    public Speed getSpeed() {
        return this.speed;
    }

    public void setSpeed(Speed speed) {
        this.speed = speed;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public int getMaxHealth() {
        return DEFAULT_MAX_HEALTH;
    }

    @Override
    public Geometry getOutline() {
        Point center = this.center;
        Circle circle = new Circle(center, RADIUS);
        return circle;
    }

    public void move() {
        int x = this.center.getX();
        int y = this.center.getY();

        int deltaX = (int)this.speed.getX();
        int deltaY = (int)this.speed.getY();
        Direction direction = this.speed.getDirection();

        switch (direction) {
            case WEST:
                x += deltaX;
                break;
            case NORTH_WEST:
                x += deltaX;
                y += deltaY;
                break;
            case NORTH:
                y += deltaY;
                break;
            case NORTH_EAST:
                x += deltaX;
                y += deltaY;
                break;
            case EAST:
                x += deltaX;
                break;
            case SOUTH_EAST:
                x += deltaX;
                y += deltaY;
                break;
            case SOUTH:
                y += deltaY;
                break;
            case SOUTH_WEST:
                x += deltaX;
                y += deltaY;
                break;
            case NONE:
                break;
        }

        this.center.setX(x);
        this.center.setY(y);
    }

    @Override
    public String toString() {
        return "Missile{" +
                ", \n    tankId='" + tankId + '\'' +
                ", \n    speed=" + speed +
                ", \n    damage=" + damage +
                "\n} \n" + super.toString();
    }

}
