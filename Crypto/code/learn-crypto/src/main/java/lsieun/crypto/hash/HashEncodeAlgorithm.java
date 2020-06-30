package lsieun.crypto.hash;

@FunctionalInterface
public interface HashEncodeAlgorithm {
    byte[] encode(int[] hash);
}
