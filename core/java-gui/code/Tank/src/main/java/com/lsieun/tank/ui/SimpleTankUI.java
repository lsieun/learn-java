package com.lsieun.tank.ui;

import java.awt.Color;
import java.awt.Graphics;

import com.lsieun.tank.ancillary.Direction;
import com.lsieun.tank.ancillary.GameConstants;
import com.lsieun.tank.ancillary.Group;
import com.lsieun.tank.geometry.Point;
import com.lsieun.tank.logic.Tank;

/**
 * Created by liusen on 10/26/18.
 */
public class SimpleTankUI {

    public static void drawTank(Graphics g, Tank tank) {
        Point center = tank.getCenter();
        Direction direction = tank.getDirection();

        Color c = g.getColor();

        if(tank.getGroup() == Group.GOOD) {
            g.setColor(Color.GREEN);
        }
        else {
            g.setColor(Color.GRAY);
        }

        double arc = 0.0;
        switch (direction) {
            case NORTH:
                drawNorth(g, center);
                break;
            case SOUTH:
                drawSouth(g, center);
                break;
            case WEST:
                drawWest(g, center);
                break;
            case EAST:
                drawEast(g, center);
                break;
            case NORTH_WEST:
                arc = Math.PI * 1 / 4;
                draw(g, center, 0 - arc);
                break;
            case SOUTH_WEST:
                arc = Math.PI * 3 / 4;
                draw(g, center, 0 - arc);
                break;
            case SOUTH_EAST:
                arc = Math.PI * 5 / 4;
                draw(g, center, 0 - arc);
                break;
            case NORTH_EAST:
                arc = Math.PI * 7 / 4;
                draw(g, center, 0 - arc);
                break;
            default:
                drawNorth(g, center);
                break;
        }

        boolean flag = true;
        if (flag && tank.getGroup() == Group.GOOD) {
            g.setColor(Color.GRAY);
            g.drawLine(0, center.getY(), GameConstants.GAME_WINDOW_WIDTH, center.getY());
            g.drawLine(center.getX(), 0, center.getX(), GameConstants.GAME_WINDOW_HEIGHT);
        }

        g.setColor(c);
    }

    private static void draw(Graphics g, Point center, double arc) {
        int w = Tank.DEFAULT_WIDTH / 6;
        int h = Tank.DEFAULT_HEIGHT / 6;

        int x1 = 0 - 3 * w;
        int x2 = 0 - w;
        int x3 = 0 + w;
        int x4 = 0 + 3 * w;

        int y1 = 0 - 3 * h;
        int y2 = 0 - h;
        int y3 = 0 + h;
        int y4 = 0 + 3 * h;

        int[] polyX = new int[] {x2, x3, x3, x4, x4, x4, x3, x3, x2, x2, x1, x1, x1, x2};
        int[] polyY = new int[] {y1, y1, y2, y2, y3, y4, y4, y3, y3, y4, y4, y3, y2, y2};
        rotate(polyX, polyY, arc, center);

        Color c = g.getColor();
        Color brighter = c.brighter();
        Color darker = c.darker();

        g.setColor(brighter);
        g.fillPolygon(polyX, polyY, polyX.length);

        g.setColor(darker);
        g.drawLine(polyX[0], polyY[0], polyX[1], polyY[1]);
        g.drawLine(polyX[0], polyY[0], polyX[13], polyY[13]);
        g.drawLine(polyX[1], polyY[1], polyX[2], polyY[2]);
        g.drawLine(polyX[12], polyY[12], polyX[13], polyY[13]);
        g.drawLine(polyX[13], polyY[13], polyX[2], polyY[2]);
        g.drawLine(polyX[2], polyY[2], polyX[3], polyY[3]);
        g.drawLine(polyX[12], polyY[12], polyX[11], polyY[11]);
        g.drawLine(polyX[13], polyY[13], polyX[8], polyY[8]);
        g.drawLine(polyX[2], polyY[2], polyX[7], polyY[7]);
        g.drawLine(polyX[3], polyY[3], polyX[4], polyY[4]);
        g.drawLine(polyX[11], polyY[11], polyX[8], polyY[8]);
        g.drawLine(polyX[8], polyY[8], polyX[7], polyY[7]);
        g.drawLine(polyX[7], polyY[7], polyX[4], polyY[4]);
        g.drawLine(polyX[11], polyY[11], polyX[10], polyY[10]);
        g.drawLine(polyX[8], polyY[8], polyX[9], polyY[9]);
        g.drawLine(polyX[7], polyY[7], polyX[6], polyY[6]);
        g.drawLine(polyX[4], polyY[4], polyX[5], polyY[5]);
        g.drawLine(polyX[10], polyY[10], polyX[9], polyY[9]);
        g.drawLine(polyX[6], polyY[6], polyX[5], polyY[5]);
        g.setColor(c);

    }

