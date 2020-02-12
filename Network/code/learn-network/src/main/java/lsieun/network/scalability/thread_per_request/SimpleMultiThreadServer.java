package lsieun.network.scalability.thread_per_request;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.NumberFormat;
import java.util.concurrent.ConcurrentHashMap;

@SuppressWarnings("Duplicates")
public class SimpleMultiThreadServer implements Runnable {
    private static ConcurrentHashMap<String, Float> map;
    private Socket clientSocket;

    static {
        map = new ConcurrentHashMap<>();
        map.put("Axle", 238.50f);
        map.put("Gear", 45.55f);
        map.put("Wheel", 86.30f);
        map.put("Rotor", 8.50f);
    }

    SimpleMultiThreadServer(Socket socket) {
        this.clientSocket = socket;
    }

    public static void main(String[] args) {
        System.out.println("Multi-Threaded Server Started");
        System.out.println("##########################");
        try {
            ServerSocket serverSocket = new ServerSocket(5000);
            while (true) {
                System.out.println("Listening for a client connection");
                Socket socket = serverSocket.accept();
                System.out.println("Connected to a Client");
                new Thread(
                        new SimpleMultiThreadServer(socket)
                ).start();
            }
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
        System.out.println("##########################");
        System.out.println("Multi-Threaded Server Terminated");
    }

    @Override
    public void run() {
        System.out.println("==========================");
        System.out.println("Client Thread Started");
        try (
                BufferedReader br = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                PrintStream out = new PrintStream(clientSocket.getOutputStream())
        ) {
            String partName = br.readLine();
            float price = map.get(partName);
            out.println(price);
            NumberFormat nf = NumberFormat.getCurrencyInstance();
            System.out.println("Request for " + partName + " and returned a price of " + nf.format(price));
            clientSocket.close();
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
        System.out.println("Client Connection Terminated");
        System.out.println("==========================");
        System.out.println();
    }
}
