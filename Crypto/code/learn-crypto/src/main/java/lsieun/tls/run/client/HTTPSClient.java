package lsieun.tls.run.client;

import lsieun.tls.cst.TLSConst;
import lsieun.tls.param.TLSClientParameters;
import lsieun.tls.run.server.HTTPSUtils;
import lsieun.tls.utils.DisplayUtils;
import lsieun.tls.utils.TLSClientUtils;
import lsieun.tls.utils.TLSConnection;
import lsieun.tls.utils.TLSUtils;
import lsieun.utils.ByteUtils;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;

public class HTTPSClient {
    public static void main(String[] args) {
        String host = "220.181.38.150";
        String path = "/index.html";
        int port = TLSConst.HTTPS_PORT;

        try (
                Socket client = new Socket();
        ) {
            SocketAddress address = new InetSocketAddress(host, port);
            client.connect(address, 5000);
            TLSConnection conn = new TLSConnection(client);
            System.out.println("Connection complete; negotiating TLS parameters");

            TLSClientParameters tls_context = new TLSClientParameters();
            TLSClientUtils.send_client_hello(conn, tls_context);

            byte[] server_hello_bytes = TLSUtils.receive_plain_message(conn);
            DisplayUtils.display_record(server_hello_bytes);

            byte[] certificate_bytes = TLSUtils.receive_plain_message(conn);
            DisplayUtils.display_record(certificate_bytes);

            byte[] server_key_exchange_bytes = TLSUtils.receive_plain_message(conn);
            DisplayUtils.display_record(server_key_exchange_bytes);

            byte[] server_hello_done_bytes = TLSUtils.receive_plain_message(conn);
            DisplayUtils.display_record(server_hello_done_bytes);

//            TLSClientUtils.tls_connect(conn, tls_context);
//            System.out.println("Retrieving document: " + path);

//            HTTPSUtils.http_get(conn, tls_context, host, path);
//            HTTPSUtils.display_result(conn, tls_context);
//
//            TLSUtils.tls_shutdown(conn, tls_context);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
