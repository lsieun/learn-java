package lsieun.calendar;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class DayOfTheWeek {
    public static void main(String[] args) {

        Calendar cal = new GregorianCalendar();

        int month = cal.get(Calendar.MONTH) + 1 ;
        int day = cal.get(Calendar.DAY_OF_MONTH);
        int year = cal.get(Calendar.YEAR);

        System.out.println( "Today: " + month + "/" + day + "/" + year );

        String[] days = new String[]{
                "Sunday", "Monday", "Tuesday", "Wednesday",
                "Thusday", "Friday", "Saturday" };

        System.out.println("Day of week: " +
                days[cal.get(Calendar.DAY_OF_WEEK) - 1] );

    }
}
