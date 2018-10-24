package com.lsieun.snake.util;


public class NumberUtil {

    public static Integer parseInt(String str, Integer defaultValue) {
        try {
            int num = Integer.parseInt(str);
            return num;
        }
        catch(NumberFormatException ex){
            return defaultValue;
        }
    }

    public static boolean equals(int a, int b) {
        Integer one = a;
        Integer another = b;
        if (one.equals(another)) return true;
        return false;
    }

}
