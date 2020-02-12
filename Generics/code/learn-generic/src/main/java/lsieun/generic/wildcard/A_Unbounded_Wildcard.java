package lsieun.generic.wildcard;

import java.util.List;

/**
 * Not only can’t you put any element (other than null ) into a List<?> ,
 * but you can’t assume anything about the type of the objects that you get out.
 */
public class A_Unbounded_Wildcard {
    public void test(List<?> list) {
//        list.add(Integer.valueOf(10));
        list.add(null);

        Object item = list.get(0);
    }
}
