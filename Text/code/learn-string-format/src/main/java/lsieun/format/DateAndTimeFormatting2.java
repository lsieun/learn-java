package lsieun.format;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateAndTimeFormatting2 {
    public static void main(String[] args) {
        System.out.println("===Composite");
        testComposite();
        System.out.println();

        System.out.println("===Year");
        testYear();
        System.out.println();

        System.out.println("===Month");
        testMonth();
        System.out.println();

        System.out.println("===Day");
        testDay();
        System.out.println();

        System.out.println("===Hour");
        testHour();
        System.out.println();

        System.out.println("===Minute");
        testMinute();
        System.out.println();

        System.out.println("===Second");
        testSecond();
        System.out.println();


        System.out.println("===Week");
        testWeek();
        System.out.println();

        System.out.println("===Other");
        testOther();
        System.out.println();

    }

    public static void testComposite() {
        // Date and time formatted with “%ta %tb %td %tT %tZ %tY” e.g. “Fri Feb 17 07:45:42 PST 2017“
        System.out.printf("%tc%n", getDate("2018-11-11 12:00:00")); // Sun Nov 11 12:00:00 CST 2018
        // Date formatted as “%tm/%td/%ty“
        System.out.printf("%tD%n", getDate("2018-08-09 12:00:00")); // 08/09/18
        // ISO 8601 formatted date with “%tY-%tm-%td“.
        System.out.printf("%tF%n", getDate("2018-08-09 12:00:00")); // 2018-08-09


        // Time formatted as 24-hours e.g. “%tH:%tM“.
        System.out.printf("%tR%n", getDate("2018-11-20 09:09:00")); // 09:09
        // Time formatted as 12-hours e.g. “%tI:%tM:%tS %Tp“.
        System.out.printf("%tr%n", getDate("2018-11-20 11:00:00")); // 11:00:00 AM
        System.out.printf("%tr%n", getDate("2018-11-20 13:00:00")); // 01:00:00 PM

        // Time formatted as 24-hours e.g. “%tH:%tM:%tS“.
        System.out.printf("%tT%n", getDate("1970-01-01 08:00:09")); // 08:00:09
        System.out.printf("%tT%n", getDate("1970-01-01 13:00:09")); // 13:00:09
    }

    public static void testYear() {
        // Century part of year formatted with two digits e.g. “00” through “99”.
        System.out.printf("%tC%n", getDate("2018-11-11 12:00:00")); // 20
        System.out.printf("%tC%n", getDate("1970-01-01 12:00:00")); // 19

        // Year formatted with 4 digits e.g. “0000” to “9999“.
        System.out.printf("%tY%n", getDate("1970-01-01 12:00:00")); // 1970
        // Year formatted with 2 digits e.g. “00” to “99“.
        System.out.printf("%ty%n", getDate("1970-01-01 12:00:00")); // 70
    }

    public static void testMonth() {
        // Full name of the month e.g. “January“, “February“, etc.
        System.out.printf("%tB%n", getDate("2018-11-11 12:00:00")); // November
        // Abbreviated month name e.g. “Jan“, “Feb“, etc.
        System.out.printf("%tb%n", getDate("2018-11-11 12:00:00")); // Nov
        // Same as %tb.
        System.out.printf("%th%n", getDate("2018-08-09 13:00:00")); // Aug

        // Month formatted with a leading 0 e.g. “01” to “12“.
        System.out.printf("%tm%n", getDate("2018-08-09 07:06:00")); // 08
    }

    public static void testDay() {
        // Day of the month formatted with two digits. e.g. “01” to “31“.
        System.out.printf("%td%n", getDate("2018-08-09 12:00:00")); // 09
        // Day of the month formatted without a leading 0 e.g. “1” to “31”.
        System.out.printf("%te%n", getDate("2018-08-09 12:00:00")); // 9

        // Day of the year formatted with leading 0s e.g. “001” to “366“.
        System.out.printf("%tj%n", getDate("2018-01-10 13:00:00")); // 010
    }

    public static void testHour() {
        // Hour of the day for the 24-hour clock e.g. “00” to “23“.
        System.out.printf("%tH%n", getDate("2018-08-09 13:00:00")); // 13

        // Hour of the day for the 12-hour clock e.g. “01” – “12“.
        System.out.printf("%tI%n", getDate("2018-08-09 09:00:00")); // 09
        System.out.printf("%tI%n", getDate("2018-08-09 13:00:00")); // 01

        // Hour of the day for the 24 hour clock without a leading 0 e.g. “0” to “23“.
        System.out.printf("%tk%n", getDate("2018-08-09 07:00:00")); // 7
        System.out.printf("%tk%n", getDate("2018-08-09 13:00:00")); // 13

        // Hour of the day for the 12-hour click without a leading 0 e.g. “1” to “12“.
        System.out.printf("%tl%n", getDate("2018-08-09 07:00:00")); // 7
        System.out.printf("%tl%n", getDate("2018-08-09 13:00:00")); // 1
    }

    public static void testMinute() {
        // Minute within the hour formatted a leading 0 e.g. “00” to “59“.
        System.out.printf("%tM%n", getDate("2018-08-09 07:06:00")); // 06
    }

    public static void testSecond() {
        // Milliseconds since epoch Jan 1 , 1970 00:00:00 UTC.
        System.out.printf("%tQ%n", getDate("1970-01-01 08:00:01")); // 1000

        // Seconds within the minute formatted with 2 digits e.g. “00” to “60”.
        System.out.printf("%tS%n", getDate("1970-01-01 08:00:09")); // 09

        // Seconds since the epoch Jan 1, 1970 00:00:00 UTC.
        System.out.printf("%ts%n", getDate("1970-01-01 08:00:09")); // 9
    }

    public static void testWeek() {
        // Full name of the day of the week, e.g. “Sunday“, “Monday“
        System.out.printf("%tA%n", getDate("2018-11-11 12:00:00")); // Sunday
        // Abbreviated name of the week day e.g. “Sun“, “Mon“, etc.
        System.out.printf("%ta%n", getDate("2018-11-11 12:00:00")); // Sun
    }

    public static void testOther() {
        // Nanosecond formatted with 9 digits and leading 0s e.g. “000000000” to “999999999”.
        System.out.printf("%tN%n", getDate("2018-11-20 12:00:00")); // 000000000
        System.out.printf("%tN%n", new Date()); // 变化的值

        // Locale specific “am” or “pm” marker.
        System.out.printf("%tp%n", getDate("2018-11-20 11:00:00")); // am
        System.out.printf("%tp%n", getDate("2018-11-20 12:00:00")); // pm
        System.out.printf("%tp%n", getDate("2018-11-20 13:00:00")); // pm
        System.out.printf("%tp%n", getDate("2018-11-20 24:00:00")); // am

        // Time zone abbreviation. e.g. “UTC“, “PST“, etc.
        System.out.printf("%tZ%n", new Date()); // CST
        // Time Zone Offset from GMT e.g. “-0800“.
        System.out.printf("%tz%n", new Date()); // +0800
    }



    public static Date getDate(String str) {
        SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = null;
        try {
            date = fmt.parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }
}
