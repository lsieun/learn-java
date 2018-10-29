package com.lsieun.tank.logic;

import com.lsieun.tank.ancillary.Group;
import com.lsieun.tank.geometry.Geometry;
import com.lsieun.tank.geometry.Point;

/**
 * Created by liusen on 10/25/18.
 */
public abstract class LifelessElement extends GameElement {

    public LifelessElement(String name, Point center, Group group) {
        super(name, center, group);
    }

    public abstract Geometry getOutline();
}
