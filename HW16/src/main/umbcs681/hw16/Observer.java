package umbcs681.hw16;

public interface Observer<T> {
    void update(Observable<T> sender, T event);
}
