package lsieun.basic.e_server_socket;

import java.io.IOException;
import java.net.ServerSocket;

public class C_Close_Try_Finally_1 {
    public static final int port = 5000;

    public static void main(String[] args) {
        ServerSocket server = null;
        try {
            server = new ServerSocket(port);
            // ... work with the server socket
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (server != null) {
                try {
                    server.close();
                } catch (IOException ex) {
                    // ignore
                }
            }
        }
    }
}
