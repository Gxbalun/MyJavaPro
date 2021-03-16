package DataStructure.QueueDemo;

import DataStructure.ArrayDemo.MyArray;

public class MyQueue<T> implements Queue<T> {

    MyArray<T> myArray;
    int size;

    public MyQueue(int capacity){
        myArray = new MyArray<>(capacity);
        size = 0;
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return myArray.isEmpty();
    }

    @Override
    public void enqueue(T ele) {
        try{
            myArray.addLast(ele);
            size++;
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public T getFront() {
        T result = null;
        try{
           result = myArray.getElementByIndex(0);
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public T dequeue() {
        T result = null;
        try{
            result = myArray.getElementByIndex(0);
            myArray.removeElementByIndex(0);
            size--;
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public String toString() {
        StringBuffer str = new StringBuffer();
        str.append(String.format("队列的容量为:%d,实际存放元素的个数为:%d ",myArray.getCapacity(),size));
        str.append("队首 ");
        str.append(myArray.toString());
        str.append(" 对尾");
        return  str.toString();
    }
}
