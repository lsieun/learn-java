package lsieun.rmi.search;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Search extends Remote {
    String query(String search) throws RemoteException;
}
