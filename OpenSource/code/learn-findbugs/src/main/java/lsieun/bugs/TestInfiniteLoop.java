package lsieun.bugs;

public class TestInfiniteLoop {
    /**
     * Infinite recursive loop
     * @return
     */
    public String resultValue() {
        return this.resultValue();
    }

    /**
     * 检测不出来
     */
    public void run() {
        int i = 10;
        while(i > 0) {
            System.out.println("Number: " + i);
            i++; // i--;
        }
    }
}
