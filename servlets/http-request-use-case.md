# HTTP请求案例

URL： 

- http://blog.51cto.com/lsieun/1775554

- Http请求案例
    - 获取请求行、请求头、实体内部的信息 [Link](code/http-request-structure)
    - 获取浏览器的类型(user-agent/header) [Link](code/http-request-browser)
    - 防止非法链接(referer/header) [Link](code/http-request-referer)
    - 获取GET方式和Post方式提交的参数 [Link](code/http-request-get-post)
    - 请求参数编码问题 [Link](code/http-request-encoding)

## 1. 获取请求行、请求头、实体内部的信息

[Link](code/http-request-structure)

```java
package lsieun;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 请求数据的请求行、请求头、实体内容
 */
@WebServlet(name="hello", urlPatterns={"/hello"})
@SuppressWarnings("serial")
public class HelloServlet extends HttpServlet{

    /**
     * 1)tomcat服务器接收到浏览器发送的请求数据，然后封装到HttpServetRequest对象中。
     * 2)tomcat服务器调用doGet方法，然后把request对象传入到servlet中。
     * 3)从request对象取出请求数据。
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
        throws ServletException, IOException {
        this.doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
        throws ServletException, IOException {
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        printRequestLine(request, response);
        printRequestHeaders(request, response);
        printRequestBody(request, response); //这里只有通过post方式提交的表单数据才能被打印出来
    }

    /* 请求行 */
    private void printRequestLine(HttpServletRequest request, HttpServletResponse response) 
            throws IOException {
        PrintWriter out = response.getWriter();

        String method = request.getMethod();
        StringBuffer url = request.getRequestURL();
        String uri = request.getRequestURI();
        String protocol = request.getProtocol();

        out.write("<h1>请求行（Request Line）</h1>");
        out.write("<strong>Method: </strong>" + method + "<br/>");
        out.write("<strong>URI: </strong>" + uri + "<br/>");
        out.write("<strong>URL: </strong>" + url + "<br/>");
        out.write("<strong>Protocol: </strong>" + protocol + "<br/>");
        out.write("<hr/>");
    }

    /* 请求头 */
    private void printRequestHeaders(HttpServletRequest request, HttpServletResponse response) 
            throws IOException {
        PrintWriter out = response.getWriter();
        out.write("<h1>请求头（Request Headers）</h1>");

        Enumeration<String> headerNames = request.getHeaderNames();
        while(headerNames.hasMoreElements()) {
            String name = headerNames.nextElement();
            String value = request.getHeader(name);
            out.write("<strong>" + name + "</strong>: " + value + "<br/>");
        }
        out.write("<hr/>");
    }

    /* 请求体 */
    private void printRequestBody(HttpServletRequest request, HttpServletResponse response) 
            throws IOException {
        PrintWriter out = response.getWriter();
        out.write("<h1>实体内容（Request Body）</h1>");

        ServletInputStream in = request.getInputStream();
        byte[] buf = new byte[1024];
        int len = 0;
        while((len = in.readLine(buf, 0, buf.length)) != -1) {
            String value = new String(buf, 0, len);
            out.write(value + "<br/>");
        }
        out.write("<hr/>");
    }
}

```

## 2. 获取浏览器的类型

获取浏览器的类型主要是依据HTTP请求头中的user-agent。[Link](code/http-request-browser)

`ContentType`可以参照`$CATALINA_HOME/conf/web.xml`文件中的内容。

```java
package lsieun;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name="hello", urlPatterns="/hello")
@SuppressWarnings("serial")
public class HelloServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
        throws ServletException, IOException {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=utf-8"); //ContentType可以参照$CATALINA_HOME/conf/web.xml文件中的内容

        StringBuilder sb = new StringBuilder();
        sb.append("您正在使用的是");
        String userAgent = request.getHeader("user-agent");

        if(userAgent.contains("Chrome")) {
            sb.append("Chrome");
        }
        else if (userAgent.contains("Firefox")) {
            sb.append("Firefox");
        }
        else if (userAgent.contains("Trident")) {
            sb.append("IE");
        }
        else {
            sb.append("未知的");
        }
        sb.append("浏览器");

        PrintWriter out = response.getWriter();
        out.write(sb.toString());
    }

}
```

## 3. 防止非法链接

防止非法链接主要是依据HTTP请求头中的referer。[Link](code/http-request-referer)

```java
package lsieun;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name="hello", urlPatterns={"/download"})
@SuppressWarnings("serial")
public class HelloServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
        throws ServletException, IOException {
        String referer = request.getHeader("referer");
        System.out.println("Referer: " + referer);

        if (referer != null && referer.contains("index.html")) {
            String filename = "汤姆和杰瑞.png";
            String encodedName = java.net.URLEncoder.encode(filename, "utf-8");
            response.setContentType("image/png");
            response.setHeader("Content-Disposition", "attachment; filename=" + encodedName);

            ServletContext ctx = this.getServletContext();
            InputStream in = ctx.getResourceAsStream("/images/tom_and_jerry.png");
            byte[] buf = new byte[1024];
            int len = 0;
            while((len = in.read(buf, 0, buf.length)) != -1) {
                response.getOutputStream().write(buf, 0, len);
            }

            in.close();

        }
        else {
            response.setCharacterEncoding("UTF-8");
            response.setContentType("text/html;charset=utf-8");
            PrintWriter out = response.getWriter();
            out.write("当前是非法链接，请回到<a href='/myapp/index.html'>首页</a>。");
        }
    }

}
```

