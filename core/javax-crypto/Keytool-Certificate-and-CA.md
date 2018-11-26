# Create Your Own Certificate and CA

URL: https://sites.google.com/site/ddmwsst/create-your-own-certificate-and-ca

This page will show how to 

- generate a key pair, 
- a certificate signing request (CSR) using Java Keytool and 
- set up your own CA using OpenSSL tool and 
- sign a certificate.

## 1. What You Need

## 1.1 Tools

- Java Keytool (`keytool`)
- OpenSSL (`openssl`)

## 1.2 Set Up Directories

- `Keys`: To store your **private/public** keys and **certificates**
- `CA`: To store necessary **certificates** and **keys** or our own Test Certificate Authority.

创建目录命令：

```bash
$ mkdir {Keys,CA}
```

## 2. Generate a Key Pair

To generate **the key pair** we are going to use Java `keytool`. 

> 工具 keytool

Java stores **keys** and **certificate** in Key Store. A Key Store is password protected file often with **JKS (Java Key Store)** extension. 

> 存储key pair和certificate的地方为keystore

In the command prompt, go to the `Keys` directory and type in the following command to generate **a key pair**, i.e. **a private key and a public key**.

```bash
$ cd Keys/
$ keytool -genkey -alias lsieun-server -keyalg RSA -sigalg SHA1withRSA -keystore keystore.jks
```

**Command Explanation**:

- `genkey`: Generate **a key pair** and add **entry** to the **key store**.
- `alias`: short name of the key pair in the key store
- `keystore`: This tells keytool that store the keys in a key store and file name of the key store is `keystore.jks`
- `keyalg`: **Key generation algorithm**. Commonly it is either `RSA` (mostly used) or `DSA` (default). Here it is `RSA`.
- `sigalg`: **The signature algorithm**. Here it says **hash/digest** the message with `SHA1` and then encrypt it using a `RSA` private key. If you do not specify then keytool uses default key pair generation algorithm as "`DSA`". The signature algorithm is derived from the algorithm of the underlying private key: If the underlying private key is of type "`DSA`", the default signature algorithm is "`SHA1withDSA`", and if the underlying private key is of type "`RSA`", the default signature algorithm is "`MD5withRSA`".


When you run this command you will be asked to enter **two password**. These two passwords have different purposes.
- (1) **Key Store Password**: You can consider that keytool will append this password to the content of the key store and then generate a hash/digest and store it into the key store. If someone modifies the key store without this password, he won't be able to update the digest message. The next time you run keytool on this keystore, it will note the mismatch and warn you not to use this key store anymore.
- (2) **Alias Password or Private Key Password**: You need to provide an entry password to protect the entry for the alias. You can consider that keytool will use this password to encrypt private key. This way other people won't be able to read  private key.


Output:

```txt
Enter keystore password:  # 例如：abcdef
Re-enter new password:   # 例如：abcdef
What is your first and last name?
  [Unknown]:  LS Web Server
What is the name of your organizational unit?
  [Unknown]:  LS-Org-Unit
What is the name of your organization?
  [Unknown]:  LS-Org
What is the name of your City or Locality?
  [Unknown]:  LS-City
What is the name of your State or Province?
  [Unknown]:  LS-State
What is the two-letter country code for this unit?
  [Unknown]:  CN
Is CN=LS Web Server, OU=LS-Org-Unit, O=LS-Org, L=LS-City, ST=LS-State, C=CN correct?
  [no]:  yes

Enter key password for <lsieun-server>
	(RETURN if same as keystore password):  # 例如：123456
Re-enter new password:  # 例如：123456
```

The "`Keytool -genkey ...`" command also prompts for alias's **distinguish name (DN)**. A **DN** carries identity information of an entity in `ASN.1` format. It consists of

- Certificate owner's common name (CN)
- Organization
- Organizational unit
- Locality or city
- State or province
- Country or region

To verify that the **key pairs** are added to the **key store** you can run the following command.

```bash
$ keytool -list -v -keystore keystore.jks 
```

Output:

