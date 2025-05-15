package umbcs681.hw16;

import java.util.Random;
import java.util.concurrent.atomic.AtomicBoolean;

public class ObservableDataHandler implements Runnable {
    private final StockQuoteObservable observable;
    private final String[] tickers = {"AAPL", "GOOG", "AMZN", "MSFT", "TSLA", "NFLX", "META"};
    private final Random random = new Random();
    private AtomicBoolean done = new AtomicBoolean(false);

    public ObservableDataHandler(StockQuoteObservable observable) {
        this.observable = observable;
    }

    public void setDone() {
        done.set(true);
    }

    @Override
    public void run() {
        while (!done.get()) {
            String ticker = tickers[random.nextInt(tickers.length)];
            double quote = 100 + random.nextDouble() * 900;

            observable.changeQuote(ticker, quote);

            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                continue;
            }
        }
    }

    public static void main(String[] args) {
        StockQuoteObservable observable = new StockQuoteObservable();

        observable.addObserver(new TableObserver());
        observable.addObserver(new LineChartObserver());
        observable.addObserver(new ThreeDObserver());

        ObservableDataHandler[] handlers = new ObservableDataHandler[12];
        Thread[] threads = new Thread[12];

        for (int i = 0; i < 12; i++) {
            handlers[i] = new ObservableDataHandler(observable);
            threads[i] = new Thread(handlers[i]);
            threads[i].start();
        }

        for (int i = 0; i < 12; i++) {
            handlers[i].setDone();
            System.out.println("DataHandler-" + i + " is done.");
        }

        for (int i = 0; i < 12; i++) {
            threads[i].interrupt();
        }

        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }

        try {
            for (int i = 0; i < 12; i++) {
                threads[i].join();
            }
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }

        System.out.println("All data handler threads terminated.");
    }
}
