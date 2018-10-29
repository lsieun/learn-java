package com.lsieun.tank.simple;

import com.lsieun.tank.logic.Missile;
import com.lsieun.tank.logic.Weapon;

/**
 * 简单子弹
 */
public class SimpleMissile extends Missile {
    public static final int DEFAULT_DAMAGE = 10;

    public SimpleMissile(Weapon weapon) {
        this("简易炮弹", weapon);
    }

    public SimpleMissile(String name, Weapon weapon) {
        super(name, weapon);
    }

    @Override
    public String toString() {
        return "SimpleMissile{} \n" + super.toString();
    }

}
