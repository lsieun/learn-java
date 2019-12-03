package lsieun.rmi.flight;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;
import java.util.Map;

public class FlightServiceImpl extends UnicastRemoteObject implements FlightService {
    private Map<String, String> arrivals = new HashMap<String, String>();

    public FlightServiceImpl() throws RemoteException {
        super();
        arrivals.put("AAA", "3:20PM");
        arrivals.put("BBB", "8:00PM");
        arrivals.put("CCC", "6:05PM");
    }

    public String getArrivalTime(String flightNo) throws RemoteException {
        String arrTime = null;
        arrTime = (String) arrivals.get(flightNo);
        if (arrTime == null) {
            throw new RemoteException("Flight number " + flightNo +
                    " does not exist");
        }
        return arrTime;
    }
}
