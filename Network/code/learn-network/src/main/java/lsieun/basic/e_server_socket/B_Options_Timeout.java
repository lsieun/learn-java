package lsieun.basic.e_server_socket;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;

public class B_Options_Timeout {
    public static final int port = 5000;

    public static void main(String[] args) {
        try (ServerSocket server = new ServerSocket(port)) {
            server.setSoTimeout(30000); // block for no more than 30 seconds
            try {
                Socket s = server.accept();
                // handle the connection
                // ...
            } catch (SocketTimeoutException ex) {
                System.err.println("No connection within 30 seconds");
            }
        } catch (IOException ex) {
            System.err.println("Unexpected IOException: " + ex);
        }
    }

    public void printSoTimeout(ServerSocket server) throws IOException {
        int timeout = server.getSoTimeout();
        if (timeout > 0) {
            System.out.println(server + " will time out after "
                    + timeout + "milliseconds.");
        } else if (timeout == 0) {
            System.out.println(server + " will never time out.");
        } else {
            System.out.println("Impossible condition occurred in " + server);
            System.out.println("Timeout cannot be less than zero." );
        }
    }
}
