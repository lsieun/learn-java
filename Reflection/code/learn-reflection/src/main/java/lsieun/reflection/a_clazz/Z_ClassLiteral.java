package lsieun.reflection.a_clazz;

public class Z_ClassLiteral {
    public static void main(String[] args) {
        // primitive types
        Class<Character> characterClass = char.class;
        Class<Short> shortClass = short.class;
        Class<Integer> intClass = int.class;
        Class<Long> longClass = long.class;
        Class<Float> floatClass = float.class;
        Class<Double> doubleClass = double.class;

        // array
        Class<int[]> int_array_class = int[].class;

        // int.class VS Integer.class
        System.out.println(int.class != Integer.class); // true
    }
}
