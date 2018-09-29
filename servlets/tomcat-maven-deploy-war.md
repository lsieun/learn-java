# Servlet 3: Deploy a War from Maven to Tomcat

URL:

- https://www.baeldung.com/tomcat-deploy-war

[TOC]

环境信息：

- Java: OpenJDK 1.8.0_181
- Maven: 3.5.2 [Download](http://maven.apache.org/download.cgi)
- Tomcat: 8.5.34 [Download](https://tomcat.apache.org/download-80.cgi)
- OS: Fedora 28


## 1. Overview

**Apache Tomcat** is one of the most popular web servers in the Java community. It ships as **a servlet container** capable of serving **W**eb **AR**chives with the `WAR` extension.

> 这段理解两个意思：  
> （1）Tomcat是一个Servlet容器；  
> （2）我们可以将War包文件放到Tomcat上，以提供Web服务。

> WAR是Web ARchives的缩写。WAR的本质是一个zip压缩包，里面包含了class/JSP/HTML文件，将它称之为一个档案袋（archives），想想，也是有点道理的。

It provides **a management dashboard** from which you can **deploy a new web application**, or **undeploy an existing one** without having to restart the container. This is especially useful in production environments.

> 这段理解三个意思：  
> （1）Tomcat提供了一个management dashboard；  
> （2）这个management dashboard有两个主要作用：部署Web应用、移除已经部署的Web应用  
> （3）在上面的两个过程中，Tomcat不需要重新启动。


## 2. Tomcat Structure

Before we begin, we should familiarize ourselves with some **terminology** and **environment variables**.

Tomcat目录结构如下：

```txt
$CATALINA_HOME
├── bin
├── BUILDING.txt
├── conf
├── CONTRIBUTING.md
├── lib
├── LICENSE
├── logs
├── NOTICE
├── README.md
├── RELEASE-NOTES
├── RUNNING.txt
├── temp
├── webapps
└── work
```

### 2.1. Environment Variables

If you have worked with Tomcat before, these will be very familiar to you:

**$CATALINA_HOME**

This variable points to the directory where our server is installed.

**$CATALINA_BASE**

This variable points to the directory of a particular instance of Tomcat, you may have multiple instances installed. If this variable is not set explicitly, then it will be assigned the same value as `$CATALINA_HOME`.

**Web applications** are deployed under the `$CATALINA_HOME\webapps` directory.

### 2.2. Terminology

**Document root**. Refers to the top-level directory of a web application, where all the application resources are located like JSP files, HTML pages, Java classes, and images.

> Document root应该是指`$CATALINA_HOME\webapps\myapp`；而Context path应该是指`/myapp`。

**Context path**. Refers to the location which is relative to the server’s address and represents **the name of the web application**.

For example, if our web application is put under the `$CATALINA_HOME\webapps\myapp` directory, it will be accessed by the URL `http://localhost/myapp`, and its context path will be `/myapp`.

**WAR**. Is the extension of a file that packages a web application directory hierarchy in `ZIP` format and is short for **Web Archive**. Java web applications are usually packaged as `WAR` files for deployment. These files can be created on the command line or with an IDE like Eclipse.

After deploying our `WAR` file, Tomcat unpacks it and stores all project files in the `webapps` directory in a new directory named after the project.


## 3. Tomcat Setup

Tomcat: [Download](https://tomcat.apache.org/)

It is required that there is a `JDK` available on the user’s machine and that the `JAVA_HOME` environment variable is set correctly.

### 3.1. Start Tomcat

We can start the Tomcat server by simply running the startup script located at `$CATALINA_HOME\bin\startup`. There is a `.bat` and a `.sh` in every installation.

Choose the appropriate option depending on whether you are using a Windows or Unix based operating system.

### 3.2. Configure Roles

During the deployment phase, we’ll have some options, one of which is to use **Tomcat’s management dashboard**. To access this dashboard, we must have **an admin user** configured with **the appropriate roles**.

> 在文章开头的时候，说到了Tomcat management dashboard，现在要开始介绍它了。  
> 这里关于management dashboard有两个概念需要配置的地方：  
> 第一个概念是“用户”，需要给用户设置用户名和密码。  
> 第二个概念是“角色”，将角色分配给用户，用户才能有权限做事情（例如，部署WAR包文件）。

To have access to the dashboard **the admin user** needs the `manager-gui` role. Later, we will need to deploy a WAR file using Maven, for this, we need the `manager-script` role too.

> 这里介绍了Tomcat中的两个角色：  
> （1）`manager-gui`，这个角色能够访问tomcat dashboard: `http://localhost:8080/manager/html`；  
> （2）`manager-script`，这个角色能够将WAR包部署到Tomcat

Let’s make these changes in `$CATALINA_HOME\conf\tomcat-users.xml`:

```xml
<tomcat-users xmlns="http://tomcat.apache.org/xml"
              xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
              xsi:schemaLocation="http://tomcat.apache.org/xml tomcat-users.xsd"
              version="1.0">
    <!-- 添加以下三行数据 -->
    <role rolename="manager-gui"/>
    <role rolename="manager-script"/>
    <user username="admin" password="password" roles="manager-gui, manager-script"/>
</tomcat-users>
```

More details about the different Tomcat roles can be found by following [this official link](https://tomcat.apache.org/tomcat-6.0-doc/manager-howto.html).

### 3.3. Test Installation

To test that Tomcat is setup properly run the startup script (`startup.bat`/`startup.sh`), if no errors are displayed on the console we can double-check by visiting `http://localhost:8080`.

If you see the Tomcat landing page, then we have installed the server correctly.

## 4. Maven Settings

If we want to use Maven for deploying our web archives, we must configure **Tomcat** as a `server` in Maven’s `settings.xml` file.

There are two locations where the `settings.xml` file may be found:

- The Maven install: `${maven.home}/conf/settings.xml`
- A user’s install: `${user.home}/.m2/settings.xml`

Once you have found it add Tomcat as follows:

```xml
  <servers>
    <!-- 添加以下5行 -->
    <server>
      <id>TomcatServer</id>
      <username>admin</username>
      <password>password</password>
    </server>
  </servers>
```

配置说明：

- （1）`server.id`：当前值为`TomcatServer`。这个值要和在后续`pom.xml`中配置的服务器的ID值保持一致。当后续处理到`pom.xml`文件时，可以再回来过进行对照。  
- （2）`server.username`和`server.password`要与`$CATALINA_HOME\conf\tomcat-users.xml`配置的值相同。

## 5. Project

[Code Link](code/tomcat-maven-war-deployment)

### 5.1 创建项目

Run this command on the console to create a new Java web application:

```bash
mvn archetype:generate -DgroupId=lsieun -DartifactId=tomcat-maven-war-deployment -DarchetypeArtifactId=maven-archetype-quickstart -DarchetypeCatalog=internal -DinteractiveMode=false
```

> 这里我修改了原来的mvn命令：  
> （1）修改了groupId/artifactId/archetypeArtifactId
> （2）添加了`-DarchetypeCatalog=internal`

执行完`mvn`命令后，会创建`tomcat-maven-war-deployment`目录. 进入到该目录中，删除其中的`App.java`和`AppTest.java`。

```bash
find ./ -name "*.java" | xargs rm -rf
```

### 5.2 项目配置：pom.xml

接下来，我们要做的就是修改`pom.xml`文件，一共四处：

- （1）将packaging由jar修改为war。
- （2）添加Servlet 3.0的Maven依赖；  
- （3）添加tomcat-maven插件；  
- （4）添加maven-war插件

第1处修改，将packaging由jar修改为war：

```xml
<packaging>war</packaging>
```

第2处修改，添加Servlet 3.0的Maven依赖：

```xml
<dependency>
    <groupId>javax.servlet</groupId>
    <artifactId>javax.servlet-api</artifactId>
    <version>3.1.0</version>
</dependency>
```

第3处修改，添加tomcat-maven插件：

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

Note that we are using the **Tomcat 7 plugin** because it works for both versions `7` and `8` without any special changes.

The configuration `url` is the url to which we are sending our deployment, Tomcat will know what to do with it. The `server` element is **the name of the server instance** that Maven recognizes. Finally, the `path` element defines **the context path of our deployment**.

This means that if our deployment succeeds, we will access the web application by hitting `http://localhost:8080/myapp`.

配置说明：

- （1）这里使用了tomcat7-maven插件，而没有使用tomcat8-maven插件。有两方面原因，第一个原因是tomcat8-maven在`http://mvnrepository.com`仓库中没有，需要引用别的插件库；第二个原因是tomcat7-maven插件对于Tomcat8.x是兼容的。
- （2）`configureation.url`是指定要将当前应用程序部署到哪个Tomcat服务器上。同时，要注意ip和port是否准确。
- （3）`configuration.server`的值`TomcatServer`要与在Maven的`settings.xml`文件中配置的`server.id`一致。
- （4）`configuration.path`的值`/myapp`就是Web应用的`Context path`。

第4处修改，添加maven-war插件：

```xml
<plugin>            
    <groupId>org.apache.maven.plugins</groupId>
    <artifactId>maven-war-plugin</artifactId>
    <version>3.2.2</version>
    <configuration>
        <failOnMissingWebXml>false</failOnMissingWebXml>      
    </configuration>
</plugin>
```

If you are migrating from **XML-based** to **Java-based configuration** and you have removed the need for `web.xml` by implementing `WebApplicationInitializer`, simply remove the requirement for the `web.xml` file to be present.

配置说明：

- （1）maven-war的作用是根据maven项目生成war包
- （2）`<failOnMissingWebXml>false</failOnMissingWebXml>`，由于我们使用的是Servlet 3.0不需要用到web.xml文件，因此将它设置为`false`。如果设置成`true`，就会因为缺少`web.xml`文件而无法生成WAR包。

四处修改完成后，使用如下命令解析Maven依赖：

```bash
mvn dependency:resolve
```

### 5.3 项目代码: HelloServlet

```java
package lsieun;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name="hello", urlPatterns={"/hello"})
@SuppressWarnings("serial")
public class HelloServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();
        out.write("<h1>Hello World</h1>\r\n");
        out.write("<p>Mirror, mirror on the wall, who is the most beautiful woman is this world?</p>\r\n");
        out.write("<p>魔镜啊，魔镜，谁是这个世界上最漂亮的女人啊？</p>\r\n");
    }
}
```

## 6. Deploy & Test

### 6.1 Deploy From Maven to Tomcat

Now we can run the following commands from Maven.

To deploy the web app:

```bash
mvn tomcat7:deploy
```

To undeploy it:

```bash
mvn tomcat7:undeploy
```

To redeploy after making changes:

```bash
mvn tomcat7:redeploy
```

### 6.2 Browser Test

打开浏览器进行测试：

```txt
http://localhost:8080/myapp/hello
```

源码：[Code Link](code/tomcat-maven-war-deployment)