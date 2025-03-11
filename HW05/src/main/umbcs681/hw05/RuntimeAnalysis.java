package umbcs681.hw05;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class RuntimeAnalysis {
    public static Map<String, Object> analyzeMovieRuntimes(List<Movie> movies) {
        System.out.println("Movie Runtime Analysis:");
        System.out.println("======================");

        // Define the threshold for short vs. standard/long films (90 minutes)
        final int RUNTIME_THRESHOLD = 90;

        // Partition movies based on runtime
        Map<Boolean, List<Movie>> partitionedMovies = movies.stream()
                .filter(movie -> movie.getRunTimeMinutes() > 0) // Filter out unknown runtimes (-1)
                .collect(Collectors.partitioningBy(
                        movie -> movie.getRunTimeMinutes() < RUNTIME_THRESHOLD
                ));

        // Get the lists of short and standard/long films
        List<Movie> shortFilms = partitionedMovies.get(true);
        List<Movie> standardFilms = partitionedMovies.get(false);

        // Display results
        System.out.println("Short films (less than 90 minutes): " + shortFilms.size());
        System.out.println("Standard/long films (90+ minutes): " + standardFilms.size());

        // Calculate average runtimes for each category
        double shortFilmsAvgRuntime = shortFilms.stream()
                .mapToInt(Movie::getRunTimeMinutes)
                .average()
                .orElse(0);

        double standardFilmsAvgRuntime = standardFilms.stream()
                .mapToInt(Movie::getRunTimeMinutes)
                .average()
                .orElse(0);

        System.out.printf("Average runtime for short films: %.1f minutes%n", shortFilmsAvgRuntime);
        System.out.printf("Average runtime for standard/long films: %.1f minutes%n", standardFilmsAvgRuntime);

        // Create a result map to return
        Map<String, Object> results = new HashMap<>();
        results.put("shortFilmsCount", shortFilms.size());
        results.put("standardFilmsCount", standardFilms.size());
        results.put("shortFilmsAvgRuntime", shortFilmsAvgRuntime);
        results.put("standardFilmsAvgRuntime", standardFilmsAvgRuntime);

        return results;
    }
}
