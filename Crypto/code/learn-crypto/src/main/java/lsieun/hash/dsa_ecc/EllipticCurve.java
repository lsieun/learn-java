package lsieun.hash.dsa_ecc;

import lsieun.crypto.asym.ecc.Point;

import java.math.BigInteger;

public class EllipticCurve {
    public final BigInteger p;
    public final BigInteger a;
    public final BigInteger b;
    public final Point G;
    public final BigInteger n;
    public final BigInteger h;

    public EllipticCurve(BigInteger p, BigInteger a, BigInteger b, Point g, BigInteger n, BigInteger h) {
        this.p = p;
        this.a = a;
        this.b = b;
        this.G = g;
        this.n = n;
        this.h = h;
    }

    public EllipticCurve(BigInteger p, BigInteger a, BigInteger b, BigInteger x,BigInteger y, BigInteger n, BigInteger h) {
        this.p = p;
        this.a = a;
        this.b = b;
        this.G = new Point(x,y);
        this.n = n;
        this.h = h;
    }
}
