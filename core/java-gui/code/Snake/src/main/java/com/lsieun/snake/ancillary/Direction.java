package com.lsieun.snake.ancillary;

/**
 * 使用四个常量表示四个方向
 */
public enum Direction {
    UP(1),
    DOWN(-1),
    LEFT(2),
    RIGHT(-2);


    private int value = 0;

    private Direction(int value) {
        this.value = value;
    }

    public static Direction valueOf(int value) {
        switch (value) {
            case 1:
                return UP;
            case -1:
                return DOWN;
            case 2:
                return LEFT;
            case -2:
                return RIGHT;
            default:
                return null;
        }
    }

    public int value() {
        return this.value;
    }

    public static boolean isOpposite(Direction one, Direction another) {
        if (one.value + another.value == 0) {
            return true;
        }
        return false;
    }
}
