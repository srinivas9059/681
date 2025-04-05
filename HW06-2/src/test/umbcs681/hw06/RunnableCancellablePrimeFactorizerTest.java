package umbcs681.hw06;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class RunnableCancellablePrimeFactorizerTest {
    @Test
    public void testThreadSafeCancellation() {
        RunnableCancellablePrimeFactorizer factorizer = new RunnableCancellablePrimeFactorizer(999999999, 2, 999999);

        Thread thread = new Thread(factorizer);  // Create a thread
        thread.start();  // Start the thread
        factorizer.setDone();  // Cancel the factorization

        // Wait for the thread to complete
        try {
            thread.join();
        } catch (InterruptedException exception) {
            System.out.println(exception.getMessage());
        }

        // Verify that the factors list is empty (should be cleared on cancellation)
        assertTrue(factorizer.getPrimeFactors().isEmpty(), "Factors list should be empty after cancellation");
    }
}
