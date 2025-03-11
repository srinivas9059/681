package umbcs681.hw05;

import java.util.ArrayList;
import java.util.List;

public class BudgetAnalysis {
    public static double analyzeAverageBudget(List<Movie> movies) {
        System.out.println("Movie Budget Analysis:");
        System.out.println("=====================");

        // Compute average budget using the 3rd version of reduce()
        ArrayList<Double> avgResult = movies.stream()
                .filter(movie -> movie.getBudgetUSD() > 0) // Fixed: comparing long to long
                .map(movie -> (double)movie.getBudgetUSD())
                .reduce(
                        new ArrayList<Double>() {{ add(0.0); add(0.0); }}, // Initial value [count, avg]
                        (result, budget) -> {
                            double currentCount = result.get(0);
                            double currentAvg = result.get(1);

                            // Increment count
                            double newCount = currentCount + 1;
                            result.set(0, newCount);

                            // Update average: (avg * count + new_budget) / new_count
                            double newAvg = (currentAvg * currentCount + budget) / newCount;
                            result.set(1, newAvg);

                            return result;
                        },
                        (finalResult, intermediateResult) -> finalResult // Combiner (just return finalResult)
                );

        // Get the average and count
        double averageBudget = avgResult.get(1);
        int movieCount = avgResult.get(0).intValue();

        System.out.printf("Average movie budget: $%.2f million%n", averageBudget / 1_000_000);
        System.out.printf("Total movies with budget data: %d%n", movieCount);

        return averageBudget;
    }
}
