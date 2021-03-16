package DataStructure.StackDemo;

import DataStructure.ArrayDemo.MyArray;

public class MyStack<T> implements Stack<T> {

    MyArray<T> myArray;//数据容器

    public MyStack(){
        this(10);
    }

    public MyStack(int capacity){
        myArray = new MyArray<>(capacity);
    }

    @Override
    public int getSize(){
        return myArray.size;
    }

    @Override
    public boolean isEmpty(){
        return myArray.isEmpty();
    }

    @Override
    public void push(T ele){
        try{
            myArray.addLast(ele);
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public T peek(){
        T result = null;
        try{
            result = myArray.getElementByIndex(myArray.size-1);
        }catch(Exception e){
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public T pop() {
        T result = null;
        try {
            result = myArray.removeLastElement();
        }catch(Exception e){
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public String toString(){
        StringBuffer str = new StringBuffer();
        str.append(String.format("栈的容量是:%d,实际存放的元素的个数:%d ",myArray.getCapacity(),myArray.size));
        str.append(myArray.toString());
        str.append(" 栈顶");
        return str.toString();
    }
}
