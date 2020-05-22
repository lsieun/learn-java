package lsieun.hash;

@FunctionalInterface
public interface HashAlgorithm {
    byte[] apply(byte[] input, int len);
}
