package lsieun.crypto.sym.des;

import lsieun.crypto.sym.des.b_tutorial.DESKey;
import lsieun.utils.ByteUtils;

import java.util.List;

public class E {
    public static void main(String[] args) {
        byte[] msg_64_bit_bytes = {'c', 'a', 'f', 'e', 'b', 'a', 'b', 'e'};
        byte[] key_64_bit_bytes = {'p', 'a', 's', 's', 'w', 'o', 'r', 'd'};

        byte[] key_56_bit_bytes = DESKey.from_64_to_56_bit_key_bytes(key_64_bit_bytes);

        // encryption
        List<byte[]> encryption_sub_key_56_bit_list = DESKey.roll_56_bit_key_bytes(key_56_bit_bytes, CipherType.ENCRYPT);
        List<byte[]> encryption_sub_key_48_bit_list = DESKey.from_56_to_48_bit_sub_key_list(encryption_sub_key_56_bit_list);
        byte[] encrypted_bytes = D.getFinalBytes(msg_64_bit_bytes, encryption_sub_key_48_bit_list);

//        List<byte[]> list = new ArrayList<>();
//        for (int i = 15; i >= 0; i--) {
//            list.add(encryption_sub_key_48_bit_list.get(i));
//        }
//
//        // decryption
//        List<byte[]> decryption_sub_key_56_bit_list = DESKey.roll_56_bit_key_bytes(key_56_bit_bytes, CipherType.DECRYPT);
//        List<byte[]> decryption_sub_key_48_bit_list = DESKey.from_56_to_48_bit_sub_key_list(decryption_sub_key_56_bit_list);
//        byte[] decrypted_bytes = D.getFinalBytes(encrypted_bytes, list);
//
//        System.out.println("=======");
        System.out.println(ByteUtils.toBinary(encrypted_bytes));
//        System.out.println(ByteUtils.toBinary(decrypted_bytes));
//        System.out.println(ByteUtils.toBinary(msg_64_bit_bytes));
    }
}
