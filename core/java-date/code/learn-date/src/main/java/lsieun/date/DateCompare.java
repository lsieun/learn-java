package lsieun.date;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateCompare {
    public static void main(String[] args) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date date1 = sdf.parse("2015-01-01");
            Date date2 = sdf.parse("2015-12-25");

            System.out.println("Date1: " + sdf.format(date1));
            System.out.println("Date2: " + sdf.format(date2));

            if( date1.compareTo(date2) > 0 ) {
                System.out.println("Date1 is after Date2");
            } else if( date1.compareTo(date2) < 0 ) {
                System.out.println("Date1 is before Date2");
            } else if( date1.compareTo(date2) == 0 ) {
                System.out.println("Date1 is same as Date2");
            }

            long diff = date2.getTime() - date1.getTime();
            int days = (int) (diff / (1000*60*60*24));
            System.out.println("Difference: " + days + " days.");


        } catch(ParseException ex) {
        }
    }
}
