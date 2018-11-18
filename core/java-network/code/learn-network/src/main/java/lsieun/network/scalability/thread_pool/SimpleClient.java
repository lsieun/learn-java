package lsieun.network.scalability.thread_pool;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;

public class SimpleClient {
    public static void main(String[] args) {
        System.out.println("Client Started");

        try (Socket socket = new Socket("127.0.0.1", 5000)) {
            System.out.println("Connected to a Server");
            PrintStream out = new PrintStream(socket.getOutputStream());
            InputStreamReader ir = new InputStreamReader(socket.getInputStream());
            BufferedReader br = new BufferedReader(ir);

            String partName = "Axle";
            out.println(partName);
            System.out.println(partName + " request sent");
            System.out.println("Response: " + br.readLine());

            socket.close();
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }

        System.out.println("Client Terminated");
    }
}
