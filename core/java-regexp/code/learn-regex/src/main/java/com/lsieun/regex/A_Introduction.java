package com.lsieun.regex;

import org.junit.Test;

public class A_Introduction {

    @Test
    public void testIsNumer(){
        //只能输入数字
        String str = "124354232";
        char[] arr = str.toCharArray();
        boolean flag = true;
        for(int i = 0 ;  i< arr.length ; i++){
            if(!(arr[i]>=48&&arr[i]<=57)){
                flag = false;
            }
        }
        System.out.println(flag?"输入正确":"输出只能是数字");
    }

    @Test
    public void testIsNumerWithRegex(){
        //只能输入数字
        String str = "12435423a2";
        boolean flag = str.matches("[0-9]+");
        System.out.println(flag?"输入正确":"只能输入数字");
    }
}
