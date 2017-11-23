package com.lsieun.base64;

import java.util.Base64;

public class C {
    public static void main(String[] args) throws Exception{
        String originalUrl = "https://www.google.co.nz/?gfe_rd=cr&ei=dzbFV&gws_rd=ssl#q=java";
        String encodedUrl = Base64.getUrlEncoder().encodeToString(originalUrl.getBytes());
        System.out.println(encodedUrl);
        byte[] decodedBytes = Base64.getUrlDecoder().decode(encodedUrl);
        String decodedUrl = new String(decodedBytes);
        System.out.println(originalUrl);
        System.out.println(decodedUrl);
    }
}
