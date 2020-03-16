package lsieun.sys;

import java.util.Date;

/**
 * <p>
 *     There’re two methods related to time in System.
 *     One is <code>currentTimeMillis</code> and the other is <code>nanoTime</code>.
 * </p>
 * <br/>
 *
 * <p>
 *     <code>currentTimeMillis</code> returns the number of milliseconds passed
 *     since the Unix Epoch, which is January 1, 1970 12:00 AM UTC
 * </p>
 * <br/>
 *
 * <p>
 *     <code>nanoTime</code> returns the time relative to JVM startup.
 *     We can call it multiple times to mark the passage of time in the application
 * </p>
 * <br/>
 *
 * <b>Note that</b> since <code>nanoTime</code> is so fine-grained,
 * it’s safer to do <code>endTime – startTime < 10000</code> than <code>endTime < startTime</code>
 * due to the possibility of numerical overflow.
 */
public class TimeObserve {
    public static void main(String[] args) {
        getNowPlusOneHour();
        getDate();
        testNanoTime();
    }

    private static void getNowPlusOneHour() {
        long value = System.currentTimeMillis() + 3600 * 1000L;
        System.out.println(value);
    }

    private static void getDate() {
        String str = new Date(System.currentTimeMillis()).toString();
        System.out.println(str);
    }

    private static void testNanoTime() {
        long startTime = System.nanoTime();
        try {
            Thread.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        long endTime = System.nanoTime();
        long diff = endTime - startTime;
        System.out.println(diff);
    }
}
