package umbcs681.hw05;

import java.util.List;
import java.util.Map;

public class MonthlyReleaseAnalysisRunnable implements Runnable {
    private final List<Movie> movies;
    private Map<Integer, Long> monthlyReleases;

    public MonthlyReleaseAnalysisRunnable(List<Movie> movies) {
        this.movies = movies;
        this.monthlyReleases = null;
    }

    @Override
    public void run() {
        System.out.println("Thread " + Thread.currentThread().getId() + ": Starting Monthly Release Analysis");
        this.monthlyReleases = MonthlyReleaseAnalysis.analyzeMonthlyReleases(movies);
        System.out.println("Thread " + Thread.currentThread().getId() + ": Completed Monthly Release Analysis");
    }

    public Map<Integer, Long> getMonthlyReleases() {
        return monthlyReleases;
    }
}