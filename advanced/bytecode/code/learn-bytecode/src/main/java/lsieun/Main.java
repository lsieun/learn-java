package lsieun;

public class Main {
    private static int[] numbers = new int[3] ;

    static {
        numbers[0] = 1;
        numbers[1] = 2;
        numbers[2] = 3;
    }

    public static void main(String[] args) {
        MovingAverage app = new MovingAverage();

        for (int number : numbers) {
            app.submit(number);
        }

        double avg = app.getAvg();
        System.out.println(avg);
    }
}
