# MessageDigest

To calculate cryptographic hashing value in Java, `MessageDigest` Class is used, under the package `java.security`.

`MessagDigest` Class provides following cryptographic hash function to find hash value of a text, they are:

- (1) MD5
- (2) SHA-1
- (3) SHA-256

This Algorithms are initialize in `static` method called `getInstance()`. After selecting the algorithm it calculate the `digest` value and return the results in **byte array**.