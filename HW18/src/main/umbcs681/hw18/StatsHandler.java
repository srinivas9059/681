package umbcs681.hw18;

public class StatsHandler implements Runnable {
    private AdmissionMonitor monitor;

    public StatsHandler(AdmissionMonitor monitor) {
        this.monitor = monitor;
    }

    public void run() {
        System.out.println("Current Visitors Count: " + monitor.countCurrentVisitors());
    }
}
