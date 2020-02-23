package lsieun.date;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Date2Calendar {
    public static void main(String[] args) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("mm/dd/yyyy hh:mm:ss");
        String dateString = "10/15/2015 09:30:45";
        Date date = sdf.parse(dateString);
        System.out.println("Date: " + date);

        // Get current time
        Calendar calendar = Calendar.getInstance();
        System.out.println("Calendar current time: " + calendar.getTime());

        // Set calendar to the same time as date
        calendar.setTime(date);
        System.out.println("Calendar after change: " + calendar.getTime());
    }
}
