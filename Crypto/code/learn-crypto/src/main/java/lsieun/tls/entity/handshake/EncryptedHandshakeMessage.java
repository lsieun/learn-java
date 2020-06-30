package lsieun.tls.entity.handshake;

public class EncryptedHandshakeMessage extends Handshake {

    public final byte[] encrypted_message;

    public EncryptedHandshakeMessage(byte[] encrypted_message) {
        super(HandshakeType.FINISHED);
        this.encrypted_message = encrypted_message;
    }

    @Override
    public byte[] getData() {
        return encrypted_message;
    }
}
