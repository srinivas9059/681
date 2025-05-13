package umbcs681.hw15.fs;

public interface FSVisitor {
    public void visit(Directory directory);
    public void visit(File file);
    public void visit(Link link);
}
