package com.lsieun.snake.ui;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.*;

import com.lsieun.snake.util.NumberUtil;

/**
 * Created by liusen on 10/24/18.
 */
public class SizeDialog extends javax.swing.JDialog {
    private JLabel rowLabel;
    private JTextField rowField;
    private JLabel colLabel;
    private JTextField colField;
    private JButton btnOK;
    private JButton btnCancel;
    private MainWindow mainFrame;

    private int rows;
    private int cols;

    public SizeDialog(MainWindow mainFrame){
        this.mainFrame = mainFrame;
        this.init();
    }

    private void init() {
        FlowLayout layout = new FlowLayout();
        this.setLayout(layout);

        rowLabel = new JLabel("行：");
        rowField = new JTextField(10);
        colLabel = new JLabel("列：");
        colField = new JTextField(10);
        btnOK = new JButton("确定");
        btnCancel = new JButton("取消");

        this.add(rowLabel);
        this.add(rowField);
        this.add(colLabel);
        this.add(colField);
        this.add(btnOK);
        this.add(btnCancel);

        this.rows = this.mainFrame.getRows();
        this.cols = this.mainFrame.getCols();
        rowField.setText("" + this.rows);
        colField.setText("" + this.cols);

        this.setTitle("设置大小");
        this.setSize(450, 90);
        this.setLocationRelativeTo(null);
        this.setResizable(false);

        this.btnOK.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                String rowText = SizeDialog.this.rowField.getText();
                String colText = SizeDialog.this.colField.getText();

                int rows = NumberUtil.parseInt(rowText, SizeDialog.this.rows);
                int cols = NumberUtil.parseInt(colText, SizeDialog.this.cols);

                System.out.println("Old");
                System.out.println("    rows: " + SizeDialog.this.rows);
                System.out.println("    cols: " + SizeDialog.this.cols);
                System.out.println("New");
                System.out.println("    rows: " + rows);
                System.out.println("    cols: " + cols);

                boolean isChanged = true;
                if (NumberUtil.equals(rows, SizeDialog.this.rows)
                        && NumberUtil.equals(cols, SizeDialog.this.cols)) {
                    isChanged = false;
                }


                if (isChanged) {
                    System.out.println("Is Changed");
                    SizeDialog.this.rows = rows;
                    SizeDialog.this.cols = cols;

                    SizeDialog.this.mainFrame.setRowsCols(rows, cols);
                } else {
                    System.out.println("Not Changed");
                }

                SizeDialog.this.setVisible(false);
                SizeDialog.this.dispose();
            }
        });

        this.btnCancel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                SizeDialog.this.setVisible(false);
                SizeDialog.this.dispose();
            }
        });
    }
}
