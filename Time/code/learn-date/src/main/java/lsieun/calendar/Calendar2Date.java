package lsieun.calendar;

import java.util.Calendar;
import java.util.Date;

public class Calendar2Date {
    public static void main(String[] args) {
        Calendar cal = Calendar.getInstance();   // GregorianCalendar
        System.out.println("Calendar's toString() is : " + cal + "\n");
        System.out.println("Time zone is: " + cal.getTimeZone() + "\n");

        // An Easier way to print the timestamp by getting a Date instance
        Date date = cal.getTime();
        System.out.println("Current date and time in Date's toString() is : " + date + "\n");
    }
}
