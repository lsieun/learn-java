package lsieun.timezone;

import java.time.ZoneId;
import java.util.TimeZone;

public class DefaultTimeZone {
    public static void main(String[] args) {
        TimeZone timeZone = TimeZone.getDefault();
        String id = timeZone.getID();
        String name = timeZone.getDisplayName();
        int offset = timeZone.getRawOffset();

        System.out.println("ID: " + id);
        System.out.println("Display Name: " + name);
        System.out.println("Offset: " + offset / (60 * 60 * 1000));
        System.out.println(timeZone);
    }
}
