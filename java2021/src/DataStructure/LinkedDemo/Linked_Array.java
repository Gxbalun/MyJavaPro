package DataStructure.LinkedDemo;
public class Linked_Array<T> {

    private class Node<T>{
        public T ele;
        public Node next;
        public int size;

        public Node(){
            this(null,null);
        }

        public Node(T ele){
            this.ele = ele;
            this.next = null;
        }

        public Node(T ele,Node node){
            this.ele = ele;
            this.next = node;
        }
    }

    private int size;

    private Node dummyHead; //虚拟头结点

    public Linked_Array(){
        this.size = 0;
        this.dummyHead = new Node(null,null);
    }

    public int getSize(){
        return size;
    }

    public boolean isEmpty(){
        return size == 0;
    }

    public void add(Node node,int index) throws IllegalAccessException {
        if (index < 0 || index > size){
            throw new IllegalAccessException("非法索引");
        }
        if (node == null){
            throw new IllegalAccessException("插入节点为空");
        }
        Node prev = dummyHead;
        for (int i = 0; i < index; i++) {
            prev = prev.next;
        }
        node.next = prev.next;
        prev.next = node;
        size++;
    }

    public void addHead(T ele) throws IllegalAccessException {
        add(new Node(ele),0);
    }

    public void addTail(T ele) throws IllegalAccessException {
        add(new Node(ele), size);
    }

    public T get(int index) throws IllegalAccessException {
        if (index < 0 || index > size){
            throw new IllegalAccessException("非法索引");
        }
        Node current = dummyHead.next;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        return (T)current.ele;
    }

    public T getHead() throws IllegalAccessException {
        return get(0);
    }

    public T getTail() throws IllegalAccessException {
        return get(size-1);
    }

    public boolean contains(T ele){
        Node current = dummyHead.next;
        while (current != null){
            if (current.ele.equals(ele)){
                return true;
            }else{
                current = current.next;
            }
        }
        return false;
    }

    public void set(int index,T ele) throws IllegalAccessException {
        if (index < 0 || index > size){
            throw new IllegalAccessException("非法索引");
        }
        Node current = dummyHead.next;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        current.ele = ele;
    }

    public void remove(int index) throws IllegalAccessException {
        if (index < 0 || index > size){
            throw new IllegalAccessException("非法索引");
        }
        Node prev = dummyHead;
        for (int i = 0; i < index; i++) {
            prev =prev.next;
        }
        Node deletenode = prev.next;
        prev.next = deletenode.next;
        deletenode.next = null;
        size--;
    }

    public void removeHead() throws IllegalAccessException {
        remove(0);
    }

    public void removeTail() throws IllegalAccessException {
        remove(size);
    }

    public void removeValue(T value){
        dummyHead.next = removeValue(dummyHead.next,value);
    }
    private Node removeValue(Node node,T value){
        //到底的情况
        if (node == null){
            return null;
        }
        Node resultNode = removeValue(node.next,value);
        if (node.ele.equals(value)){
            return resultNode;
        }else {
            node.next = resultNode;
            return node;
        }
    }

    @Override
    public String toString() {
        StringBuffer s = new StringBuffer();
        Node current = dummyHead.next;
        while (current != null){
            s.append(current.ele+"->");
            current = current.next;
        }
        s.append("null");
        return s.toString();
    }
}
