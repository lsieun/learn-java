package lsieun.basic.e_server_socket;

import java.io.IOException;
import java.net.ServerSocket;

public class B_Options_ReuseAddress {
    public static void main(String[] args) throws IOException {
        ServerSocket ss = new ServerSocket(10240);
        System.out.println("Reusable: " + ss.getReuseAddress());
    }
}
