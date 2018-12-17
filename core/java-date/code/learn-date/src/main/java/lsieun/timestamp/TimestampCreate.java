package lsieun.timestamp;

import java.sql.Timestamp;

public class TimestampCreate {
    public static void main(String[] args) {
        String text = "2015-05-28 12:45:59";
        Timestamp timestamp = Timestamp.valueOf(text);
        System.out.println(timestamp);

        long l = System.currentTimeMillis();
        timestamp = new Timestamp(l);
        System.out.println(timestamp);
    }
}
