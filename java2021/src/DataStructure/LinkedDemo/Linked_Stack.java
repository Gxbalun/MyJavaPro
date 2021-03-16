package DataStructure.LinkedDemo;

import DataStructure.Stack;

public class Linked_Stack<T> implements Stack<T> {

    Linked_Array<T> data;

    public Linked_Stack(){
        data = new Linked_Array<>();
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
    public void push(T ele) {
        try {
            data.addHead(ele);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    @Override
    public T pop() throws IllegalAccessException {
            T Head = data.getHead();
            data.removeHead();
            return Head;
    }

    @Override
    public T peek() throws IllegalAccessException {
        return data.getHead();
    }

    @Override
    public String toString() {
        StringBuffer s = new StringBuffer();
        s.append("栈顶[ ");
        s.append(data.toString());
        s.append(" ]栈底");
        return s.toString();
    }
}
