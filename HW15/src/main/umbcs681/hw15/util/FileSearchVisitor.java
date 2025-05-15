package umbcs681.hw15.util;

import umbcs681.hw15.fs.Directory;
import umbcs681.hw15.fs.FSVisitor;
import umbcs681.hw15.fs.File;
import umbcs681.hw15.fs.Link;

import java.util.LinkedList;

public class FileSearchVisitor implements FSVisitor {
    private final LinkedList<File> foundFiles = new LinkedList<>();
    private final String fileName;

    public FileSearchVisitor(String fileName) {
        this.fileName = fileName;
    }

    public LinkedList<File> getFoundFiles() {
        return foundFiles;
    }

    @Override
    public void visit(Directory directory) {

    }

    @Override
    public void visit(File file) {
        if(fileName.equals(file.getName())) {
            foundFiles.add(file);
        }
    }

    @Override
    public void visit(Link link) {

    }
}
