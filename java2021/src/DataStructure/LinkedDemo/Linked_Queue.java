package DataStructure.LinkedDemo;

import DataStructure.Queue;

public class Linked_Queue<T> implements Queue<T> {

    Linked_Array data;

    public Linked_Queue(){
        data = new Linked_Array();
    }

    @Override
    public int getSize() {
        return data.getSize();
    }

    @Override
    public boolean isEmpty() {
        return data.isEmpty();
    }

    @Override
    public T getFront() throws IllegalAccessException {
        return (T)data.getHead();
    }

    @Override
    public void enqueue(T ele) throws IllegalAccessException {
        data.addTail(ele);
    }

    @Override
    public T dequeue() throws IllegalAccessException {
        T Head = (T)data.getHead();
        data.removeHead();
        return Head;
    }

    @Override
    public String toString() {
        StringBuffer s = new StringBuffer();
        s.append("队首[ ");
        s.append(data.toString());
        s.append(" ]队尾");
        return s.toString();
    }
}
