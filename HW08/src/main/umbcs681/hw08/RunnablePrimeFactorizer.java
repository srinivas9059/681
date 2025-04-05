package umbcs681.hw08;

public class RunnablePrimeFactorizer extends PrimeFactorizer implements Runnable {
    public RunnablePrimeFactorizer(long dividend, long from, long to) {
        super(dividend);
        if (from >= 2 && to >= from) {
            this.from = from;
            this.to = to;
        } else {
            throw new RuntimeException(
                    "from must be >= 2, and to must be >= from." +
                            "from==" + from + " to==" + to);
        }
    }

    protected boolean isEven(long n) {
        return n % 2 == 0;
    }

    public void generatePrimeFactors() {
        long divisor = from;
        while (dividend != 1 && divisor <= to) {
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
        }
    }

    public void run() {
        generatePrimeFactors();
    }
}