```txt
Enter keystore password:  # 例如：abcdef
Keystore type: jks
Keystore provider: SUN

Your keystore contains 1 entry

Alias name: lsieun-server
Creation date: Nov 25, 2018
Entry type: PrivateKeyEntry
Certificate chain length: 1
Certificate[1]:
Owner: CN=LS Web Server, OU=LS-Org-Unit, O=LS-Org, L=LS-City, ST=LS-State, C=CN
Issuer: CN=LS Web Server, OU=LS-Org-Unit, O=LS-Org, L=LS-City, ST=LS-State, C=CN
Serial number: 4d97f9a7
Valid from: Sun Nov 25 22:35:35 CST 2018 until: Sat Feb 23 22:35:35 CST 2019
Certificate fingerprints:
	 MD5:  72:A5:F4:93:E4:E7:E7:DA:36:DC:B8:E3:21:B0:F1:68
	 SHA1: 38:3E:A9:CE:47:19:31:C3:00:25:FC:F9:69:E4:D7:C7:62:81:D7:02
	 SHA256: CB:05:AD:B4:0F:56:8A:2C:F8:85:D6:33:B0:5F:06:40:C3:CD:73:3D:EB:66:2C:F9:0C:F1:96:61:5D:93:CE:6B
Signature algorithm name: SHA1withRSA
Subject Public Key Algorithm: 2048-bit RSA key
Version: 3

*******************************************
*******************************************

```

While executing this command it will ask you to provide **the key store's password** to verify the content of the file. Note that keytool will **not** print the private or the public key. But it tells that key store has **one entry** and it of type `PrivateKeyEntry`. 

## 3. Your Self-Signed Certificate

So now **the key store `keystore.jks`** has **a private and public key**. **Key Store** is **not** a digital certificate. It is **a store to keep certificates and private keys**. 

Now let us **export a certificate containing the public key**. You **can not** export the **private key** using `keytool`. To extract the private key you need to use **Java Cryptography API**. 

Use the following command to export **the public key** as a **certificate**.

```bash
$ keytool -export -alias lsieun-server -file lsieun-self-signed-cert.cer -keystore keystore.jks
```

Output:

```txt
Enter keystore password:  # 例如：abcdef 
Certificate stored in file <lsieun-self-signed-cert.cer>

```

Now `lsieun-self-signed-cert.cer` is the certificate containing **your public key**. Let us print the certificate using another `keytool` command.

```bash
$ keytool -printcert -v -file lsieun-self-signed-cert.cer
```

Output:

```txt
Owner: CN=LS Web Server, OU=LS-Org-Unit, O=LS-Org, L=LS-City, ST=LS-State, C=CN
Issuer: CN=LS Web Server, OU=LS-Org-Unit, O=LS-Org, L=LS-City, ST=LS-State, C=CN
Serial number: 4d97f9a7
Valid from: Sun Nov 25 22:35:35 CST 2018 until: Sat Feb 23 22:35:35 CST 2019
Certificate fingerprints:
	 MD5:  72:A5:F4:93:E4:E7:E7:DA:36:DC:B8:E3:21:B0:F1:68
	 SHA1: 38:3E:A9:CE:47:19:31:C3:00:25:FC:F9:69:E4:D7:C7:62:81:D7:02
	 SHA256: CB:05:AD:B4:0F:56:8A:2C:F8:85:D6:33:B0:5F:06:40:C3:CD:73:3D:EB:66:2C:F9:0C:F1:96:61:5D:93:CE:6B
Signature algorithm name: SHA1withRSA
Subject Public Key Algorithm: 2048-bit RSA key
Version: 3
```

`lsieun-self-signed-cert.cer` is a **self signed certificate**. Its Owner and Issuer have the same DN. **This certificate** is signed by the private key of `lsieun-server`. 


## 4. Generate a Certificate Signing Request

At this point you have **a key pair** in **key store**, i.e. `keystore.jks` and **a certificate** containing **your public key** and your **DN** as `lsieun-self-signed-cert.cer` which is self signed. 

But browser does not trust you! Having a self signed certificate is not so much useful ha! 

To be trusted you need your certificate to be signed by **a well known CA**. To do that first you need to generate **a Certificate Signing Request (CSR)** and send it to **CA**. Keytool helps to **generate a CSR** using the following command

```bash
$ keytool -certreq -alias lsieun-server -keystore keystore.jks -file lsieun-server-cert-req.csr
```

Output:

```txt
Enter keystore password:  # 例如：abcdef
Enter key password for <lsieun-server> # 例如：123456
```

The above command extracts **required information** such as **public key**, **DN** and put it in a standard **CSR** format in file `lsieun-server-cert-req.csr`. 

A commercial CA should verify all information before they can issue a certificate with their signature. In the next section we are going to create our own **test CA** and **register it as trusted to the browser** and use it to **sign our public key**.


## 5. Set Up a Certificate Authority

