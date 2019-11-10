package lsieun.generic.f_covariant;

import java.util.ArrayList;
import java.util.List;

public class G_Covariant_02_Collection {
    public static void main(String[] args) {
        List<String> strList = new ArrayList<>();
        strList.add("Hello");
        strList.add("World");

        // Won't compile
        //List<Object> objList = strList;
    }
}
