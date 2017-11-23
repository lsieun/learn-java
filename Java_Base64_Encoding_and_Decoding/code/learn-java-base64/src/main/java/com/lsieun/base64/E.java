package com.lsieun.base64;

import org.apache.commons.codec.binary.Base64;

public class E {
    public static void main(String[] args) {
        String originalInput = "我love你";
        Base64 base64 = new Base64();
        String encodedString = new String(base64.encode(originalInput.getBytes()));
        String decodedString = new String(base64.decode(encodedString.getBytes()));
        System.out.println(originalInput);
        System.out.println(encodedString);
        System.out.println(decodedString);
    }
}
