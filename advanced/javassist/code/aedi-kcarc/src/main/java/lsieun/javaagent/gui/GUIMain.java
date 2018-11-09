package lsieun.javaagent.gui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import lsieun.javaagent.util.IOUtil;
import lsieun.javaagent.util.JetUtil;

public class GUIMain {
    
    JFrame frame = new JFrame("JAR");
    
    JPanel panel = new JPanel();
    
    JTextField field = new JTextField("请选择文件...",30);
    FileDialog openDialog = new FileDialog(frame, "请选择文件", FileDialog.LOAD);
    JButton btnBrowse = new JButton("浏览");
    JButton btnClear = new JButton("清空");
    JButton btnCrack = new JButton("破解");

    JTextArea area = new JTextArea(15,25);
    
    //滚动条
    ScrollPane bar = new ScrollPane();
    
    
    
    public void init(){
        //先把area添加 到滚动条上。
        bar.add(area);
        //先把组件添加到panel上
        field.setEnabled(false);
        panel.add(field);
        panel.add(btnBrowse);
        panel.add(btnClear);
        panel.add(btnCrack);
        
        //给输入框添加事件
        field.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                JTextField field =  (JTextField) e.getSource();
                if(field.getText().equals("请选择文件...")){
                    field.setText("");
                }
            }
        });
        
        //给按钮添加事件监听器
        btnBrowse.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {

                openDialog.setVisible(true);
                String fileDir = openDialog.getDirectory();
                String fileName = openDialog.getFile();
                File file = new File(fileDir, fileName);
                String filePath = "";
                try {
                    filePath = file.getCanonicalPath();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                GUIMain.this.field.setText(filePath);

                area.setText(area.getText()+ "Read File: " + filePath +"\r\n");
            }
        });

        //给按钮添加事件监听器
        btnClear.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                GUIMain.this.field.setText("");
                GUIMain.this.area.setText("");
            }
        });

        //给按钮添加事件监听器
        btnCrack.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
//                //获取输入框输入的路径
//                String path = field.getText();
//                //使用输入的路径构建一个File对象
//                File dir = new File(path);
//                //找到目录下的所有子文件
//                File[] files = dir.listFiles();
//                for(File file : files){
//                    area.setText(area.getText()+ file.getName()+"\r\n");
//                }
                String jarPath = field.getText();
                File file = new File(jarPath);
                if (!file.exists()) {
                    area.setText(area.getText()+ "File Not Found: " + jarPath +"\r\n");
                    return;
                }
                IOUtil.backupFile(jarPath);
                JetUtil.process(jarPath);
            }
        });
        
        //把面板添加到frame上
        frame.add(panel,BorderLayout.NORTH);
        frame.add(bar);
        frame.setResizable(false);
        FrameUtil.initFrame(frame, 600, 400);
    }
    
    public static void main(String[] args) {
        new GUIMain().init();
    }
    
    
    


}