    private static void rotate(int[] xpoints, int[] ypoints, double arc, Point center) {
        int x_center = center.getX();
        int y_center = center.getY();

        for(int i=0; i<xpoints.length; i++){
            double x_old = xpoints[i];
            double y_old = ypoints[i];

            double x_new = x_old * Math.cos(arc) - y_old * Math.sin(arc);
            double y_new = x_old * Math.sin(arc) + y_old * Math.cos(arc);

            xpoints[i] = (int) x_new + x_center;
            ypoints[i] = (int) y_new + y_center;
        }
    }

    private static void drawNorth(Graphics g, Point center) {
        int x = center.getX();
        int y = center.getY();

        int w = Tank.DEFAULT_WIDTH / 6;
        int h = Tank.DEFAULT_HEIGHT / 6;

        g.fill3DRect(x-w, y-3*h, 2*w, 2*h, true);
        g.fill3DRect(x- 3*w, y-h, 2*w, 2 * h, true);
        g.fill3DRect(x-w, y-h, 2*w, 2*h, true);
        g.fill3DRect(x+w, y-h, 2*w, 2*h, true);
        g.fill3DRect(x - 3 * w, y + h, 2 * w, 2 * h, true);
        g.fill3DRect(x + w, y + h, 2 * w, 2 * h, true);
    }

    private static void drawSouth(Graphics g, Point center) {
        int x = center.getX();
        int y = center.getY();

        int w = Tank.DEFAULT_WIDTH / 6;
        int h = Tank.DEFAULT_HEIGHT / 6;

        g.fill3DRect(x-w, y+h, 2*w, 2*h, true);
        g.fill3DRect(x+w, y-h, 2*w, 2 * h, true);
        g.fill3DRect(x-w, y-h, 2*w, 2*h, true);
        g.fill3DRect(x-3*w, y-h, 2*w, 2*h, true);
        g.fill3DRect(x+w, y-3*h, 2 * w, 2 * h, true);
        g.fill3DRect(x-3*w, y-3*h, 2*w, 2*h, true);
    }

    private static void drawWest(Graphics g, Point center) {
        int x = center.getX();
        int y = center.getY();

        int w = Tank.DEFAULT_WIDTH / 6;
        int h = Tank.DEFAULT_HEIGHT / 6;

        g.fill3DRect(x-3*w, y-h, 2*w, 2*h, true);
        g.fill3DRect(x-w, y+h, 2*w, 2 * h, true);
        g.fill3DRect(x-w, y-h, 2*w, 2*h, true);
        g.fill3DRect(x-w, y-3*h, 2*w, 2*h, true);
        g.fill3DRect(x+w, y+h, 2 * w, 2 * h, true);
        g.fill3DRect(x+w, y-3*h, 2*w, 2*h, true);
    }

    private static void drawEast(Graphics g, Point center) {
        int x = center.getX();
        int y = center.getY();

        int w = Tank.DEFAULT_WIDTH / 6;
        int h = Tank.DEFAULT_HEIGHT / 6;

        g.fill3DRect(x+w, y-h, 2*w, 2*h, true);
        g.fill3DRect(x-w, y-3*h, 2*w, 2 * h, true);
        g.fill3DRect(x-w, y-h, 2*w, 2*h, true);
        g.fill3DRect(x-w, y+h, 2*w, 2*h, true);
        g.fill3DRect(x - 3 * w, y - 3*h, 2 * w, 2 * h, true);
        g.fill3DRect(x-3*w, y+h, 2*w, 2*h, true);
    }
}
