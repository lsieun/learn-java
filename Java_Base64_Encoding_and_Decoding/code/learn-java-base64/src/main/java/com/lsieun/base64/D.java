package com.lsieun.base64;

import java.util.Base64;
import java.util.UUID;

public class D {
    public static void main(String[] args) {
        StringBuilder buffer = getMimeBuffer();
        System.out.println(buffer.toString());
        byte[] encodedAsBytes = buffer.toString().getBytes();
        String encodedMime = Base64.getMimeEncoder().encodeToString(encodedAsBytes);
        System.out.println(encodedMime);
        byte[] decodedBytes = Base64.getMimeDecoder().decode(encodedMime);
        String decodedMime = new String(decodedBytes);
        System.out.println(decodedMime);
    }

    private static StringBuilder getMimeBuffer() {
        StringBuilder buffer = new StringBuilder();
        for (int count = 0; count < 10; ++count) {
            buffer.append(UUID.randomUUID().toString());
        }
        return buffer;
    }
}
