package com.lsieun.snake.ui;


import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.*;

import com.lsieun.snake.ancillary.Direction;
import com.lsieun.snake.ancillary.GameElement;
import com.lsieun.snake.ancillary.GameState;
import com.lsieun.snake.logic.SnakeLogic;

public class LandscapePanel extends JPanel {

    /**
     * 格子宽
     */
    public static final int CELL_WIDTH = 20;

    /**
     * 格子高
     */
    public static final int CELL_HEIGHT = 20;

    private int rows;
    private int cols;

    private SnakeLogic logic;

    private Thread snakeThread;

    public LandscapePanel(int rows, int cols) {
        this(rows, cols, true, true);
    }

    public LandscapePanel(int rows, int cols, boolean crossWall, boolean crossSelf) {
        this.rows = rows;
        this.cols = cols;
        this.logic = new SnakeLogic(rows, cols, crossWall, crossSelf);
    }

    @Override
    public void paint(Graphics g) {


        GameElement[][] landscape = this.logic.getLandscape();

        //画地图
        for(int row = 0; row < this.rows; row++){
            for(int col = 0  ; col < this.cols; col++ ){

                GameElement element = landscape[row][col];

                if(element == GameElement.WALL){
                    g.setColor(Color.GRAY);
                }
                else if(element == GameElement.SNAKE) {
                    g.setColor(Color.YELLOW);
                }
                else if (element == GameElement.SNAKE_HEAD) {
                    g.setColor(Color.RED);
                }
                else if (element == GameElement.FOOD) {
                    g.setColor(Color.GREEN);
                }
                else{
                    g.setColor(Color.WHITE);
                }

                g.fill3DRect(col*CELL_WIDTH, row*CELL_HEIGHT, CELL_WIDTH, CELL_HEIGHT, true);
            }
        }

        GameState state = this.logic.getState();
        if (state == GameState.GAME_OVER) {
            g.setColor(Color.RED);
            g.setFont(new Font("宋体", Font.BOLD, 30));
            g.drawString("GAME OVER", 300, 200);
        }

    }

    private void move(){
        this.logic.move();

        this.repaint();
    }

    public void move(Direction direction) {
        GameState state = this.logic.getState();
        if (state == GameState.GAME_OVER) return;

        this.logic.changeDirection(direction);
        this.move();
    }

    public void reset() {
        if (this.snakeThread != null) {
            this.snakeThread.interrupt();
            this.snakeThread = null;
        }

        this.logic.reset();
        this.repaint();
    }

    public void start() {
        this.reset();
        this.logic.start();
        this.repaint();

        this.snakeThread = new Thread(){
            public void run(){
                while (true) {
                    GameState state = LandscapePanel.this.logic.getState();
                    if(state == GameState.STOP || state == GameState.GAME_OVER) break;
                    LandscapePanel.this.move();
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        this.snakeThread.start();

    }

    public void updatePartial() {
        //FIXME: 更新部分内容
    }

}
