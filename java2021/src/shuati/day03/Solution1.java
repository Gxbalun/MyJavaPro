/**
 * FileName: Solution1
 * Author:   abc
 * Date:     2021/2/2 8:54
 * Description: 合并区间
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package shuati.day03;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * 〈一句话功能简述〉<br> 
 * 〈合并区间〉
 *
 * @author abc
 * @create 2021/2/2
 * @since 1.0.0
 */
public class Solution1 {

    public int[][] merge(int[][] inervals){
        //创建用于保存合并元素的集合
        ArrayList<int[]> result = new ArrayList<>();
        if (inervals == null || inervals.length<2) {
            return inervals;
        }

        //对二维数组进行排序
        Arrays.sort(inervals,(a,b) -> a[0]-b[0]);
        for (int[] inerval : inervals) {
            //如果result的是空或者拿result中最后一个元素的结束时间和interval[0]开始时间，如果为空或者小于interval的开始时间，interval添加到result中
            if (result.size() == 0 || inerval[0] > result.get(result.size()-1)[1]){

                result.add(inerval);
            }else{
                result.get(result.size()-1)[1] = Math.max(result.get(result.size()-1)[1],inerval[1]);
            }
        }

        return result.toArray(new int[result.size()][2]);
    }

}