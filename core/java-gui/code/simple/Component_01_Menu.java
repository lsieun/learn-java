import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JTextArea;

public class Component_01_Menu {
    JFrame frame = new JFrame("记事本");

    // 菜单条
    JMenuBar bar = new JMenuBar();

    // 菜单
    JMenu fileMenu = new JMenu("文件");
    JMenu editMenu = new JMenu("编辑");

    JMenu swithMenu = new JMenu("切换工作目录");

    // 菜单项
    JMenuItem openItem = new JMenuItem("打开");
    JMenuItem saveItem = new JMenuItem("保存");
    JMenuItem copyItem = new JMenuItem("复制");
    JMenuItem pasteItem = new JMenuItem("粘贴");

    JMenuItem workItem1 = new JMenuItem("第1个项目");
    JMenuItem workItem2 = new JMenuItem("第2个项目");
    JMenuItem workItem3 = new JMenuItem("第3个项目");

    // 文本编辑区
    JTextArea textArea = new JTextArea(20, 30);

    public void initNotepad() {
        // 菜单 添加 菜单项
        fileMenu.add(openItem);
        fileMenu.add(saveItem);

        editMenu.add(copyItem);
        editMenu.add(pasteItem);

        // 复选菜单
        swithMenu.add(workItem1);
        swithMenu.add(workItem2);
        swithMenu.add(workItem3);
        fileMenu.add(swithMenu);

        // 菜单条添加菜单
        bar.add(fileMenu);
        bar.add(editMenu);

        // 添加菜单条 和 文本编辑区域
        frame.add(bar, BorderLayout.NORTH);
        frame.add(textArea, BorderLayout.CENTER);
        FrameUtil.initFrame(frame, 500, 600);
    }
    public static void main(String[] args) {
        new Component_01_Menu().initNotepad();
    }
}
