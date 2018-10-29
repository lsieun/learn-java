package com.lsieun.tank.action;

import com.lsieun.tank.logic.Missile;
import com.lsieun.tank.logic.Weapon;

/**
 * Created by liusen on 10/25/18.
 */
public interface Equipable {
    void equip(Weapon weapon);
    Missile fire();
}
