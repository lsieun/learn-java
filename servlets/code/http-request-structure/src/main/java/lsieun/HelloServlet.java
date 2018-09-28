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

@WebServlet(name="hello", urlPatterns={"/hello"})
@SuppressWarnings("serial")
public class HelloServlet extends HttpServlet{

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        printRequestLine(request, response);
        printRequestHeaders(request, response);
        printRequestBody(request, response);
    }

    private void printRequestLine(HttpServletRequest request, HttpServletResponse response) throws IOException {
        PrintWriter out = response.getWriter();

        String method = request.getMethod();
        StringBuffer url = request.getRequestURL();
        String uri = request.getRequestURI();
        String protocol = request.getProtocol();

        out.write("<h1>请求行（Request Line）</h1>");
        out.write("<strong>Method: </strong>" + method);
        out.write("<strong>URI:</strong>" + uri);
        out.write("<strong>URL:</strong>" + url);
        out.write("<strong>Protocol:</strong>" + protocol);
        out.write("<hr/>");
    }
    private void printRequestHeaders(HttpServletRequest request, HttpServletResponse response) throws IOException {
        PrintWriter out = response.getWriter();
        out.write("<h1>请求头（Request Headers）</h1>");

        Enumeration<String> headerNames = request.getHeaderNames();
        while(headerNames.hasMoreElements()) {
            String name = headerNames.nextElement();
            String value = request.getHeader(name);
            out.write("<strong>" + name + "</strong>" + value + "<br/>");
        }
        out.write("<hr/>");
    }

    private void printRequestBody(HttpServletRequest request, HttpServletResponse response) throws IOException {
        PrintWriter out = response.getWriter();
        out.write("<h1>实体内容（Request Body）</h1>");

        ServletInputStream in = request.getInputStream();
        byte[] buf = new byte[1024];
        int len = 0;
        while((len = in.readLine(buf, 0, buf.length)) != -1) {
            String value = new String(buf, 0, len);
            out.write(value);
        }
        out.write("<hr/>");
    }
}
