package com.lsieun.base64;


import java.util.Base64;

public class A {
    public static void main(String[] args) throws Exception{
        String CHARACTER_ENCODING = "UTF-8";
        //String originalInput = "我爱你";
        String originalInput = "我love你";
        String encodedString = Base64.getEncoder().encodeToString(originalInput.getBytes(CHARACTER_ENCODING));
        System.out.println(encodedString);
        System.out.println(encodedString.length());
        System.out.println(new String("abc").length());
        byte[] decodedBytes = Base64.getDecoder().decode(encodedString);
        String decodedString = new String(decodedBytes,CHARACTER_ENCODING);
        System.out.println(decodedString);
    }
}
