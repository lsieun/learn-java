package lsieun.z_test;

import lsieun.utils.ByteUtils;

import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import java.io.*;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class SocketTest {
    public static final String DOMAIN_NAME = "www.baidu.com";
    private static final InetSocketAddress address;

    static {
        try {
            InetAddress inetAddress = InetAddress.getByName(DOMAIN_NAME);
            address = new InetSocketAddress(inetAddress, 443);
        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) throws Exception {
        List<String> lines = getRequestContent();
        Optional<byte[]> result = fetch(lines);
        if (result.isPresent()) {
            byte[] bytes = result.get();
            String content = new String(bytes, StandardCharsets.UTF_8);
            System.out.println(content);
        }
        else {
            System.out.println("NO");
        }
    }

    public static List<String> getRequestContent() {
        List<String> list = new ArrayList<>();
        list.add("GET / HTTP/1.1");
        list.add("Host: " + DOMAIN_NAME);
        list.add("Connection: close");
        list.add("User-Agent: Mozilla/5.0");
        list.add("Accept: text/html");
        list.add("Accept-Language: en-US");
        return list;
    }

    public static Optional<byte[]> fetch(List<String> list) {
        SSLSocketFactory factory = (SSLSocketFactory) SSLSocketFactory.getDefault();
        try (
                Socket s = factory.createSocket();
        ) {
            if (s instanceof SSLSocket) {
                System.out.println("Hello");
                SSLSocket ss = (SSLSocket) s;
                ss.setEnabledCipherSuites(new String[]{
                        "TLS_RSA_WITH_AES_128_CBC_SHA"
                });
            }
            s.setSoTimeout(120000);
            s.connect(address, 20000);
            try (
                    InputStream in = s.getInputStream();
                    BufferedInputStream bin = new BufferedInputStream(in);
                    OutputStream out = s.getOutputStream();
                    BufferedOutputStream bout = new BufferedOutputStream(out);
                    OutputStreamWriter writer = new OutputStreamWriter(bout)
            ) {
                for (String line : list) {
                    writer.write(line);
                    writer.write("\r\n");
                }
                writer.write("\r\n\r\n");
                writer.flush();

                byte[] marks = new byte[4];
                for (int value = bin.read(); value != -1; value = bin.read()) {
                    byte b = (byte) value;
                    marks[0] = marks[1];
                    marks[1] = marks[2];
                    marks[2] = marks[3];
                    marks[3] = b;
                    if (marks[0] == '\r' && marks[1] == '\n' && marks[2] == '\r' && marks[3] == '\n') {
                        break;
                    }
                }

                ByteArrayOutputStream bao = new ByteArrayOutputStream();
                byte[] buff = new byte[256 * 1024];
                for (int len = bin.read(buff); len != -1; len = bin.read(buff)) {
                    bao.write(buff, 0, len);
                }
                byte[] bytes = bao.toByteArray();
                return Optional.of(bytes);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }
}
