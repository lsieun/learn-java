package lsieun.jaas.sample;

import java.net.URISyntaxException;
import java.security.Principal;
import java.util.Set;

import javax.security.auth.Subject;
import javax.security.auth.login.LoginContext;
import javax.security.auth.login.LoginException;

// https://www.cs.mun.ca/java-api-1.5/guide/security/jaas/tutorials/GeneralAcnOnly.html
public class SampleAcn {
    public static void main(String[] args) {
        try {
            String path = SampleAcn.class.getClassLoader().getResource("sample_jaas.config").toURI().getPath();
            System.out.println(path);
            System.setProperty("java.security.auth.login.config", path);
        } catch (URISyntaxException e) {
            e.printStackTrace();
            System.exit(-1);
        }


        LoginContext lc = null;
        try {
            lc = new LoginContext("Sample", new MyCallbackHandler());
        } catch (LoginException le) {
            System.err.println("Cannot create LoginContext. " + le.getMessage());
            System.exit(-1);
        } catch (SecurityException se) {
            System.err.println("Cannot create LoginContext. " + se.getMessage());
            System.exit(-1);
        }

        // the user has 3 attempts to authenticate successfully
        int i;
        for (i = 0; i < 3; i++) {
            try {

                // attempt authentication
                lc.login();

                // if we return with no exception, authentication succeeded
                break;

            } catch (LoginException le) {

                System.err.println("Authentication failed:");
                System.err.println("  " + le.getMessage());
                try {
                    Thread.currentThread().sleep(3000);
                } catch (Exception e) {
                    // ignore
                }

            }
        }

        // did they fail three times?
        if (i == 3) {
            System.out.println("Sorry");
            System.exit(-1);
        }

        System.out.println("Authentication succeeded!");

        Subject subject = lc.getSubject();
        Set<Principal> principals = subject.getPrincipals();
        for (Principal p : principals) {
            System.out.println("\t" + p.getName());
        }
    }
}
