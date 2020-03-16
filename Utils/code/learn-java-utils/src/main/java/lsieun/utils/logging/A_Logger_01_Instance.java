package lsieun.utils.logging;

import java.util.logging.Logger;

public class A_Logger_01_Instance {
    private static final Logger LOGGER = Logger.getLogger(A_Logger_01_Instance.class.getName());

    public static void main(String[] args) {
        System.out.println("main method starts");
        LOGGER.info("info message");
    }
}
