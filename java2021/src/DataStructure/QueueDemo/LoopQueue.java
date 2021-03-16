package DataStructure.QueueDemo;

public class LoopQueue<T> implements Queue<T> {

    T[] data;
    int size;
    int front,tail;

    public LoopQueue(){
        this(10);
    }

    public LoopQueue(int capacity){
        data = (T[]) new Object[capacity + 1];
        size = 0;
        front = 0;
        tail = 0;
    }

    @Override
    public boolean isEmpty() {
        return front == tail;
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public void enqueue(T ele) {
        try{
            if((tail + 1) % data.length == front){
                resize(size * 2);
            }else{
                data[tail] = ele;
                tail = (tail + 1) % data.length;
                size++;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void resize(int newCapacity){
        T[] newData = (T[]) new Object[newCapacity + 1];
        for (int i = 0; i < size; i++) {
            newData[i] = data[(front + i) % data.length];
        }
        front = 0;
        tail = size;
        data = newData;
    }

    @Override
    public T getFront() {
        if (isEmpty()){
            return null;
        }
        return data[front];
    }

    @Override
    public T dequeue() {
        if (isEmpty()){
            return null;
        }
        T result = data[front];
        front = (front + 1) % data.length;
        size--;
        if (size == data.length / 4 && data.length / 2 != 0) {
            resize(data.length / 2);
        }
        return result;
    }
}
