package com.lsieun.snake.logic;


import java.util.LinkedList;
import java.util.Random;

import com.lsieun.snake.ancillary.Direction;
import com.lsieun.snake.ancillary.GameState;
import com.lsieun.snake.ancillary.Position;
import com.lsieun.snake.ancillary.GameElement;

public class SnakeLogic {

    public static final int DEFAULT_COLS = 40;
    public static final int DEFAULT_ROWS = 30;

    /**
     * 地图高（行数）
     */
    private int rows;
    /**
     * 地图的宽(列数)
     */
    private int cols;
    private boolean crossWall = true;
    private boolean crossSelf = true;

    /**
     * 地图
     */
    private GameElement[][] background;

    /**
     * 蛇
     * <p>
     * 使用集合保存蛇节点的所有信息
     * </p>
     */
    private final LinkedList<Position> snake = new LinkedList<Position>();

    //蛇当前的方向
    private Direction direction = Direction.RIGHT; // 蛇默认是向右行走

    /**
     * 食物
     */
    Position food;

    private GameElement[][] landscape;

    //记录游戏状态
    private GameState state;

    public SnakeLogic(int rows, int cols) {
        this(rows, cols, true, true);
    }

    public SnakeLogic(int rows, int cols, boolean crossWall, boolean crossSelf) {
        this.rows = rows;
        this.cols = cols;
        this.crossWall = crossWall;
        this.crossSelf = crossSelf;

        this.initBackground();
//        this.initSnake();

        this.state = GameState.STOP;
        this.landscape = new GameElement[rows][cols];

        System.out.println("Snake Logic[rows=" + this.rows + ",cols=" + this.cols + "]");
    }

    public Direction getDirection() {
        return this.direction;
    }

    //初始化地图
    private void initBackground(){
        this.background = new GameElement[rows][cols];

        for(int row = 0; row < this.rows; row++){
            for(int col = 0; col < this.cols; col++){
                if(row==0                          //第一行
                        || row== (this.rows -1)    //最后一行
                        || col== 0                 //第一列
                        || col== (this.cols -1)) { //最后一列
                    background[row][col] = GameElement.WALL;
                }
            }
        }
    }

    //初始化蛇
    private void initSnake(){
        int row = this.rows /2;
        int col = this.cols /2;

        snake.addFirst(new Position(row, col -1));
        snake.addFirst(new Position(row, col));
        snake.addFirst(new Position(row, col+1));
    }

    //生成食物
    public void initFood(){
        //创建一个随机数对象
        Random random = new Random();
        while(true){
            int row = random.nextInt(this.rows);
            int col = random.nextInt(this.cols);
            if(background[row][col] != GameElement.WALL){
                food = new Position(row, col);
                break;
            }

        }

    }

    public GameElement[][] getLandscape() {
        this.merge();
        return this.landscape;
    }

    private void merge() {

        // merge background
        if (this.landscape != null && this.landscape.length > 0) {
            for (int row = 0; row < this.rows; row++) {
                for (int col = 0; col < this.cols; col++) {
                    this.landscape[row][col] = this.background[row][col];
                }
            }
        }

        // merge snake
        if(snake != null && snake.size()>0) {
            // 蛇身处理
            for (int i = 1; i < snake.size(); i++) {
                Position p = snake.get(i);
                int row = p.getRow();
                int col = p.getCol();
                this.landscape[row][col] = GameElement.SNAKE;
            }

            // 蛇头处理
            Position head = snake.getFirst();
            this.landscape[head.row][head.col] = GameElement.SNAKE_HEAD;
        }


        // merge food
        if (food != null) {
            int row = this.food.getRow();
            int col = this.food.getCol();
            this.landscape[row][col] = GameElement.FOOD;
        }
    }

    //蛇移动的方法
    public void move(){
        Position head = snake.getFirst();
        //蛇是根据当前的方向移动的
        switch (direction) {
            case UP:
                if(head.row == 0){
                    snake.addFirst(new Position(this.rows-1, head.col));
                }
                else{
                    snake.addFirst(new Position(head.row-1, head.col));
                }
                break;
            case DOWN:
                if(head.row == this.rows-1) {
                    snake.addFirst(new Position(0, head.col));
                }
                else{
                    snake.addFirst(new Position(head.row+1, head.col));
                }
                break;
            case LEFT:
                if(head.col ==0){
                    snake.addFirst(new Position(head.row, this.cols-1));
                }else{
                    //添加新的蛇头
                    snake.addFirst(new Position(head.row, head.col-1));
                }
                break;
            case RIGHT:
                if(head.col == this.cols-1){
                    snake.addFirst(new Position(head.row, 0));
                }else{
                    //添加新的蛇头
                    snake.addFirst(new Position(head.row, head.col+1));
                }
                break;
            default:
                break;

        }

        if(eat()){
            //吃到了食物
            initFood();

        }else{
            //删除蛇尾
            snake.removeLast();
        }

    }

    //改变当前方向的方法
    public void changeDirection(Direction newDirection){
        if (newDirection == null) return;

        //判断新方向是否与当前方向是否是相反方向，才允许其改变
        if(!Direction.isOpposite(direction, newDirection)){
            this.direction = newDirection;
        }
    }

    //吃食物
    public boolean eat(){
        //获取到原来的蛇头
        Position head = snake.getFirst();
        if(head.equals(food)){
            return true;
        }
        return false;

    }

    //游戏结束的方法
    public GameState getState() {
        if (this.state == GameState.STOP) return this.state;
        if (this.state == GameState.GAME_OVER) return this.state;

        Position head = snake.getFirst();

        //撞墙死亡
        if(!this.crossWall) {
            if(background[head.row][head.col]== GameElement.WALL){
                System.out.println("GAME OVER FOR WALL");
                System.out.println("    row: " + head.row);
                System.out.println("    col: " + head.col);
                System.out.println("    WALL: " + background[head.row][head.col]);
                this.state = GameState.GAME_OVER;
            }
        }


        //咬到自己  蛇身
        if (!this.crossSelf){
            for(int i = 1; i<snake.size() ; i++){
                Position body = snake.get(i);
                if(head.equals(body)){
                    System.out.println("GAME OVER FOR SELF");
                    System.out.println("    row: " + head.row);
                    System.out.println("    col: " + head.col);
                    System.out.println("    SELF: " + i);
                    this.state = GameState.GAME_OVER;
                }
            }
        }

        return this.state;
    }

    public void reset() {
        this.food = null;
        this.snake.clear();
        this.direction = Direction.RIGHT;
        this.state = GameState.STOP;
    }

    public void start() {
        this.reset();
        this.initSnake();
        this.initFood();
        this.state = GameState.START;
    }
}
