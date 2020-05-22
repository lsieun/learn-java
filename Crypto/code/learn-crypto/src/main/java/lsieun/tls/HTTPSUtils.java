package lsieun.tls;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class HTTPSUtils {
    public static void http_get(Connection conn, TLSParameters tls_context, String host, String path) throws IOException {
        String content = String.format("GET %s HTTP/1.1\r\nHost:%s\r\nConnection: close\r\n\r\n", path, host);
        byte[] bytes = content.getBytes(StandardCharsets.UTF_8);
        TLSUtils.tls_send(conn, tls_context, bytes);
    }

    public static void display_result(Connection conn, TLSParameters tls_context) {
        byte[] buff = new byte[1024];

        for (int len = TLSUtils.tls_recv(conn, tls_context, buff); len != -1; len = TLSUtils.tls_recv(conn, tls_context, buff)) {
            for (int i = 0; i < len; i++) {
                System.out.printf("%c", (buff[i] & 0XFF));
            }
        }

    }
}
