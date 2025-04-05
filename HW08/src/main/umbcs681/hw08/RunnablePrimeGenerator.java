package umbcs681.hw08;

public class RunnablePrimeGenerator extends PrimeGenerator implements Runnable {
	public RunnablePrimeGenerator(long from, long to) {
		super(from, to);
	}

	public void run() {
		generatePrimes();
	}
}
