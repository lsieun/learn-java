package lsieun.format;

public class Padding {
    public static void main(String[] args) {
        testPadLeftSpaces();
        testPadLeftZeros();
        testPadRightSpaces();
        testPadRightZeros();
    }

    public static void testPadLeftSpaces() {
        System.out.println("左侧填充（空格）：");
        System.out.println("|" + padLeftSpaces("mystring", 10) + "|");
        System.out.println("|" + padLeftSpaces("mystring", 15) + "|");
        System.out.println("|" + padLeftSpaces("mystring", 20) + "|");
        System.out.println();
    }

    public static void testPadLeftZeros() {
        System.out.println("左侧填充（0）：");
        System.out.println("|" + padLeftZeros("mystring", 10) + "|");
        System.out.println("|" + padLeftZeros("mystring", 15) + "|");
        System.out.println("|" + padLeftZeros("mystring", 20) + "|");
        System.out.println();
    }

    public static String padLeftSpaces(String str, int n) {
        return String.format("%1$" + n + "s", str);
    }

    public static String padLeftZeros(String str, int n) {
        return String.format("%1$" + n + "s", str).replace(' ', '0');
    }

    public static void testPadRightSpaces() {
        System.out.println("右侧填充（空格）：");
        System.out.println("|" + padRightSpaces("mystring", 10) + "|");
        System.out.println("|" + padRightSpaces("mystring", 15) + "|");
        System.out.println("|" + padRightSpaces("mystring", 20) + "|");
        System.out.println();
    }

    public static void testPadRightZeros() {
        System.out.println("右侧填充（0）：");
        System.out.println("|" + padRightZeros("mystring", 10) + "|");
        System.out.println("|" + padRightZeros("mystring", 15) + "|");
        System.out.println("|" + padRightZeros("mystring", 20) + "|");
        System.out.println();
    }

    public static String padRightSpaces(String str, int num) {
        return String.format("%1$-" + num + "s", str);
    }

    public static String padRightZeros(String str, int num) {
        return String.format("%1$-" + num + "s", str).replace(' ', '0');
    }
}
