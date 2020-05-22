package lsieun.hash;

@FunctionalInterface
public interface HashFinalizeAlgorithm {
    void block_finalize(byte[] padded_block, long length_in_bits);
}
