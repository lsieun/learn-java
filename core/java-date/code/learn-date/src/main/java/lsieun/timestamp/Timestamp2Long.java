package lsieun.timestamp;

import java.sql.Timestamp;

public class Timestamp2Long {
    public static void main(String[] args) {
        long l = System.currentTimeMillis();
        Timestamp timestamp = new Timestamp(l);
        System.out.println(timestamp);
        System.out.println(l);
        System.out.println(timestamp.getTime());
    }
}
