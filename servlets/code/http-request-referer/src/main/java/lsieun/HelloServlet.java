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
