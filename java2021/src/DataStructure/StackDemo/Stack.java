package DataStructure.StackDemo;

public interface Stack<T> {
    public void push(T ele) throws IllegalAccessException;

    public T pop() throws Exception;

    public T peek() throws IllegalAccessException;

    public int getSize();

    public boolean isEmpty();
}
