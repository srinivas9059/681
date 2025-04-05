package umbcs681.hw08;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class RunnableCancellablePrimeGeneratorTest {
    @Test
    public void testThreadSafeCancellation() {
        RunnableCancellablePrimeGenerator generator = new RunnableCancellablePrimeGenerator(1, 100000);

        Thread thread = new Thread(generator);  // Create a thread
        thread.start();  // Start the thread
        generator.setDone();  // Cancel the prime generation

        // Wait for the thread to complete
        try {
            thread.join();
        } catch (InterruptedException exception) {
            System.out.println(exception.getMessage());
        }

        // Verify that the primes list is empty (should be cleared on cancellation)
        assertTrue(generator.getPrimes().isEmpty(), "Primes list should be empty after cancellation");
    }
}
