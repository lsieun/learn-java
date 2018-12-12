package lsieun.bugs.dodgy;


public class TestNullPointer {
    /**
     * Null Pointer Exception
     */
    public void testNullExp() {
        TestNullPointer h = null;
        h.toString();
    }

    /**
     * Null Pointer Exception
     * @param str
     * @param obj
     */
    public void testNullExp(String str, Object obj) {
        if ((str == null && obj == null) || str.equals(obj)) {
            //do something
        }
    }
}
