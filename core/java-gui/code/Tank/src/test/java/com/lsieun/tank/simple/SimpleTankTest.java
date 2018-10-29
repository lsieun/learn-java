package com.lsieun.tank.simple;

import org.junit.Test;

import com.lsieun.tank.ancillary.Direction;
import com.lsieun.tank.ancillary.Group;
import com.lsieun.tank.geometry.Point;
import com.lsieun.tank.logic.Weapon;

/**
 * Created by liusen on 10/25/18.
 */
public class SimpleTankTest {

    @Test
    public void testCreate() {
        Point center = new Point(20, 30);
        SimpleTank tank = new SimpleTank(center, Group.GOOD, Direction.NORTH);



        Weapon weapon = SimpleWeapon.getInstance();

        tank.equip(weapon);

        tank.fire();
        System.out.println(tank);
    }
}
