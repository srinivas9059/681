package umbcs681.hw07;

import java.time.LocalDateTime;
import java.util.LinkedList;

public class Directory extends FSElement {
    private final LinkedList<FSElement> children = new LinkedList<>();

    public Directory(Directory parent, String name, int size, LocalDateTime creationTime) {
        super(parent, name, size, creationTime);

        if(parent != null) {
            parent.appendChild(this);
        }
    }

    public LinkedList<FSElement> getChildren() {
        return children;
    }

    public void appendChild(FSElement child) {
        children.add(child);
        child.setParent(this);
    }

    public int countChildren() {
        return children.size();
    }

    public LinkedList<Directory> getSubDirectories() {
        LinkedList<Directory> directories = new LinkedList<>();
        for (FSElement element : children) {
            if (element.isDirectory()) {
                directories.add((Directory) element);
            }
        }
        return directories;
    }

    public LinkedList<File> getFiles() {
        LinkedList<File> files = new LinkedList<>();
        for (FSElement element : children) {
            if (element.isFile()) {
                files.add((File) element);
            }
        }
        return files;
    }

    public LinkedList<Link> getLinks() {
        LinkedList<Link> links = new LinkedList<>();
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
}
