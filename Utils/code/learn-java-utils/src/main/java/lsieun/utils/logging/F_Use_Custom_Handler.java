package lsieun.utils.logging;

import lsieun.utils.logging.custom.MyLogger;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class F_Use_Custom_Handler {
    // use the classname for the logger, this way you can refactor
    private final static Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);


    public void doSomeThingAndLog() {
        // ... more code

        // now we demo the logging

        // set the LogLevel to Severe, only severe Messages will be written
        LOGGER.setLevel(Level.SEVERE);
        LOGGER.severe("Severe Log 1");
        LOGGER.warning("Warning Log 1");
        LOGGER.info("Info Log 1");
        LOGGER.finest("Really not important 1");

        // set the LogLevel to Info, severe, warning and info will be written
        // finest is still not written
        LOGGER.setLevel(Level.INFO);
        LOGGER.severe("Severe Log 2");
        LOGGER.warning("Warning Log 2");
        LOGGER.info("Info Log 2");
        LOGGER.finest("Really not important 2");
    }

    public static void main(String[] args) {
        F_Use_Custom_Handler tester = new F_Use_Custom_Handler();
        try {
            MyLogger.setup();
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Problems with creating the log files");
        }
        tester.doSomeThingAndLog();
    }
}
