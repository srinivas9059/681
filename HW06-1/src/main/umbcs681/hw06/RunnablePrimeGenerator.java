package umbcs681.hw06;

public class RunnablePrimeGenerator extends PrimeGenerator implements Runnable {
	public RunnablePrimeGenerator(long from, long to) {
		super(from, to);
	}

	public void run() {
		generatePrimes();
	}
}
