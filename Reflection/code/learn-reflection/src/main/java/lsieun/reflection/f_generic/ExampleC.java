package lsieun.reflection.f_generic;

import java.io.Serializable;

public class ExampleC<@ReadOnly K extends Number & Serializable & Cloneable, T> {
    //K has a specified upper boundary Number
    K key;
    //T does not specify an upper boundary, which defaults to Object
    T value;
}
