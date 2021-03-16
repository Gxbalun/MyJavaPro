package shuati.day01;

/**
 *   自定义数组
 */

public class Array<T> {
    private T []data;
    private int size;

    public Array(int capacity){
        data = (T[]) new Object[capacity];
        size = 0;
    }

    public Array(){
        new Array(10);
    }

    //判断是否为空
    public boolean isEmpty(){
        return size == 0;
    }

    //获取有效元素个数
    public int getSize(){
        return size;
    }

    //获取数组容量
    public int getlength(){
        return data.length;
    }

    //在数组末尾添加元素
    public void addLast(T ele){
        /*if(size == data.length){
            throw new IllegalArgumentException("Array is full");
        }
        data[size] = ele;
        size++;*/
        addElement(size,ele);
    }

    //向数组指定位置添加元素
    public void addElement(int index,T ele){
        if(size == data.length){
            resize(2* data.length);
        }
        if (index < 0 || index > size){
            throw new IllegalArgumentException("index is wrong");
        }
        for (int i = size-1; i >= index; i--) {
            data[i+1] = data[i];
        }
        data[index] = ele;
        size++;
    }

    private void resize(int newcapacity){
        T[] newdata = (T[]) new Object[newcapacity];
        for (int i = 0; i < size; i++) {
            newdata[i] = data[i];
        }
        data = newdata;
    }

    //获取指定索引的元素
    public T getElement(int index){
        if (index < 0 || index >= size){
            throw new IllegalArgumentException("index is wrong");
        }
        return data[index];
    }

    //修改指定位置的元素
    public void setElement(int index,T ele){
        if (index < 0 || index >= size){
            throw new IllegalArgumentException("index is wrong");
        }
        data[index] = ele;
    }

    //判断数组中是否包含指定元素
    public boolean contains(T ele){
        for (int i = 0; i < size; i++) {
            if (data[i] == ele){
                return true;
            }
        }
        return false;
    }

    //获取指定元素的索引
    public int getIndex(T ele){
        for (int i = 0; i < size; i++) {
            if (data[i] == ele){
                return i;
            }
        }
        return -1;
    }

    //删除指定位置上的元素
    public T remove(int index){
        if (index < 0 || index >= size){
            throw new IllegalArgumentException("index is wrong");
        }
        T ret = data[index];
        for (int i = index; i < size; i++) {
            data[i-1] = data[i];
        }
        size--;
        data[size] = null;
        if (size == data.length){
            resize(data.length/2);
        }
        return ret;
    }

    public T removeFirst(){
        return remove(0);
    }

    public T removeLast(){
        return remove(size-1);
    }

    //删除数组中指定的元素
    public void removeElement(T ele){
        int index = getIndex(ele);
        if (index != -1){
            remove(index);
        }
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        res.append(String.format("Array size: %d ,capacity: %d\n",size,data.length));
        res.append("[");
        for (int i = 0; i < size; i++) {
            res.append(data[i]);
            if (i != size-1){
                res.append(",");
            }
        }
        res.append("]");
        return res.toString();
    }
}
