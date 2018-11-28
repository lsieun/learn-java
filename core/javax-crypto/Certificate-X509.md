# X.509 certificate

URL: https://searchsecurity.techtarget.com/definition/X509-certificate

An **X.509 certificate** is a **digital certificate** that uses the widely accepted international X.509 public key infrastructure (PKI) standard to verify that **a public key belongs to the user**, computer or service identity contained within the certificate.

> X. 509 certificate 分成两个层次来理解：  
> （1） digital certificate。从本质上来说，它就是一个数字证书。  
> （2） X.509格式。从格式的角度，来规定了证书里包含了哪些内容。

An X.509 certificate contains information about **the identity**(接收证书的一方) to which a certificate is issued and **the identity**(颁发证书的一方) that issued it. 

Standard information in an X.509 certificate includes:

- **Version** – which **X.509 version** applies to the certificate (which indicates what data the certificate must include)
- **Serial number** – the identity creating the certificate must assign it a serial number that distinguishes it from other certificates
- **Algorithm information** – the algorithm used by the issuer to sign the certificate
- **Issuer distinguished name** – the name of the entity issuing the certificate (usually a certificate authority)
- **Validity period of the certificate** – start/end date and time
- **Subject distinguished name** – the name of the identity the certificate is issued to
- **Subject public key information** – the public key associated with the identity
- **Extensions** (optional)

**Many of the certificates** that people refer to as **Secure Sockets Layer (SSL) certificates** are in fact **X.509 certificates**.

