import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class JOptionPane_01_ShowInput {
    public static void main(String[] args) {
        // 创建JFrame
        JFrame frame = new JFrame("Hello World");
        FrameUtil.initFrame(frame, 300, 400);
        
        // 输入对话框
        String username = JOptionPane.showInputDialog("请输入你的用户名");
        System.out.println("username = " + username);

        // 退出
        FrameUtil.quit(frame);
    }
}
