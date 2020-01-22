package lsieun.except.unchecked;

import java.util.Date;

public class G_NoClassDefFoundError {
    public static void main(String[] args) {
//        System.out.println(new Date());
//        ClassWithInitErrors instance;
//        try {
//            instance = new ClassWithInitErrors();
//        }
//        catch (Throwable th) {
//            // th.printStackTrace();
//            System.out.println("Throwable");
//        }
//        instance = new ClassWithInitErrors();

        try {
            Class.forName("lsieun.except.unchecked.ClassWithInitErrors");
        } catch (Throwable t) {
            //
        }

        ClassWithInitErrors instance = new ClassWithInitErrors();
    }
}
