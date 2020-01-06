package lsieun.generic.bean;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

public class Pair<X, Y> {
    private X first;
    private Y second;

    public Pair(Class<X> typeA, Class<Y> typeB) throws IllegalAccessException, InstantiationException {
        this.first = typeA.newInstance();
        this.second = typeB.newInstance();
    }

    public Pair(X first, Y second) {
        this.first = first;
        this.second = second;
    }

    public X getFirst() {
        return first;
    }

    public void setFirst(X first) {
        this.first = first;
    }

    public Y getSecond() {
        return second;
    }

    public void setSecond(Y second) {
        this.second = second;
    }

//    public <A super X, B super Y> B addToMap(Map<A,B> map) {  // error: type parameter cannot have lower bound
//        return map.put(first, second);
//    }

    public Object addToMap(Map<? super X, ? super Y> map) {
        return map.put(first, second);
    }

    @Override
    public String toString() {
        return "Pair{" +
                "first=" + first +
                ", second=" + second +
                '}';
    }

    public static void main(String[] args) {
        try {
            Pair<A, B> p = new Pair<>(A.class, B.class);
            System.out.println(p);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
    }
}
