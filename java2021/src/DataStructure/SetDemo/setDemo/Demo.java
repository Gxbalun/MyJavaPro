package DataStructure.SetDemo.setDemo;

import DataStructure.Set;
import DataStructure.SetDemo.BSTreeSet;
import DataStructure.SetDemo.LinkedSet;

import java.util.ArrayList;

public class Demo {

    public double testSet(Set set,String fileName){
        long startTime = System.nanoTime();
        ArrayList<String> arrayList = new ArrayList<>();
        boolean result = ReadFileDemo.readFile(fileName,arrayList);
        if (result){
            System.out.println("pride-and-prejudice共有"+arrayList.size()+"个单词");
            for (int i = 0; i < arrayList.size(); i++) {
                set.add(arrayList.get(i));
            }
            System.out.println("pride-and-prejudice共有"+set.getSize()+"个不重复单词");
        }
        long endTime = System.nanoTime();
        return (endTime - startTime) / 1000000000.0;
    }

    public static void main(String[] args) {
        String fileName = "D:/Study/JAVA补习/补习/数据结构与算法/树/myArr01/src/com/company/supplement/pride-and-prejudice.txt";
        LinkedSet<String> linkedSet = new LinkedSet<>();
        double time = new Demo().testSet(linkedSet,fileName);
        System.out.println(time);
        System.out.println("--------------------");
        BSTreeSet<String> bsTreeSet = new BSTreeSet<>();
        time = new Demo().testSet(bsTreeSet,fileName);
        System.out.println(time);
    }
}
