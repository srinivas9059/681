package umbcs681.hw15.util;

import umbcs681.hw15.fs.Directory;
import umbcs681.hw15.fs.FSVisitor;
import umbcs681.hw15.fs.File;
import umbcs681.hw15.fs.Link;

public class CountingVisitor implements FSVisitor {
    private int directoryCount = 0;
    private int fileCount = 0;
    private int linkCount = 0;

    public int getDirectoryCountCount() {
        return directoryCount;
    }

    public int getFileCount() {
        return fileCount;
    }

    public int getLinkCount() {
        return linkCount;
    }

    @Override
    public void visit(Directory directory) {
        directoryCount += 1;
    }

    @Override
    public void visit(File file) {
        fileCount += 1;
    }

    @Override
    public void visit(Link link) {
        linkCount += 1;
    }
}
