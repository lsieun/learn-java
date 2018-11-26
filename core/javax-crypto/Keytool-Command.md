# keytool Command

Use the `keytool` command to manage the **keystore** database of private keys.

## 1. key pair

### 1.1 generate

Generate a Java keystore and key pair

```bash
keytool -genkey -alias mydomain -keyalg RSA -keystore keystore.jks -keysize 2048
```

Generate a keystore and self-signed certificate

```bash
keytool -genkey -alias selfsigned -keyalg RSA -keystore keystore.jks -storepass password -validity 360 -keysize 2048
```

## 2. certificate

### 2.1 import

Import a signed primary certificate to an existing Java keystore

```bash
keytool -import -trustcacerts -alias mydomain -file mydomain.crt -keystore keystore.jks
```

### 2.2 export 

Export a certificate from a keystore

```bash
keytool -export -alias mydomain -file mydomain.crt -keystore keystore.jks
```

### 2.3 delete

Delete a certificate from a Java Keytool keystore

```bash
keytool -delete -alias mydomain -keystore keystore.jks
```

### 2.4 list

Check which certificates are in a Java keystore

```bash
keytool -list -v -keystore keystore.jks
```

### 2.5 check

Check a stand-alone certificate

```bash
keytool -printcert -v -file mydomain.crt
```

Check a particular keystore entry using an alias

```bash
keytool -list -v -keystore keystore.jks -alias mydomain
```

## 3. keystore

### 3.1 password

Change a Java keystore password

```bash
keytool -storepasswd -new new_storepass -keystore keystore.jks
```

## 4. Other

### 4.1 CA

List Trusted CA Certs

```bash
keytool -list -v -keystore $JAVA_HOME/jre/lib/security/cacerts
```

Import New CA into Trusted Certs

```
keytool -import -trustcacerts -alias CA_ALIAS -file /path/to/ca/ca.pem -keystore $JAVA_HOME/jre/lib/security/cacerts
```

Import a root or intermediate CA certificate to an existing Java keystore

```bash
keytool -import -trustcacerts -alias root -file Thawte.crt -keystore keystore.jks
```


### 4.2 CSR

Generate a certificate signing request (CSR) for an existing Java keystore

```bash
keytool -certreq -alias mydomain -keystore keystore.jks -file mydomain.csr
```

### 4.3 Conversion

If you need to change the type of keystore.

- PFX keystore to JKS keystore:

```bash
keytool -importkeystore -srckeystore mypfxfile.pfx -srcstoretype pkcs12 -destkeystore newjkskeystore.jks -deststoretype JKS
```

- JKS keystore to PFX keystore:

```bash
keytool -importkeystore -srckeystore myjksfile.jks -srcstoretype JKS -deststoretype PKCS12 -destkeystore newpfxkeystore.pfx
```






## syntax

The command has the following syntax:

```bash
keytool -genkey -alias alias -keysize keysize -dname "dname" -validity valDays -keystore keystore -storepass storepass

keytool -certreq -alias alias -keystore keystore -storepass storepass -file filename

keytool -import -alias alias -keystore keystore -storepass storepass -file filename
```

- `-genkey`

Specifies that the command generates a self-signed certificate.

- `-certreq`

Specifies that the command creates a certificate signing request (CSR).

- `-import`

Specifies that the command imports a certificate into the Java key store.

- `-alias alias`

Defines the name alias to the generated certificate. You use this name to refer to this specific certificate.

Default: "mykey"

- `-keysize keysize`

Specifies the size of the public/private key pair to generate.

Limits: 1024 or 2048

Default: 1024

- `-dname "dname"`

Specifies the distinguished name for the certificate. Keytool assigns this name as both the **subject** and **issuer** name. Attribute names are **case insensitive**. **Order does matter**: each attribute name must appear in the designated order. It is not necessary to specify all attribute names. If any attribute value contains a comma, escape the comma with a backslash (`\`) character.

Format: "CN=cName, OU=orgUnit, O=org, L=city, S=state, C=countryCode"

Example: "CN=John Smith, O=Company\, Inc.,C=US"

Default: keytool prompts you for a value for each attribute name.

- `-validity valDays`

Defines how many days the certificate is valid.

Default: 90 days

- `-keystore keystore`

Defines keystore as the name of the file that contains the key store. If this file does not exist, keytool creates it for you.

Default: ".keystore" in the user’s home directory

- `-storepass storepass`

Defines storepass as the password for the key store.

Default: keytool prompts you for the password of the key store.

- `-file filename`

Defines the the name of the file that keytool creates when generating the certificate signing request (CSR) for `-certreq`. Defines the name of the file that contains the certificate to be imported for `-import`.

Default: Standard output for `-certreq`, Standard input for `-import`

