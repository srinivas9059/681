package umbcs681.hw09;

public class RunnableCancellablePrimeFactorizer extends RunnablePrimeFactorizer {
    private volatile boolean done = false;

    public RunnableCancellablePrimeFactorizer(long dividend, long from, long to) {
        super(dividend, from, to);
    }

    public void setDone() {
        done = true;
    }

    public void generatePrimeFactors() {
        long divisor = from;
        while (dividend != 1 && divisor <= to) {
            if (done) {
                System.out.println("Stopped factorizing.");
                this.factors.clear();
                break;
            }

            if (divisor > 2 && isEven(divisor)) {
                divisor++;
                continue;
            }

            if (dividend % divisor == 0) {
                factors.add(divisor);
                dividend /= divisor;
            } else {
                if (divisor == 2) {
                    divisor++;
                } else {
                    divisor += 2;
                }
            }

            try {
                Thread.sleep(5000);
            } catch (InterruptedException exception) {
                continue;
            }
        }
    }
}
