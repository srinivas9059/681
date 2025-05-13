package umbcs681.hw17;

import java.util.List;

public class BudgetAnalysis {

    public static double analyzeAverageBudget(List<Movie> movies) {
        System.out.println("Movie Budget Analysis:");
        System.out.println("=====================");

        // Parallel-safe reduction using 3rd version of reduce()
        BudgetStats stats = movies.stream().parallel()
                .filter(movie -> movie.getBudgetUSD() > 0)
                .map(movie -> (double) movie.getBudgetUSD())
                .reduce(
                        new BudgetStats(0, 0),
                        BudgetStats::add,       // Accumulator
                        (finalRes, intermRes) -> finalRes.combine(intermRes)
                );

        // Extract result
        double averageBudget = stats.average();
        int movieCount = (int) stats.count();

        System.out.printf("Average movie budget: $%.2f million%n", averageBudget / 1_000_000);
        System.out.printf("Total movies with budget data: %d%n", movieCount);

        return averageBudget;
    }
}
