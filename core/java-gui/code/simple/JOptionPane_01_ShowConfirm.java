import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class JOptionPane_01_ShowConfirm {
    public static void main(String[] args) {
        // 创建JFrame
        JFrame frame = new JFrame("Hello World");
        FrameUtil.initFrame(frame, 300, 400);
        
        // 确认对话框
        int num = JOptionPane.showConfirmDialog(frame, "文件尚未保存，您确定要退出吗？");
        System.out.println("num = " + num);

        // 退出
        FrameUtil.quit(frame);
    }
}
