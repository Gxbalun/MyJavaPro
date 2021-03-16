package shuati.day02;

import java.util.LinkedList;
import java.util.List;

/**
 * 输出有效括号的个数
 * */

/*
 *  三要素：
 *      1.路径：当前已经做出的选择
 *      2.选择列表：可以做的选择
 *      3.结束条件：到达决策树的底层，无法再做出选择的条件
 *
 *  递归的关键：递归前做出选择，递归后撤销选择。
 *
 *  LinkedList result = new Linked();
 *  public void backTrack(路径,选择列表){
 *      if(满足结束条件){
 *          result.add(结果);
 *      }
 *      for(选择:选择列表){
 *          做出选择;
 *          backTrack(路径,选择列表);
 *          撤销选择;
 *      }
 *  }
 *
 *  选择列表只有两个：左括号，右括号。
 *  条件：左括号必须先添加而且有括号的数量必须小于左括号。
*/
public class solution01 {

    List<String> result = new LinkedList<>();

    public List<String> generateParentesis(int n){
        if (n == 0) {
            return result;
        }
        backTrack(n,new StringBuilder(),0,0);
        return result;
    }

    //定义方法路径选择
    public void backTrack(int n,StringBuilder tmpSb,int left,int right){
        StringBuilder newSb = new StringBuilder(tmpSb);
        if (left == right && left == n){
            result.add(newSb.toString());
        }
        if (left < n){
            newSb.append("(");
            backTrack(n,newSb,left+1,right);
            newSb.setLength(left+right);
        }
        if (right < left){
            newSb.append(")");
            backTrack(n,newSb,left,right+1);
            newSb.setLength(left+right);
        }
    }

    public static void main(String[] args) {
        solution01 solution01 = new solution01();
        List<String> list = solution01.generateParentesis(3);
        System.out.println(list);
    }
}
