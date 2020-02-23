package lsieun.format;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateAndTimeFormatting {
    public static void main(String[] args) {
        testZ();
    }

    public static void testA() {
        System.out.printf("%tA%n", getDate("2018-11-11 12:00:00")); // Sunday
        System.out.printf("%TA%n", getDate("2018-11-11 12:00:00")); // SUNDAY
        System.out.printf("%ta%n", getDate("2018-11-11 12:00:00")); // Sun
        System.out.printf("%Ta%n", getDate("2018-11-11 12:00:00")); // SUN
    }

    public static void testB() {
        System.out.printf("%tB%n", getDate("2018-11-11 12:00:00")); // November
        System.out.printf("%TB%n", getDate("2018-11-11 12:00:00")); // NOVEMBER
        System.out.printf("%tb%n", getDate("2018-11-11 12:00:00")); // Nov
        System.out.printf("%Tb%n", getDate("2018-11-11 12:00:00")); // NOV
    }

    public static void testC() {
        System.out.printf("%tC%n", getDate("2018-11-11 12:00:00")); // 20
        System.out.printf("%TC%n", getDate("2018-11-11 12:00:00")); // 20
        System.out.printf("%tc%n", getDate("2018-11-11 12:00:00")); // Sun Nov 11 12:00:00 CST 2018
        System.out.printf("%Tc%n", getDate("2018-11-11 12:00:00")); // SUN NOV 11 12:00:00 CST 2018
    }

    public static void testDE() {
        System.out.printf("%tD%n", getDate("2018-08-09 12:00:00")); // 08/09/18
        System.out.printf("%TD%n", getDate("2018-08-09 12:00:00")); // 08/09/18

        // d和e的差别
        System.out.printf("%td%n", getDate("2018-08-09 12:00:00")); // 09
        System.out.printf("%te%n", getDate("2018-08-09 12:00:00")); // 9
    }

    public static void testF() {
        System.out.printf("%tF%n", getDate("2018-08-09 12:00:00")); // 2018-08-09
    }

    public static void testH() {
        System.out.printf("%tH%n", getDate("2018-08-09 13:00:00")); // 13
        System.out.printf("%th%n", getDate("2018-08-09 13:00:00")); // Aug
    }

    public static void testIL() {
        System.out.printf("%tI%n", getDate("2018-08-09 13:00:00")); // 01
        System.out.printf("%tj%n", getDate("2018-01-10 13:00:00")); // 010

        System.out.printf("%tk%n", getDate("2018-08-09 07:00:00")); // 7
        System.out.printf("%tk%n", getDate("2018-08-09 13:00:00")); // 13

        System.out.printf("%tl%n", getDate("2018-08-09 07:00:00")); // 7
        System.out.printf("%tl%n", getDate("2018-08-09 13:00:00")); // 1
    }

    public static void testM() {
        System.out.printf("%tM%n", getDate("2018-08-09 07:06:00")); // 06

        System.out.printf("%tm%n", getDate("2018-08-09 07:06:00")); // 08
    }

    public static void testNQ() {
        System.out.printf("%tN%n", getDate("2018-11-20 12:00:00")); // 000000000
        System.out.printf("%tN%n", new Date()); // 007000000

        System.out.printf("%tp%n", getDate("2018-11-20 11:00:00")); // am
        System.out.printf("%tp%n", getDate("2018-11-20 12:00:00")); // pm
        System.out.printf("%tp%n", getDate("2018-11-20 13:00:00")); // pm
        System.out.printf("%tp%n", getDate("2018-11-20 24:00:00")); // am

        System.out.printf("%tQ%n", getDate("1970-01-01 08:00:01")); // 1000
    }

    public static void testR() {
        System.out.printf("%tR%n", getDate("2018-11-20 09:09:00")); // 09:09
        System.out.printf("%tr%n", getDate("2018-11-20 11:00:00")); // 11:00:00 AM
        System.out.printf("%tr%n", getDate("2018-11-20 13:00:00")); // 01:00:00 PM
    }

    public static void testS() {
        System.out.printf("%tS%n", getDate("1970-01-01 08:00:09")); // 09
        System.out.printf("%ts%n", getDate("1970-01-01 08:00:09")); // 9
    }

    public static void testT() {
        System.out.printf("%tT%n", getDate("1970-01-01 08:00:09")); // 08:00:09
    }

    public static void testY() {
        System.out.printf("%tY%n", getDate("1970-01-01 12:00:00")); // 1970
        System.out.printf("%ty%n", getDate("1970-01-01 12:00:00")); // 70
    }

    public static void testZ() {
        // CST可视为美国、澳大利亚、古巴或中国的标准时间。
        // CST可以为如下4个不同的时区的缩写：
        //     美国中部时间：Central Standard Time (USA) UT-6:00
        //     古巴标准时间：Cuba Standard Time UT-4:00
        //     中国标准时间：China Standard Time UT+8:00
        //     澳大利亚中部时间：Central Standard Time (Australia) UT+9:30

        System.out.printf("%tZ%n", new Date()); // CST
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
