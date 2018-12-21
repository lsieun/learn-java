package lsieun.rmi.messenger;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Client {
    public static void main(String[] args) throws Exception {
        Registry registry = LocateRegistry.getRegistry();
        MessengerService server = (MessengerService) registry.lookup("MessengerService");
        String responseMessage = server.sendMessage("Client Message");
        System.out.println(responseMessage);
    }
}
