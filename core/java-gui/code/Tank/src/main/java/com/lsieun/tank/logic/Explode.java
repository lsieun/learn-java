package com.lsieun.tank.logic;

import com.lsieun.tank.action.Damageable;
import com.lsieun.tank.ancillary.Group;
import com.lsieun.tank.geometry.Geometry;
import com.lsieun.tank.geometry.Point;

/**
 * Created by liusen on 10/25/18.
 */
public class Explode extends LivingElement implements Damageable{
    public static final int DEFAULT_HEALTH = 10;

    private static final int[] diameters = {4, 7, 12, 18, 26, 32, 49, 30, 14, 6};

    private Point center;

    public Explode(Point center) {
        super("", center, Group.NEUTRAL, DEFAULT_HEALTH);

        this.center = center;
    }

    @Override
    public int getMaxHealth() {
        return DEFAULT_HEALTH;
    }

    @Override
    public Geometry getOutline() {
        return null;
    }

    public int getRadius() {
        if (health <=0) return 0;

        int radius = diameters[DEFAULT_HEALTH - health];
        this.health--;
        return radius;
    }
}
