package umbcs681.hw05;

import java.util.List;

public class BudgetAnalysisRunnable implements Runnable {
    private final List<Movie> movies;
    private double averageBudget;

    public BudgetAnalysisRunnable(List<Movie> movies) {
        this.movies = movies;
        this.averageBudget = 0.0;
    }

    @Override
    public void run() {
        System.out.println("Thread " + Thread.currentThread().getId() + ": Starting Budget Analysis");
        this.averageBudget = BudgetAnalysis.analyzeAverageBudget(movies);
        System.out.println("Thread " + Thread.currentThread().getId() + ": Completed Budget Analysis");
    }

    public double getAverageBudget() {
        return averageBudget;
    }
}
