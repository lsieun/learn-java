package lsieun.format;

import java.util.Date;

public class FormatSpecifier{
    public static void main(String[] args) {
        testX();
    }

    public static void testA() {
        System.out.printf("%a%n", 1.0); // 0x1.0p0
        System.out.printf("%a%n", 2.0); // 0x1.0p1
        System.out.printf("%a%n", 3.0); // 0x1.8p1
        System.out.printf("%a%n", 4.0); // 0x1.0p2
        System.out.printf("%a%n", 5.0); // 0x1.4p2
        System.out.printf("%a%n", 6.0); // 0x1.8p2
    }

    public static void testB() {
        // Integer
        System.out.printf("%b%n", 0); // true
        System.out.printf("%b%n", 1); // true

        // Boolean
        System.out.printf("%b%n", true); // true
        System.out.printf("%b%n", false); // false

        // Object
        System.out.printf("%b%n", new Object()); // true
        System.out.printf("%b%n", null); // false
    }

    public static void testC() {
        // Char
        System.out.printf("%c%n", 'a');
        System.out.printf("%c%n", 'A');
        System.out.printf("%c%n", '宋');

        // String
        //System.out.printf("%c%n", "A"); // Exception
    }

    public static void testD() {
        // Char
        System.out.printf("%d%n", (byte)'a'); // 97
        System.out.printf("%d%n", (byte)'A'); // 65
        System.out.printf("%d%n", (byte)'宋'); // -117

        // Integer
        System.out.printf("%d%n", 100); // 100
    }

    public static void testE() {
        System.out.printf("%e%n", 0.0);  // 0.000000e+00
        System.out.printf("%e%n", 0.01); //1.000000e-02
        System.out.printf("%e%n", 0.1);  //1.000000e-01

        System.out.printf("%e%n", 1.0);   //1.000000e+00
        System.out.printf("%e%n", 10.0);  //1.000000e+01
        System.out.printf("%e%n", 100.0); //1.000000e+02

        System.out.printf("%e%n", Math.PI); // 3.141593e+00
    }

    public static void testF() {
        System.out.printf("%f%n", 0.0);  // 0.000000
        System.out.printf("%f%n", 0.01); // 0.010000
        System.out.printf("%f%n", 0.1);  // 0.100000

        System.out.printf("%f%n", 1.0);   // 1.000000
        System.out.printf("%f%n", 10.0);  // 10.000000
        System.out.printf("%f%n", 100.0); // 100.000000

        System.out.printf("%f%n", Math.PI); // 3.141593
    }

    public static void testG() {
        System.out.printf("%g%n", 0.0);  // 0.00000
        System.out.printf("%g%n", 0.01); // 0.0100000
        System.out.printf("%g%n", 0.1);  // 0.100000

        System.out.printf("%g%n", 1.0);   // 1.00000
        System.out.printf("%g%n", 10.0);  // 10.0000
        System.out.printf("%g%n", 100.0); // 100.000

        System.out.printf("%g%n", Math.PI); // 3.14159
    }

    public static void testH() {
        // char
        System.out.printf("%h%n", 'a'); // 61
        System.out.printf("%h%n", 'A'); // 41

        // String
        System.out.printf("%h%n", "A"); // 41

        // Object
        System.out.printf("%h%n", new Object()); // 7d4991ad
    }

    public static void testO() {
        System.out.printf("%o%n", 0); // 0
        System.out.printf("%o%n", 1); // 1
        System.out.printf("%o%n", 7); // 7
        System.out.printf("%o%n", 8); // 10

        System.out.printf("%o%n", 15); // 17
        System.out.printf("%o%n", 16); // 20

        System.out.printf("%o%n", 64); // 100
        System.out.printf("%o%n", 65); // 101

        System.out.printf("%o%n", 255); // 377
        System.out.printf("%o%n", 256); // 400
    }

    public static void testS() {
        // Integer
        System.out.printf("%s%n", 0);
        System.out.printf("%s%n", 1);

        // char
        System.out.printf("%s%n", 'a');
        System.out.printf("%s%n", 'A');
        System.out.printf("%s%n", '宋');

        // String
        System.out.printf("%s%n", "不知不觉，就会心生懈(XIE)怠(DAI)");
    }

    public static void testT() {
        System.out.printf("%tF%n", new Date()); // 2018-11-19
        System.out.printf("%tT%n", new Date()); // 18:44:59
    }

    public static void testX() {
        System.out.printf("%x%n", 0);  // 0
        System.out.printf("%x%n", 1);  // 1
        System.out.printf("%x%n", 10); // a
        System.out.printf("%x%n", 11); // b
        System.out.printf("%x%n", 12); // c
        System.out.printf("%x%n", 13); // d
        System.out.printf("%x%n", 14); // e
        System.out.printf("%x%n", 15); // f
        System.out.printf("%x%n", 16); // 10
    }
}
