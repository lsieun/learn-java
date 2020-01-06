package lsieun.reflection.f_generic;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

public class ExampleE {
    public void testWildcardType(
            List<? extends OutputStream> numberList,
            List<? super InputStream> upperList,
            List<Integer> list,
            InputStream inputStream) {
    }
}
