package lsieun.jsr305;

public class Hello {
    // Infinite recursive loop
    public String resultValue() {
        return this.resultValue();
    }

    // Null Pointer Exception
    public void testNullExp() {
        Hello h = null;
        h.resultValue();
    }

    // Null Pointer Exception
    public void testNullExp(String str, Object obj) {
        if ((str == null && obj == null) || str.equals(obj)) {
            //do something
        }
    }

    // Method whose return value should not be ignored
    public void testUpper() {
        String str = "Java";

        str.toUpperCase();

        if (str.equals("JAVA")) {
            System.out.println("Equal");
        }
        else {
            System.out.println("Not");
        }
    }

    // Suspicious equal() comparison
    public void testEqual() {
        Integer value = new Integer(10);
        String str = new String("10");
        if (str != null && !str.equals(value)) {
            //do something;
        }
    }
}
