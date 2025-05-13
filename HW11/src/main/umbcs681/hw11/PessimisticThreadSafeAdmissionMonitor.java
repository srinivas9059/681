package umbcs681.hw11;

import java.util.concurrent.locks.ReentrantLock;

public class PessimisticThreadSafeAdmissionMonitor extends AdmissionMonitor {
    private int currentVisitors = 0;
    private ReentrantLock lock = new ReentrantLock();

    public void enter() {
        lock.lock();
        try {
            currentVisitors++;
        } finally {
            lock.unlock();
        }

    }

    public void exit() {
        lock.lock();
        try {
            currentVisitors--;
        } finally {
            lock.unlock();
        }
    }

    public int countCurrentVisitors() {
        lock.lock();
        try {
            return currentVisitors;
        } finally {
            lock.unlock();
        }
    }
}
