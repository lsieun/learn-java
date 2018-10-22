import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFrame;
import javax.swing.JButton;

public class Event_01_MouseListener {
    public static void main(String[] args) {
        JFrame frame = new JFrame("鼠标监听事件");

        JButton btn = new JButton("Hello");
        frame.add(btn);

        /*
        btn.addMouseListener(new MouseListener() {
            @Override
            public void mouseReleased(MouseEvent e) {
                System.out.println("鼠标松开...");
            }
            
            @Override
            public void mousePressed(MouseEvent e) {
                System.out.println("鼠标按下..");
            }
            
            @Override
            public void mouseExited(MouseEvent e) {
                System.out.println("鼠标移出...");
            }
            
            @Override
            public void mouseEntered(MouseEvent e) {
                System.out.println("鼠标进入...");
            }
            
            @Override
            public void mouseClicked(MouseEvent e) {
                System.out.println("鼠标单击..");
            }
        });
        */

        // 如果添加鼠标监听器的时候只使用到单击事件，但是目前要实现所有的方法？
        // 解决方案： 适配器。
        // 适配器是实现了MouseListener方法的所有方法，但是实现的方法全部都是空实现。
        btn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                System.out.println("鼠标点击。。。");
                if (e.getClickCount() == 2) {
                    System.out.println("鼠标双击。。。");
                }
            }
        });

        FrameUtil.initFrame(frame, 300, 400);
    }
}
