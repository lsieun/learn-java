package lsieun.utils.logging;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Nose {
    // Obtain a suitable logger.
    private static Logger logger = Logger.getLogger(Nose.class.getName());
    public static void main(String argv[]) {
        System.out.println(logger);
        System.out.println(logger.getParent());
        System.out.println(logger.getParent().getParent());
        logger.setLevel(Level.ALL);
        // Log a FINE tracing message
        logger.fine("doing stuff");
        try {
        } catch (Exception ex) {
            // Log the exception
            logger.log(Level.WARNING, "trouble sneezing", ex);
        }
        logger.fine("done");
    }
}
