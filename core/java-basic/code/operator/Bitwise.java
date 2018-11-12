public class Bitwise {

    public void and() {
        int a = 60;
        int b = 13;
        int result = a & b;
    }

    public void or() {
        int a = 60;
        int b = 13;
        int result = a | b;
    }

    public void xor() {
        int a = 60;
        int b = 13;
        int result = a ^ b;
    }

    public void compliment() {
        int a = 60;
        int result = ~a;
    }

    public void leftShift() {
        int a = 60;
        int result = a << 2;
    }

    public void rightShift() {
        int a = 60;
        int result = a >> 2;
    }

    public void zeroFillRightShift() {
        int a = 60;
        int result = a >>> 2;
    }
}
