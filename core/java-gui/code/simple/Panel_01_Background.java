import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Panel_01_Background {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Hello");

        JPanel panel = new JPanel();
        panel.setBackground(Color.RED);

        frame.add(panel);
        FrameUtil.initFrame(frame, 300, 400);
    }
}
