package DataStructure;

public interface Stack<T> {
    public int getSize();

    public boolean isEmpty();

    public void push(T ele);

    public T pop() throws IllegalAccessException;

    public T peek() throws IllegalAccessException;
}
