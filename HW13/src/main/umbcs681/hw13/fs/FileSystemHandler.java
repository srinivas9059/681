package umbcs681.hw13.fs;

import umbcs681.hw13.util.FileCrawlingVisitor;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.concurrent.locks.ReentrantLock;

public class FileSystemHandler implements Runnable {
    private ReentrantLock lock = new ReentrantLock();
    private LinkedList<File> sharedFiles;
    private volatile boolean done = false;

    public FileSystemHandler(LinkedList<File> sharedFiles) {
        this.sharedFiles = sharedFiles;
    }

    public void setDone() {
        this.done = true;
    }

    public static void createFileSystem() {
        FileSystem fs = FileSystem.getFileSystem();

        Directory src = new Directory(null, "src", 0, LocalDateTime.now());
        Directory mainDir = new Directory(src, "main", 0, LocalDateTime.now());
        File readmeFile = new File(src, "readme.md", 150, LocalDateTime.now());

        File aFile = new File(mainDir, "a.java", 100, LocalDateTime.now());
        File bFile = new File(mainDir, "b.java", 200, LocalDateTime.now());

        fs.appendRootDir(src);
    }

    @Override
    public void run() {
        createFileSystem();
        if (!done) {
            Directory src = FileSystem.getFileSystem().getRootDirs().get(0);
            FileCrawlingVisitor fileCrawlingVisitor = new FileCrawlingVisitor();
            src.accept(fileCrawlingVisitor);

            lock.lock();
            try {
                sharedFiles.addAll(fileCrawlingVisitor.getFiles());
            } finally {
                lock.unlock();
            }

            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }
        } else {
            System.out.println("done is set true");
        }
    }

    public static void main(String[] args) {
        LinkedList<File> sharedFiles = new LinkedList<>();
        FileSystemHandler[] fileSystemHandlers = new FileSystemHandler[3];
        Thread[] threads = new Thread[3];

        for (int i = 0; i < 3; i++) {
            fileSystemHandlers[i] = new FileSystemHandler(sharedFiles);
            threads[i] = new Thread(fileSystemHandlers[i]);
            threads[i].start();
        }

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }

        for (int i = 0; i < 3; i++) {
            fileSystemHandlers[i].setDone();
        }

        for (int i = 0; i < 3; i++) {
            threads[i].interrupt();
        }

        for (int i = 0; i < 3; i++) {
            try {
                threads[i].join();
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }
        }

        System.out.println("\nTotal number of files: " + sharedFiles.size());
        System.out.println("All files collected:");
        for (File file : sharedFiles) {
            System.out.println(file.getName());
        }
        System.out.println("\nAll threads finished.");
    }
}
