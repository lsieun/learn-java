package lsieun.timezone;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class DifferentTimeZoneDates {
    public static void main(String[] args) {

        TimeZone tz = TimeZone.getTimeZone("EST");
        Calendar cal = Calendar.getInstance(tz);
        cal.set(Calendar.MONTH, 11); //December
        cal.set(Calendar.DATE, 31);
        cal.set(Calendar.YEAR, 2013);
        cal.set(Calendar.HOUR,23);
        cal.set(Calendar.MINUTE,45);
        cal.set(Calendar.SECOND,52);

        Date date = cal.getTime();

        SimpleDateFormat sdf = new SimpleDateFormat("zzz yyyy-MM-dd HH:mm:ss");

        System.out.println(sdf.format(date));

        sdf.setTimeZone(TimeZone.getTimeZone("Asia/Tokyo"));
        System.out.println(sdf.format(date));

        sdf.setTimeZone(TimeZone.getTimeZone("Europe/Luxembourg"));
        System.out.println(sdf.format(date));

    }
}
