package lsieun.timezone;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class GetTimeGMT {
    public static void main(String[] args) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy MMM dd HH:mm:ss zzz");

        Date date = new Date();

        System.out.println("Local Time: " + sdf.format(date));

        sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
        System.out.println("GMT Time  : " + sdf.format(date));
    }
}
