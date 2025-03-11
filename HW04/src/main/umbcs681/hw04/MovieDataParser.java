package umbcs681.hw04;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MovieDataParser {
    private List<Movie> movies;

    public MovieDataParser() {
        this.movies = List.of();
    }

    public List<Movie> getMovies() {
        return movies;
    }

    public void parseMovies(String filePath) {
        Path path = Paths.get(filePath);

        try (Stream<String> lines = Files.lines(path)) {
            this.movies = lines.skip(1)
                    .map(this::parseMovieLine)
                    .collect(Collectors.toList());

            System.out.println("Successfully parsed " + movies.size() + " movies");
        } catch (IOException ex) {
            System.err.println("Error reading file: " + ex.getMessage());
            this.movies = List.of();
        }
    }

    private Movie parseMovieLine(String line) {
        String[] parts = line.split(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)");
        List<String> fields = Stream.of(parts)
                .map(part -> part.trim().replaceAll("^\"|\"$", ""))
                .toList();

        return new Movie(
                fields.get(0), fields.get(1), fields.get(2), fields.get(3), fields.get(4), fields.get(5), fields.get(6), fields.get(7),
                fields.get(8), fields.get(9), fields.get(10), fields.get(11), fields.get(12), fields.get(13), fields.get(14), fields.get(15)
        );
    }
}
