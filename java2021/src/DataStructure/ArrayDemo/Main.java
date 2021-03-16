package DataStructure.ArrayDemo;

import DataStructure.QueueDemo.LoopQueue;
import DataStructure.QueueDemo.MyQueue;
import DataStructure.StackDemo.MyStack;

import java.util.Random;

public class Main {

    public static void main(String[] args) throws Exception {
        //arrayTest();
        //stackTest();
        //queueTest();
        //queueTest2();
    }

    public static void arrayTest() {
        try{
            MyArray<Integer> myArray = new MyArray<>(20);
            for (int i = 0; i < 50; i++) {
                myArray.addLast(i+1);
            }
            System.out.println(myArray);
            myArray.addElement(5,5555);
            System.out.println(myArray);
            myArray.removeElement(2);
            System.out.println(myArray);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void stackTest(){
        MyStack<Integer> myStack = new MyStack<>(20);
        Random random = new Random();
        for (int i = 0; i < 50; i++) {
            myStack.push(random.nextInt(50));
            System.out.println(myStack.toString());
        }
        for (int i = 0; i < 49; i++) {
            myStack.pop();
            System.out.println(myStack.toString());
        }
    }

    public static void queueTest(){
        MyQueue<Integer> myQueue = new MyQueue<>(20);
        Random random = new Random();
        for (int i = 0; i < 50; i++) {
            myQueue.enqueue(random.nextInt(50));
            System.out.println(myQueue.toString());
        }
        while (!myQueue.isEmpty()){
            myQueue.dequeue();
            System.out.println(myQueue.toString());
        }
    }

    public static void queueTest2(){
        MyQueue<Integer> myQueue = new MyQueue<>(20);
        Random random = new Random();
        long startT = System.nanoTime();
        for (int i = 0; i < 100000; i++) {
            myQueue.enqueue(random.nextInt(50));
        }
        while(!myQueue.isEmpty()){
            myQueue.dequeue();
        }
        long endT = System.nanoTime();
        System.out.println("ArrayQueue:"+(endT-startT)/1000000000);
        LoopQueue<Integer> loopQueue = new LoopQueue<>(20);
        Random random1 = new Random();
        long startT1 = System.nanoTime();
        for (int i = 0; i < 100000; i++) {
            myQueue.enqueue(random1.nextInt(50));
        }
        while(!myQueue.isEmpty()){
            myQueue.dequeue();
        }
        long endT1 = System.nanoTime();
        System.out.println("LoopQueue:"+(endT1-startT1)/1000000000);
    }
}
