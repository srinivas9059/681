package umbcs681.hw05;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class ThreadedMovieAnalysisTest {
    private static List<Movie> movies;

    @BeforeAll
    public static void setUp() {
        MovieDataParser parser = new MovieDataParser();
        parser.parseMovies("cleaned_imdb_2024.csv");
        movies = parser.getMovies();
        System.out.println("Test setup complete with " + movies.size() + " movies");
    }

    @Test
    public void testConcurrentMovieAnalysis() throws InterruptedException {
        System.out.println("\n----- Running All Analyses Concurrently -----\n");

        // Create runnable instances
        BudgetAnalysisRunnable budgetRunnable = new BudgetAnalysisRunnable(movies);
        MonthlyReleaseAnalysisRunnable monthlyRunnable = new MonthlyReleaseAnalysisRunnable(movies);
        RuntimeAnalysisRunnable runtimeRunnable = new RuntimeAnalysisRunnable(movies);

        // Create threads
        Thread budgetThread = new Thread(budgetRunnable);
        Thread monthlyThread = new Thread(monthlyRunnable);
        Thread runtimeThread = new Thread(runtimeRunnable);

        // Start all threads
        budgetThread.start();
        monthlyThread.start();
        runtimeThread.start();

        // Wait for all threads to complete
        budgetThread.join();
        monthlyThread.join();
        runtimeThread.join();

        // Get results
        double avgBudget = budgetRunnable.getAverageBudget();
        Map<Integer, Long> monthlyResults = monthlyRunnable.getMonthlyReleases();
        Map<String, Object> runtimeResults = runtimeRunnable.getRuntimeAnalysisResults();

        // Test Budget Analysis results
        double manualAvg = movies.stream()
                .filter(movie -> movie.getBudgetUSD() > 0)
                .mapToLong(Movie::getBudgetUSD)
                .average()
                .orElse(0);
        assertEquals(manualAvg, avgBudget, 0.01, "Calculated average should match manual average");

        // Test Monthly Release Analysis results
        long totalMoviesInMonths = monthlyResults.values().stream()
                .mapToLong(Long::longValue).sum();
        long moviesWithDates = movies.stream()
                .filter(movie -> movie.getReleaseDate() != null)
                .count();
        assertEquals(moviesWithDates, totalMoviesInMonths,
                "Total movies in months should match count of movies with valid dates");

        // Test Runtime Analysis results
        double shortAvg = (double) runtimeResults.get("shortFilmsAvgRuntime");
        double standardAvg = (double) runtimeResults.get("standardFilmsAvgRuntime");
        assertTrue(shortAvg > 0 && shortAvg < 90,
                "Average runtime for short films should be between 0-90 minutes");
        assertTrue(standardAvg >= 90,
                "Average runtime for standard films should be 90+ minutes");
    }
}
