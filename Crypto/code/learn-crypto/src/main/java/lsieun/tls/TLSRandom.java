package lsieun.tls;

import lsieun.utils.ByteUtils;

import java.util.Random;

public class TLSRandom {
    public final int gmt_unix_time;
    public final byte[] random_bytes;

    public TLSRandom(int gmt_unix_time) {
        this.gmt_unix_time = gmt_unix_time;
        this.random_bytes = new byte[28];
        long timestamp = System.currentTimeMillis();
        Random rand = new Random(timestamp);
        rand.nextBytes(random_bytes);
    }

    public TLSRandom(int gmt_unix_time, byte[] random_bytes) {
        this.gmt_unix_time = gmt_unix_time;
        this.random_bytes = random_bytes;
    }

    public byte[] toBytes() {
        byte[] timestamp_bytes = new byte[4];
        timestamp_bytes[0] = (byte) ((gmt_unix_time >> 24) & 0xFF);
        timestamp_bytes[1] = (byte) ((gmt_unix_time >> 16) & 0xFF);
        timestamp_bytes[2] = (byte) ((gmt_unix_time >> 8) & 0xFF);
        timestamp_bytes[3] = (byte) ((gmt_unix_time) & 0xFF);
        return ByteUtils.concatenate(timestamp_bytes, random_bytes);
    }
}
