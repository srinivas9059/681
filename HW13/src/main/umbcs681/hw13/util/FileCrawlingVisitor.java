package umbcs681.hw13.util;

import umbcs681.hw13.fs.Directory;
import umbcs681.hw13.fs.FSVisitor;
import umbcs681.hw13.fs.File;
import umbcs681.hw13.fs.Link;

import java.util.LinkedList;

public class FileCrawlingVisitor implements FSVisitor {
    private final LinkedList<File> filesCrawled = new LinkedList<>();

    public LinkedList<File> getFiles() {
        return filesCrawled;
    }

    @Override
    public void visit(Directory directory) {

    }

    @Override
    public void visit(File file) {
        filesCrawled.add(file);
    }

    @Override
    public void visit(Link link) {

    }
}
