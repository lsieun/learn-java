package lsieun.joda;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

public class CurrentDateTime {
    public static void main(String[] args) {
        DateTime dateTime = new DateTime();
        System.out.println(dateTime);

        DateTimeFormatter dtf = DateTimeFormat.forPattern("MM/dd/yyyy HH:mm:ss");
        System.out.println(dtf.print(dateTime));
    }
}
