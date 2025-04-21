package umbcs681.hw10;

import java.util.List;
import java.util.Map;

public class RuntimeAnalysisRunnable implements Runnable {
    private volatile boolean done = false;
    private final List<Movie> movies;
    private Map<String, Object> runtimeAnalysisResults;

    public RuntimeAnalysisRunnable(List<Movie> movies) {
        this.movies = movies;
        this.runtimeAnalysisResults = null;
    }

    public void setDone() {
        done = true;
    }

    public boolean getDone() {
        return done;
    }

    @Override
    public void run() {
        System.out.println("Thread " + Thread.currentThread().getId() + ": Starting Runtime Analysis");

        if (!done) {
            try {
                this.runtimeAnalysisResults = RuntimeAnalysis.analyzeMovieRuntimes(movies);

                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    System.out.println("Thread " + Thread.currentThread().getId() + ": Runtime Analysis interrupted");
                }

                if (!done) {
                    System.out.println("Thread " + Thread.currentThread().getId() + ": Completed Runtime Analysis");
                } else {
                    System.out.println("Thread " + Thread.currentThread().getId() + ": Runtime Analysis was cancelled after computation");
                    this.runtimeAnalysisResults = null;
                }
            } catch (Exception e) {
                System.out.println("Thread " + Thread.currentThread().getId() + ": Runtime Analysis error: " + e.getMessage());
            }
        } else {
            System.out.println("Thread " + Thread.currentThread().getId() + ": Runtime Analysis cancelled before starting");
        }
    }

    public Map<String, Object> getRuntimeAnalysisResults() {
        return runtimeAnalysisResults;
    }
}
