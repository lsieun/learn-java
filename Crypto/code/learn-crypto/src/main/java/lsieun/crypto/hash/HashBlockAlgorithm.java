package lsieun.crypto.hash;

@FunctionalInterface
public interface HashBlockAlgorithm {
    void block_operate(byte[] input, int[] hash);
}