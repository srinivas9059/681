package umbcs681.hw07;

public class GetFileSystemRunnable implements Runnable {
    private final FileSystem[] fileSystemInstances;
    private final int index;

    public GetFileSystemRunnable(FileSystem[] fileSystemInstances, int index) {
        this.fileSystemInstances = fileSystemInstances;
        this.index = index;
    }

    @Override
    public void run() {
        FileSystem fs = FileSystem.getFileSystem();
        fileSystemInstances[index] = fs;
        System.out.println("Thread " + Thread.currentThread().threadId() +
                " got FileSystem instance: " + fs);
    }
}
