package lsieun.crypto.des;

import lsieun.crypto.des.b_tutorial.DESMsg;
import lsieun.utils.ByteUtils;

import java.util.ArrayList;
import java.util.List;

public class D {
    public static void main(String[] args) {
        byte[][] subKeys = DESCipherTest.getTestSubkeys();
        byte[] theMsg = DESCipherTest.getTestMsg();

        List<byte[]> sub_key_48_bit_list = new ArrayList<>();
        for (byte[] bytes : subKeys) {
            sub_key_48_bit_list.add(bytes);
        }

        byte[] final_bytes = getFinalBytes(theMsg, sub_key_48_bit_list);
        System.out.println(ByteUtils.toBinary(final_bytes));
    }

    public static byte[] getFinalBytes(byte[] content_64_bit_bytes, List<byte[]> sub_key_48_bit_list) {
        byte[] permuted_msg_64_bit_bytes = DESUtils.permute(content_64_bit_bytes, DESConst.ip_table);
        System.out.println(ByteUtils.toBinary(permuted_msg_64_bit_bytes));

        List<byte[]> list = DESMsg.process(permuted_msg_64_bit_bytes, sub_key_48_bit_list);

        byte[] the_last_swap_bytes = DESMsg.swap(list.get(15));
        System.out.println("After Swap: \n" + ByteUtils.toBinary(the_last_swap_bytes));

        byte[] final_bytes = DESUtils.permute(the_last_swap_bytes, DESConst.fp_table);
        System.out.println("After Final: \n" + ByteUtils.toBinary(final_bytes));
        return final_bytes;
    }
}
