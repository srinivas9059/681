package umbcs681.hw12;

import java.time.LocalDateTime;
import java.util.LinkedList;

public class FileSystemHandler implements Runnable {

    public static void createFileSystem() {
        FileSystem fs = FileSystem.getFileSystem();

        Directory src = new Directory(null, "src", 0, LocalDateTime.now());
        Directory mainDir = new Directory(src, "main", 0, LocalDateTime.now());
        Directory testDir = new Directory(src, "test", 0, LocalDateTime.now());

        File aFile = new File(mainDir, "a.java", 100, LocalDateTime.now());
        File aTestFile = new File(testDir, "aTest.java", 150, LocalDateTime.now());

        fs.appendRootDir(src);
    }

    @Override
    public void run() {
        createFileSystem();

        FileSystem fs = FileSystem.getFileSystem();
        Directory root = fs.getRootDirs().get(0);

        System.out.println("Root Directory: " + root.getName());
        System.out.println("Total Size: " + root.getTotalSize());
        System.out.println("Child Count: " + root.countChildren());

        LinkedList<Directory> subDirs = root.getSubDirectories();

        Directory mainDir = subDirs.get(0);
        Directory testDir = subDirs.get(1);

        File mainFile = mainDir.getFiles().get(0);
        File testFile = testDir.getFiles().get(0);

        System.out.println("main/ contains: " + mainFile.getName());
        System.out.println("test/ contains: " + testFile.getName());
    }

    public static void main(String[] args) {
        Thread[] threads = new Thread[12];

        for (int i = 0; i < 12; i++) {
            threads[i] = new Thread(new FileSystemHandler());
            threads[i].start();
        }

        for (int i = 0; i < 12; i++) {
            try {
                threads[i].join();
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }
        }

        System.out.println("All threads finished.");
    }
}
