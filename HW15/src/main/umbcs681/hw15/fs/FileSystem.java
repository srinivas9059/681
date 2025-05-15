package umbcs681.hw15.fs;

import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicReference;

public class FileSystem {
    private static final AtomicReference<FileSystem> instance = new AtomicReference<>();
    private final ConcurrentLinkedQueue<Directory> rootDirs = new ConcurrentLinkedQueue<>();

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

    public ConcurrentLinkedQueue<Directory> getRootDirs() {
        return rootDirs;
    }

    public void appendRootDir(Directory root) {
        rootDirs.add(root);
    }
}
