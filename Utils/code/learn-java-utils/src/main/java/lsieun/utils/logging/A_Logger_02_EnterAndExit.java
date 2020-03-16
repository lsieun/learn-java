package lsieun.utils.logging;

import java.util.logging.Level;
import java.util.logging.Logger;

public class A_Logger_02_EnterAndExit {
    private static final Logger logger = Logger.getLogger(A_Logger_02_EnterAndExit.class.getName());

    public void doIt() {
        Logger rootLogger = Logger.getLogger("");
        rootLogger.setLevel(Level.ALL);
        rootLogger.getHandlers()[0].setLevel(Level.ALL);

        logger.entering(getClass().getName(), "doIt");

        try {
            //... something that can throw an exception
            logger.log(Level.INFO, "Hello World");
            throw new RuntimeException("Just Exit");
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error doing XYZ", e);
        }

        logger.exiting(getClass().getName(), "doIt");
    }

    public static void main(String[] args) {
        A_Logger_02_EnterAndExit instance = new A_Logger_02_EnterAndExit();
        instance.doIt();
    }
}
