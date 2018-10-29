package com.lsieun.tank.logic;

import com.lsieun.tank.ancillary.Group;
import com.lsieun.tank.geometry.Geometry;
import com.lsieun.tank.geometry.Point;
import com.lsieun.tank.geometry.Rectangle;

/**
 * Created by liusen on 10/26/18.
 */
public class WeaponPackage extends LifelessElement {

    private static final int WIDTH = 10;
    private static final int HEIGHT = 10;

    private Weapon weapon;

    public WeaponPackage(String name, Point center, Group group, Weapon weapon) {
        super(name, center, group);

        this.weapon = weapon;
    }

    public Geometry getOutline() {
        Point center = this.getCenter();
        Rectangle rectangle = new Rectangle(center, WIDTH, HEIGHT);
        return rectangle;
    }

    public Weapon getWeapon() {
        return weapon;
    }

    public void setWeapon(Weapon weapon) {
        this.weapon = weapon;
    }

    @Override
    public String toString() {
        return "WeaponPackage{" +
                "weapon=" + weapon +
                "} " + super.toString();
    }
}
