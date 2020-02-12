package lsieun.network.udp.bio;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UDPServer {
    public UDPServer() {
        System.out.println("UDP Server Started");
        try (DatagramSocket serverSocket = new DatagramSocket(9003)) {
            while (true) {
                // (1) Receive
                byte[] receiveMessage = new byte[1024];
                DatagramPacket receivePacket = new DatagramPacket(receiveMessage, receiveMessage.length);
                // This method blocks until a datagram is received.
                serverSocket.receive(receivePacket);

                // (2) Display
                String message = new String(receivePacket.getData(),0, receivePacket.getLength());
                System.out.println(
                        "Received from client: [" + message + "]"
                        + "\nFrom: " + receivePacket.getAddress()
                );

                // (3) Reply
                InetAddress inetAddress = receivePacket.getAddress();
                int port = receivePacket.getPort();
                byte[] sendMessage;
                sendMessage = message.getBytes();

                DatagramPacket sendPacket = new DatagramPacket(sendMessage, sendMessage.length, inetAddress, port);
                serverSocket.send(sendPacket);
            }
        }
        catch (IOException ex) {}
        System.out.println("UDP Server Terminating");
    }

    public static void main(String[] args) {
        new UDPServer();
    }
}
