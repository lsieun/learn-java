package lsieun.network.udp.multicasting;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.NetworkInterface;
import java.net.StandardSocketOptions;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.nio.channels.MembershipKey;

public class ChannelMulticastClient {
    public static void main(String[] args) {
        try {
            System.setProperty("java.net.preferIPv6Stack", "true");
            NetworkInterface ni = NetworkInterface.getByName("wlp3s0");

            DatagramChannel channel = DatagramChannel.open()
                    .bind(new InetSocketAddress(9003))
                    .setOption(StandardSocketOptions.IP_MULTICAST_IF, ni);
            InetAddress group = InetAddress.getByName("FF01:0:0:0:0:0:0:FC");
            MembershipKey key = channel.join(group, ni);
            System.out.println("Join Multicast Group: " + key);
            System.out.println("Waiting for a message...");

            ByteBuffer buffer = ByteBuffer.allocate(1024);
            channel.receive(buffer);

            buffer.flip();
            System.out.print("Received: [");
            StringBuilder message = new StringBuilder();
            while (buffer.hasRemaining()) {
                message.append((char)buffer.get());
            }
            System.out.println(message + "]");

            key.drop();
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
