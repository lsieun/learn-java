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
