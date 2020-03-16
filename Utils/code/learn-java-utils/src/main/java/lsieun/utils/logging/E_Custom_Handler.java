package lsieun.utils.logging;

import lsieun.utils.logging.custom.MyLogHandler;

import java.util.logging.LogManager;
import java.util.logging.Logger;

public class E_Custom_Handler {
    public static void main(String[] args) {
        LogManager logManager = LogManager.getLogManager();
        //reset() will remove all default handlers
        logManager.reset();
        Logger rootLogger = logManager.getLogger("");

        rootLogger.addHandler(new MyLogHandler());
        AppClass appClass = new AppClass();
        appClass.doSomething();
        rootLogger.info("some message");
    }
}
