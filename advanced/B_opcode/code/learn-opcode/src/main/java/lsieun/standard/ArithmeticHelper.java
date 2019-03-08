package lsieun.standard;

public class ArithmeticHelper {
    private String expression;
    private String field;
    private int delta;

    public ArithmeticHelper(String expression) {
        this.expression = expression;
    }

    // region getters and setters
    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public int getDelta() {
        return delta;
    }

    public void setDelta(int delta) {
        this.delta = delta;
    }
    // endregion

    public void parse() {
        if(this.expression.contains("+")) {
            String[] array = expression.split("\\+");
            this.field = array[0];
            this.delta = Integer.parseInt(array[1]);
        }
        else if(this.expression.contains("-")) {
            String[] array = expression.split("-");
            this.field = array[0];
            this.delta = 0 - Integer.parseInt(array[1]);
        }
        else {
            this.field = expression;
            this.delta = 0;
        }
    }

    @Override
    public String toString() {
        return "ArithmeticHelper {" +
                "field='" + field + '\'' +
                ", delta=" + delta +
                '}';
    }

    public static void main(String[] args) {
        String str = "shot";
        ArithmeticHelper instance = new ArithmeticHelper(str);
        instance.parse();
        System.out.println(instance);
    }
}
