package pattern.observer.push;

public interface Observer<E> {
    void update(E changedData);
}
