import java.awt.BorderLayout;
import java.awt.FileDialog;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JTextArea;
import javax.swing.JOptionPane;

/*
 菜单组件
    
    菜单条(MenuBar) 、  菜单（Menu） 、 菜单项(MenuItem)
    
    菜单条可以添加菜单
    
    菜单可以添加菜单项
    
    复选菜单：
        首先菜单添加菜单 ， 菜单添加菜单项。
*/

public class Notepad {
    
    JFrame frame = new JFrame("记事本");
    
    //菜单条
    JMenuBar bar = new JMenuBar();
    
    //文件菜单
    JMenu fileMenu = new JMenu("文件");
    JMenu editMenu  = new JMenu("编辑");
    JMenu helpMenu  = new JMenu("帮助");
    
    
    TextArea area = new TextArea(20,30);

    private void initFileMenu() {
        //菜单项
        JMenuItem newItem = new JMenuItem("新建");
        JMenuItem openItem = new JMenuItem("打开");
        JMenuItem saveItem = new JMenuItem("保存");
        JMenuItem closeItem = new JMenuItem("关闭");
        JMenuItem quitItem = new JMenuItem("退出");
        
        //复选菜单
        JMenu switchMenu = new JMenu("切换工作目录");
        JMenuItem workMenu1 = new JMenuItem("0910project");
        JMenuItem workMenu2 = new JMenuItem("1208project");
        JMenuItem workMenu3 = new JMenuItem("1110project");
        switchMenu.add(workMenu1);
        switchMenu.add(workMenu2);
        switchMenu.add(workMenu3);

        //菜单添加菜单项目
        fileMenu.add(newItem);
        fileMenu.add(openItem);
        fileMenu.add(saveItem);
        fileMenu.add(closeItem);
        //菜单添加菜单就是复选菜单
        fileMenu.add(switchMenu);
        fileMenu.add(quitItem);
        
        //给保存添加事件
        saveItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                        FileDialog fileDialog = new FileDialog(frame, "请选择保存的路径",FileDialog.SAVE);
                        fileDialog.setVisible(true);
                        
                        //获取用户选择的路径与文件名
                        String path = fileDialog.getDirectory();
                        String fileName = fileDialog.getFile();
                        //创建一个输入对象
                        FileOutputStream fileOutputStream = new FileOutputStream(new File(path,fileName));
                        
                        //获取文本域的内容，把内容写出
                        String content = area.getText();
                        //content = content+"\r\n";
                        //content = content.replaceAll("\n", "\r\n");
                        fileOutputStream.write(content.getBytes());
                        //关闭资源
                        fileOutputStream.close();
                    
                } catch (IOException ex) {
                        ex.printStackTrace();
                }
            }
        
        });

        quitItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FrameUtil.quit(frame);
            }
        });

    }

    private void initEditMenu() {
        JMenuItem cutItem = new JMenuItem("剪切");
        JMenuItem copyItem = new JMenuItem("复制");
        JMenuItem pasteItem = new JMenuItem("粘贴");

        editMenu.add(cutItem);
        editMenu.add(copyItem);
        editMenu.add(pasteItem);
    }

    private void initHelpMenu() {
        JMenuItem registerItem = new JMenuItem("注册...");
        JMenuItem aboutItem = new JMenuItem("关于");

        helpMenu.add(registerItem);
        helpMenu.add(aboutItem);

        aboutItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //System.out.println("About Click");
                JOptionPane.showMessageDialog(frame, "程序：Super Notepad\r\n作者：Aegis\r\n版本：1.0.0 Beta", "关于", JOptionPane.INFORMATION_MESSAGE);
            }
        });
    }

    public void initMenuBar() {
        this.initFileMenu();
        this.initEditMenu();
        this.initHelpMenu();
        
        //菜单条添加菜单
        bar.add(fileMenu);
        bar.add(editMenu);
        bar.add(helpMenu);
    }
    
    public void init(){
        this.initMenuBar();
        
        //添加菜单条
        frame.add(bar,BorderLayout.NORTH);
        //添加编辑区域
        frame.add(area, BorderLayout.CENTER);

        // 初始化窗体
        FrameUtil.initFrame(frame, 500, 600);
    
    }
    
    public static void main(String[] args) {
        Notepad notepad = new Notepad();
        notepad.init();
    }
}
