package com.lsieun.tank.logic;


import java.util.LinkedList;
import java.util.List;

import com.lsieun.tank.action.Damageable;
import com.lsieun.tank.action.Equipable;
import com.lsieun.tank.action.Moveable;
import com.lsieun.tank.ancillary.Direction;
import com.lsieun.tank.ancillary.Group;
import com.lsieun.tank.ancillary.Speed;
import com.lsieun.tank.ancillary.WeaponType;
import com.lsieun.tank.geometry.Geometry;
import com.lsieun.tank.geometry.Point;
import com.lsieun.tank.geometry.Rectangle;
import com.lsieun.tank.util.GameUtil;

public class Tank extends LivingElement implements Moveable, Equipable, Damageable {

    public static final int DEFAULT_HEALTH = 20;
    public static final int DEFAULT_SPEED = 20;
    public static final int DEFAULT_WIDTH = 60;
    public static final int DEFAULT_HEIGHT = 60;

    protected Direction direction;
    protected List<Weapon> weapons;
    protected Weapon currentWeapon;
    protected int speedScalar;


    public Tank(String name, Point center, Group group, Direction direction) {
        this(name, center, group, direction, DEFAULT_SPEED);
    }

    public Tank(String name, Point center, Group group,
                Direction direction, int speedScalar) {
        super(name, center, group, DEFAULT_HEALTH);

        this.direction = direction;
        this.speedScalar = speedScalar;
        this.weapons = new LinkedList<Weapon>();
    }


    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public List<Weapon> getWeapons() {
        return weapons;
    }

    public int getSpeedScalar() {
        return speedScalar;
    }

    public void setSpeedScalar(int speedScalar) {
        this.speedScalar = speedScalar;
    }

    public Weapon getCurrentWeapon() {
        if(this.currentWeapon == null || this.currentWeapon.getMissileCount() < 1) {
            for (int i = 0; i < this.weapons.size(); i++) {
                Weapon weapon = this.weapons.get(i);
                if (weapon.getType() == WeaponType.LIMITED && weapon.getMissileCount() > 0) {
                    this.currentWeapon = weapon;
                }
            }
        }

        if(this.currentWeapon == null || this.currentWeapon.getMissileCount() < 1) {
            for (int i = 0; i < this.weapons.size(); i++) {
                Weapon weapon = this.weapons.get(i);
                if (weapon.getType() == WeaponType.UNLIMITED) {
                    this.currentWeapon = weapon;
                }
            }
        }
        return currentWeapon;
    }

    public void setCurrentWeapon(Weapon currentWeapon) {
        this.currentWeapon = currentWeapon;
    }

    public int getMaxHealth() {
        return DEFAULT_HEALTH;
    }

    @Override
    public Geometry getOutline() {
        Point center = this.getCenter();
        Rectangle rectangle = new Rectangle(center, DEFAULT_WIDTH, DEFAULT_HEIGHT);
        return rectangle;
    }

    public void equip(Weapon weapon) {
        if (weapon == null) return;

        boolean exists = false;
        for (int i=0; i< this.weapons.size(); i++) {
            Weapon w = this.weapons.get(i);
            if(w.getId().equals(weapon.getId())) {
                WeaponType type = w.getType();
                if (type == WeaponType.LIMITED) {
                    int oldCount = w.getMissileCount();
                    int newCount = weapon.getMissileCount();
                    w.setMissileCount(oldCount + newCount);
                }

                exists = true;
            }
        }

        if (!exists) {
            this.weapons.add(weapon);
            weapon.setTank(this);
        }

    }

    public Missile fire() {
        Weapon weapon = this.getCurrentWeapon();
//        System.out.println(weapon);
        Missile missile = weapon.fire();
//        System.out.println("Tank Fire Missile: \n" + missile);
        return missile;
    }

    public Speed getSpeed() {
        Speed speed = GameUtil.scalar2Speed(this.speedScalar, this.direction);
        return speed;
    }

    public void move() {
        int x = this.center.getX();
        int y = this.center.getY();

        int velocity = (int)Math.sqrt(speedScalar);
        int deltaX = velocity;
        int deltaY = velocity;
        Direction direction = this.getDirection();


        switch (direction) {
            case WEST:
                x -= deltaX;
                break;
            case NORTH_WEST:
                x -= deltaX;
                y -= deltaY;
                break;
            case NORTH:
                y -= deltaY;
                break;
            case NORTH_EAST:
                x += deltaX;
                y -= deltaY;
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
                x -= deltaX;
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
        return "Tank{" +
                "\n    direction=" + direction +
                ", \n    speedScalar=" + speedScalar +
                ", \n    currentWeapon=" + currentWeapon.getName() +
                "\n} \n" + super.toString();
    }
}
