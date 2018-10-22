import javax.swing.JFrame;
import javax.swing.JDialog;

public class JDialog_01_Demo {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Hello World");
        FrameUtil.initFrame(frame, 300, 400);

        JDialog dialog = new JDialog(frame, "对话框标题", true);
        dialog.setSize(200, 100);
        dialog.setLocationRelativeTo(null);
        dialog.setVisible(true);
    }
}
