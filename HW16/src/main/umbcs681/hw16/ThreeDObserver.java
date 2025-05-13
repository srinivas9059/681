package umbcs681.hw16;

public class ThreeDObserver implements Observer<StockEvent> {
    @Override
    public void update(Observable<StockEvent> sender, StockEvent event) {
        System.out.printf("[3DObserver] Ticker: %s | Quote: %.2f%n", event.ticker(), event.quote());
    }
}
