package umbcs681.hw07;

import java.util.LinkedList;
import java.util.concurrent.locks.ReentrantLock;

public class FileSystem {
    private static FileSystem instance = null;
    private static final ReentrantLock lock = new ReentrantLock();
    private final LinkedList<Directory> rootDirs = new LinkedList<>();

    private FileSystem() {
        // Private constructor
    }

    public static FileSystem getFileSystem() {
        lock.lock();
        try {
            if (instance == null) {
                instance = new FileSystem();
            }
            return instance;
        } finally {
            lock.unlock();
        }
    }

    public LinkedList<Directory> getRootDirs() {
        return rootDirs;
    }

    public void appendRootDir(Directory root) {
        rootDirs.add(root);
    }
}
