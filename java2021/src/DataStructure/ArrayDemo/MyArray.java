package DataStructure.ArrayDemo;
public class MyArray<T> {
    
   public int size; //实际存放的元素的个数
   public T []data;  //数据容器

    //构造函数
    public MyArray(int capacity){
    data = (T[]) new Object[capacity];
    size = 0;
}

    public MyArray(){
        this(20);
    }

    //e判断数组是否为空
    public boolean isEmpty(){
        return size == 0;
    }

    // 获取数组的容量
    public int getCapacity(){
        return data.length;
    }

    //在数组最后一个元素后添加元素
    public void addLast(T ele) throws Exception {
        addElement(size,ele);
    }

    //向数组指定位置添加指定元素
    public void addElement(int index, T ele) throws Exception {
        if (index < 0 || index > size) {
            throw new Exception("Index is invalid!");
        }
        if (size == data.length) {
            // 进行数组扩容
            resize(2 * getCapacity());
        }
        for (int i = size; i > index; i--) {
            data[i] = data[i-1];
        }
        data[index] = ele;
        size++;
    }

    //改变数组容量
    private void resize(int newCapacity){
        T [] newData =(T[]) new Object[newCapacity];
        for (int i = 0; i < size; i++) {
            newData[i] = data[i];
        }
        data = newData;
    }

    //修改指定位置的元素
    public void setElementByIndex(int index,T ele) throws Exception{
        if(index < 0 || index >= size){
            throw new Exception("位置错误");
        }
        data[index] = ele;
    }

    //获取指定位置的元素
    public T getElementByIndex(int index) throws Exception{
        if(index < 0 || index >= size){
            throw new Exception("位置错误");
        }
        return data[index];
    }

    //判断数组中是否包含指定元素
    public boolean contains(T ele){
        boolean flag = false;
        for (int i = 0; i < size; i++) {
            if (data[i] == ele){
                flag = true;
                break;
            }
        }
        return flag;
    }

    //寻找指定元素的位置
    public int findIndex(T ele){
        int index = -1;
        for (int i = 0; i < size; i++) {
            if (data[i] == ele){
                index = i;
                break;
            }
        }
        return index;
    }

    // 删除指定位置的元素
    public T removeElementByIndex(int index) throws Exception{
        T result = data[index];
        if(index < 0 || index >= size){
            throw new Exception("位置错误");
        }
        for (int i = index; i < size-1; i++) {
            data[i] = data[i+1];
        }
        size--;
        //缩容
        if (size == getCapacity()/3 && getCapacity()/2 != 0){
            resize(getCapacity()/2);
        }
        return result;
    }

    //删除指定元素
    public void removeElement(T ele) throws Exception{
            removeElementByIndex(findIndex(ele));
    }

    public T removeLastElement() throws Exception {
        removeElementByIndex(size - 1);
        return data[size-1];
    }

    public String toString() {
        StringBuffer str = new StringBuffer();
        str.append(String.format("容量为:%d,存放的元素个数是:%d ", getCapacity(), size));
        str.append("[");
        for (int i = 0; i < size; i++) {
           str.append(data[i]);
           if (i != size-1){
               str.append(",");
           }
        }
        str.append("]");
        return str.toString();
    }
}
