package lsieun.utils.logging;

import java.util.logging.Logger;

public class D_CustomFormat_SysProperty {
    private static Logger LOGGER = null;

    static {
        System.setProperty("java.util.logging.SimpleFormatter.format",
                "[%1$tF %1$tT] [%4$-7s] %5$s %n");
        LOGGER = Logger.getLogger(D_CustomFormat_SysProperty.class.getName());
    }

    public static void main(String[] args) {
        System.out.println("-- main method starts --");
        LOGGER.info("This is an info message");
        LOGGER.warning("This is a warning message");
    }
}
