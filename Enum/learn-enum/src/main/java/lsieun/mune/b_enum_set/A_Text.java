package lsieun.mune.b_enum_set;

// Bit field enumeration constants - OBSOLETE!
public class A_Text {
    public static final int STYLE_BOLD          = 1 << 0; // 1
    public static final int STYLE_ITALIC        = 1 << 1; // 2
    public static final int STYLE_UNDERLINE     = 1 << 2; // 4
    public static final int STYLE_STRIKETHROUGH = 1 << 3; // 8

    // Parameter is bitwise OR of zero or more STYLE_ constants
    public void applyStyles(int styles) {
        //
    }

    public static void main(String[] args) {
        A_Text text = new A_Text();
        text.applyStyles(STYLE_BOLD | STYLE_ITALIC);
    }
}
