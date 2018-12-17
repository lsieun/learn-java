package lsieun.calendar;

import java.util.Calendar;

public class CalendarSetter {
    public static void main(String[] args) {
        Calendar cal = Calendar.getInstance();   // GregorianCalendar
        System.out.println("Now: " + cal.getTime());

        // Manipulating Dates
        Calendar calTemp;
        calTemp = (Calendar) cal.clone();
        calTemp.add(Calendar.DAY_OF_YEAR, -365);
        System.out.println("365 days ago, it was: " + calTemp.getTime());

        calTemp = (Calendar) cal.clone();
        calTemp.add(Calendar.HOUR_OF_DAY, 11);
        System.out.println("After 11 hours, it will be: " + calTemp.getTime());

        // Roll
        calTemp = (Calendar) cal.clone();
        calTemp.roll(Calendar.HOUR_OF_DAY, 11);
        System.out.println("Roll 11 hours, it will be: " + calTemp.getTime());
        System.out.println();
    }
}
