package umbcs681.hw06;

import java.util.concurrent.locks.ReentrantLock;

public class RunnableCancellablePrimeGenerator extends RunnablePrimeGenerator {
	private boolean done = false;
	private final ReentrantLock lock = new ReentrantLock();

	public RunnableCancellablePrimeGenerator(long from, long to) {
		super(from, to);
	}

	public void setDone() {
		lock.lock();
		try {
			done = true;
		} finally {
			lock.unlock();
		}
	}

	public void generatePrimes() {
		for (long n = from; n <= to; n++) {
			// Acquire the lock to check if thread should stop
			lock.lock();
			try {
				if (done) {
					System.out.println("Stopped generating prime numbers.");
					this.primes.clear();
					break;
				}
				// Check if the number is prime and add it to the list
				if (isPrime(n)) {
					this.primes.add(n);
				}
			} finally {
				lock.unlock();
			}
		}
	}
}
