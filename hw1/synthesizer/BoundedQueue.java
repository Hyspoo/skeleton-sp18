package synthesizer;

public interface BoundedQueue<T> {

    int capacity();
    int fillCount();
    void enqueue(T x);
    T dequeue();
    T peek();

    default boolean isEmpty() {
        return false;
    }
    default boolean isFull() {
        return false;
    }
}
