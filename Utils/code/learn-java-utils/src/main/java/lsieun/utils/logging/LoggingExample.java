package lsieun.utils.logging;

import lsieun.utils.logging.custom.MyFilter;
import lsieun.utils.logging.custom.MyFormatter;
import lsieun.utils.logging.custom.MyHandler;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.logging.*;

public class LoggingExample {
    private static Logger logger = Logger.getLogger(LoggingExample.class.getName());
    private static final String BASE_PATH = LoggingExample.class.getClassLoader().getResource(".").getPath();

    public static void main(String[] args) {
        try {
            String config_path = BASE_PATH + "mylogging.properties";
            LogManager.getLogManager().readConfiguration(new FileInputStream(config_path));
        } catch (SecurityException | IOException e1) {
            e1.printStackTrace();
        }
//        logger.setLevel(Level.FINE);
//        logger.addHandler(new ConsoleHandler());
        //adding custom handler
//        logger.addHandler(new MyHandler());
        try {
            //FileHandler file name with max size and number of log files limit
            String log_path = BASE_PATH + "logger.log";
            Handler fileHandler = new FileHandler(log_path, 2000, 5);
            fileHandler.setFormatter(new MyFormatter());
            //setting custom filter for FileHandler
            fileHandler.setFilter(new MyFilter());
//            logger.addHandler(fileHandler);

            for(int i=0; i<1000; i++){
                //logging messages
                logger.log(Level.INFO, "Msg"+i);
            }
            logger.log(Level.CONFIG, "Config data");
        } catch (SecurityException | IOException e) {
            e.printStackTrace();
        }
    }
}
