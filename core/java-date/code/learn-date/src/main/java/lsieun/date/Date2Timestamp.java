package lsieun.date;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Date2Timestamp {
    public static void main(String[] args) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("mm/dd/yyyy hh:mm:ss");
        String dateString = "10/15/2015 09:30:45";
        Date date = sdf.parse(dateString);
        System.out.println("Date: " + date);

        Timestamp timestamp = new Timestamp(date.getTime());
        System.out.println("Timestamp: " + timestamp);
    }
}
