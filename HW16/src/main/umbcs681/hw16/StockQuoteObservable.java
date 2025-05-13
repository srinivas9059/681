package umbcs681.hw16;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class StockQuoteObservable extends Observable<StockEvent> {
    private final ConcurrentMap<String, Double> tickerQuotes = new ConcurrentHashMap<>();

    public void changeQuote(String ticker, double quote) {
        tickerQuotes.put(ticker, quote);
        notifyObservers(new StockEvent(ticker, quote));
    }
}
