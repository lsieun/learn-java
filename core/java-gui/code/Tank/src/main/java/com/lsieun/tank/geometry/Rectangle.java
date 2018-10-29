package com.lsieun.tank.geometry;

import com.lsieun.tank.geometry.Geometry;

public class Rectangle implements Geometry {
    private int startX;
    private int startY;
    private int width;
    private int height;

    public Rectangle(int startX, int startY, int width, int height) {
        this.startX = startX;
        this.startY = startY;
        this.width = width;
        this.height = height;
    }

    public Rectangle(Point center, int width, int height) {
        this.startX = center.getX() - width/2;
        this.startY = center.getY() - height/2;
        this.width = width;
        this.height = height;
    }

    public int getStartX() {
        return startX;
    }

    public void setStartX(int startX) {
        this.startX = startX;
    }

    public int getStartY() {
        return startY;
    }

    public void setStartY(int startY) {
        this.startY = startY;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    @Override
    public String toString() {
        return "Rectangle{" +
                "startX=" + startX +
                ", startY=" + startY +
                ", width=" + width +
                ", height=" + height +
                '}';
    }
}
