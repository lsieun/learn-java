# RSA (cryptosystem)

URL:

- https://www.pagedon.com/rsa-explained-simply/programming

## 1. Introduction

### 1.1 RSA的名字由来

The acronym **RSA** is made of the initial letters of the surnames(姓，姓氏) of **Ron Rivest**, **Adi Shamir**, and **Leonard Adleman**, who first publicly described the algorithm in 1978. 

> RSA名字的由来  


### 1.2 RSA是非对称加密

`RSA` (**Rivest-Shamir-Adleman**) is one of the first **public-key cryptosystems** and is widely used for **secure data transmission**. In such a cryptosystem, **the encryption key** is **public** and it is different from **the decryption key** which is kept secret (**private**). 

> **public-key cryptosystems**，也就是非对称加密  
> 使用public key和private key

![](images/rsa-encryption.jpg)




### 1.3 RSA的原理（因素分解）

In **RSA**, this asymmetry is based on the practical difficulty of the factorization of the product of two large prime numbers, the "factoring problem". 

> RSA所基于的原理： factoring problem  



## 2. RSA Encryption Explained Simply

RSA encryption is an Algorithm understood by so few people and used by many. 

### 2.1 GENERATE A PUBLIC KEY AND PRIVATE KEY

First we need our keys: 

- **A private key** that the server will keep and 
- **a public key** that can be given away.

We need 2 prime numbers: `p` and `q`, 

```txt
p = 29
q = 31
```

Calculate `n = p * q = 29 * 31 = 899`

Calculate `t = (p -1) * (q – 1) = (29 – 1) * (31 – 1) = 840`

Choose a prime number `e`. `e` needs to be relatively prime to `t`. (`t` cannot be divisible by `e`) Lets pick `11`

We now need to find a `d`. We will use the formula: `d * e [=] 1 mod t`

This means (`d * 11) / t` will give us a remainder of `1`. You have to find the inverse of `e mod t`. Since we are dealing with such small numbers we can sort of guess our `d` until we find one that works.

`(611 * 11) = 6721`, `6721 / 840 = 8` with remainder `1`. So `611` works! We now have everything we need for a **private** and **public** key to encrypt our data.

- `p` – 29
- `q` – 31
- `n` – 899
- `t` – 840
- `e` – 11
- `d` – 611

Our **public key** becomes `n` and `e`.
Our **private key** becomes `n` and `d`.


### 2.2 ENCRYPTING OUR MESSAGE

We give our **public key** numbers to the person that wants to send us their message. They will encrypt the message with the formula:

<code>C = M<sup>e</sup> mod n</code>

Parameter Explained: 

- `C` is our encrypted Message. 
- `M` is our original message.

So if we took the letter ‘`w`’ whose ascii value is `119`.

`C = 11911 mod 899 = 595` 

We now send `595` to the server.

Read More: 

- How to do [fast modular exponentiation](http://www.pagedon.com/modular-exponentiation/cprogramming/)


### 2.3 DECRYPTING OUR MESSAGE

In order to decrypt the message we need our **private key**: `n` and `d`.

> Keep in mind we don’t give anybody our private key.

We use the formula <code>M = C<sup>d</sup> mod n</code>, 
so `M = 595611 mod 899 = 119`. 
`M = 119` whose character value is ‘`w`’ our original message!


