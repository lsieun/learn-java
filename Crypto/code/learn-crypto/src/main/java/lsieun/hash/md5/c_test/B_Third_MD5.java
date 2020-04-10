package lsieun.hash.md5.c_test;

import lsieun.hash.md5.A_MD5;
import lsieun.hash.md5.MD5Example;

public class B_Third_MD5 {
    public static void main(String[] args) {
        byte[] bytes = MD5Example.collision_str_2.getBytes();

        A_MD5 md5 = new A_MD5();
        md5.Update(bytes, bytes.length);
        System.out.println("Hash            : " + md5.asHex());
    }
}
