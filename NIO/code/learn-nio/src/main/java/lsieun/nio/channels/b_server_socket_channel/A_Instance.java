package lsieun.nio.channels.b_server_socket_channel;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.SocketAddress;
import java.nio.channels.ServerSocketChannel;

public class A_Instance {
    public static void main(String[] args) {
        try {
            ServerSocketChannel serverChannel = ServerSocketChannel.open();
            ServerSocket socket = serverChannel.socket();
            SocketAddress address = new InetSocketAddress(80);
            socket.bind(address);
        } catch (IOException ex) {
            System.err.println("Could not bind to port 80 because " + ex.getMessage());
        }

        try {
            ServerSocketChannel server = ServerSocketChannel.open();
            SocketAddress address = new InetSocketAddress(80);
            server.bind(address);
        } catch (IOException ex) {
            System.err.println("Could not bind to port 80 because " + ex.getMessage());
        }
    }
}
