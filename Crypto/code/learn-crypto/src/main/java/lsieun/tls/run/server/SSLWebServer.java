package lsieun.tls.run.server;

import lsieun.tls.cst.TLSConst;
import lsieun.tls.utils.TLSConnection;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class SSLWebServer {
    public static void process_https_request(Socket socket) {
        TLSConnection conn = new TLSConnection(socket);
        RequestProcessor t = new RequestProcessor(conn);
        t.start();
    }

    public static void main(String[] args) {
        int port = TLSConst.HTTPS_PORT;
        try (ServerSocket server = new ServerSocket(port)) {
            while (true) {
                Socket socket = server.accept();
                process_https_request(socket);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
