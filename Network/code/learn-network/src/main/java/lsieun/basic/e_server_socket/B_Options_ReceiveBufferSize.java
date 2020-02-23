package lsieun.basic.e_server_socket;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;

public class B_Options_ReceiveBufferSize {
    public static void main(String[] args) throws IOException {
        ServerSocket ss = new ServerSocket();
        int receiveBufferSize = ss.getReceiveBufferSize();
        if (receiveBufferSize < 131072) {
            ss.setReceiveBufferSize(131072);
        }
        ss.bind(new InetSocketAddress(8000));
        //...
    }
}
