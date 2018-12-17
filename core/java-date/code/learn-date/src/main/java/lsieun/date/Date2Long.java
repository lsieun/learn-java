package lsieun.date;

import java.util.Date;

public class Date2Long {
    public static void main(String[] args) {
        Date now = new Date();
        long time = now.getTime();
        System.out.println(time);
        System.out.println(System.currentTimeMillis());
    }
}
