import javax.swing.JFrame;


public class FrameUtilTest {
    public static void main(String[] args) {
        JFrame frame = new JFrame("测试FrameUtil工具类");
        FrameUtil.initFrame(frame, 300, 400);
    }
}
