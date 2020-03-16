package lsieun.utils.logging;

import java.util.logging.Level;
import java.util.logging.Logger;

public class A_Logger_03_Parameter {
    private static final Logger logger = Logger.getLogger(A_Logger_03_Parameter.class.getName());

    public static void main(String[] args) {
        // 一个参数
        logger.log(Level.SEVERE, "Hello logging: {0} ", "P1");

        // 一个数组参数
        logger.log(Level.SEVERE, "Hello logging: {0}, {1}",
                new Object[] {"P1", "P2"});

        // 一个Throwable参数
        logger.log(Level.SEVERE, "Hello logging",
                new RuntimeException("Error"));
    }
}
