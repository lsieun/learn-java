package lsieun.timestamp;

import java.sql.Timestamp;
import java.util.Date;

public class TimestampCompare {
    public static void main(String[] args) {

        Timestamp timestamp1 = new Timestamp(new Date().getTime());
        System.out.println(timestamp1);

        Timestamp timestamp2 = new Timestamp(new Date().getTime());
        System.out.println(timestamp2);

        // Test if timestamp1 is after timestamp2
        if(timestamp1.after(timestamp2)){
            System.out.println("timestamp1 is after timestamp2");
        }

        // Test if timestamp1 is before timestamp2
        if(timestamp1.before(timestamp2)){
            System.out.println("timestamp1 is before timestamp2");
        }

        timestamp1 = new Timestamp(new Date().getTime());
        timestamp2 = new Timestamp(timestamp1.getTime());

        // Test if timestamp1 is the same time as timestamp2
        if(timestamp1.equals(timestamp2)){
            System.out.println("timestamp1 equals timestamp2");
        }
    }
}
