package lsieun.generic.f_covariant;

import java.util.ArrayList;
import java.util.List;

public class G_Covariant_04_Collection_ContraCovariant {
    public void test1() {
        List<? super Number> super_num_list = new ArrayList<>();
        List<? super Integer> super_int_list = super_num_list;
    }

    public void test2() {
        List<? super Number> super_num_list = new ArrayList<>();
        super_num_list.add(new Integer(1000));
    }

    public static void main(String[] args) {
        List<? super Number> super_num_list = new ArrayList<>();
        super_num_list.add(new Integer(1000));
        Object object = super_num_list.get(0);
        System.out.println(object);
    }
}
