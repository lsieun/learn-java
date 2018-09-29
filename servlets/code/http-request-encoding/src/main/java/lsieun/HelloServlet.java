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
