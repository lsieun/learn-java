package com.lsieun.tank.ancillary;

/**
 * 速度
 */
public class Speed {
    private double x;
    private double y;
    private Direction direction;

    public Speed() {
        this.x = 0;
        this.y = 0;
        this.direction = Direction.NONE;
    }

    public Speed(double x, double y, Direction direction) {
        this.x = x;
        this.y = y;
        this.direction = direction;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    @Override
    public String toString() {
        return "Speed{" +
                "x=" + x +
                ", y=" + y +
                ", direction=" + direction +
                '}';
    }
}
