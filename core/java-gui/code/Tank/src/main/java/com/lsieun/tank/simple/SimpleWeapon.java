package com.lsieun.tank.simple;

import com.lsieun.tank.ancillary.WeaponType;
import com.lsieun.tank.logic.Weapon;

/**
 * Created by liusen on 10/26/18.
 */
public class SimpleWeapon extends Weapon {

    public SimpleWeapon() {
        this("简易武器", WeaponType.UNLIMITED);
    }

    private SimpleWeapon(String name, WeaponType type) {
        super(name, type, 0);
    }

    public static SimpleWeapon getInstance() {
        SimpleWeapon instance = new SimpleWeapon();
        return instance;
    }

    @Override
    public String toString() {
        return "SimpleWeapon{} \n" + super.toString();
    }
}
