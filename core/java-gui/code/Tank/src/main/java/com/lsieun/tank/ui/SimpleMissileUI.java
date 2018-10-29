package com.lsieun.tank.ui;

import java.awt.*;

import com.lsieun.tank.geometry.Circle;
import com.lsieun.tank.logic.Missile;
import com.lsieun.tank.simple.SimpleMissile;

/**
 * Created by liusen on 10/25/18.
 */
public class SimpleMissileUI {

    public static void drawMissile(Graphics g, Missile missile){
        int x = missile.getCenter().getX();
        int y = missile.getCenter().getY();
        int radius = missile.RADIUS;

        Color c = g.getColor();
        g.setColor(Color.MAGENTA);
        g.fillOval(x-radius, y-radius, 2*radius, 2*radius);
        g.setColor(c);
    }
}
