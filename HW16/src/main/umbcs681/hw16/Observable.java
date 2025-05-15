package umbcs681.hw16;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

public abstract class Observable<T> {
    private final List<Observer<T>> observers = new ArrayList<>();
    protected final ReentrantLock lockObs = new ReentrantLock();

    public void addObserver(Observer<T> o) {
        lockObs.lock();
        try {
            observers.add(o);
        } finally {
            lockObs.unlock();
        }
    }

    public void removeObserver(Observer<T> o) {
        lockObs.lock();
        try {
            observers.remove(o);
        } finally {
            lockObs.unlock();
        }
    }

    public int countObservers() {
        lockObs.lock();
        try {
            return observers.size();
        } finally {
            lockObs.unlock();
        }
    }

    public List<Observer<T>> getObservers() {
        lockObs.lock();
        try {
            return new ArrayList<>(observers);
        } finally {
            lockObs.unlock();
        }
    }

    public void clearObservers() {
        lockObs.lock();
        try {
            observers.clear();
        } finally {
            lockObs.unlock();
        }
    }

    public void notifyObservers(T event) {
        List<Observer<T>> snapshot;

        lockObs.lock();
        try {
            snapshot = new ArrayList<>(observers);
        } finally {
            lockObs.unlock();
        }

        for (Observer<T> observer : snapshot) {
            observer.update(this, event);
        }
    }
}
