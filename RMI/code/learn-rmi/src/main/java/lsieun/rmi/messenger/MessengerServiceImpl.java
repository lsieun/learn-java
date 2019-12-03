package lsieun.rmi.messenger;

public class MessengerServiceImpl implements MessengerService {
    @Override
    public String sendMessage(String clientMessage) {
        return "Client Message".equals(clientMessage) ? "Server Message" : null;
    }

    public String unexposedMethod() {
        return "Hello World";
    }

}
