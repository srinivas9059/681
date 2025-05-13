package umbcs681.hw18;

import java.util.concurrent.locks.ReentrantReadWriteLock;

public class OptimisticThreadSafeAdmissionMonitor extends AdmissionMonitor {
    private int currentVisitors = 0;
    private ReentrantReadWriteLock lock = new ReentrantReadWriteLock();

    public void enter() {
        lock.writeLock().lock();
        try {
            currentVisitors++;
        } finally {
            lock.writeLock().unlock();
        }

    }

    public void exit() {
        lock.writeLock().lock();
        try {
            currentVisitors--;
        } finally {
            lock.writeLock().unlock();
        }
    }

    public int countCurrentVisitors() {
        lock.readLock().lock();
        try {
            return currentVisitors;
        } finally {
            lock.readLock().unlock();
        }
    }
}
