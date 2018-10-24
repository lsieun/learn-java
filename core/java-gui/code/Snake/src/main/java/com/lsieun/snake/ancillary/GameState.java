package com.lsieun.snake.ancillary;

public enum GameState {

    STOP(0),
    START(1),
    GAME_OVER(2);

    private int value;

    private GameState(int value) {
        this.value = value;
    }

    public static GameState valueOf(int value) {
        switch (value) {
            case 0:
                return STOP;
            case 1:
                return START;
            case 2:
                return GAME_OVER;
            default:
                return null;
        }
    }

    public int value() {
        return this.value;
    }
}
