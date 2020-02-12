package lsieun.network.scalability.thread_per_request;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;

public class SimpleClient {
    public static void main(String[] args) {
        System.out.println("Client Started");
        try {
            // 第一次请求
            System.out.println("==========================");
            Socket socket = new Socket("127.0.0.1", 5000);
            System.out.println("Connected to a Server");
            PrintStream out = new PrintStream(socket.getOutputStream());
            InputStreamReader ir = new InputStreamReader(socket.getInputStream());
            BufferedReader br = new BufferedReader(ir);

            String partName = "Axle";
            out.println(partName);
            System.out.println(partName + " request sent");
            System.out.println("Response: " + br.readLine());
            socket.close();
            System.out.println("Connection Closed");
            System.out.println("==========================");

            // 第二次请求
            System.out.println("==========================");
            socket = new Socket("127.0.0.1", 5000);
            System.out.println("Connected to a Server");
            out = new PrintStream(socket.getOutputStream());
            ir = new InputStreamReader(socket.getInputStream());
            br = new BufferedReader(ir);

            partName = "Wheel";
            out.println(partName);
            System.out.println(partName + " request sent");
            System.out.println("Response: " + br.readLine());
            socket.close();
            System.out.println("Connection Closed");
            System.out.println("==========================");
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
        System.out.println("Client Terminated");
    }
}
