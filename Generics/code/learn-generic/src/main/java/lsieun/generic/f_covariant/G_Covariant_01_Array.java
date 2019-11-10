package lsieun.generic.f_covariant;

public class G_Covariant_01_Array {
    public static void main(String[] args) {
        String[] words = {"Hello", "World!"};
        Object[] objects = words;
        // Oh, dear, runtime error
        objects[0] = new Integer(42);
    }
}
