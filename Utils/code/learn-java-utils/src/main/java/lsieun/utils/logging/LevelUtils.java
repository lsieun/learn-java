package lsieun.utils.logging;

import java.lang.reflect.Field;
import java.util.Comparator;
import java.util.Set;
import java.util.TreeSet;
import java.util.logging.Level;

public class LevelUtils {
    public static Set<Level> getAllLevels() throws IllegalAccessException {
        Class<Level> levelClass = Level.class;

        Set<Level> allLevels = new TreeSet<>(Comparator.comparingInt(Level::intValue));

        for (Field field : levelClass.getDeclaredFields()) {
            if (field.getType() == Level.class) {
                allLevels.add((Level) field.get(null));
            }
        }
        return allLevels;
    }
}
