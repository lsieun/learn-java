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
