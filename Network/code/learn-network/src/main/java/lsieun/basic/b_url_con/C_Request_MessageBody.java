package lsieun.basic.b_url_con;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;

public class C_Request_MessageBody {
    public static void main(String[] args) {
        try {
            URL u = new URL("http://www.somehost.com/cgi-bin/acgi");
            // open the connection and prepare it to POST
            URLConnection uc = u.openConnection();
            uc.setDoOutput(true);
            OutputStream raw = uc.getOutputStream();
            OutputStream buffered = new BufferedOutputStream(raw);
            OutputStreamWriter out = new OutputStreamWriter(buffered, "8859_1");
            out.write("first=Julie&middle=&last=Harting&work=String+Quartet\r\n");
            out.flush();
            out.close();
        } catch (IOException ex) {
            System.err.println(ex);
        }
    }
}