Hoping that you have already installed **OpenSSL** and appended its bin folder in your classpath, let us now try to setup a **Test CA**. 

Go to `CA` directory you created at the beginning. Here we are going to create **a single tier CA**, where **root CA** and **issuing CA** are the same. 

```bash
$ cd CA/
$ RANDFILE=rand
```

Openssl commands need to save **a random seed information** to a file ("random file"). You need to tell it the path to that file. Here, just tell it to use a file named "`rand`" in **the current folder**.

Next, to start **Test CA**, we need **a private key**. This is the top secret of the CA. If this is compromised then the CA is doomed!!! All certificates issued by this CA will be revoked. This is why **the root private key** is so important and often kept off-line necessitating a multi-tier hierarchy. 

For our **test CA** we need to 

- **create the key-pair** and 
- **create a certificate signing request** for the root CA's public key. 
 
Please note this **CSR** is for **the CA itself**. These two steps can be done in a single command using **SSL** as follows

```bash
$ openssl req -new -keyout cakey.pem -out careq.pem
```

**Command Explanation**:

- `req`: work on **certificate signing request**
- `new`: create **a new private key** and add **a certificate request**
- `keyout`: keep the private key in file `cakey.pem`. **Top secret file!!**
- `out`: write the CSR in file `careq.pem`
- `config`: provides the config file. This is needed for the first time only.

When you run the above command it will ask our **Test CA**'s **DN** and **a password** to encrypt the private key while writing in `cakey.pem` file.

Output:

```txt
Generating a 2048 bit RSA private key
.................+++
..................................................................................................................................................+++
writing new private key to 'cakey.pem'
Enter PEM pass phrase: # 例如：cakeypass
Verifying - Enter PEM pass phrase: # 例如：cakeypass
-----
You are about to be asked to enter information that will be incorporated
into your certificate request.
What you are about to enter is what is called a Distinguished Name or a DN.
There are quite a few fields but you can leave some blank
For some fields there will be a default value,
If you enter '.', the field will be left blank.
-----
Country Name (2 letter code) [AU]:CN
State or Province Name (full name) [Some-State]:CA-State
Locality Name (eg, city) []:CA-City
Organization Name (eg, company) [Internet Widgits Pty Ltd]:CA-Org-Name
Organizational Unit Name (eg, section) []:CA-Org-Unit
Common Name (e.g. server FQDN or YOUR name) []:Test CA
Email Address []:admin@testca.com

Please enter the following 'extra' attributes
to be sent with your certificate request
A challenge password []:
An optional company name []
```

Now we need to generate **a certificate** out of **Test CA's CSR**. Obviously this would be **self signed**. The following **OpenSSL** command is used to generate **a self signed certificate** from the **CSR**.

```bash
$ openssl x509 -signkey cakey.pem -req -days 3650 -in careq.pem -out caroot.cer -extensions v3_ca
```

**Command Explanation**:

- `x509`: Work on an X.509 certificate
- `signkey`: self sign the certificate using private in as stored in file cakey.pem
- `req`: tells that input is a CSR
- `days`: specify days of validity of the generated certificate
- `in`: the input, i.e. CSR careq.pem
- `out`: write the output certificate on caroot.cer
- `extensions`: apply x.509 v3 extensions

Output:

```txt
Signature ok
subject=/C=CN/ST=CA-State/L=CA-City/O=CA-Org-Name/OU=CA-Org-Unit/CN=Test CA/emailAddress=admin@testca.com
Getting Private key
Enter pass phrase for cakey.pem: # 例如：cakeypass
```

At this point you have **self signed root certificate** of our **Test CA**. **This certificate** along with **the private key** will be used to **sign others certificates**. **This root public certificate** should be publicly available and must be trusted by browsers and programs.

You can open it using keytool:

```bash
$ keytool -printcert -v -file caroot.cer
```

Output:

```txt
Owner: EMAILADDRESS=admin@testca.com, CN=Test CA, OU=CA-Org-Unit, O=CA-Org-Name, L=CA-City, ST=CA-State, C=CN
Issuer: EMAILADDRESS=admin@testca.com, CN=Test CA, OU=CA-Org-Unit, O=CA-Org-Name, L=CA-City, ST=CA-State, C=CN
Serial number: d34a449e83c243c6
Valid from: Sun Nov 25 23:53:42 CST 2018 until: Wed Nov 22 23:53:42 CST 2028
Certificate fingerprints:
	 MD5:  05:52:CB:31:B6:B7:62:DF:8D:F0:F6:CF:AC:6E:80:91
	 SHA1: DA:78:A4:0D:FF:BC:B1:BE:82:5A:3F:02:42:4C:74:A9:19:6E:76:FE
	 SHA256: A8:3B:86:57:E6:F4:4C:A2:A0:71:75:D6:24:83:00:8E:FB:86:BD:B9:8B:61:05:22:C9:04:B5:BF:9B:16:94:B4
Signature algorithm name: SHA256withRSA
Subject Public Key Algorithm: 2048-bit RSA key
Version: 1
```

