package lsieun.joda;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

public class GetDateTime {
    public static void main(String[] args) {

        String dateString = "20/05/2015 12:45:30" ;
        DateTimeFormatter formatter = DateTimeFormat.forPattern("dd/MM/yyyy HH:mm:ss");
        DateTime dateTime = formatter.parseDateTime(dateString);
        System.out.println(dateTime);
    }
}
