package lsieun.mune.b_enum_set;

import java.util.EnumSet;
import java.util.Set;

// EnumSet - a modern replacement for bit fields
public class B_Text {
    public enum Style {BOLD, ITALIC, UNDERLINE, STRIKETHROUGH}

    // Any Set could be passed in, but EnumSet is clearly best
    public void applyStyles(Set<Style> styles) {
        //
    }

    public static void main(String[] args) {
        B_Text text = new B_Text();
        text.applyStyles(EnumSet.of(Style.BOLD, Style.ITALIC));
    }
}
