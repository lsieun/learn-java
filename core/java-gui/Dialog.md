# Dialog

继承关系图(AWT)

![](images/java-component-tree.png)

## 1. JDialog

```java
JDialog(Frame owner, String title, boolean modal)
```

```java
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

```

## 2. FileDialog

文件对话框(FileDialog)：

```java
FileDialog(Dialog parent, String title, int mode)
```

```java
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

```

