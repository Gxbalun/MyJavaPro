/**
 * FileName: Solution2
 * Author:   abc
 * Date:     2021/2/2 9:44
 * Description: 最大的连续子序列
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package shuati.day03;

/**
 * 〈一句话功能简述〉<br> 
 * 〈最大的连续子序列〉
 *
 * @author abc
 * @create 2021/2/2
 * @since 1.0.0
 */

/*
*
*   思路:使用分治思想(使用动态规划才是最优解)：递归拆解数组，将数组分为左右两部分，当我们拆解到某个元素时返回
*   ，并组装。
*
*   假设拆解到[l,r] 这部分为[l,m] 和[m+1,r],需要合并的话需要维护几个变量
*   lSum  [l,r] 内以l为左端点的最大子序列和
*   rSum [l,r] 以r为右端点的最大子序列和
*   mSum [l,r] 内最大的子序列和
*   iSum [l,r]的区间和
* */
public class Solution2 {

    class Status{

        int lSum;
        int rSum;
        int mSum;
        int iSum;

        public Status(int lSum, int rSum, int mSum, int iSum) {
            this.lSum = lSum;
            this.rSum = rSum;
            this.mSum = mSum;
            this.iSum = iSum;
        }
    }

    public  Status getInfo(int[] nums,int l,int r){
        if (l == r) {
            return  new Status(nums[l],nums[l],nums[l],nums[l]);
        }

        //求出mid的值
        int m = l+(r-l)/2;
        //递归
        
        Status left = getInfo(nums,l,m);
        Status right = getInfo(nums,m+1,r);

        //left.lSum or  left.iSum+right.lSum
        int lSum = Math.max(left.lSum,left.iSum+right.lSum);
        int rSum = Math.max(right.rSum,left.rSum+right.iSum);
        int mSum =Math.max(left.rSum+right.lSum,Math.max(left.mSum,right.mSum));
        int iSum = left.iSum+ right.iSum;

        return  new Status(lSum,rSum,mSum,iSum);
    }

}