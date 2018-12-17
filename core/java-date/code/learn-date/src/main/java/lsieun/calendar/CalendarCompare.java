package lsieun.calendar;

import java.util.Calendar;

public class CalendarCompare {
    public static void main(String[] args) {

        Calendar c1 = Calendar.getInstance();
        Calendar c2 = Calendar.getInstance();

        c2.set(Calendar.YEAR, 2020);

        System.out.println("c1 year: " + (c1.get(Calendar.YEAR)));
        System.out.println("c2 year: " + (c2.get(Calendar.YEAR)));

        System.out.println("c1 after c2: " + c1.after(c2));
        System.out.println("c1 before c2: " + c1.before(c2));
    }
}
