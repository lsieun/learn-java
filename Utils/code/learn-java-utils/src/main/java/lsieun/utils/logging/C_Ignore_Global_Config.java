package lsieun.utils.logging;

import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

public class C_Ignore_Global_Config {
    private static Logger logger = Logger.getLogger("com.wombat.nose");

    public static void main(String argv[]) {

        try {
            FileHandler fh = new FileHandler("mylog.txt");
            // Send logger output to our FileHandler.
            logger.addHandler(fh);
            // Request that every detail gets logged.
            logger.setLevel(Level.ALL);
            // Log a simple INFO message.
            logger.info("doing stuff");
        } catch (Exception ex) {
            logger.log(Level.WARNING, "trouble sneezing", ex);
        }
        logger.fine("done");
    }
}
