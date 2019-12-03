import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;

public class Server {
    public static void main(String[] args) {
        try {
            FlightServiceImpl fsi = new FlightServiceImpl();
            Naming.rebind("rmi://localhost:6000/FlightService", fsi);
            System.out.println("FlightService is waiting for the requests on port 6000...");
        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }
}
