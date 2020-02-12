package lsieun.network.scalability.httpserver;

import java.net.InetSocketAddress;
import java.util.concurrent.Executors;

import com.sun.net.httpserver.HttpServer;

public class MyHttpServer {
    public static void main(String[] args) throws Exception {
        System.out.println("MyHTTPServer Started");

        HttpServer server = HttpServer.create(new InetSocketAddress(80), 0);
        //server.createContext("/index", new OtherHandler());
        server.setExecutor(Executors.newCachedThreadPool());
        server.start();
    }
}
