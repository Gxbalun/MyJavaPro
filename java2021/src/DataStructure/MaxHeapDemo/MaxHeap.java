package DataStructure.MaxHeapDemo;
/*
    最大堆
    1.是一渴望完全二叉树
    2.父节点(优先级)总是大于等于孩子结点
 */
public class MaxHeap<T extends Comparable<T>> {
    private T []data; //数据容器，保存完全二叉树中结点的内容
    private int size;   //保存树中元素的个数

    public MaxHeap(){
        data = (T[])new Comparable[50];
        size = 0;
    }

    //判断树是否为空
    public boolean isEmpty(){
        return size == 0;
    }

    //获取树中元素的个数
    public int getSize(){
        return size;
    }

    //获取数据
    public T[] getData(){
        return data;
    }

    //向最大堆的这棵树中添加元素
    public void add(T ele){
        data[size] = ele;
        size++;
        //上浮操作
        floatUp(size-1);
        //floatUp2(size-1,ele);
    }
    private void floatUp(int index){
        //获取父结点的所有
        int parentIndex = (index - 1)/2;
        while (index > 0 && data[parentIndex].compareTo(data[index]) < 0){
            //进行比较操作
            swap(data,parentIndex,index);
            index = parentIndex;
            parentIndex = (index - 1)/2;
        }
    }
    private void floatUp2(int index,T ele){
        //获取父结点的所有
        int parentIndex = (index - 1)/2;
        while (index > 0 && data[parentIndex].compareTo(ele) < 0){
            //进行比较操作
            data[index] = data[parentIndex];
            index = parentIndex;
            parentIndex = (index - 1)/2;
        }
        data[index] = ele;
    }

    //取出优先级最高的元素
    public T removeMaxEle() {
        if (isEmpty()){
            throw new IllegalArgumentException("堆为空");
        }
        T ele = data[0];
        //下沉操作
        swim();
        return ele;
    }
    private void swim(){
        data[0] = data[size - 1];
        size--;
        int currentIndex = 0;
        int leftIndex = 2 * currentIndex + 1;
        int changeIndex = leftIndex;
        while (leftIndex < getSize()) {
            //与左右孩子优先级最高的结点进行交换
            //找到优先级高的结点
            if (leftIndex + 1 < getSize() && data[leftIndex + 1].compareTo(data[leftIndex]) > 0) { //存在右结点
                changeIndex += 1;
            }
            if (data[currentIndex].compareTo(data[changeIndex]) < 0) {
                //交换操作
                swap(data, changeIndex, currentIndex);
                currentIndex = changeIndex;
                leftIndex = 2 * currentIndex + 1;
                changeIndex = leftIndex;
            }else {
                break;
            }
        }
    }

    //辅助方法(交换操作)
    private void swap(T []arr,int parentIndex,int index){
        T temp = arr[parentIndex];
        arr[parentIndex] = arr[index];
        arr[index] = temp;
        /*或者
        arr[index] = arr[index] - arr[parentIndex];
        arr[parentIndex] = arr[parentIndex] + arr[index];
        arr[index] = arr[parentIndex] - arr[index];
        */
    }

    //替换优先级最高的元素
    public void replace(T newValue){
        if (isEmpty()){
            throw new IllegalArgumentException("堆为空");
        }
        swim(newValue);
    }
    private void swim(T newValue){
        data[0] = newValue;
        int currentIndex = 0;   //从根结点开始下沉操作
        int leftIndex = 2 * currentIndex + 1;
        int changeIndex = leftIndex;
        while (leftIndex < getSize()) {
            //与左右孩子优先级最高的结点进行交换
            //找到优先级高的结点
            if (leftIndex + 1 < getSize() && data[leftIndex + 1].compareTo(data[leftIndex]) > 0) { //存在右结点
                changeIndex += 1;
            }
            if (data[currentIndex].compareTo(data[changeIndex]) < 0) {
                //交换操作
                swap(data, changeIndex, currentIndex);
                currentIndex = changeIndex;
                leftIndex = 2 * currentIndex + 1;
                changeIndex = leftIndex;
            }else {
                break;
            }
        }
    }

    //创建最大堆 Heapify(将任意数组整理成最大堆)
    public void heapify(T []arr){
        if (arr == null || arr.length == 0){
            throw new IllegalArgumentException("数组为空");
        }
        //从最后一个元素的父结点开始整理(进行下沉操作)
        int adjustIndex = (arr.length - 1 - 1)/2;
        for (; adjustIndex >= 0; adjustIndex--) {
            swim(arr,adjustIndex,arr.length);
        }
    }
    private void swim(T []arr,int index,int length){
        int currentIndex = index;
        int leftIndex = 2 * currentIndex + 1;
        int changeIndex = leftIndex;
        while (leftIndex < length){
            if (leftIndex + 1 < length && arr[leftIndex + 1].compareTo(arr[leftIndex]) > 0){
                changeIndex += 1;
            }
            if (arr[currentIndex].compareTo(arr[changeIndex]) < 0){
                swap(arr,changeIndex,currentIndex);
                currentIndex = changeIndex;
                leftIndex = 2 * currentIndex + 1;
                changeIndex = leftIndex;
            }else{
                break;
            }
        }
    }

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < getSize(); i++) {
            sb.append(data[i]+",");
        }
        return sb.substring(0,sb.lastIndexOf(","));
    }

    public static void main(String[] args) {
        //Integer []arr = {23,45,13,24,18};
        Integer []arr2 = {1,45,57,67,12,18,34,27,58};
        MaxHeap<Integer> maxHeap = new MaxHeap<>();
        for (int i = 0; i < arr2.length; i++) {
            maxHeap.add(arr2[i]);
        }
        System.out.println(maxHeap);
        maxHeap.heapify(arr2);
        System.out.println(maxHeap);
        /*System.out.println();
        for (int i = 0; i < arr.length; i++) {
            maxHeap.add(arr[i]);
        }
        Integer []data = maxHeap.getData();
        for (int i = 0; i < maxHeap.getSize(); i++) {
            System.out.println(data[i]);
        }
        System.out.println("---------------------");
        maxHeap.replace(22);
        data = maxHeap.getData();
        for (int i = 0; i < maxHeap.getSize(); i++) {
            System.out.println(data[i]);
        }
        //取出最大元素
        for (int i = 0; i < 5; i++) {
            Integer maxele = maxHeap.removeMaxEle();
            System.out.println("maxelement: "+maxele);
        }*/
    }
}
