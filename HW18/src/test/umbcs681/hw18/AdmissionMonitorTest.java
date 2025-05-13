package umbcs681.hw18;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class AdmissionMonitorTest {

    @Test
    public void testAtomicAdmissionHandler() {
        AdmissionMonitor monitor = new AtomicAdmissionHandler();
        ExecutorService executor = Executors.newFixedThreadPool(12);

        try {
            executor.execute(new EntranceHandler(monitor));
            executor.execute(new EntranceHandler(monitor));
            executor.execute(new EntranceHandler(monitor));
            executor.execute(new ExitHandler(monitor));
            executor.execute(new ExitHandler(monitor));
            executor.execute(new StatsHandler(monitor));
            executor.execute(new EntranceHandler(monitor));
            executor.execute(new EntranceHandler(monitor));
            executor.execute(new EntranceHandler(monitor));
            executor.execute(new ExitHandler(monitor));
            executor.execute(new ExitHandler(monitor));
            executor.execute(new StatsHandler(monitor));

            executor.shutdown();
            executor.awaitTermination(10, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        assertEquals(2, monitor.countCurrentVisitors());
    }

    @Test
    public void testPessimisticAdmissionHandler() {
        AdmissionMonitor monitor = new PessimisticThreadSafeAdmissionMonitor();
        ExecutorService executor = Executors.newFixedThreadPool(12);

        try {
            executor.execute(new EntranceHandler(monitor));
            executor.execute(new EntranceHandler(monitor));
            executor.execute(new EntranceHandler(monitor));
            executor.execute(new ExitHandler(monitor));
            executor.execute(new ExitHandler(monitor));
            executor.execute(new StatsHandler(monitor));
            executor.execute(new EntranceHandler(monitor));
            executor.execute(new EntranceHandler(monitor));
            executor.execute(new EntranceHandler(monitor));
            executor.execute(new ExitHandler(monitor));
            executor.execute(new ExitHandler(monitor));
            executor.execute(new StatsHandler(monitor));

            executor.shutdown();
            executor.awaitTermination(10, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        assertEquals(2, monitor.countCurrentVisitors());
    }

    @Test
    public void testOptimisticAdmissionHandler() {
        AdmissionMonitor monitor = new OptimisticThreadSafeAdmissionMonitor();
        ExecutorService executor = Executors.newFixedThreadPool(12);

        try {
            executor.execute(new EntranceHandler(monitor));
            executor.execute(new EntranceHandler(monitor));
            executor.execute(new EntranceHandler(monitor));
            executor.execute(new ExitHandler(monitor));
            executor.execute(new ExitHandler(monitor));
            executor.execute(new StatsHandler(monitor));
            executor.execute(new EntranceHandler(monitor));
            executor.execute(new EntranceHandler(monitor));
            executor.execute(new EntranceHandler(monitor));
            executor.execute(new ExitHandler(monitor));
            executor.execute(new ExitHandler(monitor));
            executor.execute(new StatsHandler(monitor));

            executor.shutdown();
            executor.awaitTermination(10, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        assertEquals(2, monitor.countCurrentVisitors());
    }
}
