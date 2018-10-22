import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class JOptionPane_01_ShowMessage {
    public static void main(String[] args) {
        // 创建JFrame
        JFrame frame = new JFrame("Hello World");
        FrameUtil.initFrame(frame, 300, 400);
        
        // 消息对话框
        JOptionPane.showMessageDialog(frame, "今天的天气非常好", "通知", JOptionPane.INFORMATION_MESSAGE);
        // 警告对话框
        JOptionPane.showMessageDialog(frame, "今天的天气有雷阵雨", "警告", JOptionPane.WARNING_MESSAGE);
        // 错误对话框
        JOptionPane.showMessageDialog(frame, "今天的天气...，系统出错了", "错误", JOptionPane.ERROR_MESSAGE);

        // 退出
        FrameUtil.quit(frame);
    }
}
