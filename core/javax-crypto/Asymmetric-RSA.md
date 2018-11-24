# RSA (cryptosystem)

`RSA` (**Rivest-Shamir-Adleman**) is one of the first **public-key cryptosystems** and is widely used for **secure data transmission**. In such a cryptosystem, **the encryption key** is **public** and it is different from **the decryption key** which is kept secret (**private**). 

> **public-key cryptosystems**，也就是非对称加密，详细见如下的英文。  
> **Public-key cryptography**, or **asymmetric cryptography**, is any cryptographic system that uses **pairs of keys**: **public keys** which may be disseminated(传播；散播；扩散) widely, and **private keys** which are known only to the owner. This accomplishes **two functions**: **authentication**, where **the public key** verifies that a holder of the paired private key sent the message, and **encryption**, where only the paired private key holder can decrypt the message encrypted with the public key.

In **RSA**, this asymmetry is based on the practical difficulty of the factorization of the product of two large prime numbers, the "factoring problem". 

> RSA所基于的原理： factoring problem  
> In number theory, **integer factorization** is the decomposition of a composite number into a product of smaller integers. If these integers are further restricted to prime numbers, the process is called **prime factorization**.  
> When the numbers are sufficiently large, no efficient, non-quantum integer factorization algorithm is known. 

The acronym **RSA** is made of the initial letters of the surnames(姓，姓氏) of **Ron Rivest**, **Adi Shamir**, and **Leonard Adleman**, who first publicly described the algorithm in 1978. 

> RSA名字的由来  

