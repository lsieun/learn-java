package lsieun.rmi.search;

import java.rmi.Naming;

public class Server {
    public static void main(String[] args) throws Exception {
        Search obj = new SearchQuery();
//        LocateRegistry.createRegistry(1900);
        Naming.rebind("rmi://localhost:6000/geeksforgeeks", obj);
    }
}
