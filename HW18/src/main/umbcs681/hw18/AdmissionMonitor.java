package umbcs681.hw18;

public class AdmissionMonitor {
    private int currentVisitors = 0;

    public void enter() {
        currentVisitors++;
    }

    public void exit() {
        currentVisitors--;
    }

    public int countCurrentVisitors() {
        return currentVisitors;
    }
}
