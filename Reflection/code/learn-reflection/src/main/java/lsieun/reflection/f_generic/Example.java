package lsieun.reflection.f_generic;

import java.util.Date;
import java.util.List;

class Example<T> {
    private List<String> strList;
    private List<Date> dateList;
    private List<T> tList;

    public List<String> getStrList() {
        return strList;
    }

    public List<Date> getDateList() {
        return dateList;
    }
}
