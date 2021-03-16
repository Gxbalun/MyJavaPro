package DataStructure;

public interface Queue<T> {
    public int getSize();

    public boolean isEmpty();

    public T getFront() throws IllegalAccessException;

    public void enqueue(T ele) throws Exception;

    public T dequeue() throws IllegalAccessException;
}
