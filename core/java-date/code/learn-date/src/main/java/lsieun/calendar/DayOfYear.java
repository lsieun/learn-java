package lsieun.calendar;

import java.util.Calendar;

public class DayOfYear {
    public static void main(String[] args) {
        Calendar c = Calendar.getInstance();

        // Jan=0, Feb=1, Mar=2 ... //
        c.set(2013,0,1);
        int doy = c.get(Calendar.DAY_OF_YEAR);
        System.out.println("01/01/2013 Day of Year: " + doy);

        c.set(2013,5,20);
        doy = c.get(Calendar.DAY_OF_YEAR);
        System.out.println("06/20/2013 Day of Year: " + doy);

        c.set(2013,11,31);
        doy = c.get(Calendar.DAY_OF_YEAR);
        System.out.println("12/31/2013 Day of Year: " + doy);

    }
}
