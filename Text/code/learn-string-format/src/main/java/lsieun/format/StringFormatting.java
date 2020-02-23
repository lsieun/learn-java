package lsieun.format;

public class StringFormatting {
    public static void main(String[] args) {
        // Default formatting
        System.out.println("\n默认");
        System.out.printf("|%s|%n", "Hello World");    // |Hello World|

        // Specify Field Length
        System.out.println("\n指定宽度");
        System.out.printf("|%30s|%n", "Hello World");  // |                   Hello World|

        // Left Justify Text
        System.out.println("\n指定宽度+左对齐");
        System.out.printf("|%-30s|%n", "Hello World"); // |Hello World                   |

        // Specify Maximum Number of Characters
        System.out.println("\n限制字符个数");
        System.out.printf("|%.5s|%n", "Hello World");  // |Hello|

        // Field Width and Maximum Number of Characters
        System.out.println("\n指定宽度+限制字符个数");
        System.out.printf("|%30.5s|%n", "Hello World");// |                         Hello|
    }
}
