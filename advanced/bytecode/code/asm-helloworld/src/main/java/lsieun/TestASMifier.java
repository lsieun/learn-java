package lsieun;

import org.objectweb.asm.util.ASMifier;

public class TestASMifier {
    public static void main(String[] args) {
        try {
            ASMifier.main(new String[]{JavaHelloWorld.class.getName()});
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
