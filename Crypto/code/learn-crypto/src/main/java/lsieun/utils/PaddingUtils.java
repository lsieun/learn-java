package lsieun.utils;

// TODO: 实现不同的padding
public class PaddingUtils {

    public static byte[] add_PKCS5Padding(byte[] input_bytes, int block_size) {
        int length = input_bytes.length;
        int padding_len = block_size - (length % block_size);
        byte[] output_bytes = new byte[length + padding_len];

        // This implements PKCS #5 padding.
        System.arraycopy(input_bytes, 0, output_bytes, 0, length);
        for (int i = 0; i < padding_len; i++) {
            output_bytes[length + i] = (byte) padding_len;
        }
        return output_bytes;
    }

    public static byte[] remove_PKCS5Padding(byte[] input_bytes) {
        // calculate padding length
        int length = input_bytes.length;
        int padded_len = input_bytes[length - 1];
        int remain_len = length - padded_len;

        // remove PKCS #5 padding.
        byte[] output_bytes = new byte[remain_len];
        System.arraycopy(input_bytes, 0, output_bytes, 0, remain_len);
        return output_bytes;
    }
}
