package lsieun.basic.d_socket;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class A_Instance_Connecting {
    public static void main(String[] args) {
        try {
            Socket toOReilly = new Socket("www.oreilly.com", 80);
            // send and receive data...
        } catch (UnknownHostException ex) {
            System.err.println(ex);
        } catch (IOException ex) {
            System.err.println(ex);
        }
    }
}
