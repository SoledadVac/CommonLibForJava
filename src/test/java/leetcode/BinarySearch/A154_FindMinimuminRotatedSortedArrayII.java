package leetcode.BinarySearch;

import org.junit.Test;

/**
 * \* Created: liuhuichao
 * \* Date: 2019/5/23
 * \* Time: 26:24 PM
 * \* Description: 寻找旋转排序数组中的最小值 II
 * 假设按照升序排序的数组在预先未知的某个点上进行了旋转。
 * <p>
 * ( 例如，数组 [0,1,2,4,5,6,7] 可能变为 [4,5,6,7,0,1,2] )。
 * <p>
 * 请找出其中最小的元素。
 * <p>
 * 注意数组中可能存在重复的元素。
 * <p>
 * 示例 1：
 * <p>
 * 输入: [1,3,5]
 * 输出: 1
 * 示例 2：
 * <p>
 * 输入: [2,2,2,0,1]
 * 输出: 0
 * 说明：
 * <p>
 * 这道题是 寻找旋转排序数组中的最小值 的延伸题目。
 * 允许重复会影响算法的时间复杂度吗？会如何影响，为什么？
 * \
 */
public class A154_FindMinimuminRotatedSortedArrayII {
    public int findMin(int[] nums) {
        int left = 0;
        int right = nums.length;
        int min = Integer.MAX_VALUE;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == nums[left]) {
                //可能是递增的序列
                min = Math.min(min, nums[left]);
                left++;
            } else if (nums[mid] > nums[left]) {
                //有序的
                min = Math.min(min, nums[left]);
                left = mid + 1;
            } else {
                min = Math.min(min, nums[mid]);
                right = mid;
            }
        }
        if (left != nums.length) {
            min = Math.min(min, nums[left]);
        }
        return min;
    }


    @Test
    public void test() {
        //2, 2, 2, 0, 1
        int nums[] = {3, 1, 3, 3};
        System.out.println("result = " + findMin(nums));
    }


}
