package DataStructure.MaxHeapDemo;

import DataStructure.Queue;

public class PriorityQueue<T extends Comparable<T>> implements Queue<T> {

    private MaxHeap<T> maxHeap;

    public PriorityQueue(){
        maxHeap = new MaxHeap();
    }
    @Override
    public void enqueue(T ele) throws IllegalAccessException {
        maxHeap.add(ele);
    }

    @Override
    public T dequeue() throws IllegalAccessException {
        return maxHeap.removeMaxEle();
    }

    @Override
    public T getFront() throws IllegalAccessException {
        return maxHeap.getData()[0];
    }

    @Override
    public int getSize() {
        return maxHeap.getSize();
    }

    @Override
    public boolean isEmpty() {
        return maxHeap.isEmpty();
    }
}
