package lsieun.jaas.sample;

import java.net.URISyntaxException;
import java.security.Principal;
import java.security.PrivilegedAction;
import java.util.Iterator;

import javax.security.auth.Subject;
import javax.security.auth.login.LoginContext;
import javax.security.auth.login.LoginException;

// https://www.cs.mun.ca/java-api-1.5/guide/security/jaas/tutorials/GeneralAcnAndAzn.html
public class SampleAzn {
    public static void main(String[] args) {
        try {
            String authFilePath = SampleAzn.class.getClassLoader().getResource("sample_jaas.config").toURI().getPath();
            System.out.println(authFilePath);
            System.setProperty("java.security.auth.login.config", authFilePath);

            String authorizationFilePath = SampleAzn.class.getClassLoader().getResource("sampleazn.policy").toURI().getPath();
            System.out.println(authorizationFilePath);
            System.setProperty("Djava.security.policy", authorizationFilePath);
        } catch (Exception e) {
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

        Subject mySubject = lc.getSubject();

        // let's see what Principals we have
        Iterator principalIterator = mySubject.getPrincipals().iterator();
        System.out.println("Authenticated user has the following Principals:");
        while (principalIterator.hasNext()) {
            Principal p = (Principal)principalIterator.next();
            System.out.println("\t" + p.toString());
        }

        System.out.println("User has " + mySubject.getPublicCredentials().size() + " Public Credential(s)");

        // now try to execute the SampleAction as the authenticated Subject
        PrivilegedAction action = new SampleAction();
        Subject.doAsPrivileged(mySubject, action, null);

        System.exit(0);
    }
}
