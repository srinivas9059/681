package umbcs681.hw05;

import java.util.List;
import java.util.Map;

public class RuntimeAnalysisRunnable implements Runnable {
    private final List<Movie> movies;
    private Map<String, Object> runtimeAnalysisResults;

    public RuntimeAnalysisRunnable(List<Movie> movies) {
        this.movies = movies;
        this.runtimeAnalysisResults = null;
    }

    @Override
    public void run() {
        System.out.println("Thread " + Thread.currentThread().getId() + ": Starting Runtime Analysis");
        this.runtimeAnalysisResults = RuntimeAnalysis.analyzeMovieRuntimes(movies);
        System.out.println("Thread " + Thread.currentThread().getId() + ": Completed Runtime Analysis");
    }

    public Map<String, Object> getRuntimeAnalysisResults() {
        return runtimeAnalysisResults;
    }
}