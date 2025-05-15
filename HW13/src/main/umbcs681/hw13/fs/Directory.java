package umbcs681.hw13.fs;

import java.time.LocalDateTime;
import java.util.LinkedList;

public class Directory extends FSElement {
    private final LinkedList<FSElement> children = new LinkedList<>();

    public Directory(Directory parent, String name, int size, LocalDateTime creationTime) {
        super(parent, name, size, creationTime);
        if (parent != null) {
            parent.appendChild(this);
        }
    }

    public LinkedList<FSElement> getChildren() {
        readWriteLock.readLock().lock();
        try {
            return new LinkedList<>(children);
        } finally {
            readWriteLock.readLock().unlock();
        }
    }

    public void appendChild(FSElement child) {
        readWriteLock.writeLock().lock();
        try {
            children.add(child);
            child.setParent(this);
        } finally {
            readWriteLock.writeLock().unlock();
        }
    }

    public int countChildren() {
        readWriteLock.readLock().lock();
        try {
            return children.size();
        } finally {
            readWriteLock.readLock().unlock();
        }
    }

    public LinkedList<Directory> getSubDirectories() {
        readWriteLock.readLock().lock();
        try {
            LinkedList<Directory> directories = new LinkedList<>();
            for (FSElement element : children) {
                if (element.isDirectory()) {
                    directories.add((Directory) element);
                }
            }
            return directories;
        } finally {
            readWriteLock.readLock().unlock();
        }
    }

    public LinkedList<File> getFiles() {
        readWriteLock.readLock().lock();
        try {
            LinkedList<File> files = new LinkedList<>();
            for (FSElement element : children) {
                if (element.isFile()) {
                    files.add((File) element);
                }
            }
            return files;
        } finally {
            readWriteLock.readLock().unlock();
        }
    }

    public LinkedList<Link> getLinks() {
        readWriteLock.readLock().lock();
        try {
            LinkedList<Link> links = new LinkedList<>();
            for (FSElement element : children) {
                if (element.isLink()) {
                    links.add((Link) element);
                }
            }
            return links;
        } finally {
            readWriteLock.readLock().unlock();
        }
    }

    public int getTotalSize() {
        readWriteLock.readLock().lock();
        try {
            int totalSize = 0;
            for (FSElement element : children) {
                if (element.isDirectory()) {
                    totalSize += ((Directory) element).getTotalSize();
                } else {
                    totalSize += element.getSize();
                }
            }
            return totalSize;
        } finally {
            readWriteLock.readLock().unlock();
        }
    }

    @Override
    public boolean isDirectory() {
        return true;
    }

    @Override
    public boolean isFile() {
        return false;
    }

    @Override
    public boolean isLink() {
        return false;
    }

    @Override
    public void accept(FSVisitor visitor) {
        visitor.visit(this);

        readWriteLock.readLock().lock();
        try {
            for(FSElement element : children) {
                element.accept(visitor);
            }
        } finally {
            readWriteLock.readLock().unlock();
        }
    }
}
