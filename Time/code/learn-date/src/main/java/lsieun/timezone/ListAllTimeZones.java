package lsieun.timezone;

import java.util.TimeZone;

public class ListAllTimeZones {
    public static void main(String[] args) {
        for (String str: TimeZone.getAvailableIDs()) {
            System.out.println(str);
        }
    }
}