## 4. 获取GET方式和Post方式提交的参数

[Link](code/http-request-get-post)

```java
package lsieun;

import java.io.IOException;
import java.io.InputStream;
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
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
        throws ServletException, IOException {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=utf-8");

        String queryString = request.getQueryString();
        response.getWriter().write("<h1>通过GET方式获取提交的表单参数</h1>");
		response.getWriter().write(queryString);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
        throws ServletException, IOException {
        response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=utf-8");
		response.getWriter().write("<h1>通过POST方式获取提交的表单参数</h1>");
		InputStream inStream = request.getInputStream();//获取到InputStream
		byte[] buf = new byte[1024];
		int len = 0;
		while((len = inStream.read(buf, 0, buf.length)) != -1)
		{
			String postData = new String(buf,0,len);
			response.getWriter().write(postData);
		}
    }
}

```

## 5. 请求参数编码问题

[Link](code/http-request-encoding)

**注意**：Tomcat 8对于GET的请求参数获取不需要再进行手动解码的过程了，而Tomcat 7仍然需要对GET请求进行手动解码。可能参考[http-request](http-request.md)中`2.3 请求参数编码问题`内容。

参数编码问题的原因：

- a)浏览器默认情况下以UTF-8编码向Tomcat服务器发送HTTP请求。 同样，Tomcat在接收到HTTP请求时，默认以iso-8859-1进行解码。
- b)如果HTTP请求中的数据是英文字符，由于UTF-8对iso-8859-1字符集的兼容性，因此不会出现乱码的问题。
- c)如果HTTP请求中的数据是中文字符，由于字符编码和解码的不同，就会出乱码的情况。

要解决乱码的问题，就需要进行编码的转换，思路如下：    

```
String str --> byte[] bytes --> String newString
```

- 首先，得到到Tomcat接收到的字符串str；
- 其次，用`iso-8859-1`编码方式获取到字符串str的字节数组bytes；
- 最后，用`utf-8`编码的方式将字节数组bytes转换为新的字符串newString。

GET方式和POST方式解决乱码问题的一些差异：

- 通过GET方式传递的参数，只能通过对每一个传递的参数进行编码转换。
- 通过POST方式传递的参数，除了对每个传递的参数进行编码转换之外，还有一种简单的方式，就是使用`request.setCharacterEncoding("UTF-8")`;这样后续获取参数时就不需要再进行编码转换了。

```java
package lsieun;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name="hello", urlPatterns={"/hello"})
@SuppressWarnings("serial")
public class HelloServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
        throws ServletException, IOException {
        response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		out.write("<h1>"+request.getMethod()+"方式</h1>");
		
		Enumeration<String> paramEnum =  request.getParameterNames();
		while(paramEnum.hasMoreElements())
		{
			String paramName = paramEnum.nextElement();
			if(paramName.equals("hobby"))
			{
				String[] hobbies_iso_8859_1 = request.getParameterValues("hobby");
				String[] hobbies = new String[hobbies_iso_8859_1.length];
				for(int i=0;i<hobbies_iso_8859_1.length;i++)
				{
					hobbies[i] = convertToUTF8Encoding(hobbies_iso_8859_1[i]);
				}
				out.write(paramName + ": " + Arrays.toString(hobbies) + "<br/>");
			}
			else
			{
				String paramValue_iso_8859_1 = request.getParameter(paramName);
                System.out.println(paramValue_iso_8859_1);
				String paramValue = convertToUTF8Encoding(paramValue_iso_8859_1);
				out.write(paramName + ": " + paramValue + "<br/>");
			}
			
		}
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
        throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		out.write("<h1>"+request.getMethod()+"方式</h1>");
		
		Enumeration<String> paramEnum =  request.getParameterNames();
		while(paramEnum.hasMoreElements())
		{
			String paramName = paramEnum.nextElement();
			if(paramName.equals("hobby"))
			{
				String[] hobbies = request.getParameterValues("hobby");
				out.write(paramName + ": " + Arrays.toString(hobbies) + "<br/>");
			}
			else
			{
				String paramValue = request.getParameter(paramName);
				out.write(paramName + ": " + paramValue + "<br/>");
			}
		}
    }

    private String convertToUTF8Encoding(String orginalString) throws IOException
	{
		return new String(orginalString.getBytes("iso-8859-1"),"UTF-8");
	}
}

```

- Http请求案例
    - 获取请求行、请求头、实体内部的信息 [Link](code/http-request-structure)
    - 获取浏览器的类型(user-agent/header) [Link](code/http-request-browser)
    - 防止非法链接(referer/header) [Link](code/http-request-referer)
    - 获取GET方式和Post方式提交的参数 [Link](code/http-request-get-post)
    - 请求参数编码问题 [Link](code/http-request-encoding)
