package lsieun.timezone;

import java.util.Calendar;
import java.util.TimeZone;

public class GetTimeZone {
    public static void main(String[] args) {

        Calendar now = Calendar.getInstance();
        TimeZone tz = now.getTimeZone();
        System.out.println("Time Zone: " + tz.getDisplayName());

    }
}
