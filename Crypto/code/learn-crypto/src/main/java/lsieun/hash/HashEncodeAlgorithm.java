package lsieun.hash;

@FunctionalInterface
public interface HashEncodeAlgorithm {
    byte[] encode(int[] hash);
}
