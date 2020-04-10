package lsieun.hash.hmac;

import lsieun.hash.md5.MD5Utils;
import lsieun.hash.sha1.SHA1Utils;
import lsieun.hash.sha256.SHA256Utils;

import java.util.Arrays;

public class MACUtils {
    public static byte[] hmac_sha256(byte[] key_bytes, byte[] message_bytes) {
        int block_size = 64;
        int key_length = key_bytes.length;
        byte[] standard_key_bytes;
        if (key_length > block_size) {
            standard_key_bytes = SHA256Utils.sha256_hash(key_bytes, key_length);
        }
        else if (key_length < block_size) {
            standard_key_bytes = new byte[block_size];
            Arrays.fill(standard_key_bytes, (byte) 0);
            System.arraycopy(key_bytes, 0, standard_key_bytes, 0, key_length);
        }
        else {
            standard_key_bytes = new byte[block_size];
            System.arraycopy(key_bytes, 0, standard_key_bytes, 0, key_length);
        }

        byte[] o_key_pad = new byte[block_size];
        Arrays.fill(o_key_pad, (byte) 0x5c);
        xor(o_key_pad, standard_key_bytes, block_size);

        byte[] i_key_pad = new byte[block_size];
        Arrays.fill(i_key_pad, (byte) 0x36);
        xor(i_key_pad, standard_key_bytes, block_size);

        byte[] merge_bytes1 = merge_bytes(i_key_pad, message_bytes);
        byte[] digest_bytes1 = SHA256Utils.sha256_hash(merge_bytes1, merge_bytes1.length);
        byte[] merge_bytes2 = merge_bytes(o_key_pad, digest_bytes1);
        byte[] digest_bytes2 = SHA256Utils.sha256_hash(merge_bytes2, merge_bytes2.length);
        return digest_bytes2;
    }

    public static byte[] hmac_sha1(byte[] key_bytes, byte[] message_bytes) {
        int block_size = 64;
        int key_length = key_bytes.length;
        byte[] standard_key_bytes;
        if (key_length > block_size) {
            standard_key_bytes = SHA1Utils.sha1_hash(key_bytes, key_length);
        }
        else if (key_length < block_size) {
            standard_key_bytes = new byte[block_size];
            Arrays.fill(standard_key_bytes, (byte) 0);
            System.arraycopy(key_bytes, 0, standard_key_bytes, 0, key_length);
        }
        else {
            standard_key_bytes = new byte[block_size];
            System.arraycopy(key_bytes, 0, standard_key_bytes, 0, key_length);
        }

        byte[] o_key_pad = new byte[block_size];
        Arrays.fill(o_key_pad, (byte) 0x5c);
        xor(o_key_pad, standard_key_bytes, block_size);

        byte[] i_key_pad = new byte[block_size];
        Arrays.fill(i_key_pad, (byte) 0x36);
        xor(i_key_pad, standard_key_bytes, block_size);

        byte[] merge_bytes1 = merge_bytes(i_key_pad, message_bytes);
        byte[] digest_bytes1 = SHA1Utils.sha1_hash(merge_bytes1, merge_bytes1.length);
        byte[] merge_bytes2 = merge_bytes(o_key_pad, digest_bytes1);
        byte[] digest_bytes2 = SHA1Utils.sha1_hash(merge_bytes2, merge_bytes2.length);
        return digest_bytes2;
    }

    public static byte[] hmac_md5(byte[] key_bytes, byte[] message_bytes) {
        int block_size = 64;
        int key_length = key_bytes.length;
        byte[] standard_key_bytes;
        if (key_length > block_size) {
            standard_key_bytes = MD5Utils.md5_hash(key_bytes, key_length);
        }
        else if (key_length < block_size) {
            standard_key_bytes = new byte[block_size];
            Arrays.fill(standard_key_bytes, (byte) 0);
            System.arraycopy(key_bytes, 0, standard_key_bytes, 0, key_length);
        }
        else {
            standard_key_bytes = new byte[block_size];
            System.arraycopy(key_bytes, 0, standard_key_bytes, 0, key_length);
        }

        byte[] o_key_pad = new byte[block_size];
        Arrays.fill(o_key_pad, (byte) 0x5c);
        xor(o_key_pad, standard_key_bytes, block_size);

        byte[] i_key_pad = new byte[block_size];
        Arrays.fill(i_key_pad, (byte) 0x36);
        xor(i_key_pad, standard_key_bytes, block_size);

        byte[] merge_bytes1 = merge_bytes(i_key_pad, message_bytes);
        byte[] digest_bytes1 = MD5Utils.md5_hash(merge_bytes1, merge_bytes1.length);
        byte[] merge_bytes2 = merge_bytes(o_key_pad, digest_bytes1);
        byte[] digest_bytes2 = MD5Utils.md5_hash(merge_bytes2, merge_bytes2.length);
        return digest_bytes2;
    }

    public static void xor(byte[] dest_bytes, byte[] src_bytes, int len) {
        for (int i=0;i<len;i++) {
            dest_bytes[i] = (byte)((dest_bytes[i] & 0xFF) ^ (src_bytes[i] & 0xFF));
        }
    }

    public static byte[] merge_bytes(byte[] bytes1, byte[] bytes2) {
        int byte1_length = bytes1.length;
        int byte2_length = bytes2.length;
        int total_length = byte1_length + byte2_length;
        byte[] result_bytes = new byte[total_length];
        System.arraycopy(bytes1, 0, result_bytes, 0, byte1_length);
        System.arraycopy(bytes2, 0, result_bytes, byte1_length, byte2_length);
        return result_bytes;
    }
}
