package lsieun.jaas.login;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.security.auth.callback.Callback;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.callback.NameCallback;
import javax.security.auth.callback.PasswordCallback;
import javax.security.auth.callback.UnsupportedCallbackException;

public class ConsoleCallbackHandler implements CallbackHandler {
    public void handle(Callback[] callbacks) throws IOException, UnsupportedCallbackException {
        for (int i = 0; i < callbacks.length; i++) {
            if (callbacks[i] instanceof NameCallback) {
                NameCallback nameCb = (NameCallback)callbacks[i];
                System.out.print(nameCb.getPrompt());
                String user=(new BufferedReader(new InputStreamReader(System.in))).readLine();
                nameCb.setName(user);
            } else if (callbacks[i] instanceof PasswordCallback) {
                PasswordCallback passCb = (PasswordCallback)callbacks[i];
                System.out.print(passCb.getPrompt());
                String pass=(new BufferedReader(new InputStreamReader(System.in))).readLine();
                passCb.setPassword(pass.toCharArray());
            } else {
                throw(new UnsupportedCallbackException(callbacks[i], "Callback class not supported"));
            }
        }
    }
}
