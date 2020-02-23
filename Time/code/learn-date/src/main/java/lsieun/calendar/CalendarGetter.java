package lsieun.calendar;

import java.util.Calendar;

public class CalendarGetter {
    public static void main(String[] args) {
        Calendar cal = Calendar.getInstance();   // GregorianCalendar
        // Print Calendar's field
        System.out.println("Year  : " + cal.get(Calendar.YEAR));
        System.out.println("Month : " + cal.get(Calendar.MONTH));
        System.out.println("Day of Month : " + cal.get(Calendar.DAY_OF_MONTH));
        System.out.println("Day of Week  : " + cal.get(Calendar.DAY_OF_WEEK));
        System.out.println("Day of Year  : " + cal.get(Calendar.DAY_OF_YEAR));
        System.out.println("Week of Year : " + cal.get(Calendar.WEEK_OF_YEAR));
        System.out.println("Week of Month : " + cal.get(Calendar.WEEK_OF_MONTH));
        System.out.println("Day of the Week in Month : " + cal.get(Calendar.DAY_OF_WEEK_IN_MONTH));
        System.out.println("Hour  : " + cal.get(Calendar.HOUR));
        System.out.println("AM PM : " + cal.get(Calendar.AM_PM));
        System.out.println("Hour of the Day : " + cal.get(Calendar.HOUR_OF_DAY));
        System.out.println("Minute : " + cal.get(Calendar.MINUTE));
        System.out.println("Second : " + cal.get(Calendar.SECOND));
        System.out.println();
    }
}
