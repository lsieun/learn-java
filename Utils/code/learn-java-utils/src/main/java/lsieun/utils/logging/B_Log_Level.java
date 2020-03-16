package lsieun.utils.logging;

import java.util.logging.ConsoleHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

public class B_Log_Level {
    private static final Logger LOGGER = Logger.getLogger(B_Log_Level.class.getName());

    public static void main(String[] args) {
        Level current_level = Level.FINE;
        LOGGER.setLevel(current_level);
        LOGGER.setUseParentHandlers(false);

        ConsoleHandler handler = new ConsoleHandler();
        handler.setLevel(current_level);
        LOGGER.addHandler(handler);

        LOGGER.severe("severe message");
        LOGGER.warning("warning message");
        LOGGER.info("info message");
        LOGGER.config("config message");

        LOGGER.fine("fine message");
        LOGGER.finer("finer message");
        LOGGER.finest("finest message");
    }
}
