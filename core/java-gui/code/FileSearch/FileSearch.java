import java.awt.BorderLayout;
import java.awt.ScrollPane;
import java.awt.Scrollbar;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class FileSearch {
    
    JFrame frame = new JFrame("文件搜索器");
    
    JPanel panel = new JPanel();
    
    JTextField field = new JTextField("请输入目录名...",15);
    
    JButton button = new JButton("搜索");
    
    JTextArea area = new JTextArea(15,15);
    
    //滚动条
    ScrollPane bar = new ScrollPane();
    
    
    
    public void init(){
        //先把area添加 到滚动条上。
        bar.add(area);
        //先把组件添加到panel上
        panel.add(field);
        panel.add(button);
        
        //给输入框添加事件
        field.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                JTextField field =  (JTextField) e.getSource();
                if(field.getText().equals("请输入目录名...")){
                    field.setText("");
                }
            }
        });
        
        //给按钮添加事件监听器
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //获取输入框输入的路径
                String path = field.getText();
                //使用输入的路径构建一个File对象
                File dir = new File(path);
                //找到目录下的所有子文件
                File[] files = dir.listFiles();
                for(File file : files){ // 1208Project  资料
                    area.setText(area.getText()+ file.getName()+"\r\n");
                 }
            }
        });
        
        //把面板添加到frame上
        frame.add(panel,BorderLayout.NORTH);
        frame.add(bar);     
        FrameUtil.initFrame(frame, 300, 400);
    }
    
    public static void main(String[] args) {
        new FileSearch().init();
        
        
    
    }
    
    
    


}
