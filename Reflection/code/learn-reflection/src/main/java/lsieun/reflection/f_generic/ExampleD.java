package lsieun.reflection.f_generic;

import java.util.List;

public class ExampleD<T> {
    //Generic array type
    private T[] value;
    private List<String>[] list;

    //Not a generic array type
    private List<String> singleList;
    private T singleValue;
}
