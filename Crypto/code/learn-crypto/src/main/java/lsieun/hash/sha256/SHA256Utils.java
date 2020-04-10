package lsieun.hash.sha256;

import java.util.Arrays;

@SuppressWarnings("Duplicates")
public class SHA256Utils {
    public static int ch(int x, int y, int z) {
        return (x & y) ^ (~x & z);
    }

    public static int maj(int x, int y, int z) {
        return (x & y) ^ (x & z) ^ (y & z);
    }

    public static int rotr(int x, int n) {
        return (x >>> n) | ((x) << (32 - n));
    }

    public static int shr(int x, int n) {
        return x >>> n;
    }

    public static int sigma_rot(int x, int i) {
        return rotr(x, i != 0 ? 6 : 2) ^ rotr(x, i != 0 ? 11 : 13) ^ rotr(x, i != 0 ? 25 : 22);
    }

    public static int sigma_shr(int x, int i) {
        return rotr(x, i != 0 ? 17 : 7) ^ rotr(x, i != 0 ? 19 : 18) ^ shr(x, i != 0 ? 10 : 3);
    }

    public static void sha256_block_operate(byte[] block, int[] hash) {
        int[] W = new int[64];
        for (int t = 0; t < 64; t++) {
            if (t < 16) {
                W[t] = ((block[(t * 4)] & 0xFF) << 24) |
                        ((block[(t * 4) + 1] & 0xFF) << 16) |
                        ((block[(t * 4) + 2] & 0xFF) << 8) |
                        (block[(t * 4) + 3] & 0xFF);
            } else {
                W[t] = sigma_shr(W[t - 2], 1) +
                        W[t - 7] +
                        sigma_shr(W[t - 15], 0) +
                        W[t - 16];
            }
        }

        int a = hash[0];
        int b = hash[1];
        int c = hash[2];
        int d = hash[3];
        int e = hash[4];
        int f = hash[5];
        int g = hash[6];
        int h = hash[7];

        for (int t = 0; t < 64; t++) {
            int temp1 = h + sigma_rot(e, 1) + ch(e, f, g) + SHA256Const.k[t] + W[t];
            int temp2 = sigma_rot(a, 0) + maj(a, b, c);

            h = g;
            g = f;
            f = e;
            e = d + temp1;
            d = c;
            c = b;
            b = a;
            a = temp1 + temp2;

        }

        hash[0] += a;
        hash[1] += b;
        hash[2] += c;
        hash[3] += d;
        hash[4] += e;
        hash[5] += f;
        hash[6] += g;
        hash[7] += h;
    }

    public static byte[] sha256_hash(byte[] input, int len) {
        int[] hash = new int[SHA256Const.SHA256_RESULT_SIZE];
        int length_in_bits = len * 8;

        hash[0] = SHA256Const.sha256_initial_hash[0];
        hash[1] = SHA256Const.sha256_initial_hash[1];
        hash[2] = SHA256Const.sha256_initial_hash[2];
        hash[3] = SHA256Const.sha256_initial_hash[3];
        hash[4] = SHA256Const.sha256_initial_hash[4];
        hash[5] = SHA256Const.sha256_initial_hash[5];
        hash[6] = SHA256Const.sha256_initial_hash[6];
        hash[7] = SHA256Const.sha256_initial_hash[7];

        int quotient = len / SHA256Const.SHA256_BLOCK_SIZE;
        int remainder = len % SHA256Const.SHA256_BLOCK_SIZE;
        byte[] input_block = new byte[SHA256Const.SHA256_BLOCK_SIZE];
        for (int i = 0; i < quotient; i++) {
            Arrays.fill(input_block, (byte) 0);
            System.arraycopy(input, i * SHA256Const.SHA256_BLOCK_SIZE, input_block, 0, SHA256Const.SHA256_BLOCK_SIZE);
            sha256_block_operate(input_block, hash);
        }

        if (remainder >= SHA256Const.SHA256_INPUT_BLOCK_SIZE) {
            Arrays.fill(input_block, (byte) 0);
            System.arraycopy(input, quotient * SHA256Const.SHA256_BLOCK_SIZE, input_block, 0, remainder);
            input_block[remainder] = (byte) 0x80;
            sha256_block_operate(input_block, hash);

            Arrays.fill(input_block, (byte) 0);
            fill_length_in_bits(input_block, length_in_bits);
            sha256_block_operate(input_block, hash);
        } else {
            Arrays.fill(input_block, (byte) 0);
            System.arraycopy(input, quotient * SHA256Const.SHA256_BLOCK_SIZE, input_block, 0, remainder);
            input_block[remainder] = (byte) 0x80;
            fill_length_in_bits(input_block, length_in_bits);
            sha256_block_operate(input_block, hash);
        }

        return encode(hash);
    }

    public static void fill_length_in_bits(byte[] input_block, int length_in_bits) {
        input_block[SHA256Const.SHA256_BLOCK_SIZE - 4] = (byte) ((length_in_bits & 0xFF000000) >> 24);
        input_block[SHA256Const.SHA256_BLOCK_SIZE - 3] = (byte) ((length_in_bits & 0x00FF0000) >> 16);
        input_block[SHA256Const.SHA256_BLOCK_SIZE - 2] = (byte) ((length_in_bits & 0x0000FF00) >> 8);
        input_block[SHA256Const.SHA256_BLOCK_SIZE - 1] = (byte) (length_in_bits & 0x000000FF);
    }

    public static byte[] encode(int[] hash) {
        int size = hash.length * 4;
        byte[] bytes = new byte[size];

        for (int i = 0; i < size; i += 4) {
            int quotient = i / 4;
            bytes[i + 0] = (byte) ((hash[quotient] >>> 24) & 0xff);
            bytes[i + 1] = (byte) ((hash[quotient] >>> 16) & 0xff);
            bytes[i + 2] = (byte) ((hash[quotient] >>> 8) & 0xff);
            bytes[i + 3] = (byte) (hash[quotient] & 0xff);
        }

        return bytes;
    }
}
