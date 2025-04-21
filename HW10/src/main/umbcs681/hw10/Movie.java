package umbcs681.hw10;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Movie {
    private final String homePage;
    private final String movieName;
    private final List<String> genres;
    private final String overview;
    private final List<String> cast;
    private final List<String> originalLanguages;
    private final String storyline;
    private final List<String> productionCompanies;
    private LocalDate releaseDate;
    private final String tagline;
    private double voteAverage;
    private final long voteCount;
    private final long budgetUSD;
    private final long revenue;
    private int runTimeMinutes;
    private final String releaseCountry;

    public Movie(String homePage, String movieName, String genres, String overview,
                 String cast, String originalLanguage, String storyline,
                 String productionCompany, String releaseDate, String tagline,
                 String voteAverage, String voteCount, String budgetUSD,
                 String revenue, String runTimeMinutes, String releaseCountry) {

        this.homePage = homePage;
        this.movieName = movieName;
        this.genres = parseListField(genres);
        this.overview = overview;
        this.cast = parseListField(cast);
        this.originalLanguages = parseListField(originalLanguage);
        this.storyline = storyline;
        this.productionCompanies = parseListField(productionCompany);

        // Parse date
        try {
            if (releaseDate.contains("-")) {
                this.releaseDate = LocalDate.parse(releaseDate);
            } else {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yy");
                this.releaseDate = LocalDate.parse(releaseDate, formatter);
            }
        } catch (Exception e) {
            System.out.println("Error parsing date '" + releaseDate + "': " + e.getMessage());
            this.releaseDate = null;
        }

        this.tagline = tagline;

        try {
            this.voteAverage = Double.parseDouble(voteAverage);
        } catch (NumberFormatException e) {
            this.voteAverage = 0.0;
        }

        this.voteCount = parseNumericalValue(voteCount);
        this.budgetUSD = parseNumericalValue(budgetUSD);
        this.revenue = parseNumericalValue(revenue);

        if (runTimeMinutes.equalsIgnoreCase("Unknown")) {
            this.runTimeMinutes = -1;
        } else {
            try {
                this.runTimeMinutes = Integer.parseInt(runTimeMinutes);
            } catch (NumberFormatException e) {
                this.runTimeMinutes = -1;
            }
        }

        this.releaseCountry = releaseCountry;
    }

    // Helper method to parse list fields
    private List<String> parseListField(String field) {
        if (field == null || field.isEmpty() || field.equals("[]")) {
            return List.of();
        }

        String content = field.substring(1, field.length() - 1);

        return Arrays.stream(content.split(","))
                .map(s -> s.trim().replaceAll("^'|'$", ""))
                .collect(Collectors.toList());
    }

    // Helper method to parse numbers
    private long parseNumericalValue(String value) {
        if (value == null || value.isEmpty()) return 0;
        try {
            String cleaned = value.replaceAll("[$,]", "");
            double doubleValue = Double.parseDouble(cleaned.substring(0, cleaned.length() - 1));
            if (cleaned.endsWith("k") || cleaned.endsWith("K")) {
                return (long)(doubleValue * 1000);
            } else if (cleaned.endsWith("m") || cleaned.endsWith("M")) {
                return (long)(doubleValue * 1000000);
            } else if (cleaned.endsWith("b") || cleaned.endsWith("B")) {
                return (long)(doubleValue * 1000000000);
            } else {
                return Long.parseLong(cleaned);
            }
        } catch (NumberFormatException e) {
            return 0;
        }
    }

    public String getHomePage() {
        return homePage;
    }

    public String getMovieName() {
        return movieName;
    }

    public List<String> getGenres() {
        return genres;
    }

    public String getOverview() {
        return overview;
    }

    public List<String> getCast() {
        return cast;
    }

    public List<String> getOriginalLanguages() {
        return originalLanguages;
    }

    public String getStoryline() {
        return storyline;
    }

    public List<String> getProductionCompanies() {
        return productionCompanies;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public String getTagline() {
        return tagline;
    }

    public double getVoteAverage() {
        return voteAverage;
    }

    public long getVoteCount() {
        return voteCount;
    }

    public long getBudgetUSD() {
        return budgetUSD;
    }

    public long getRevenue() {
        return revenue;
    }

    public int getRunTimeMinutes() {
        return runTimeMinutes;
    }

    public String getReleaseCountry() {
        return releaseCountry;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "homePage='" + homePage + '\'' +
                ", movieName='" + movieName + '\'' +
                ", genres=" + genres +
                ", overview='" + overview + '\'' +
                ", cast=" + cast +
                ", originalLanguages=" + originalLanguages +
                ", storyline='" + storyline + '\'' +
                ", productionCompanies=" + productionCompanies +
                ", releaseDate=" + releaseDate +
                ", tagline='" + tagline + '\'' +
                ", voteAverage=" + voteAverage +
                ", voteCount='" + voteCount + '\'' +
                ", budgetUSD='" + budgetUSD + '\'' +
                ", revenue='" + revenue + '\'' +
                ", runTimeMinutes=" + runTimeMinutes +
                ", releaseCountry='" + releaseCountry + '\'' +
                '}';
    }
}
