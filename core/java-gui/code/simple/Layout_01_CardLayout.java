import java.util.List;
import java.util.ArrayList;
import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;

public class Layout_01_CardLayout {
    public static void main(String[] args) {
        JFrame frame = new JFrame("卡片布局管理器");

        JPanel panel = new JPanel();
        frame.add(panel);

        // 创建一个卡片布局管理器
        CardLayout layout = new CardLayout();
        panel.setLayout(layout);

        JButton btn1 = new JButton("东");
        JButton btn2 = new JButton("南");
        JButton btn3 = new JButton("西");
        JButton btn4 = new JButton("北");

        List<JButton> btnList = new ArrayList<JButton>();
        btnList.add(btn1);
        btnList.add(btn2);
        btnList.add(btn3);
        btnList.add(btn4);

        for (int i = 0; i < btnList.size(); i++) {
            JButton btn = btnList.get(i);
            panel.add(btn);
            btn.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    layout.next(panel); // 下一张
                    // layout.previous(panel); // 上一张
                }
            });
        }

        // 初始化窗体
        FrameUtil.initFrame(frame, 300, 300);
    }
}
