package lsieun.timestamp;

import java.sql.Timestamp;
import java.text.ParseException;
import java.util.Date;

public class Timestamp2Date {
    public static void main(String[] args) throws ParseException {

        String text = "2015-05-28 12:45:59";
        Timestamp timestamp = Timestamp.valueOf(text);
        System.out.println("Timestamp: " + timestamp);

        Date date = new Date(timestamp.getTime());
        System.out.println("Date: " + date);

    }
}
