import java.rmi.Remote;
import java.rmi.RemoteException;

public interface FlightService extends Remote {
    public String getArrivalTime(String flightNo) throws RemoteException;
}
