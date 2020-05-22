package lsieun.tls;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;

public class HTTPS {
    public static void main(String[] args) {
        String host = "127.0.0.1";
        String path = "/index.html";
        int port = TLSConst.HTTPS_PORT;

        try (
                Socket client = new Socket();
        ) {
            SocketAddress address = new InetSocketAddress(host, port);
            client.connect(address, 5000);
            Connection conn = new Connection(client);
            System.out.println("Connection complete; negotiating TLS parameters");

            TLSParameters tls_context = new TLSParameters();
            TLSUtils.tls_connect(conn, tls_context);
            System.out.println("Retrieving document: " + path);

            HTTPSUtils.http_get(conn, tls_context, host, path);
            HTTPSUtils.display_result(conn, tls_context);

            TLSUtils.tls_shutdown();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
