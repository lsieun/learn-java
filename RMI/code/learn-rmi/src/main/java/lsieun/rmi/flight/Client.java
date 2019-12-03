package lsieun.rmi.flight;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class Client {
    public static void main(String[] args) {
        try {
            FlightService service = (FlightService) Naming.lookup("rmi://localhost:6000/FlightService");
            String arrival = service.getArrivalTime("AAA");
            System.out.println("Arrival time is " + arrival);
        } catch (NotBoundException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
}
