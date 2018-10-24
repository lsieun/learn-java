package com.lsieun.snake.ancillary;


public enum GameElement {
    BLANK(0),
    WALL(1),
    SNAKE(2),
    SNAKE_HEAD(3),
    FOOD(4);

    private int value = 0;

    private GameElement(int value) {
        this.value = value;
    }

    public static GameElement valueOf(int value) {
        switch (value) {
            case 0:
                return BLANK;
            case 1:
                return WALL;
            case 2:
                return SNAKE;
            case 3:
                return SNAKE_HEAD;
            case 4:
                return FOOD;
            default:
                return null;
        }
    }

    public int value() {
        return this.value;
    }
}
