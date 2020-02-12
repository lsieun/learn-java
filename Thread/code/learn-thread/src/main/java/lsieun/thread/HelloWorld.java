package lsieun.thread;

import java.io.*;

public class HelloWorld {
    public static final int CHAR_NUM = 5120;
    public static final int SLEEP_NANO_NUM = 10;

    public static void testWriter(Writer writer) {
        Runnable r1 = () -> {
            while (true) {
                try {
                    writer.write("AAAAA_AAAAA\n");
                } catch (IOException e) {
                    e.printStackTrace();
                    break;
                }
            }
        };

        Runnable r2 = () -> {
            while (true) {
                try {
                    writer.write("BBBBB_BBBBB\n");
                } catch (IOException e) {
                    e.printStackTrace();
                    break;
                }
            }
        };

        Thread t1 = new Thread(r1);
        Thread t2 = new Thread(r2);

        t1.start();
        t2.start();
    }

    public static void testStream(OutputStream out) {
        Runnable r1 = () -> {
            byte[] bytes = new byte[CHAR_NUM];
            for (int i = 0; i < (CHAR_NUM-1); i++) {
                bytes[i] = 'A';
            }
            bytes[CHAR_NUM-1] = '\n';
            while (true) {
                try {
                    long start = System.currentTimeMillis();
                    out.write(bytes);
                    long end = System.currentTimeMillis();
                    System.out.println("range: " + (end- start));
                    Thread.sleep(0L, SLEEP_NANO_NUM);
                } catch (Exception e) {
                    break;
                }
            }
        };

        Runnable r2 = () -> {
            byte[] bytes = new byte[CHAR_NUM];
            for (int i = 0; i < (CHAR_NUM-1); i++) {
                bytes[i] = 'B';
            }
            bytes[CHAR_NUM-1] = '\n';
            while (true) {
                try {
                    out.write(bytes);
                    Thread.sleep(0L, SLEEP_NANO_NUM);
                } catch (Exception e) {
                    break;
                }
            }
        };

        Thread t1 = new Thread(r1);
        Thread t2 = new Thread(r2);

        t1.start();
        t2.start();
    }

    public static void main(String[] args) {
        String dir_path = System.getProperty("user.dir");
        String filepath = String.format("%s/target/test-file.txt", dir_path);
        System.out.println("file://" + filepath);

        try (
                OutputStream out = new FileOutputStream(filepath);
//                OutputStreamWriter writer = new OutputStreamWriter(out)
        ) {
//            testWriter(writer);
            testStream(out);
            Thread.sleep(10);
        } catch (Exception ex) {
            System.exit(0);
        }


    }
}
