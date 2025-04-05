package umbcs681.hw07;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

public class FileSystemTest {
    @Test
    public void testSingletonFileSystemWithMultipleThreads() {
        FileSystem[] fileSystemInstances = new FileSystem[10];
        List<Thread> threads = new ArrayList<>();

        // Create 10 threads that will try to get the FileSystem instance simultaneously
        for (int i = 0; i < 10; i++) {
            Thread thread = new Thread(new GetFileSystemRunnable(fileSystemInstances, i));
            threads.add(thread);
        }

        // Start all threads
        for (Thread thread : threads) {
            thread.start();
        }

        // Wait for all threads to complete
        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                fail("Thread interrupted: " + e.getMessage());
            }
        }

        // Check that all threads got the same instance
        FileSystem firstInstance = fileSystemInstances[0];
        assertNotNull(firstInstance, "FileSystem instance should not be null");

        for (int i = 1; i < fileSystemInstances.length; i++) {
            assertSame(firstInstance, fileSystemInstances[i],
                    "All threads should get the same FileSystem instance");
        }
    }
}
