package lsieun.reflection.f_generic;

import java.util.Date;
import java.util.List;
import java.util.Map;

class Example<T> {
    private List rawList;
    private List<String> strList;
    private List<Date> dateList;
    private List<T> tList;
    private Map<String, Object> map;
    private Map.Entry<String, Date> entry;

    public List<String> getStrList() {
        return strList;
    }

    public List<Date> getDateList() {
        return dateList;
    }
}
