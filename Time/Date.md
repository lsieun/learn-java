# Date


```java
package java.util;

public class Date implements java.io.Serializable, Cloneable, Comparable<Date> {

    private transient long fastTime;

    private transient BaseCalendar.Date cdate;


    public Date() {
        this(System.currentTimeMillis());
    }

    public Date(long date) {
        fastTime = date;
    }

    public long getTime() {
        return getTimeImpl();
    }

    private final long getTimeImpl() {
        if (cdate != null && !cdate.isNormalized()) {
            normalize();
        }
        return fastTime;
    }

    public void setTime(long time) {
        fastTime = time;
        cdate = null;
    }


}

```









