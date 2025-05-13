package umbcs681.hw17;

public record BudgetStats(double count, double sum) {
    public BudgetStats add(double budget) {
        return new BudgetStats(count + 1, sum + budget);
    }

    public BudgetStats combine(BudgetStats other) {
        return new BudgetStats(this.count + other.count, this.sum + other.sum);
    }

    public double average() {
        return count > 0 ? sum / count : 0.0;
    }
}
