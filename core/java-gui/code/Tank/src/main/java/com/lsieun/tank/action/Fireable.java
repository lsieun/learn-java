package com.lsieun.tank.action;

import com.lsieun.tank.logic.Missile;

/**
 * 可发射的
 */
public interface Fireable{
    Missile fire();
    int getDamage();
    int getMissileCount();
}
