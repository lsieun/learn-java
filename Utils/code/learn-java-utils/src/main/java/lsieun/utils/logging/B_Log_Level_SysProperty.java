package lsieun.utils.logging;

import java.lang.reflect.Field;
import java.util.Comparator;
import java.util.Set;
import java.util.TreeSet;
import java.util.logging.Level;
import java.util.logging.Logger;

public class B_Log_Level_SysProperty {
    private static Logger LOGGER;

    static {
        String path = B_Log_Level_SysProperty.class
                .getClassLoader()
                .getResource("level_logging.properties")
                .getFile();
        System.setProperty("java.util.logging.config.file", path);
        LOGGER = Logger.getLogger(B_Log_Level_SysProperty.class.getName());
    }

    public static void main(String[] args) throws Exception {
        Set<Level> levels = LevelUtils.getAllLevels();
        int i = 1;
        for (Level level : levels) {
            LOGGER.log(level, level.getName() + " - " + (i++));
        }
    }


}
