package shuati.day02;
/**
 *   二分搜索
 * */
public class solution02 {
    public int searchInsert(int []nums,int target){
        if (nums == null || nums.length == 0){
            return 0;
        }

        int left = 0;
        int right = nums.length-1;
        int mid = left + (right-left)/2;

        while (left<right){
            if (nums[mid] == target){
                return mid;
            }else if (nums[mid] > target){
                right = mid;
            }else {
                left = mid + 1;
            }
        }
        return nums[left] < target ? left+1:left;
    }

}
