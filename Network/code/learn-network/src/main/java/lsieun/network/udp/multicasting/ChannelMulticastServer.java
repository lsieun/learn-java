package lsieun.network.udp.multicasting;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.NetworkInterface;
import java.net.StandardSocketOptions;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;

public class ChannelMulticastServer {
    public static void main(String[] args) {
        try {
            System.setProperty("java.net.preferIPv6Stack", "true");
            DatagramChannel channel = DatagramChannel.open();
            NetworkInterface ni = NetworkInterface.getByName("wlp3s0");
            channel.setOption(StandardSocketOptions.IP_MULTICAST_IF, ni);
            InetSocketAddress group = new InetSocketAddress("FF01:0:0:0:0:0:0:FC", 9003);

            String message = "The message";
            ByteBuffer buffer = ByteBuffer.allocate(message.length());
            buffer.put(message.getBytes());

            while(true) {
                channel.send(buffer, group);
                System.out.println("Sent the multicast message: " + message);
                buffer.clear();

                buffer.mark();
                System.out.print("Sent: [");
                StringBuilder msg = new StringBuilder();
                while(buffer.hasRemaining()) {
                    msg.append((char)buffer.get());
                }
                System.out.println(msg + "]");
                buffer.reset();

                Thread.sleep(1000);
            }
        }
        catch (IOException | InterruptedException ex) {
            ex.printStackTrace();
        }
    }
}
