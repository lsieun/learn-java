package com.lsieun.tank.action;

import com.lsieun.tank.geometry.Geometry;
import com.lsieun.tank.geometry.Point;

/**
 * 可以看见的。
 * 如果两个Visible的东西，碰撞到一起，要阻止彼此的运动。
 */
public interface Visible {
    Point getCenter();
    Geometry getOutline();
}
