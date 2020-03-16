package lsieun.utils.logging;

import java.io.IOException;
import java.util.logging.*;

public class B {
    public static void main(String[] args) {
        try {
            SimpleFormatter sf = new SimpleFormatter();
            Handler fh = new FileHandler("%t/wombat.log");
            fh.setFormatter(sf);
            Logger.getLogger("").addHandler(fh);
            Logger.getLogger("com.wombat").setLevel(Level.FINEST);
            Logger.getLogger("com.wombat").fine("Hello World");
            System.out.println(Logger.getLogger("").getLevel());
            System.out.println(Logger.getLogger("com.wombat").getLevel());
            System.out.println(fh.getLevel());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
