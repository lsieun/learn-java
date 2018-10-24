package com.lsieun.snake.ui;


import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.*;

public class CrossDialog extends javax.swing.JDialog {
    private JLabel crossWallLabel;
    private JLabel crossSelfLabel;

    private JRadioButton crossWallTrueButton;
    private JRadioButton crossWallFalseButton;
    private JRadioButton crossSelfTrueButton;
    private JRadioButton crossSelfFalseButton;

    private JButton btnOK;
    private JButton btnCancel;

    private MainWindow mainFrame;

    private boolean crossWall;
    private boolean crossSelf;

    public CrossDialog(MainWindow mainFrame) {
        this.mainFrame = mainFrame;
        this.crossWall = this.mainFrame.isCrossWall();
        this.crossSelf = this.mainFrame.isCrossSelf();

        this.init();
    }

    private void init() {
        GridLayout layout = new GridLayout(3, 1, 1, 2);
        this.setLayout(layout);

        this.crossWallLabel = new JLabel("穿过墙体：");
        this.crossSelfLabel = new JLabel("穿过自身：");

        this.crossWallTrueButton = new JRadioButton("是", this.crossWall);
        this.crossWallFalseButton = new JRadioButton("否", !this.crossWall);
        this.crossSelfTrueButton = new JRadioButton("是", this.crossSelf);
        this.crossSelfFalseButton = new JRadioButton("否", !this.crossSelf);

        btnOK = new JButton("确定");
        btnCancel = new JButton("取消");



        ButtonGroup wallGroup = new ButtonGroup();
        wallGroup.add(this.crossWallTrueButton);
        wallGroup.add(this.crossWallFalseButton);

        ButtonGroup selfGroup = new ButtonGroup();
        selfGroup.add(this.crossSelfTrueButton);
        selfGroup.add(this.crossSelfFalseButton);

        JPanel wallPanel = new JPanel();
        wallPanel.add(this.crossWallLabel);
        wallPanel.add(this.crossWallTrueButton);
        wallPanel.add(this.crossWallFalseButton);

        JPanel crossPanel = new JPanel();
        crossPanel.add(this.crossSelfLabel);
        crossPanel.add(this.crossSelfTrueButton);
        crossPanel.add(this.crossSelfFalseButton);

        JPanel btnPanel = new JPanel();
        btnPanel.add(btnOK);
        btnPanel.add(btnCancel);

        this.add(wallPanel);
        this.add(crossPanel);
        this.add(btnPanel);

        this.setTitle("设置穿越");
        this.setSize(200, 165);
        this.setLocationRelativeTo(null);
        this.setResizable(false);

        this.btnOK.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                boolean crossWall = CrossDialog.this.crossWallTrueButton.isSelected();
                boolean crossSelf = CrossDialog.this.crossSelfTrueButton.isSelected();

                System.out.println("Old");
                System.out.println("    WALL: " + CrossDialog.this.crossWall);
                System.out.println("    SELF: " + CrossDialog.this.crossSelf);
                System.out.println("New");
                System.out.println("    WALL: " + crossWall);
                System.out.println("    SELF: " + crossSelf);

                boolean isChanged = true;
                if (CrossDialog.this.crossWall == crossWall && CrossDialog.this.crossSelf == crossSelf) {
                    isChanged = false;
                }


                if (isChanged) {
                    System.out.println("Is Changed");
                    CrossDialog.this.crossWall = crossWall;
                    CrossDialog.this.crossSelf = crossSelf;

                    CrossDialog.this.mainFrame.setCross(crossWall, crossSelf);
                } else {
                    System.out.println("Not Changed");
                }

                CrossDialog.this.setVisible(false);
                CrossDialog.this.dispose();
            }
        });

        this.btnCancel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                CrossDialog.this.setVisible(false);
                CrossDialog.this.dispose();
            }
        });
    }
}
