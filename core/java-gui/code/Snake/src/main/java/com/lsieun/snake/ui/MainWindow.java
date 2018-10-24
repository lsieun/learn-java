package com.lsieun.snake.ui;


import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.*;

import com.lsieun.snake.ancillary.Direction;
import com.lsieun.snake.util.FrameUtil;

public class MainWindow extends javax.swing.JFrame {
    public static final int DEFAULT_COLS = 20;
    public static final int DEFAULT_ROWS = 20;

    private JMenuBar topMenuBar;
    private LandscapePanel panel;

    private JMenu startMenu;
    private JMenu settingsMenu;
    private JMenu helpMenu;

    private JMenuItem newItem;
    private JMenuItem resetItem;
    private JMenuItem quitItem;

    private JMenuItem sizeItem;
    private JMenuItem crossItem;


    private JMenuItem howItem;
    private JMenuItem aboutItem;

    private int rows;
    private int cols;
    private boolean crossWall;
    private boolean crossSelf;

    public MainWindow() {
        this(DEFAULT_ROWS, DEFAULT_COLS);
    }

    public MainWindow(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        this.crossWall = false;
        this.crossSelf = false;

        this.init();

        this.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {


                int code = e.getKeyCode();
                Direction currentDirection = null;
                switch (code) {
                    case KeyEvent.VK_UP:
                        currentDirection = Direction.UP;
                        break;
                    case KeyEvent.VK_DOWN:
                        currentDirection = Direction.DOWN;
                        break;
                    case KeyEvent.VK_LEFT:
                        currentDirection = Direction.LEFT;
                        break;
                    case KeyEvent.VK_RIGHT:
                        currentDirection = Direction.RIGHT;
                        break;
                    default:
                        break;

                }
                System.out.println("Key: " + currentDirection);
                MainWindow.this.panel.move(currentDirection);
            }
        });
    }

    private void init() {
        this.initMenuBar();
        this.initPanel();

        this.setTitle("贪吃蛇");
        String logoFilePath = MainWindow.class.getClassLoader().getResource("logo.png").getFile();
        System.out.println("LOGO: " + logoFilePath);
        this.setIconImage(Toolkit.getDefaultToolkit().createImage(logoFilePath));
    }

    private void initMenuBar() {
        this.initStartMenu();
        this.initSettingsMenu();
        this.initHelpMenu();

        topMenuBar = new JMenuBar();
        topMenuBar.add(startMenu);
        topMenuBar.add(settingsMenu);
        topMenuBar.add(helpMenu);

        this.add(topMenuBar, BorderLayout.NORTH);
    }

    private void initStartMenu() {
        // 菜单
        startMenu = new JMenu("开始(S)");

        // 菜单项
        newItem = new JMenuItem("新游戏");
        resetItem = new JMenuItem("重置");
        quitItem = new JMenuItem("退出");

        // 关联
        startMenu.add(newItem);
        startMenu.add(resetItem);
        startMenu.add(quitItem);

        // 处理事件
        newItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                MainWindow.this.panel.start();
            }

        });

        resetItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                MainWindow.this.panel.reset();
            }
        });

        quitItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                FrameUtil.quit(MainWindow.this);
            }
        });


    }

    private void initSettingsMenu() {
        // 菜单
        settingsMenu = new JMenu("设置(S)");

        sizeItem = new JMenuItem("大小");
        crossItem = new JMenuItem("穿越");
        settingsMenu.add(sizeItem);
        settingsMenu.add(crossItem);

        sizeItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SizeDialog dialog = new SizeDialog(MainWindow.this);

                dialog.setVisible(true);
            }
        });

        crossItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CrossDialog dialog = new CrossDialog(MainWindow.this);

                dialog.setVisible(true);
            }
        });
    }

    private void initHelpMenu() {
        // 菜单
        helpMenu = new JMenu("帮助(H)");

        // 菜单项
        final JMenuItem howItem = new JMenuItem("操作");
        final JMenuItem aboutItem = new JMenuItem("关于");

        // 关联
        helpMenu.add(howItem);
        helpMenu.add(aboutItem);

        // 处理事件
        howItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                JOptionPane.showMessageDialog(MainWindow.this, "请使用方向键（上、下、左、右）进行控制", "操作", JOptionPane.INFORMATION_MESSAGE);
            }
        });

        aboutItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                StringBuilder sb = new StringBuilder();
                sb.append("程序：贪吃蛇\r\n");
                sb.append("作者：牛孙\r\n");
                sb.append("发布：2018-10-22\r\n");
                sb.append("版本：1.0.0 Beta\r\n");
                JOptionPane.showMessageDialog(MainWindow.this, sb.toString(), "关于", JOptionPane.INFORMATION_MESSAGE);
            }
        });

    }

    private void initPanel() {
        this.panel = new LandscapePanel(this.rows, this.cols, this.crossWall, this.crossSelf);
        this.add(panel, BorderLayout.CENTER);
    }

    public int getRows() {
        return rows;
    }

    public int getCols() {
        return cols;
    }

    public void setRowsCols(int rows, int cols) {
        boolean isChanged = true;
        if (this.rows == rows && this.cols == cols) {
            isChanged = false;
        }
        if (!isChanged) return;

        this.rows = rows;
        this.cols = cols;

        // 更改游戏面板
        if (this.panel != null) {
            this.panel.reset();
            this.remove(this.panel);
            this.initPanel();
        }

        // 更改游戏窗口
        int width = LandscapePanel.CELL_WIDTH * cols + 2;
        int height = LandscapePanel.CELL_HEIGHT * (rows + 3);

        this.setResizable(false);
        FrameUtil.initFrame(this, width, height);
    }

    public boolean isCrossWall() {
        return crossWall;
    }

    public boolean isCrossSelf() {
        return crossSelf;
    }

    public void setCross(boolean crossWall, boolean crossSelf) {
        boolean isChanged = true;
        if (this.crossWall == crossWall && this.crossSelf == crossSelf) {
            isChanged = false;
        }
        if (!isChanged) return;

        this.crossWall = crossWall;
        this.crossSelf = crossSelf;

        // 更改游戏面板
        if (this.panel != null) {
            this.panel.reset();
            this.remove(this.panel);
            this.panel = null;
            this.initPanel();
        }

        // 更改游戏窗口
        int width = LandscapePanel.CELL_WIDTH * this.cols + 2;
        int height = LandscapePanel.CELL_HEIGHT * (this.rows + 3);

        this.setResizable(false);
        FrameUtil.initFrame(this, width, height);
    }

    public static void main(String[] args) {
        MainWindow game = new MainWindow();

        int width = LandscapePanel.CELL_WIDTH * MainWindow.DEFAULT_COLS + 2;
        int height = LandscapePanel.CELL_HEIGHT * (MainWindow.DEFAULT_ROWS + 3);

        game.setResizable(false);
        FrameUtil.initFrame(game, width, height);
    }
}
