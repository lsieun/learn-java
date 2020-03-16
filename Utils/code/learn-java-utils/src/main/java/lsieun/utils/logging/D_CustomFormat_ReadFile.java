package lsieun.utils.logging;

import java.io.IOException;
import java.io.InputStream;
import java.util.logging.LogManager;
import java.util.logging.Logger;

public class D_CustomFormat_ReadFile {
    private static Logger LOGGER = null;

    static {
        InputStream stream = D_CustomFormat_ReadFile.class.getClassLoader().
                getResourceAsStream("logging.properties");
        try {
            LogManager.getLogManager().readConfiguration(stream);
            LOGGER = Logger.getLogger(D_CustomFormat_ReadFile.class.getName());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        System.out.println("-- main method starts --");
        LOGGER.info("This is an info message");
        LOGGER.warning("This is a warning message");
    }
}
