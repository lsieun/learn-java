package com.lsieun.tank.action;

import com.lsieun.tank.ancillary.Speed;

/**
 * Created by liusen on 10/25/18.
 */
public interface Moveable {
    Speed getSpeed();
    void move();
}
