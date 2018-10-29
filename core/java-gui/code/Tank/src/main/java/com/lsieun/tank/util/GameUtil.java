package com.lsieun.tank.util;

import com.lsieun.tank.ancillary.Direction;
import com.lsieun.tank.ancillary.GameConstants;
import com.lsieun.tank.ancillary.Group;
import com.lsieun.tank.ancillary.Speed;
import com.lsieun.tank.geometry.Point;
import com.lsieun.tank.logic.Missile;
import com.lsieun.tank.logic.Tank;

/**
 * Created by liusen on 10/26/18.
 */
public class GameUtil {
    public static Speed scalar2Speed(double speedScalar, Direction direction) {

        int velocity = (int)(speedScalar * 1.0 * Math.sin(Math.PI/4));
        double x = 0;
        double y = 0;

        switch (direction) {
            case WEST:
                x -= speedScalar;
                break;
            case NORTH_WEST:
                x -= velocity;
                y -= velocity;
                break;
            case NORTH:
                y -= speedScalar;
                break;
            case NORTH_EAST:
                x += velocity;
                y -= velocity;
                break;
            case EAST:
                x += speedScalar;
                break;
            case SOUTH_EAST:
                x += velocity;
                y += velocity;
                break;
            case SOUTH:
                y += speedScalar;
                break;
            case SOUTH_WEST:
                x -= velocity;
                y += velocity;
                break;
            case NONE:
                break;
        }

        return new Speed(x, y, direction);
    }

    public static void hit(Missile missile, Tank tank) {
        // Group
        Group groupM = missile.getGroup();
        Group groupT = tank.getGroup();
        if(groupM == groupT) return;

        // Distance
        Point centerM = missile.getCenter();
        Point centerT = tank.getCenter();
        int distance = getDistance(centerM, centerT);
        if(distance >= (tank.DEFAULT_WIDTH + 2*Missile.RADIUS)/2) return;

        int amount = missile.getDamage();
        tank.damage(amount);
        missile.damage(amount);
    }

    public static void hit(Missile m1, Missile m2) {
        // Group
        Group group1 = m1.getGroup();
        Group group2 = m2.getGroup();
        if(group1 == group2) return;


        // Distance
        Point center1 = m1.getCenter();
        Point center2 = m2.getCenter();
        int distance = getDistance(center1, center2);
        if(distance >= 2*Missile.RADIUS) return;

//        System.out.println("m1: " + m1 + "\n m2:" + m2);
        int amount = m1.getDamage() + m2.getDamage();
        m1.damage(amount);
        m2.damage(amount);
    }

    public static void hitWall(Missile m) {
        Point center = m.getCenter();
        int x = center.getX();
        int y = center.getY();

        if((x-Missile.RADIUS < GameConstants.GAME_WORLD_LEFT)
                || (x+Missile.RADIUS > GameConstants.GAME_WORLD_RIGHT)
                || (y-Missile.RADIUS < GameConstants.GAME_WORLD_TOP)
                || (y+Missile.RADIUS > GameConstants.GAME_WORLD_BOTTOM)) {
            m.setHealth(0);
        }
    }

    public static int getDistance(Point p1, Point p2) {
        int x1 = p1.getX();
        int y1 = p1.getY();

        int x2 = p2.getX();
        int y2 = p2.getY();

        int distance = (int)Math.sqrt((x1-x2)*(x1-x2) + (y1-y2)*(y1-y2));
        return distance;
    }
}
