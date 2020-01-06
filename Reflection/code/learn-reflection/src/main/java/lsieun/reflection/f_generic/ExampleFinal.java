package lsieun.reflection.f_generic;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

public class ExampleFinal<T extends Number & Serializable & Cloneable, U extends Number, V extends Serializable, W> {
    // Class
    private int intValue;
    private Long longValue;
    private Serializable s;
    private int[] int_array;
    private TimeUnit timeUnit;

    // Type Variable
    private T t; // Class + Interface
    private U u; // Class
    private V v; // Interface
    private W w; // None

    // Parameterized Type
    private Comparable<Integer> cmp;
    private Function<T, U> func;
    private List<String> str_list;
    private List<T> t_list;
    private Map<String, Date> map;
    private Map.Entry<String, Date> entry;

    // WildcardType
    private List<?> unbouned_wildcard_list;
    private List<? extends Number> upper_bouned_wildcard_list;
    private List<? super Number> lower_bouned_wildcard_list;
    private Map<? extends Number, ? super String> wildcard_map;

    // Generic Array
    private T[] t_Array;
    private List<T>[] list_t_array;
    private List<String>[] list_str_array;
    private List<Date>[] list_date_array;
    private List<? extends Number>[] list_upper_array;
    private List<? super Number>[] list_lower_array;
    private List<?>[] list_wildcard_array;
}
