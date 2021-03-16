/**
 * FileName: Solution4
 * Author:   abc
 * Date:     2021/2/2 12:08
 * Description: 多数元素
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package shuati.day03;

import java.util.HashMap;

/**
 * 〈一句话功能简述〉<br> 
 * 〈多数元素〉
 *
 * @author abc
 * @create 2021/2/2
 * @since 1.0.0
 */
public class Solution4 {

    //求多数元素
    public int majorityElement(int[] nums){
        //定义HashMap
        HashMap<Integer,Integer> map = new HashMap<>();

        //将数组中的元素放进map的key当中
        for (int num : nums) {
            if (!map.containsKey(num)) {
                map.put(num,0);
            }

            map.put(num,map.get(num)+1);
        }

        //定义中位值（half）<map中value ：当前value匹配的key就是多数元素
        int half = nums.length/2;

        for (Integer key : map.keySet()) {
            if (map.get(key)>half) {
                return key;
            }
        }

        return -1;
    }

}