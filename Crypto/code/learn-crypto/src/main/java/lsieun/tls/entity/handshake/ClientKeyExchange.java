package lsieun.tls.entity.handshake;

public class ClientKeyExchange extends Handshake {

    public final byte[] data;

    public ClientKeyExchange(byte[] data) {
        super(HandshakeType.CLIENT_KEY_EXCHANGE);
        this.data = data;
    }

    @Override
    public byte[] getData() {
        return this.data;
    }
}
