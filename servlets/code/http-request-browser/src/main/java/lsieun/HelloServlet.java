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
