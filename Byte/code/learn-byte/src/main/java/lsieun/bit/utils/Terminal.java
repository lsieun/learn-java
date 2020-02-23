package lsieun.bit.utils;

import java.util.ArrayList;
import java.util.List;

public class Terminal {
    private static final String EOL = System.lineSeparator();
    private static final String EMPTY = "";

    public static void printBinaryOperation(int a, int b, BitOp op) {
        // calculate result
        int result = ByteUtils.calculate(a, b, op);

        // print info
        Format f = new Format(a, b);
        int num_len = f.num_len;
        String format = f.template;

        List<Pair> list = new ArrayList<>();
        list.add(new Pair(a, ByteUtils.toBinary(a, num_len)));
        list.add(new Pair(b, ByteUtils.toBinary(b, num_len)));
        list.add(new Pair(op.val, op.val));
        list.add(new Pair(result, ByteUtils.toBinary(result, num_len)));

        String message = constructMessage(list, format);
        System.out.println(message);
    }

    public static void printUnaryOperator(int a, BitOp op) {
        // calculate result
        int b = ByteUtils.calculate(a, op);

        // print info
        Format f = new Format(a, b);
        int num_len = f.num_len;
        String format = f.template;

        List<Pair> list = new ArrayList<>();
        list.add(new Pair(a, ByteUtils.toBinary(a, num_len)));
        list.add(new Pair(op.val, op.val));
        list.add(new Pair(b, ByteUtils.toBinary(b, num_len)));

        String message = constructMessage(list, format);
        System.out.println(message);
    }

    public static String constructMessage(List<Pair> list, String format) {
        if (list == null || list.size() < 1) return EMPTY;

        StringBuilder sb = new StringBuilder();
        for (Pair p : list) {
            String line = String.format(format, p.first, p.second);
            sb.append(line).append(EOL);
        }
        return sb.toString();
    }
}
