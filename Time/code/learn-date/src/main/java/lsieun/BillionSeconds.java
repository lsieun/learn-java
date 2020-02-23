package lsieun;

public class BillionSeconds {
    public static void main(String[] args) {

        int total = 1000000000;

        int second = 1;
        int minute = 60 * second;
        int hour = 60 * minute;
        int day = 24 * hour;
        int fortnight = 14 * day;
        int year = 365 * day;

        int count = total;

        System.out.println("Total seconds: " + total);

        int years = count / year;
        System.out.print(years + " years, " );

        count = count % year;

        int fortnights = count / fortnight;
        System.out.print(fortnights + " fortnights, " );

        count = count % fortnight;

        int days = count / day;
        System.out.print(days + " days, " );

        count = count % day;

        int hours = count / hour;
        System.out.print(hours + " hours, " );

        count = count % hour;

        int minutes = count / minute;
        System.out.print(minutes + " minutes, " );

        int seconds = count % minute;
        System.out.println(seconds + " seconds." );
    }
}
