package DataStructure;

public interface Set<T> {

    boolean isEmpty();

    boolean contains(T t);

    void add(T t);

    void removeEle(T t);

    int getSize();
}
