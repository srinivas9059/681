package umbcs681.hw10;

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

        // Cancel the runnables
        budgetRunnable.setDone();
        monthlyRunnable.setDone();
        runtimeRunnable.setDone();

        // Interrupt the threads
        budgetThread.interrupt();
        monthlyThread.interrupt();
        runtimeThread.interrupt();

        // Wait for all threads to complete
        budgetThread.join();
        monthlyThread.join();
        runtimeThread.join();

        // Get results
        double avgBudget = budgetRunnable.getAverageBudget();
        Map<Integer, Long> monthlyResults = monthlyRunnable.getMonthlyReleases();
        Map<String, Object> runtimeResults = runtimeRunnable.getRuntimeAnalysisResults();

        assertTrue(budgetRunnable.getDone());
        assertTrue(monthlyRunnable.getDone());
        assertTrue(runtimeRunnable.getDone());
    }
}
