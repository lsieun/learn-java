package lsieun.bit.c_use;

public class B_Add_0X100 {
    private static final String FORMAT = "|%6s|%6s|";

    public static void main(String[] args) {
        add0X100();
    }

    public static void add0X100() {
        String title = String.format(FORMAT, "before", "after");
        System.out.println(title);

        int[] array = new int[] {0, 15, 16, 255};
        for (int item : array) {
            print(item);
        }
    }

    public static void print(int value) {
        String before = Integer.toString(value, 16);
        String after = Integer.toString(value + 0x100, 16);
        String line = String.format(FORMAT, before, after);
        System.out.println(line);
    }
}
