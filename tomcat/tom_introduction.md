# Introduction to Apache Tomcat

URL: 

- https://www.baeldung.com/tomcat

[TOC]

## 1. Overview

Simply put, **Apache Tomcat** is **a web server** and **servlet container** that is used to deploy and serve Java web applications.

> Tomcat是一个Servlet容器（servlet container）。

## 2. Install Tomcat on Linux (Fedora)

### 2.1 Download and Uncompress

Similarly, we’re going to [download](https://tomcat.apache.org/download-80.cgi) and uncompress Tomcat:

```bash
sudo mkdir /opt/tomcat
sudo tar -zxf apache-tomcat-8.5.34.tar.gz -C /opt/tomcat --strip-components=1
```

### 2.2 Ensure that Java is Installed

Let’s also make sure that we have Java installed and available on the system:

```bash
java -version
```

You should get the following output:

```txt
$ java -version

openjdk version "1.8.0_181"
OpenJDK Runtime Environment (build 1.8.0_181-b13)
OpenJDK 64-Bit Server VM (build 25.181-b13, mixed mode)
```

### 2.3 Create a User and a Group

We’re going to run the server under a separate group and user; let’s create a group for it first:

```bash
sudo groupadd tomcat
```

And let’s create a Tomcat user to avoid use the root user:

```bash
sudo useradd -s /bin/false -g tomcat -d /opt/tomcat/ tomcat
```

Let’s also update the permissions of the server – to use them with the new user and group:

```bash
cd /opt/tomcat/

sudo chgrp -R tomcat conf
sudo chmod g+rwx conf
sudo chmod g+r conf/* # 这里有点问题，可能是权限不对
sudo chown -R tomcat work/ temp/ logs/
```

### 2.4 Start

Go to the `tomcat/bin` directory and execute the following command:

```
./catalina.sh start
```

You should see the following output:

```txt
Using CATALINA_BASE:   /opt/tomcat
Using CATALINA_HOME:   /opt/tomcat
Using CATALINA_TMPDIR: /opt/tomcat/temp
Using JRE_HOME:        /usr
Using CLASSPATH:       /opt/tomcat/bin/bootstrap.jar:/opt/tomcat/bin/tomcat-juli.jar
Tomcat started.
```

## 3. Tomcat Manager

To access **the Tomcat manager**, we need to create **a user** with the privileges to do that.

In this file, we are going to define the users to access **the tomcat manager**.

```xml
<?xml version='1.0' encoding='utf-8'?>
<tomcat-users xmlns="http://tomcat.apache.org/xml"
  xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
  xsi:schemaLocation="http://tomcat.apache.org/xml tomcat-users.xsd"
  version="1.0">
    <user username="admin" password="admin" roles="manager-gui,admin-gui"/>
</tomcat-users>
```

In the `<user>` tag, we are defining a user “`admin`” with the password “`admin`” with the roles `manager-gui` and `admin-gui`.

Now restart the server and open again the URL:

```
http://localhost:8080
```

This time click on the “`Manager App`” button and the server will ask for credentials.

## 4. SSL Certificate






