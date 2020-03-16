# Configure the CA Compliance Manager Server to Use SSL

URL: https://docops.ca.com/ca-compliance-manager-for-z-os/2-0/en/integrating-with-ca-chorus/secure-the-web-application-server-with-ssl/set-up-encryption-between-the-browser-and-ca-compliance-manager/configure-the-ca-compliance-manager-server-to-use-ssl


To configure the CA Compliance Manager server to use SSL when communicating with the browser, you must edit the `server.xml` file. This file is located in the `conf` directory within the **CA** Web Administrator installation directory.

## 1. Open the `server.xml` file and locate the following inactive lines:

```xml
<!--
<Connector port="8443" protocol="HTTP/1.1" SSLEnabled="true"
           maxThreads="150" scheme="https" secure="true"
           clientAuth="false" sslProtocol="TLS" />
-->
```

## 2. Replace the configuration with the following active lines:

```xml
<Connector port="8443" protocol="HTTP/1.1" SSLEnabled="true"
           maxThreads="150" scheme="https" secure="true"
           clientAuth="false" sslProtocol="TLS"
           keystoreFile="keystore" keystorePass="storepass"
           keystoreType="storetype" keyAlias="alias" />
```

## 3. Modify the attributes as follows

- `keystoreFile="keystore"`

Defines the name of the file that contains the key store. If the certificate was created using the Java `keytool` application, this is the name that you assigned to the key store when it was created. If the certificate was created using the z/OS security manager, this is the name of the Z/OS UNIX file into which you copied the contents of the MVS data set that the EXPORT command created.

Default: The file "`.keystore`" in the CA Web Administrator server home directory.

- `keystorePass="storepass"`

Defines the password for the key store. If the certificate was created using the Java `keytool` application, this is the password that you assigned to the keystore when it was created. If the certificate was created using **the z/OS security manager**, this is the password that you assigned to the key store on the `EXPORT` command.

Default: “changeit”

- `keystoreType="{JKS|PKCS12}"`

Defines the type of the key store file. If the certificate was created using the Java `keytool` application, specify the value “`JKS`”. If the certificate was created using the **z/OS security manager**, specify the value “`PKCS12`”.

Default: “JKS”

- `keyAlias="alias"`

Defines the certificate name that you defined in keytool. If the certificate was created using the z/OS security manager, omit this attribute. If the certificate was created using the Java keytool application, specify the name you assigned to the certificate when you created it.

Default: The first key in the key store.


## 4. Restart the CA Compliance Manager server.

