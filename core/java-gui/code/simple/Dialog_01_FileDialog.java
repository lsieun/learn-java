import java.awt.FileDialog;
import javax.swing.JFrame;

public class Dialog_01_FileDialog {
    public static void main(String[] args) {
        JFrame frame = new JFrame("文件对话框");
        FrameUtil.initFrame(frame, 300, 400);

        // 打开对话框
        FileDialog openDialog = new FileDialog(frame, "请选择打开的文件", FileDialog.LOAD);
        openDialog.setVisible(true);
        System.out.println("Path: " + openDialog.getDirectory());
        System.out.println("FileName: " + openDialog.getFile());

        // 保存对话框
        FileDialog saveDialog = new FileDialog(frame, "请选择保存的路径", FileDialog.SAVE);
        saveDialog.setVisible(true);
        System.out.println("Path: " + saveDialog.getDirectory());
        System.out.println("FileName: " + saveDialog.getFile());

    }
}
