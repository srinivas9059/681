package umbcs681.hw16;

public class LineChartObserver implements Observer<StockEvent> {
    @Override
    public void update(Observable<StockEvent> sender, StockEvent event) {
        System.out.printf("[LineChartObserver] Ticker: %s | Quote: %.2f%n", event.ticker(), event.quote());
    }
}
