package lsieun.crypto.x509;

import lsieun.utils.DateUtils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ValidityPeriod {
    public final Date notBefore;
    public final Date notAfter;

    public ValidityPeriod(Date notBefore, Date notAfter) {
        this.notBefore = notBefore;
        this.notAfter = notAfter;
    }

    @Override
    public String toString() {
        return "ValidityPeriod {" +
                "notBefore='" + DateUtils.format(notBefore) + "'" +
                ", notAfter='" + DateUtils.format(notAfter) + "'" +
                '}';
    }
}
