package lsieun.timestamp;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;

public class AddTime {
    public static void main( String[] args ) {

        Timestamp timestamp = new Timestamp(new Date().getTime());
        System.out.println(timestamp);

        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(timestamp.getTime());

        // add 30 seconds
        cal.add(Calendar.SECOND, 30);
        timestamp = new Timestamp(cal.getTime().getTime());
        System.out.println(timestamp);

        // add 5 hours
        cal.setTimeInMillis(timestamp.getTime());
        cal.add(Calendar.HOUR, 5);
        timestamp = new Timestamp(cal.getTime().getTime());
        System.out.println(timestamp);

        // add 30 days
        cal.setTimeInMillis(timestamp.getTime());
        cal.add(Calendar.DAY_OF_MONTH, 30);
        timestamp = new Timestamp(cal.getTime().getTime());
        System.out.println(timestamp);

        // add 6 months
        cal.setTimeInMillis(timestamp.getTime());
        cal.add(Calendar.MONTH, 6);
        timestamp = new Timestamp(cal.getTime().getTime());
        System.out.println(timestamp);
    }
}
