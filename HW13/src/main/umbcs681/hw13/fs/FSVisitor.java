package umbcs681.hw13.fs;

public interface FSVisitor {
    public void visit(Directory directory);
    public void visit(File file);
    public void visit(Link link);
}
