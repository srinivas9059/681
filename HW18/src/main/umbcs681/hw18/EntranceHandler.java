package umbcs681.hw18;

public class EntranceHandler implements Runnable {
    private AdmissionMonitor monitor;

    public EntranceHandler(AdmissionMonitor monitor) {
        this.monitor = monitor;
    }

    public void run() {
        monitor.enter();
    }
}
