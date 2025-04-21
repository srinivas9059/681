package umbcs681.hw10;

import java.time.Month;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class MonthlyReleaseAnalysis {
    public static Map<Integer, Long> analyzeMonthlyReleases(List<Movie> movies) {
        System.out.println("Monthly Release Distribution:");
        System.out.println("============================");

        // Group movies by release month and count them
        Map<Integer, Long> moviesByMonth = movies.stream()
                .collect(Collectors.groupingBy(
                        movie -> movie.getReleaseDate().getMonthValue(),
                        Collectors.counting()
                ));

        // Print the results in order of months using a for loop
        for (int month = 1; month <= 12; month++) {
            long count = moviesByMonth.getOrDefault(month, 0L);
            System.out.printf("%s: %d movies%n", Month.of(month), count);
        }

        return moviesByMonth;
    }
}
