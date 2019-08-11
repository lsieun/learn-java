package lsieun.reflection.b_method.b_get_one_method;

class Example {
    public double publicSum(int a, double b) {
        return a + b;
    }

    public static double publicStaticMultiply(float a, long b) {
        return a * b;
    }

    private boolean privateAnd(boolean a, boolean b) {
        return a && b;
    }

    protected int protectedMax(int a, int b) {
        return a > b ? a : b;
    }
}
