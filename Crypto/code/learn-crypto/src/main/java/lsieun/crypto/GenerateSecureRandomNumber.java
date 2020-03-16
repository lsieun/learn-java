package lsieun.crypto;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Arrays;

// https://examples.javacodegeeks.com/core-java/security/generate-a-secure-random-number-example/
public class GenerateSecureRandomNumber {
    public static void main(String[] args) {
        try {

            // Create a secure random number generator using the SHA1PRNG algorithm
            SecureRandom secureRandomGenerator = SecureRandom.getInstance("SHA1PRNG");

            // Get 128 random bytes
            byte[] randomBytes = new byte[128];
            System.out.println("randomBytes: " + Arrays.toString(randomBytes));
            secureRandomGenerator.nextBytes(randomBytes);
            System.out.println("randomBytes: " + Arrays.toString(randomBytes));

            // Create two secure number generators with the same seed
            int seedByteCount = 5;
            byte[] seed = secureRandomGenerator.generateSeed(seedByteCount);
            System.out.println("seed: " + Arrays.toString(seed));

            SecureRandom secureRandom1 = SecureRandom.getInstance("SHA1PRNG");
            secureRandom1.setSeed(seed);
            SecureRandom secureRandom2 = SecureRandom.getInstance("SHA1PRNG");
            secureRandom2.setSeed(seed);

        } catch (NoSuchAlgorithmException e) {
        }
    }
}
