package umbcs681.hw13.fs;

import java.util.LinkedList;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class FileSystem {
    private static final AtomicReference<FileSystem> instance = new AtomicReference<>();
    private final LinkedList<Directory> rootDirs = new LinkedList<>();
    private ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();

    private FileSystem() {

    }

    public static FileSystem getFileSystem() {
        FileSystem current = instance.get();
        if (current == null) {
            FileSystem newInstance = new FileSystem();
            if (instance.compareAndSet(null, newInstance)) {
                return newInstance;
            } else {
                return instance.get();
            }
        }
        return current;
    }

    public LinkedList<Directory> getRootDirs() {
        readWriteLock.readLock().lock();
        try {
            return rootDirs;
        } finally {
            readWriteLock.readLock().unlock();
        }
    }

    public void appendRootDir(Directory root) {
        readWriteLock.writeLock().lock();
        try {
            rootDirs.add(root);
        } finally {
            readWriteLock.writeLock().unlock();
        }
    }
}
