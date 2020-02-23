package lsieun.basic.e_server_socket;

import java.io.IOException;
import java.net.ServerSocket;

public class C_Close_Try_With_Resource {
    public static final int port = 5000;

    public static void main(String[] args) {
        try (ServerSocket server = new ServerSocket(port)) {
            // ... work with the server socket
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
