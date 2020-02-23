package lsieun.nio.byte_order;

import java.nio.ByteOrder;

public class ByteOrderTest {
    public static void main(String[] args) {
        ByteOrder value = ByteOrder.nativeOrder();
        System.out.println(value);
    }
}
