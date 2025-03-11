package umbcs681.hw05;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class MovieDataParserTest {
    private static List<Movie> movies;

    @BeforeAll
    public static void setUp() {
        MovieDataParser parser = new MovieDataParser();
        parser.parseMovies("cleaned_imdb_2024.csv");
        movies = parser.getMovies();
        System.out.println("Test setup complete with " + movies.size() + " movies");
    }

    @Test
    public void testMonthlyReleaseAnalysis() {
        System.out.println("\n----- Testing Monthly Release Analysis -----\n");
        Map<Integer, Long> results = MonthlyReleaseAnalysis.analyzeMonthlyReleases(movies);

        // Calculate total movies from the monthly data
        long totalMoviesInMonths = results.values().stream().mapToLong(Long::longValue).sum();

        // Get count of movies with valid release dates
        long moviesWithDates = movies.stream()
                .filter(movie -> movie.getReleaseDate() != null)
                .count();

        assertEquals(moviesWithDates, totalMoviesInMonths, "Total movies in months should match count of movies with valid dates");
    }

    @Test
    public void testBudgetAnalysis() {
        System.out.println("\n----- Testing Budget Analysis -----\n");
        double avgBudget = BudgetAnalysis.analyzeAverageBudget(movies);

        // Verify by manual calculation
        double manualAvg = movies.stream()
                .filter(movie -> movie.getBudgetUSD() > 0)
                .mapToLong(Movie::getBudgetUSD)
                .average()
                .orElse(0);

        assertEquals(manualAvg, avgBudget, 0.01, "Calculated average should match manual average");
    }

    @Test
    public void testRuntimeAnalysis() {
        System.out.println("\n----- Testing Runtime Analysis -----\n");
        Map<String, Object> results = RuntimeAnalysis.analyzeMovieRuntimes(movies);

        double shortAvg = (double) results.get("shortFilmsAvgRuntime");
        double standardAvg = (double) results.get("standardFilmsAvgRuntime");

        assertTrue(shortAvg > 0 && shortAvg < 90, "Average runtime for short films should be between 0-90 minutes");
        assertTrue(standardAvg >= 90, "Average runtime for standard films should be 90+ minutes");
    }
}
