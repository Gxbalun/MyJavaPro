package DataStructure.LinkedDemo;

import java.util.Random;

public class MyLinked {
    public static void main(String[] args) throws Exception {
        //LinkedArrayTest();
        //LinkedStackTest();
        LinkedQueueTest();
    }

    public static void LinkedStackTest() throws Exception {
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

    public static void LinkedArrayTest() throws IllegalAccessException {
        Linked_Array l = new Linked_Array();
        Random random = new Random();
        for (int i = 0; i < 10; i++) {
            l.addHead(random.nextInt(100));
            System.out.println(l);
        }
        int size = l.getSize();
        System.out.print("获取4位置上的元素:"+l.get(4));
        System.out.println();
        for (int i = 0; i < size; i++) {
            l.remove(0);
            System.out.println(l);
        }
    }

    public static void LinkedQueueTest() throws Exception {
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
