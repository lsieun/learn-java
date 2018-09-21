# How to Deploy a WAR File to Tomcat

URL:

- https://www.baeldung.com/tomcat-deploy-war

## 1. Tomcat Structure

Before we begin, we should familiarize ourselves with some **terminology** and **environment variables**.

### 1.1 Environment Variables

If you have worked with Tomcat before, these will be very familiar to you:

```
$CATALINA_HOME
```

This variable points to **the directory where our server is installed**.

```
$CATALINA_BASE
```

This variable points to **the directory of a particular instance of Tomcat**, you may have multiple instances installed. If this variable is not set explicitly, then it will be assigned the same value as `$CATALINA_HOME`.

**Web applications** are deployed under the `$CATALINA_HOME\webapps` directory.

### 1.2 Terminology

**Document root**. Refers to **the top-level directory of a web application**, where all the application resources are located like JSP files, HTML pages, Java classes, and images.

**Context path**. Refers to the location which is relative to the server’s address and represents **the name of the web application**.

For example, if our web application is put under the `$CATALINA_HOME\webapps\myapp` directory, it will be accessed by the URL `http://localhost/myapp`, and **its context path** will be `/myapp`.

**WAR**. Is the extension of a file that packages a web application directory hierarchy in ZIP format and is short for Web Archive. **Java web applications** are usually packaged as `WAR` files for deployment. These files can be created on the command line or with an IDE like Eclipse.

After deploying our `WAR` file, Tomcat unpacks it and stores all project files in the webapps directory in a new directory named after the project.


## 2. Tomcat Setup

The Tomcat Apache web server is free software that can be [downloaded from their website](https://tomcat.apache.org/). It is required that there is a `JDK` available on the user’s machine and that the `JAVA_HOME` environment variable is set correctly.

## 2.1 Start Tomcat

We can start the Tomcat server by simply running the startup script located at `$CATALINA_HOME\bin\startup`. There is a `.bat` and a `.sh` in every installation.

Choose the appropriate option depending on whether you are using a `Windows` or `Unix` based operating system.

## 2.2 Configure Roles

During the deployment phase, we’ll have some options, one of which is to use Tomcat’s **management dashboard**. To access this dashboard, we must have **an admin user** configured with **the appropriate roles**.

To have access to **the dashboard** the admin user needs the `manager-gui` role. Later, we will need to deploy a WAR file using `Maven`, for this, we need the `manager-script` role too.

Let’s make these changes in `$CATALINA_HOME\conf\tomcat-users.xml`:

```xml
<role rolename="manager-gui"/>
<role rolename="manager-script"/>
<user username="admin" password="password" roles="manager-gui, manager-script"/>
```

More details about the different Tomcat roles can be found by following [this official link](https://tomcat.apache.org/tomcat-6.0-doc/manager-howto.html).

### 2.3 Set Directory Permissions

Finally, ensure that there is `read/write` permission on the **Tomcat installation directory**.

### 2.4 Test Installation

To test that Tomcat is setup properly run the startup script (`startup.bat`/`startup.sh`), if no errors are displayed on the console we can double-check by visiting `http://localhost:8080.`

If you see the Tomcat landing page, then we have installed the server correctly.

### 2.5 Resolve Port Conflict

By default, Tomcat is set to listen to connections on port `8080`. If there is another application that is already bound to this port, the startup console will let us know.

To change the port, we can edit the server configuration file server.xml located at `$CATALINA_HOME\conf\server.xml`. By default, the connector configuration is as follows:

```xml
<Connector port="8080" protocol="HTTP/1.1"
  connectionTimeout="20000" redirectPort="8443" />
```

For instance, if we want to change our port to `8081`, then we will have to change the connector’s port attribute like so:

```xml
<Connector port="8081" protocol="HTTP/1.1"
  connectionTimeout="20000" redirectPort="8443" />
```

Sometimes, the port we have chosen is not open by default, in this case, we will need to open this port with the appropriate commands in the Unix kernel or creating the appropriate firewall rules in Windows, how this is done is beyond the scope of this article.

## 3. Deploy From Maven

If we want to use `Maven` for deploying our web archives, we must configure `Tomcat` as **a server** in Maven’s `settings.xml` file.

There are two locations where the settings.xml file may be found:

- The Maven install: `${maven.home}/conf/settings.xml`
- A user’s install: `${user.home}/.m2/settings.xml`

Once you have found it add Tomcat as follows:

```xml
<server>
    <id>TomcatServer</id>
    <username>admin</username>
    <password>password</password>
</server>
```

We will now need to create a basic web application from `Maven` to test the deployment. Let’s navigate to where we would like to create the application.

Run this command on the console to create a new Java web application:

```bash
mvn archetype:generate \
-DgroupId=com.baeldung \
-DartifactId=tomcat-war-deployment \
-DarchetypeArtifactId=maven-archetype-webapp \
-DinteractiveMode=false
```

This will create a complete web application in the directory `tomcat-war-deployment` which, if we deploy now and access via the browser, prints `hello world!`.

But before we do that we need to make one change to enable `Maven` deployment. So head over to the `pom.xml` and add this plugin:

```xml
<plugin>
    <groupId>org.apache.tomcat.maven</groupId>
    <artifactId>tomcat7-maven-plugin</artifactId>
    <version>2.2</version>
    <configuration>
        <url>http://localhost:8080/manager/text</url>
        <server>TomcatServer</server>
        <path>/myapp</path>
    </configuration>
</plugin>
```

Note that we are using the `Tomcat 7` plugin because it works for both versions `7` and `8` without any special changes.

The configuration `url` is **the url to which we are sending our deployment**, Tomcat will know what to do with it. The `server` element is **the name of the server instance** that Maven recognizes. Finally, the `path` element defines **the context path of our deployment**.

This means that if our deployment succeeds, we will access the web application by hitting http://localhost:8080/myapp.

Now we can run the following commands from Maven.

To deploy the web app:

```bash
mvn tomcat7:deploy
```

To undeploy it:

```
mvn tomcat7:undeploy
```

To redeploy after making changes:

```bash
mvn tomcat7:redeploy
```






