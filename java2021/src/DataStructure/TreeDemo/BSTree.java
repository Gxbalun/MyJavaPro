package DataStructure.TreeDemo;

import java.util.LinkedList;
import java.util.Queue;

public class BSTree<T extends Comparable<T>> {

    private static class Node<T extends Comparable<T>>{

        T ele;  //结点value
        Node left,right;    //左右孩子

        public Node(T ele){
            this.ele = ele;
            this.left = null;
            this.right = null;
        }
    }

    private Node root;  //根结点
    private int size;

    public BSTree(){
        root = null;
        size = 0;
    }

    //获取书中的结点个数
    public int getSize(){
        return size;
    }

    //判断树是否为空
    public boolean isEmpty(){
        return size == 0;
    }

    //向树中添加元素
    public void add(T ele){
        root = addElement(root,ele);
    }
    private Node addElement(Node node,T ele){
        if (node == null){
            size++;
            return new Node(ele);
        }
        if (node.ele.compareTo(ele) > 0){
            node.left = addElement(node.left,ele);
        }else if (node.ele.compareTo(ele) < 0){
            node.right = addElement(node.right,ele);
        }
        return node;
    }

    //判断二分搜索树中是否存在指定元素
    public boolean contains(T ele){
        return containsElement(root,ele);
    }
    private boolean containsElement(Node node,T ele){
        if (node == null){
            return false;
        }
        if (node.ele.compareTo(ele) > 0){
            return containsElement(node.left,ele);
        }else if (node.ele.compareTo(ele) < 0){
            return containsElement(node.right,ele);
        }else{
            return true;
        }
    }

    //二叉树的遍历(前序遍历)  中左右
    public String preOrder(){
        if (root == null){
            return null;
        }
        StringBuffer result = new StringBuffer();
        preOrderTree(root,result);
        return result.toString();
    }
    private void preOrderTree(Node node,StringBuffer result){
        if (node == null){
            return;
        }
        //1.当前结点
        result.append(node.ele + "--");
        //2.左子树
        preOrderTree(node.left,result);
        //3.右子树
        preOrderTree(node.right,result);
    }

    //二叉树的遍历(后序遍历)  左右中
    public String subOrder(){
        if (root == null){
            return null;
        }
        StringBuffer result = new StringBuffer();
        subOrderTree(root,result);
        return result.toString();
    }
    private void subOrderTree(Node node,StringBuffer result){
        if (node == null){
            return;
        }
        //1.左子树
        subOrderTree(node.left,result);
        //2.右子树
        subOrderTree(node.right,result);
        //3.当前结点
        result.append(node.ele + "--");
    }

    //二叉树的遍历(中序遍历)  左中右(有排序的功能)
    public String middleOrder(){
        if (root == null){
            return null;
        }
        StringBuffer result = new StringBuffer();
        middleOrderTree(root,result);
        return result.toString();
    }
    private void middleOrderTree(Node node,StringBuffer result){
        if (node == null){
            return;
        }
        //1.左子树
        middleOrderTree(node.left,result);
        //2.当前结点
        result.append(node.ele + "--");
        //3.右子树
        middleOrderTree(node.right,result);
    }

    //层序遍历
    public String levelOrder(){
        StringBuffer result = new StringBuffer();
        Queue<Node> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()){
            Node node = queue.poll();
            result.append(node.ele+"--");
            if (node.left != null){
                queue.offer(node.left);
            }
            if (node.right != null){
                queue.offer(node.right);
            }
        }
        return result.toString();
    }

    //查询二分搜索树中的最小元素
    public Node findMinEle(){
        if (root == null){
            return null;
        }
        Node current = root;
        while (current.left == null){
            current = current.left;
        }
        return current;
    }

    //查找二分搜索树中的最小元素(方式2 递归方式)
    public Node findMinEle2(){
        if (root == null){
            return null;
        }
        return findMinEle2(root);
    }
    private Node findMinEle2(Node node){
        if (node.left == null){
            return node;
        }
        return findMinEle2(node.left);
    }

    //查询二分搜索树中的最大元素
    public Node findMaxEle(){
        if (root == null){
            return null;
        }
        Node current = root;
        while (current.right == null){
            current = current.right;
        }
        return current;
    }

    //删除最小元素
    public T removeMinEle(){
        Node delNode = findMinEle2();
        if (delNode == null){
            return null;
        }
        //进行删除操作
        root = removeMinNode(root);
        return (T) delNode.ele;
    }
    private Node removeMinNode(Node node){
        if (node.left == null){
            Node result = node.right;
            node.right = null;
            size--;
            return result;
        }
        node.left = removeMinNode(node.left);
        return node;
    }

    //从二分搜索树中找到待删除结点
    public Node getNode(T ele){
        if (root == null){
            return null;
        }
        return getNode(root,ele);
    }
    private Node getNode(Node node,T ele){
        if (node == null){
            return null;
        }
        if (node.ele.compareTo(ele) == 0){
            return node;
        }else if (node.ele.compareTo(ele) > 0){
            return getNode(node.left,ele);
        }else {
            return getNode(node.right,ele);
        }
    }


    public static void main(String[] args) {
        int []a = {2,1,5,3,4,7,6};
        BSTree<Integer> tree = new BSTree<>();
        for (int i = 0; i < a.length; i++) {
            tree.add(a[i]);
        }
        System.out.println("树的大小为: "+tree.getSize());
        System.out.println("树中包含9吗?"+tree.contains(9));
        System.out.println("树中包含2吗?"+tree.contains(2));
        System.out.println("前序遍历\n"+tree.preOrder());
        System.out.println("后序遍历\n"+tree.subOrder());
        System.out.println("中序遍历\n"+tree.middleOrder());
        System.out.println("层序遍历\n"+tree.levelOrder());
        System.out.println("树中的最小元素为:"+tree.findMinEle());
        System.out.println("树中的最大元素为:"+tree.findMaxEle());
        System.out.println("删除最小元素~~~");
        tree.removeMinEle();
        System.out.println(tree.middleOrder());
        System.out.println(tree.getNode(3));
    }
}
