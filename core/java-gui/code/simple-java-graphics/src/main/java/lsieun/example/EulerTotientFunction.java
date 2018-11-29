package lsieun.example;


import java.util.ArrayList;
import java.util.List;

import lsieun.graphics.Color;
import lsieun.graphics.Ellipse;
import lsieun.graphics.Rectangle;

// https://en.wikipedia.org/wiki/Euler%27s_totient_function
public class EulerTotientFunction {
    public static void main(String[] args) {
        int maxNum = 500;
        Rectangle box = new Rectangle(0, 0, maxNum, maxNum);
        box.draw();

        List<Integer> primeList = findPrime(maxNum);

        for (int x=2; x < maxNum; x++) {
            for (Integer y : primeList) {
                if (y >= x) break;
                if (x % y != 0) {
                    System.out.println("x = " + x + ", y = " + y);
                    Ellipse egg = new Ellipse(x, maxNum - y, 2, 2);
                    egg.setColor(Color.YELLOW);
                    egg.fill();
                }

            }
        }
    }

    public static boolean hasSamePrime(List<Integer> list1, List<Integer> list2) {
        boolean flag = false;

        int i = 0;
        int j = 0;

        while (i < list1.size() && j < list2.size()) {
            Integer value1 = list1.get(i);
            Integer value2 = list2.get(j);

            if (value1.equals(value2))  {
                flag = true;
                break;
            }

            if (value1 < value2) {
                i++;
            }
            else {
                j++;
            }
        }

        return flag;
    }

    public static List<Integer> findPrime(int num) {
        List<Integer> list = new ArrayList<Integer>();

        for (int i=2; i<num; i++) {
            if (isPrime(i)) {
                int quotient = num % i;
                if (quotient != 0) {
                    list.add(i);
                }
            }
        }

        return list;
    }


    //checks whether an int is prime or not.
    public static boolean isPrime(int n) {
        //check if n is a multiple of 2
        if (n%2==0) return false;
        //if not, then just check the odds
        for(int i=3;i*i<=n;i+=2) {
            if(n%i==0)
                return false;
        }
        return true;
    }
}
