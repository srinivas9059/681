package umbcs681.hw11;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class AdmissionMonitorTest {
    @Test
    public void testAtomicAdmissionHandler() throws InterruptedException {
        AdmissionMonitor monitor = new AtomicAdmissionHandler();

        Thread e1 = new Thread(new EntranceHandler(monitor));
        Thread e2 = new Thread(new EntranceHandler(monitor));
        Thread e3 = new Thread(new EntranceHandler(monitor));
        Thread x1 = new Thread(new ExitHandler(monitor));
        Thread x2 = new Thread(new ExitHandler(monitor));
        Thread s1 = new Thread(new StatsHandler(monitor));
        Thread e4 = new Thread(new EntranceHandler(monitor));
        Thread e5 = new Thread(new EntranceHandler(monitor));
        Thread e6 = new Thread(new EntranceHandler(monitor));
        Thread x3 = new Thread(new ExitHandler(monitor));
        Thread x4 = new Thread(new ExitHandler(monitor));
        Thread s2 = new Thread(new StatsHandler(monitor));

        e1.start(); e2.start(); e3.start();
        x1.start(); x2.start();
        s1.start();
        e4.start(); e5.start(); e6.start();
        x3.start(); x4.start();
        s2.start();

        e1.join(); e2.join(); e3.join();
        x1.join(); x2.join();
        s1.join();
        e4.join(); e5.join(); e6.join();
        x3.join(); x4.join();
        s2.join();

        assertEquals(2, monitor.countCurrentVisitors());
    }

    @Test
    public void testPessimisticAdmissionHandler() throws InterruptedException {
        AdmissionMonitor monitor = new PessimisticThreadSafeAdmissionMonitor();

        Thread e1 = new Thread(new EntranceHandler(monitor));
        Thread e2 = new Thread(new EntranceHandler(monitor));
        Thread e3 = new Thread(new EntranceHandler(monitor));
        Thread x1 = new Thread(new ExitHandler(monitor));
        Thread x2 = new Thread(new ExitHandler(monitor));
        Thread s1 = new Thread(new StatsHandler(monitor));
        Thread e4 = new Thread(new EntranceHandler(monitor));
        Thread e5 = new Thread(new EntranceHandler(monitor));
        Thread e6 = new Thread(new EntranceHandler(monitor));
        Thread x3 = new Thread(new ExitHandler(monitor));
        Thread x4 = new Thread(new ExitHandler(monitor));
        Thread s2 = new Thread(new StatsHandler(monitor));

        e1.start(); e2.start(); e3.start();
        x1.start(); x2.start();
        s1.start();
        e4.start(); e5.start(); e6.start();
        x3.start(); x4.start();
        s2.start();

        e1.join(); e2.join(); e3.join();
        x1.join(); x2.join();
        s1.join();
        e4.join(); e5.join(); e6.join();
        x3.join(); x4.join();
        s2.join();

        assertEquals(2, monitor.countCurrentVisitors());
    }

    @Test
    public void testOptimisticAdmissionHandler() throws InterruptedException {
        AdmissionMonitor monitor = new OptimisticThreadSafeAdmissionMonitor();

        Thread e1 = new Thread(new EntranceHandler(monitor));
        Thread e2 = new Thread(new EntranceHandler(monitor));
        Thread e3 = new Thread(new EntranceHandler(monitor));
        Thread x1 = new Thread(new ExitHandler(monitor));
        Thread x2 = new Thread(new ExitHandler(monitor));
        Thread s1 = new Thread(new StatsHandler(monitor));
        Thread e4 = new Thread(new EntranceHandler(monitor));
        Thread e5 = new Thread(new EntranceHandler(monitor));
        Thread e6 = new Thread(new EntranceHandler(monitor));
        Thread x3 = new Thread(new ExitHandler(monitor));
        Thread x4 = new Thread(new ExitHandler(monitor));
        Thread s2 = new Thread(new StatsHandler(monitor));

        e1.start(); e2.start(); e3.start();
        x1.start(); x2.start();
        s1.start();
        e4.start(); e5.start(); e6.start();
        x3.start(); x4.start();
        s2.start();

        e1.join(); e2.join(); e3.join();
        x1.join(); x2.join();
        s1.join();
        e4.join(); e5.join(); e6.join();
        x3.join(); x4.join();
        s2.join();

        assertEquals(2, monitor.countCurrentVisitors());
    }
}
