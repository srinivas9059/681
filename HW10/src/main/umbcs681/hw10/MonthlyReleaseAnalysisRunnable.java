package umbcs681.hw10;

import java.util.List;
import java.util.Map;

public class MonthlyReleaseAnalysisRunnable implements Runnable {
    private volatile boolean done = false;
    private final List<Movie> movies;
    private Map<Integer, Long> monthlyReleases;

    public MonthlyReleaseAnalysisRunnable(List<Movie> movies) {
        this.movies = movies;
        this.monthlyReleases = null;
    }

    public void setDone() {
        done = true;
    }

    public boolean getDone() {
        return done;
    }

    @Override
    public void run() {
        System.out.println("Thread " + Thread.currentThread().getId() + ": Starting Monthly Release Analysis");

        if (!done) {
            try {
                this.monthlyReleases = MonthlyReleaseAnalysis.analyzeMonthlyReleases(movies);

                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    System.out.println("Thread " + Thread.currentThread().getId() + ": Monthly Release Analysis interrupted");
                }

                if (!done) {
                    System.out.println("Thread " + Thread.currentThread().getId() + ": Completed Monthly Release Analysis");
                } else {
                    System.out.println("Thread " + Thread.currentThread().getId() + ": Monthly Release Analysis was cancelled after computation");
                    this.monthlyReleases = null;
                }
            } catch (Exception e) {
                System.out.println("Thread " + Thread.currentThread().getId() + ": Monthly Release Analysis error: " + e.getMessage());
            }
        } else {
            System.out.println("Thread " + Thread.currentThread().getId() + ": Monthly Release Analysis cancelled before starting");
        }
    }

    public Map<Integer, Long> getMonthlyReleases() {
        return monthlyReleases;
    }
}
