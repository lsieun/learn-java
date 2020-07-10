package lsieun.tls.utils;

import java.io.*;
import java.net.Socket;

public class TLSConnection implements Closeable {
    public final Socket socket;
    public final InputStream in;
    public final OutputStream out;

    public TLSConnection(Socket socket) {
        this.socket = socket;

        try {
            this.in = new BufferedInputStream(socket.getInputStream());
            this.out = new BufferedOutputStream(socket.getOutputStream());
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    public void send(byte[] data) throws IOException {
        DisplayUtils.display_record(data);
        this.out.write(data);
        this.out.flush();
    }

    @Override
    public void close() throws IOException {
        if (socket != null) {
            socket.close();
        }
    }
}
