package lsieun;

import java.util.List;
import java.util.ArrayList;

public class MovingAverage {
    private List<Double> list = new ArrayList<Double>();

    public void submit(double value) {
        this.list.add(value);
    }

    public double getAvg() {
        if (this.list.size() < 1) return 0;
        double sum = 0.0;
        int size = this.list.size();
        for (int i=0; i<size; i++) {
            sum += this.list.get(i);
        }

        return sum / size;
    }
}
