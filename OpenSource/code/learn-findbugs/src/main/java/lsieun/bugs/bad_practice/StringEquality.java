package lsieun.bugs.bad_practice;

public class StringEquality {
    public void testString() {
        String str1 = "abc";
        String str2 = "abc";

        if (str1 == str2) {
            System.out.println("YES");
        }

        if(str1 != str2) {
            System.out.println("NO");
        }
    }

    /**
     * Suspicious equal() comparison
     */
    public void testEqual() {
        Integer value = new Integer(10);
        String str = new String("10");
        if (str != null && !str.equals(value)) {
            //do something;
        }
    }
}
