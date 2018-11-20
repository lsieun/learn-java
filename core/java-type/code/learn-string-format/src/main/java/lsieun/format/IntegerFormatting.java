package lsieun.format;

public class IntegerFormatting {
    public static void main(String[] args) {
        // Default formatting
        System.out.println("\n\n默认");
        System.out.printf("%d%n", 93);        // 93
        System.out.printf("%d%n", -93);       // 93

        // "%+d", 结果带正负号
        System.out.println("\n\n正负号");
        System.out.printf("|%+d|%n", 99);     // |+99|
        System.out.printf("|%+d|%n", -99);    // |-99|
        //System.out.printf("|%-d|%n", 99);   // Exception

        // A space before positive numbers
        // A “-” is included for negative numbers as per normal.
        System.out.println("\n\n空格");
        System.out.printf("|% d|%n", 93);     // | 93|
        System.out.printf("|% d|%n", -93);    // |-93|

        // Specifying a width
        System.out.println("\n\n指定宽度");
        System.out.printf("|%20d|%n", 93);    // |                  93|
        System.out.printf("|%20d|%n", -93);   // |                 -93|

        // Left-justifying within the specified width
        System.out.println("\n\n左对齐");
        System.out.printf("|%-20d|%n", 93);   // |93                  |
        System.out.printf("|%-20d|%n", -93);  // |-93                 |
        System.out.printf("|%-+20d|%n", 93);  // |+93                 |

        // Print positive numbers with a “+”
        System.out.println("\n\n指定宽度 and 正负号");
        System.out.printf("|%+20d|%n", 93);   // |                 +93|
        System.out.printf("|%+20d|%n", -93);  // |                 -93|


        // Pad with zeros
        System.out.println("\n\nPadding with zeros");
        System.out.printf("|%020d|%n", 93);   // |00000000000000000093|

        //
        System.out.println("\n\n逗号分隔");
        System.out.printf("|%,d|%n", 10000000);    // |10,000,000|
        System.out.printf("|%,d|%n", -10000000);   // |-10,000,000|

        System.out.println("\n\n左小括号");
        System.out.printf("|%(d|%n", 36);    // |36|
        System.out.printf("|%(d|%n", -36);   // |(36)|

        System.out.println("\n\n八进制");
        System.out.printf("|%o|%n", 15);    // |17|
        System.out.printf("|%#o|%n", 15);   // |017|

        System.out.println("\n\n十六进制");
        System.out.printf("|%x|%n", 93);    // |5d|
        System.out.printf("|%#x|%n", 93);   // |0x5d|
        System.out.printf("|%#X|%n", 93);   // |0X5D|
    }
}
