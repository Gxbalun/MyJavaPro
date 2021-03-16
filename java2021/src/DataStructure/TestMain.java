package DataStructure;
import DataStructure.LinkedDemo.Linked_Array;
import DataStructure.LinkedDemo.Linked_Queue;
import DataStructure.LinkedDemo.Linked_Stack;

import java.util.Random;

public class TestMain {
    public static void main(String[] args) throws Exception {
        //Linked_Array_Test();
        //Linked_Stack_Test();
        //Linked_Queue_Test();
    }

    public static void Linked_Array_Test() throws IllegalAccessException {
        Linked_Array l = new Linked_Array();
        Random random = new Random();
        for (int i = 0; i < 10; i++) {
            l.addHead(random.nextInt(100));
            System.out.println(l);
        }
        int size = l.getSize();
        System.out.print("获取4位置上的元素:"+l.get(4));
        System.out.println();
        System.out.println("修改4位置上的元素");
        l.set(4, 999999);
        System.out.println(l);
        System.out.println("是否包含值为999999元素:"+l.contains(999999));
        for (int i = 0; i < size; i++) {
            l.remove(0);
            System.out.println(l);
        }
    }

    public static void Linked_Stack_Test() throws IllegalAccessException {
        Linked_Stack l = new Linked_Stack();
        Random random = new Random();
        for (int i = 0; i < 10; i++) {
            l.push(random.nextInt(100));
            System.out.println(l);
        }
        int size = l.getSize();
        System.out.print("获取栈顶元素:"+l.peek());
        System.out.println();
        for (int i = 0; i < size; i++) {
            l.pop();
            System.out.println(l);
        }
    }

    public static void Linked_Queue_Test() throws IllegalAccessException {
        Linked_Queue l = new Linked_Queue();
        Random random = new Random();
        for (int i = 0; i < 10; i++) {
            l.enqueue(random.nextInt(100));
            System.out.println(l);
        }
        int size = l.getSize();
        System.out.print("获取队首元素:"+l.getFront());
        System.out.println();
        for (int i = 0; i < size; i++) {
            l.dequeue();
            System.out.println(l);
        }
    }
}
