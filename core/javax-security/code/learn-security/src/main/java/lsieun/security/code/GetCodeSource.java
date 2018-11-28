package lsieun.security.code;

import java.net.URL;
import java.security.CodeSigner;
import java.security.CodeSource;
import java.security.ProtectionDomain;

public class GetCodeSource {
    public static void main(String[] args) {
        try {
            //Class clazz = Class.forName("lsieun.security.code.GetCodeSource");
            Class clazz = Class.forName("com.lsieun.tank.TankSimpleClient");
            ProtectionDomain pd = clazz.getProtectionDomain();
            CodeSource cs = pd.getCodeSource();
            if (cs == null) return;

            URL url = cs.getLocation();
            System.out.println("Class Location: " + url.getFile());
            System.out.println("================================");

            CodeSigner[] codeSigners = cs.getCodeSigners();
            if (codeSigners != null && codeSigners.length > 0) {
                for (CodeSigner item : codeSigners) {
                    System.out.println(item.getSignerCertPath());
                }
            }

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
