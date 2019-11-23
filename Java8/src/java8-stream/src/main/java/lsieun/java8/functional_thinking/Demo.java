package lsieun.java8.functional_thinking;

import java.io.File;
import java.util.Arrays;

public class Demo {
    public static void main(String[] args) {
        File[] hiddenFiles = new File(".").listFiles(File::isHidden);
        System.out.println(Arrays.toString(hiddenFiles));
    }

}