## 6. Trusting CA's Root Certificate

**Every browser** keeps **well know CA's root certificates** in their trust store. Browsers manage (add/delete) these certificates during security patches time to time. 

**Java Environment** also keeps **root CA certificates** in` JRE_HOME\lib\security\cacerts` file. It is a keystore and you can open it using the following command. 

```bash
$ cd /usr/local/jdk1.8.0_181/jre/lib/security
$ keytool -list -protected -keystore cacerts
```

Output:

```txt

*****************  WARNING WARNING WARNING  *****************
* The integrity of the information stored in your keystore  *
* has NOT been verified!  In order to verify its integrity, *
* you must provide your keystore password.                  *
*****************  WARNING WARNING WARNING  *****************

Keystore type: jks
Keystore provider: SUN

Your keystore contains 105 entries

verisignclass2g2ca [jdk], Aug 25, 2016, trustedCertEntry, 
Certificate fingerprint (SHA1): B3:EA:C4:47:76:C9:C8:1C:EA:F2:9D:95:B6:CC:A0:08:1B:67:EC:9D
digicertassuredidg3 [jdk], Aug 25, 2016, trustedCertEntry, 
Certificate fingerprint (SHA1): F5:17:A2:4F:9A:48:C6:C9:F8:A2:00:26:9F:DC:0F:48:2C:AB:30:89
verisignuniversalrootca [jdk], Aug 25, 2016, trustedCertEntry, 
Certificate fingerprint (SHA1): 36:79:CA:35:66:87:72:30:4D:30:A5:FB:87:3B:0F:A7:7B:B7:0D:54
```

You can add **Test CA's root certificate** to your JRE using `keytool` command. The initial password of the "`cacerts`" keystore file is "`changeit`". You can also add the **Test CA's root certificate** in `keystore.jks` or your own key store and point to that key store at runtime when you need to use **any certificate signed by this Test CA**. 

If you cannot convince JRE that **Test CA's root is trusted**, **all certificates signed by Test CA** will not work at runtime! You will see shortly how **Test CA's cert** can be added to `keystore.jks`.


## 7. Get Your Own Certificate Signed by CA

**Test CA** is ready, we have added it to **the browser's truststore**. Now it the time to get **your own certificate** signed by the **Test CA**. 

However, before that, you need to note that when a **CA** issues **a new certificate**, it will put **a unique serial number** into **that certificate**. So you need to tell `OpenSSL` **what is the next serial number to use**. To do that drop a `serial.txt` file containing a serial number in **the `CA` directory**. 

```bash
$ echo 1234 > serial.txt
```

This way OpenSSL will use `1234` as **the next serial number**. Then it will set it to `1235` automatically. 

To sign your server's **public key** and **DN** we now need the **CSR** which is `lsieun-server-cert-req.csr` and use the following OpenSSL command:

```bash
$ openssl x509 -CA caroot.cer -CAkey cakey.pem -CAserial serial.txt -req -in ../Keys/lsieun-server-cert-req.csr -out ../Keys/lsieun-Test-CA-signed-cert.cer -days 365
```

**Command Explanation**:

- `x509`: again working with X.509 certificates.
- `CA`: sign the certificate using `caroot.cer`. For exapmle it can find the **DN** of the **CA** here
- `CAkey`: take the private key from `cakey.pem` file
- `CAserial`: point to **serial number file**
- `req`: says that input is a CSR and not a certificate itself
- `in`: provides the input CSR as `../Keys/lsieun-server-cert-req.csr`
- `out`: write the output certificate in `../Keys/lsieun-Test-CA-signed-cert.cer` file
- `days`: validity of the certificate

Output:

```txt
Signature ok
subject=/C=CN/ST=LS-State/L=LS-City/O=LS-Org/OU=LS-Org-Unit/CN=LS Web Server
Getting CA Private Key
Enter pass phrase for cakey.pem: # 例如：cakeypass
```

