package lsieun.javaagent.gui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.util.List;

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
    
    JTextField field = new JTextField("Please Select JAR file...",30);
    FileDialog openDialog = new FileDialog(frame, "Select JAR File", FileDialog.LOAD);
    JButton btnBrowse = new JButton("Browse");
    JButton btnClear = new JButton("Clear");
    JButton btnCrack = new JButton("Crack");

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
                if(field.getText().equals("Please Select JAR file...")){
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
                String jarPath = field.getText();
                File file = new File(jarPath);
                if (!file.exists()) {
                    area.setText(area.getText()+ "File Not Found: " + jarPath +"\r\n");
                    return;
                }
                String backupFile = IOUtil.backupFile(jarPath);
                area.setText(area.getText()+ "Backup File: " + backupFile +"\r\n");
                List<String> resultList = JetUtil.process(jarPath);
                for(String line : resultList) {
                    area.setText(area.getText()+ "" + line +"\r\n");
                }
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
