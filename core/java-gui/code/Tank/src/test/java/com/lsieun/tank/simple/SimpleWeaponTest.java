package com.lsieun.tank.simple;

import org.junit.Test;

import com.lsieun.tank.logic.Missile;

/**
 * Created by liusen on 10/26/18.
 */
public class SimpleWeaponTest {
    @Test
    public void testCreate() {
        SimpleWeapon weapon = SimpleWeapon.getInstance();
        System.out.println(weapon);

//        SimpleWeapon weapon2 = SimpleWeapon.getInstance();
//        System.out.println(weapon2);
//
//        Missile missile = weapon.fire();
//        System.out.println(missile);

        System.out.println(Math.sin(Math.PI/6));
    }
}
