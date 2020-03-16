package lsieun.utils.logging;

import java.util.logging.Logger;

public class AppClass {
    private static final Logger LOGGER = Logger.getLogger(AppClass.class.getName());

    public void doSomething(){
        LOGGER.info("in AppClass");
    }
}
