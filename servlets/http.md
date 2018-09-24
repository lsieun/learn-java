# HTTP协议

URL：

- http://www.ntu.edu.sg/home/ehchua/programming/webprogramming/http_basics.html

## 1. TCP/IP Vs. HTTP

`TCP/IP`和`HTTP`的区别如下：

- `TCP/IP`协议，关注的是**客户端**与**服务器端**之间**数据是否传输成功**！
- `HTTP`协议，是在TCP/IP协议之上封装的一层协议，关注的是**数据传输的格式是否规范**。

## 2. 从Servlet.service到HttpServlet.doXXX

从Servlet到HttpServlet的继承关系：

- `Servlet` (package: `javax.servlet`, type: `Interface`)
    - `GenericServlet` (package: `javax.servlet`, type: `abstract class`)
        - `HttpServlet` (package: `javax.servlet.http`, type: `abstract class`)

在`javax.servlet.Servlet`接口中有一个service方法，如下：

```java
public void service(ServletRequest req, ServletResponse res)
```

在`javax.servlet.http.HttpServlet`类当中除了覆写`public`的`service`方法之外，还定义了自己的`protected`的`service`方法。

Tomcat服务器首先会调用Servlet的service方法，然后在service方法中再根据请求方式来分别调用对应的doXX方法。

```java
package javax.servlet.http;

import javax.servlet.GenericServlet;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public abstract class HttpServlet extends GenericServlet {

    @Override
    public void service(ServletRequest req, ServletResponse res){

        HttpServletRequest  request;
        HttpServletResponse response;

        try {
            request = (HttpServletRequest) req;
            response = (HttpServletResponse) res;
        } catch (ClassCastException e) {
            throw new ServletException("non-HTTP request or response");
        }
        service(request, response);
    }

    protected void service(HttpServletRequest req, HttpServletResponse resp){

        String method = req.getMethod();

        if (method.equals(METHOD_GET)) {
            doGet(req, resp);
        } else if (method.equals(METHOD_HEAD)) {
            doHead(req, resp);
        } else if (method.equals(METHOD_POST)) {
            doPost(req, resp);
        } else if (method.equals(METHOD_PUT)) {
            doPut(req, resp);
        } else if (method.equals(METHOD_DELETE)) {
            doDelete(req, resp);
        } else if (method.equals(METHOD_OPTIONS)) {
            doOptions(req,resp);
        } else if (method.equals(METHOD_TRACE)) {
            doTrace(req,resp);
        } else {
            resp.sendError(HttpServletResponse.SC_NOT_IMPLEMENTED, errMsg);
        }
    }    

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        //...
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        //...
    }

    //...

}

```












