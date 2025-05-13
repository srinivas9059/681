package umbcs681.hw11;

import java.util.concurrent.atomic.AtomicInteger;

public class AtomicAdmissionHandler extends AdmissionMonitor {
    private AtomicInteger currentVisitors = new AtomicInteger(0);

    public void enter() {
        currentVisitors.incrementAndGet();
    }

    public void exit() {
        currentVisitors.decrementAndGet();
    }

    public int countCurrentVisitors() {
        return currentVisitors.get();
    }
}
