package lsieun.security.code;

import java.net.URL;
import java.security.CodeSource;
import java.security.ProtectionDomain;

import com.lsieun.tank.TankSimpleClient;


public class GetProtectionDomain {
    public static void main(String[] args) {
        //ProtectionDomain domain = GetProtectionDomain.class.getProtectionDomain();
        ProtectionDomain domain = TankSimpleClient.class.getProtectionDomain();
        System.out.println("Protection domain: " + domain);
        System.out.println("============================\n");

        CodeSource source = domain.getCodeSource();
        System.out.println("Code source: " + source);
        System.out.println("============================\n");

        if (source != null) {
            URL location = source.getLocation();
            System.out.println("Location: " + location);

        } else {
            System.out.println("Code source is null");
        }
    }
}
