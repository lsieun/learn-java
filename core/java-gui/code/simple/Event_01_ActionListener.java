import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JButton;

public class Event_01_ActionListener {
    public static void main(String[] args) {
        JFrame frame = new JFrame("事件");

        JButton button = new JButton("滚滚红尘终误我");
        frame.add(button);

        // 给按钮添加动作监听器
        // 动作事件监听器(ActionListener)对于 鼠标点击 以及 空格键 都会起作用的。
        button.addActionListener(new ActionListener() {
            // 当按钮被点击的时候，就会调用actionPerformed的方法。
            // 当前按钮被点击时，JVM就会把对应的事件封装为ActionEvent，并且调用actionPerformed方法。
            @Override
            public void actionPerformed(ActionEvent e) {
                // 获取事件源
                JButton btn = (JButton) e.getSource();
                if (btn.getText().equals("滚滚红尘终误我")) {
                    btn.setText("今宵月夜可流连");
                }
                else {
                    btn.setText("滚滚红尘终误我");
                }
            }
        });

        FrameUtil.initFrame(frame, 300, 400);
    }
}
