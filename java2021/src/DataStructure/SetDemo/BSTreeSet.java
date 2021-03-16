package DataStructure.SetDemo;

import DataStructure.Set;
import DataStructure.TreeDemo.BSTree;

public class BSTreeSet<T extends Comparable<T>> implements Set<T> {

    BSTree<T> data;

    public BSTreeSet(){
        data = new BSTree<>();
    }

    @Override
    public boolean isEmpty() {
        return data.isEmpty();
    }

    @Override
    public boolean contains(T t) {
        return data.contains(t);
    }

    @Override
    public void add(T t) {
        data.add(t);
    }

    @Override
    public void removeEle(T t) {
        removeEle(t);
    }

    @Override
    public int getSize() {
        return data.getSize();
    }
}
