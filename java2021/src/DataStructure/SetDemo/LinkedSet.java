package DataStructure.SetDemo;

import DataStructure.LinkedDemo.Linked_Array;
import DataStructure.Set;

public class LinkedSet<T> implements Set<T> {
    //数据容器
    Linked_Array<T> data;

    public LinkedSet(){
        data = new Linked_Array<>();
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
        boolean flag = contains(t);
        if (flag){
            return;
        }
        try {
            data.addTail(t);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void removeEle(T t) {
        boolean flag = contains(t);
        if (!flag){
            return;
        }
        data.removeValue(t);
    }

    @Override
    public int getSize() {
        return data.getSize();
    }
}
