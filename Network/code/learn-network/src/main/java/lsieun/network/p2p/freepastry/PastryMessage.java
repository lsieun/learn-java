package lsieun.network.p2p.freepastry;

import rice.p2p.commonapi.Id;
import rice.p2p.commonapi.Message;

public class PastryMessage implements Message {
    private final Id from;
    private final Id to;
    private final String messageBody;

    public PastryMessage(Id from, Id to, String messageBody) {
        this.from = from;
        this.to = to;
        this.messageBody = messageBody;
    }

    public int getPriority() {
        return Message.LOW_PRIORITY;
    }

    @Override
    public String toString() {
        return "From: " + this.from
                + " To: " + this.to
                + " [" + this.messageBody + "]";
    }
}
