package lsieun.jaas.login;

import javax.security.auth.login.LoginContext;

public class App {
    public static void main(String[] args) throws Exception {

        String path = App.class.getClassLoader().getResource("jaas.config").toURI().getPath();
        System.out.println(path);
        System.setProperty("java.security.auth.login.config", path);

        ConsoleCallbackHandler cbh = new ConsoleCallbackHandler();
        LoginContext lc = new LoginContext("Example", cbh);
        lc.login();
    }
}
