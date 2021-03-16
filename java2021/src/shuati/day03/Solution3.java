/**
 * FileName: Solution3
 * Author:   abc
 * Date:     2021/2/2 11:29
 * Description: 插入区间
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package shuati.day03;

import java.util.LinkedList;

/**
 * 〈一句话功能简述〉<br> 
 * 〈插入区间〉
 *
 * @author abc
 * @create 2021/2/2
 * @since 1.0.0
 */
public class Solution3 {


    public int[][] insert(int[][] intervals,int[] newInerval){
        LinkedList<int[]> result = new LinkedList<>();
        //维护一个指针
        int index =0 ;

        //第一步将intervals中开始时间比newIntervals中开始时间小的元素放入如result中；
        for (int[] interval : intervals) {
            if (interval[0] < newInerval[0]) {
                result.add(interval);
                index++;
            }else{
                break;
            }
        }

        //第二步：将newIntervals的元素放进result中（需要判断是否合并区间）
        if (result.size() == 0 || result.getLast()[1]< newInerval[0]) {
            result.add(newInerval);
        }else{
            result.getLast()[1]  = Math.max(result.getLast()[1],newInerval[1]);
        }

        //第三步。将intervals中剩余元素添加的result中
        for (int i = index; i < intervals.length; i++) {
            if (result.getLast()[1]<intervals[i][0]) {
                result.add(intervals[i]);
            }else{
                result.getLast()[1] = Math.max(result.getLast()[1],intervals[i][1]);
            }
        }

        return  result.toArray(new int[result.size()][2]);
    }
}