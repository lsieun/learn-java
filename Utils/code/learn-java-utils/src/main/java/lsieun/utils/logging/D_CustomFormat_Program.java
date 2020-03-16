package lsieun.utils.logging;

import java.util.Date;
import java.util.logging.ConsoleHandler;
import java.util.logging.LogRecord;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class D_CustomFormat_Program {
    private static Logger LOGGER = null;

    static {
        Class<?> clazz = D_CustomFormat_Program.class;
        String fqcn = clazz.getName();
        int index = fqcn.lastIndexOf('.');
        String package_name = fqcn.substring(0, index);

        Logger mainLogger = Logger.getLogger(package_name);
        mainLogger.setUseParentHandlers(false);
        ConsoleHandler handler = new ConsoleHandler();
        handler.setFormatter(new SimpleFormatter() {
            private static final String format = "[%1$tF %1$tT] [%2$-7s] %3$s %n";

            @Override
            public synchronized String format(LogRecord lr) {
                return String.format(format,
                        new Date(lr.getMillis()),
                        lr.getLevel().getLocalizedName(),
                        lr.getMessage()
                );
            }
        });
        mainLogger.addHandler(handler);
        LOGGER = Logger.getLogger(fqcn);
    }


    public static void main(String[] args) {
        System.out.println("-- main method starts --");
        LOGGER.info("This is an info message");
        LOGGER.warning("This is a warning message");
    }
}
