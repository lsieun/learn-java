package lsieun.utils.logging;

import java.lang.reflect.Field;
import java.util.Comparator;
import java.util.Set;
import java.util.TreeSet;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;

public class B_Log_Level_Program {
    private static Logger log = Logger.getLogger(B_Log_Level_Program.class.getName());

    static {
        System.setProperty("java.util.logging.SimpleFormatter.format",
                "[%1$tF %1$tT %1$tL] [%4$-7s] %5$s %n");
    }

    public static void main(String[] args) throws Exception {
        setLevel(Level.ALL);
        Set<Level> levels = LevelUtils.getAllLevels();
        int i = 1;
        for (Level level : levels) {
            log.log(level, level.getName() + " - " + (i++));
        }
    }

    public static void setLevel(Level targetLevel) {
        // （1）设置Logger的Level
        Logger root = Logger.getLogger("");
        root.setLevel(targetLevel);
        // （2）设置Handler的Level
        for (Handler handler : root.getHandlers()) {
            handler.setLevel(targetLevel);
        }
        System.out.println("level set: " + targetLevel.getName());
    }
}
