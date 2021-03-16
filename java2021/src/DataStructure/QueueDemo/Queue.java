package DataStructure.QueueDemo;

public interface Queue<T> {
    public void enqueue(T ele) throws Exception;

    public int getSize();

    public boolean isEmpty();

    public T getFront() throws IllegalAccessException;

    public T dequeue() throws IllegalAccessException;
}
