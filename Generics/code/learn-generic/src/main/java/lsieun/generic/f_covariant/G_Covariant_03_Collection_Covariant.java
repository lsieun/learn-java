package lsieun.generic.f_covariant;

import java.util.ArrayList;
import java.util.List;

public class G_Covariant_03_Collection_Covariant {
    public void test1() {
        List<? extends Number> numList = new ArrayList<>();
        List<? extends Object> objList = new ArrayList<>();

        objList = numList;
    }

    public void test2() {
        List<Integer> intList = new ArrayList<>();
        intList.add(new Integer(1024));
        intList.add(new Integer(2048));

        List<? extends Number> numList = intList;

        List<? extends Object> objList = numList;
    }

}
