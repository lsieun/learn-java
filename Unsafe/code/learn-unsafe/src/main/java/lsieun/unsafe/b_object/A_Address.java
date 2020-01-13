package lsieun.unsafe.b_object;

import lsieun.unsafe.utils.UnsafeUtils;
import sun.misc.Unsafe;

public class A_Address {
    public static void main(String[] args) {
        showLong();
    }

    public static void showBytes() {
        try {
            Unsafe unsafe = UnsafeUtils.getUnsafe();

            // Writing to a memory - MAX VALUE Byte
            byte value = Byte.MAX_VALUE;
            long bytes = 1;
            // Allocate given memory size
            long memoryAddress = unsafe.allocateMemory(bytes);
            // Write value to the allocated memory
            unsafe.putAddress(memoryAddress, value); // or putByte

            // Output the value written and the memory address
            System.out.println("[Byte] Writing " + value + " under the " + memoryAddress + " address.");

            long readValue = unsafe.getAddress(memoryAddress); // or getByte

            // Output the value from
            System.out.println("[Byte] Reading " + readValue + " from the " + memoryAddress + " address.");

            // C style! Release the Kraken... Memory!! :)
            unsafe.freeMemory(memoryAddress);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void showLong() {
        try {
            Unsafe unsafe = UnsafeUtils.getUnsafe();

            // Writing to a memory - MAX VALUE of Long
            long value = Long.MAX_VALUE;
            long bytes = Long.SIZE;
            // Allocate given memory size
            long memoryAddress = unsafe.allocateMemory(bytes);
            // Write value to the allocated memory
            unsafe.putLong(memoryAddress, value);

            // Output the value written and the memory address
            System.out.println("[Long] Writing " + value + " under the " + memoryAddress + " address.");

            // Read the value from the memory
            long readValue = unsafe.getLong(memoryAddress);

            // Output the value from
            System.out.println("[Long] Reading " + readValue + " from the " + memoryAddress + " address.");

            // C style! Release the Kraken... Memory!! :)
            unsafe.freeMemory(memoryAddress);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
