package umbcs681.hw10;

import java.util.List;

public class BudgetAnalysisRunnable implements Runnable {
    private volatile boolean done = false;
    private final List<Movie> movies;
    private double averageBudget;

    public BudgetAnalysisRunnable(List<Movie> movies) {
        this.movies = movies;
        this.averageBudget = 0.0;
    }

    public void setDone() {
        done = true;
    }

    public boolean getDone() {
        return done;
    }

    @Override
    public void run() {
        System.out.println("Thread " + Thread.currentThread().getId() + ": Starting Budget Analysis");

        if (!done) {
            try {
                this.averageBudget = BudgetAnalysis.analyzeAverageBudget(movies);

                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    System.out.println("Thread " + Thread.currentThread().getId() + ": Budget Analysis interrupted");
                }

                if (!done) {
                    System.out.println("Thread " + Thread.currentThread().getId() + ": Completed Budget Analysis");
                } else {
                    System.out.println("Thread " + Thread.currentThread().getId() + ": Budget Analysis was cancelled after computation");
                    this.averageBudget = 0.0;
                }
            } catch (Exception e) {
                System.out.println("Thread " + Thread.currentThread().getId() + ": Budget Analysis error: " + e.getMessage());
            }
        } else {
            System.out.println("Thread " + Thread.currentThread().getId() + ": Budget Analysis cancelled before starting");
        }
    }

    public double getAverageBudget() {
        return averageBudget;
    }
}
