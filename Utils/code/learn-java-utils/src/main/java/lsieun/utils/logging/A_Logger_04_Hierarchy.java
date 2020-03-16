package lsieun.utils.logging;

import java.util.logging.Logger;

public class A_Logger_04_Hierarchy {
    public static void main(String[] args) {
        Logger logger = Logger.getLogger("");
        Logger logger1 = Logger.getLogger("com");
        Logger logger2 = Logger.getLogger("com.example");
        Logger logger3 = Logger.getLogger("com.example.www");

        if (
                logger3.getParent() == logger2 ||
                        logger2.getParent() == logger1 ||
                        logger1.getParent() == logger
        ) {
            System.out.println("Logger Hierarchy");
        }
    }
}
