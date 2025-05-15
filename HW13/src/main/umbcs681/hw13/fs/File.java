package umbcs681.hw13.fs;

import java.time.LocalDateTime;

public class File extends FSElement {
    public File(Directory parent, String name, int size, LocalDateTime creationTime) {
        super(parent, name, size, creationTime);
        parent.appendChild(this);
    }

    @Override
    public boolean isDirectory() {
        return false;
    }

    @Override
    public boolean isFile() {
        return true;
    }

    @Override
    public boolean isLink() {
        return false;
    }

    @Override
    public void accept(FSVisitor visitor) {
        readWriteLock.readLock().lock();
        try {
            visitor.visit(this);
        } finally {
            readWriteLock.readLock().unlock();
        }
    }
}
