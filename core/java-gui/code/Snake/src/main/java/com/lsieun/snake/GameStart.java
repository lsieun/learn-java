package com.lsieun.snake;


import com.lsieun.snake.ui.MainWindow;
import com.lsieun.snake.ui.LandscapePanel;
import com.lsieun.snake.util.FrameUtil;

public class GameStart {
    public static final int DEFAULT_ROWS = 20;
    public static final int DEFAULT_COLS = 40;


    public static void main(String[] args) {
        MainWindow game = new MainWindow(DEFAULT_ROWS, DEFAULT_COLS);

        int width = LandscapePanel.CELL_WIDTH * DEFAULT_COLS + 2;
        int height = LandscapePanel.CELL_HEIGHT * (DEFAULT_ROWS + 3);
        FrameUtil.initFrame(game, width, height);
    }
}
