package com.lsieun.tank.logic;

import com.lsieun.tank.action.Damageable;
import com.lsieun.tank.ancillary.Group;
import com.lsieun.tank.geometry.Geometry;
import com.lsieun.tank.geometry.Point;

/**
 * Created by liusen on 10/26/18.
 */
public abstract class LivingElement extends GameElement implements Damageable {
    protected int health;

    public LivingElement(String name, Point center, Group group, int health) {
        super(name, center, group);

        this.health = health;
    }


    public int getHealth() {
        if (this.health < 0) return 0;
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public void damage(int amount) {
        this.health = this.health - amount;
    }

    public abstract int getMaxHealth();
    public abstract Geometry getOutline();
}