Now you have **a certificate** signed by **Test CA**, i.e. `lsieun-Test-CA-signed-cert.cer`. Let us open it using `keytool`:

```bash
$ keytool -printcert -v -file lsieun-Test-CA-signed-cert.cer
```

Output:

```txt
Owner: CN=LS Web Server, OU=LS-Org-Unit, O=LS-Org, L=LS-City, ST=LS-State, C=CN
Issuer: EMAILADDRESS=admin@testca.com, CN=Test CA, OU=CA-Org-Unit, O=CA-Org-Name, L=CA-City, ST=CA-State, C=CN
Serial number: 1235
Valid from: Mon Nov 26 00:23:25 CST 2018 until: Tue Nov 26 00:23:25 CST 2019
Certificate fingerprints:
	 MD5:  6D:AC:D2:D0:7E:1D:29:12:90:C0:82:CF:07:70:93:0F
	 SHA1: 96:A5:08:03:B0:33:03:7B:4D:D6:AD:7D:A1:5B:60:55:39:E0:23:88
	 SHA256: 80:53:E5:C0:B7:75:DB:B3:89:A1:18:47:F7:44:E9:6F:1B:7F:DF:16:E2:80:14:F4:7C:98:1C:29:8C:B0:90:BF
Signature algorithm name: SHA256withRSA
Subject Public Key Algorithm: 2048-bit RSA key
Version: 1
```

Now whoever trust **Test CA**, will be able to trust **the public key** writtin in `lsieun-Test-CA-signed-cert.cer` and be able to send encrypted message to `lsieun-server`. And `lsieun-server` can decrypt the message using **the private key** stored in `keystore.jks`.


## 8. Keep Your Certificates in Key Store

You can separately keep the `lsieun-Test-CA-signed-cert.cer` and send it to clients or import `lsieun-Test-CA-signed-cert.cer` to **the key store**. Because that's the way to use the certificate in a Java application. But before that we need to **import the signer certificate**, i.e. **root certificate of Test CA**, otherwise keytool will not import lsieun-Test-CA-signed-cert.cer`. Because it would not be able to trust the signer.

While importing **Test CA's root certificate**, keytool will ask for a confirmation that we really trust **Test CA**. If you say `YES`, keytool will take **all certificates** signed by **the root certificate**. 

```bash
$ keytool -import -alias TestCA -file ../CA/caroot.cer -keystore keystore.jks
```

Output:

```txt
Enter keystore password:  
Owner: EMAILADDRESS=admin@testca.com, CN=Test CA, OU=CA-Org-Unit, O=CA-Org-Name, L=CA-City, ST=CA-State, C=CN
Issuer: EMAILADDRESS=admin@testca.com, CN=Test CA, OU=CA-Org-Unit, O=CA-Org-Name, L=CA-City, ST=CA-State, C=CN
Serial number: d34a449e83c243c6
Valid from: Sun Nov 25 23:53:42 CST 2018 until: Wed Nov 22 23:53:42 CST 2028
Certificate fingerprints:
	 MD5:  05:52:CB:31:B6:B7:62:DF:8D:F0:F6:CF:AC:6E:80:91
	 SHA1: DA:78:A4:0D:FF:BC:B1:BE:82:5A:3F:02:42:4C:74:A9:19:6E:76:FE
	 SHA256: A8:3B:86:57:E6:F4:4C:A2:A0:71:75:D6:24:83:00:8E:FB:86:BD:B9:8B:61:05:22:C9:04:B5:BF:9B:16:94:B4
Signature algorithm name: SHA256withRSA
Subject Public Key Algorithm: 2048-bit RSA key
Version: 1
Trust this certificate? [no]:  yes
Certificate was added to keystore

```

Import `lsieun-Test-CA-signed-cert.cer` which is now signed by **Test CA**. One point to note here is that while importing this certificate you have to specify **the same alias name** that was used to generate **the key pair**. Only then `keytool` will replace **default self signed `lsieun-server`'s certificate** with **the certificate signed by Test CA**. 

```bash
$ keytool -import -alias lsieun-server -file lsieun-Test-CA-signed-cert.cer -keystore keystore.jks
```

Output:

```txt
Enter keystore password:  
Enter key password for <lsieun-server>
Certificate reply was installed in keystore
```

We started with **a empty key store** and added **several certificates** in it while working on this tutorial. Let us see what all is there in **the Key Store**.

```bash
$ keytool -list -v -keystore keystore.jks
```

Output:

