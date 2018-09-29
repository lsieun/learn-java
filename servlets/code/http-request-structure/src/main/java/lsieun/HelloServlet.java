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
