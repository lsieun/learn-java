package lsieun.tls;

import java.io.*;
import java.net.Socket;

public class Connection {
    public final InputStream in;
    public final OutputStream out;

    public Connection(InputStream in, OutputStream out) {
        this.in = in;
        this.out = out;
    }

    public Connection(Socket s) throws IOException {
        this.in = new BufferedInputStream(s.getInputStream());
        this.out = new BufferedOutputStream(s.getOutputStream());
    }
}
