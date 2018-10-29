package com.lsieun.tank.simple;

import com.lsieun.tank.ancillary.Direction;
import com.lsieun.tank.ancillary.Group;
import com.lsieun.tank.geometry.Point;
import com.lsieun.tank.logic.Tank;

/**
 * Created by liusen on 10/25/18.
 */
public class SimpleTank extends Tank {

    public SimpleTank(Point center, Group group, Direction direction) {
        this("简易坦克", center, group, direction);
    }

    public SimpleTank(String name, Point center, Group group, Direction direction) {
        super(name, center, group, direction);
    }

    @Override
    public String toString() {
        return "SimpleTank{} \n" + super.toString();
    }
}
