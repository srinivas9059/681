package umbcs681.hw12;

import java.time.LocalDateTime;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public abstract class FSElement {
    protected String name;
    protected int size;
    protected LocalDateTime creationTime;
    protected Directory parent;
    protected ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();

    public FSElement(Directory parent, String name, int size, LocalDateTime creationTime) {
        this.parent = parent;
        this.name = name;
        this.size = size;
        this.creationTime = creationTime;
    }

    public Directory getParent() {
        return parent;
    }

    public String getName() {
        return name;
    }

    public int getSize() {
        return size;
    }

    public LocalDateTime getCreationTime() {
        return creationTime;
    }

    public void setParent(Directory parent) {
        this.parent = parent;
    }

    public abstract boolean isDirectory();

    public abstract boolean isFile();

    public abstract boolean isLink();
}
