package lsieun.crypto.hash;

@FunctionalInterface
public interface HashAlgorithm {
    byte[] apply(byte[] input);
}
