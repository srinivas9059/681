package umbcs681.hw12;

import java.time.LocalDateTime;

public class Link extends FSElement {
    private final FSElement target;

    public Link(Directory parent, String name, int size, LocalDateTime creationTime, FSElement target) {
        super(parent, name, size, creationTime);
        this.target = target;
        parent.appendChild(this);
    }

    public FSElement getTarget() {
        readWriteLock.readLock().lock();
        try {
            return target;
        } finally {
            readWriteLock.readLock().unlock();
        }
    }

    @Override
    public boolean isDirectory() {
        return false;
    }

    @Override
    public boolean isFile() {
        return false;
    }

    @Override
    public boolean isLink() {
        return true;
    }
}
