# Introduction to Java Servlets

URL:

- https://www.baeldung.com/intro-to-servlets
- https://github.com/eugenp/tutorials/tree/master/javax-servlets

[TOC]

## 1. Overview

## 2. The Servlet and the Container

Simply put, a `Servlet` is a class that handles requests, processes them and reply back with a response.

**For example**, we can use a Servlet to collect input from a user through an HTML form, query records from a database, and create web pages dynamically.

`Servlets` are under the control of another Java application called a **Servlet Container**. When an application running in a web server receives a request, **the Server** hands the request to **the Servlet Container** – which in turn passes it to **the target Servlet**.

> the Server(硬件，物理机器) --> the Servlet Container(软件，例如Tomcat) --> the target Servlet

## 3. Maven Dependencies

To add Servlet support in our web app, the javax.servlet-api dependency is required:

```xml
<dependency>
    <groupId>javax.servlet</groupId>
    <artifactId>javax.servlet-api</artifactId>
    <version>3.1.0</version>
</dependency>
```

## 4. Servlet Lifecycle

Let’s go through the set of methods which define **the lifecycle of a Servlet**.

### 4.1. init()
The `init` method is designed to be called **only once**. If an instance of the servlet does not exist, **the web container**:

- Loads the servlet class 加载类文件
- Creates an instance of the servlet class 创建类实例
- Initializes it by calling the `init` method 调用init方法

The `init` method must complete successfully before the servlet can receive any requests. **The servlet container** cannot place the servlet into service if the `init` method either throws a `ServletException` or does not return within **a time period** defined by the Web server.

```java
public void init() throws ServletException {
    // Initialization code like set up database etc....
}
```

### 4.2. service()

This method is only called after the servlet’s `init()` method has completed successfully.

The Container calls the `service()` method to handle requests coming from the client, interprets the HTTP request type (GET, POST, PUT, DELETE, etc.) and calls `doGet`, `doPost`, `doPut`, `doDelete`, etc. methods as appropriate.

```java
public void service(ServletRequest request, ServletResponse response) 
  throws ServletException, IOException {
    // ...
}
```

### 4.3. destroy()

Called by **the Servlet Container** to take the Servlet out of service.

This method is only called once all threads within the servlet’s service method have exited or after a timeout period has passed. After the container calls this method, it will not call the service method again on the Servlet.

```java
public void destroy() {
    // 
}
```

## 5. Example Servlet

**Let’s now setup a full example** of handling information using a form.

To start, let’s define a servlet with a mapping `/calculateServlet` which will capture the information `POST`ed by the form and return the result using a `RequestDispatcher`:

```java
@WebServlet(name = "FormServlet", urlPatterns = "/calculateServlet")
public class FormServlet extends HttpServlet {
 
    @Override
    protected void doPost(HttpServletRequest request, 
      HttpServletResponse response)
      throws ServletException, IOException {
 
        String height = request.getParameter("height");
        String weight = request.getParameter("weight");
 
        try {
            double bmi = calculateBMI(
              Double.parseDouble(weight), 
              Double.parseDouble(height));
             
            request.setAttribute("bmi", bmi);
            response.setHeader("Test", "Success");
            response.setHeader("BMI", String.valueOf(bmi));
 
            RequestDispatcher dispatcher 
              = request.getRequestDispatcher("index.jsp");
            dispatcher.forward(request, response);
        } catch (Exception e) {
            response.sendRedirect("index.jsp");
        }
    }
 
    private Double calculateBMI(Double weight, Double height) {
        return weight / (height * height);
    }
}
```

As shown above, classes annotated with `@WebServlet` must extend the `javax.servlet.http.HttpServlet` class. It is important to note that `@WebServlet` annotation is only available from **Java EE 6** onward.

- `@WebServlet`: http://docs.oracle.com/javaee/6/api/javax/servlet/annotation/WebServlet.html
- `HttpServlet`: https://docs.oracle.com/javaee/7/api/javax/servlet/http/HttpServlet.html

The `@WebServlet` annotation is processed by **the container** at deployment time, and the corresponding servlet made available at **the specified URL patterns**. It is worth noticing that by using the annotation to define URL patterns, we can avoid using **XML deployment descriptor** named `web.xml` for our Servlet mapping.

```xml
<web-app ...>
 
    <servlet>
       <servlet-name>FormServlet</servlet-name>
       <servlet-class>com.root.FormServlet</servlet-class>
    </servlet>
    <servlet-mapping>
        <servlet-name>FormServlet</servlet-name>
        <url-pattern>/calculateServlet</url-pattern>
    </servlet-mapping>
 
</web-app>
```

Next, let’s create a basic HTML form:

```html
<form name="bmiForm" action="calculateServlet" method="POST">
    <table>
        <tr>
            <td>Your Weight (kg) :</td>
            <td><input type="text" name="weight"/></td>
        </tr>
        <tr>
            <td>Your Height (m) :</td>
            <td><input type="text" name="height"/></td>
        </tr>
        <th><input type="submit" value="Submit" name="find"/></th>
        <th><input type="reset" value="Reset" name="reset" /></th>
    </table>
    <h2>${bmi}</h2>
</form>
```




