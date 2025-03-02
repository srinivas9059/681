package umbcs681.hw01.fs;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.stream.Collectors;

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
        return children.stream()
                .filter(element -> element.isDirectory())
                .map(element -> (Directory) element)
                .collect(Collectors.toCollection(LinkedList::new));
    }

    public LinkedList<File> getFiles() {
        return children.stream()
                .filter(element -> element.isFile())
                .map(element -> (File) element)
                .collect(Collectors.toCollection(LinkedList::new));
    }

    public LinkedList<Link> getLinks() {
        return children.stream()
                .filter(element -> element.isLink())
                .map(element -> (Link) element)
                .collect(Collectors.toCollection(LinkedList::new));
    }

    public int getTotalSize() {
        return children.stream()
                .mapToInt(element -> element.isDirectory()
                        ? ((Directory) element).getTotalSize()
                        : element.getSize())
                .sum();
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
        children.forEach(element -> element.accept(visitor));
    }
}
