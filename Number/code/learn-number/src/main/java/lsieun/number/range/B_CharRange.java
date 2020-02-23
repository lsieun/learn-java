package lsieun.number.range;

public class B_CharRange {
    public static void main(String[] args) {
        char minValue = Character.MIN_VALUE;// 0
        char maxValue = Character.MAX_VALUE;// 65535

        System.out.println("Character Size: " + Character.SIZE);
        System.out.println("Character Bytes: " + Character.BYTES);
        System.out.println("Char Min Value: " + (int)minValue);
        System.out.println("Char Max Value: " + (int)maxValue);
    }
}
