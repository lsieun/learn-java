package lsieun.crypto.hash;

@FunctionalInterface
public interface HashContextAlgorithm {
    DigestCtx get();
}
