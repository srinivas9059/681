package umbcs681.hw15.fs;

import java.time.LocalDateTime;
import java.util.concurrent.ConcurrentLinkedQueue;

public class Directory extends FSElement {
    private final ConcurrentLinkedQueue<FSElement> children = new ConcurrentLinkedQueue<>();

    public Directory(Directory parent, String name, int size, LocalDateTime creationTime) {
        super(parent, name, size, creationTime);
        if (parent != null) {
            parent.appendChild(this);
        }
    }

    public ConcurrentLinkedQueue<FSElement> getChildren() {
        return children;
    }

    public void appendChild(FSElement child) {
        children.add(child);
        child.setParent(this);
    }

    public int countChildren() {
        return children.size();
    }

    public ConcurrentLinkedQueue<Directory> getSubDirectories() {
        ConcurrentLinkedQueue<Directory> directories = new ConcurrentLinkedQueue<>();
        for (FSElement element : children) {
            if (element.isDirectory()) {
                directories.add((Directory) element);
            }
        }
        return directories;
    }

    public ConcurrentLinkedQueue<File> getFiles() {
        ConcurrentLinkedQueue<File> files = new ConcurrentLinkedQueue<>();
        for (FSElement element : children) {
            if (element.isFile()) {
                files.add((File) element);
            }
        }
        return files;
    }

    public ConcurrentLinkedQueue<Link> getLinks() {
        ConcurrentLinkedQueue<Link> links = new ConcurrentLinkedQueue<>();
        for (FSElement element : children) {
            if (element.isLink()) {
                links.add((Link) element);
            }
        }
        return links;
    }

    public int getTotalSize() {
        int totalSize = 0;
        for (FSElement element : children) {
            if (element.isDirectory()) {
                totalSize += ((Directory) element).getTotalSize();
            } else {
                totalSize += element.getSize();
            }
        }
        return totalSize;
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
        for(FSElement element : children) {
            element.accept(visitor);
        }
    }
}