```txt
Enter keystore password:  
Keystore type: jks
Keystore provider: SUN

Your keystore contains 2 entries

Alias name: testca
Creation date: Nov 26, 2018
Entry type: trustedCertEntry

Owner: EMAILADDRESS=admin@testca.com, CN=Test CA, OU=CA-Org-Unit, O=CA-Org-Name, L=CA-City, ST=CA-State, C=CN
Issuer: EMAILADDRESS=admin@testca.com, CN=Test CA, OU=CA-Org-Unit, O=CA-Org-Name, L=CA-City, ST=CA-State, C=CN
Serial number: d34a449e83c243c6
Valid from: Sun Nov 25 23:53:42 CST 2018 until: Wed Nov 22 23:53:42 CST 2028
Certificate fingerprints:
	 MD5:  05:52:CB:31:B6:B7:62:DF:8D:F0:F6:CF:AC:6E:80:91
	 SHA1: DA:78:A4:0D:FF:BC:B1:BE:82:5A:3F:02:42:4C:74:A9:19:6E:76:FE
	 SHA256: A8:3B:86:57:E6:F4:4C:A2:A0:71:75:D6:24:83:00:8E:FB:86:BD:B9:8B:61:05:22:C9:04:B5:BF:9B:16:94:B4
Signature algorithm name: SHA256withRSA
Subject Public Key Algorithm: 2048-bit RSA key
Version: 1


*******************************************
*******************************************


Alias name: lsieun-server
Creation date: Nov 26, 2018
Entry type: PrivateKeyEntry
Certificate chain length: 2
Certificate[1]:
Owner: CN=LS Web Server, OU=LS-Org-Unit, O=LS-Org, L=LS-City, ST=LS-State, C=CN
Issuer: EMAILADDRESS=admin@testca.com, CN=Test CA, OU=CA-Org-Unit, O=CA-Org-Name, L=CA-City, ST=CA-State, C=CN
Serial number: 1235
Valid from: Mon Nov 26 00:23:25 CST 2018 until: Tue Nov 26 00:23:25 CST 2019
Certificate fingerprints:
	 MD5:  6D:AC:D2:D0:7E:1D:29:12:90:C0:82:CF:07:70:93:0F
	 SHA1: 96:A5:08:03:B0:33:03:7B:4D:D6:AD:7D:A1:5B:60:55:39:E0:23:88
	 SHA256: 80:53:E5:C0:B7:75:DB:B3:89:A1:18:47:F7:44:E9:6F:1B:7F:DF:16:E2:80:14:F4:7C:98:1C:29:8C:B0:90:BF
Signature algorithm name: SHA256withRSA
Subject Public Key Algorithm: 2048-bit RSA key
Version: 1
Certificate[2]:
Owner: EMAILADDRESS=admin@testca.com, CN=Test CA, OU=CA-Org-Unit, O=CA-Org-Name, L=CA-City, ST=CA-State, C=CN
Issuer: EMAILADDRESS=admin@testca.com, CN=Test CA, OU=CA-Org-Unit, O=CA-Org-Name, L=CA-City, ST=CA-State, C=CN
Serial number: d34a449e83c243c6
Valid from: Sun Nov 25 23:53:42 CST 2018 until: Wed Nov 22 23:53:42 CST 2028
Certificate fingerprints:
	 MD5:  05:52:CB:31:B6:B7:62:DF:8D:F0:F6:CF:AC:6E:80:91
	 SHA1: DA:78:A4:0D:FF:BC:B1:BE:82:5A:3F:02:42:4C:74:A9:19:6E:76:FE
	 SHA256: A8:3B:86:57:E6:F4:4C:A2:A0:71:75:D6:24:83:00:8E:FB:86:BD:B9:8B:61:05:22:C9:04:B5:BF:9B:16:94:B4
Signature algorithm name: SHA256withRSA
Subject Public Key Algorithm: 2048-bit RSA key
Version: 1


*******************************************
*******************************************
```

**The key store** has **two entries**. One `PrivateKeyEntry` entry, it is basically **a certificate chain** now containing **two certificates**. The other `trustedCertEntry` entry, which is just **a trusted self signed Test CA's root certificate**. 

Here we learnt 

- how to create a certificate, 
- get it signed by a CA, 
- import it back to keystore to create the whole certificate chain and all trust issues. 

Let us use these certificates to enable a SSL Communication. Later on when we shall try to actually program Web Service security, we shall create a client and a server certificate, get them signed by our test CA and use them in our program. 

