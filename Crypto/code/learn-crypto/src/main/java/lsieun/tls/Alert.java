package lsieun.tls;

public class Alert {
    public final AlertLevel level;
    public final AlertDescription description;

    public Alert(AlertLevel level, AlertDescription description) {
        this.level = level;
        this.description = description;
    }
}
