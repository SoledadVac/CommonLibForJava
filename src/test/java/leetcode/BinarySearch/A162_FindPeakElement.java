package leetcode.BinarySearch;

import org.junit.Test;

/**
 * \* Created: liuhuichao
 * \* Date: 2019/5/5
 * \* Time: 4:50 PM
 * \* Description: Find Peak Element
 * \
 * 峰值元素是指其值大于左右相邻值的元素。
 * <p>
 * 给定一个输入数组 nums，其中 nums[i] ≠ nums[i+1]，找到峰值元素并返回其索引。
 * <p>
 * 数组可能包含多个峰值，在这种情况下，返回任何一个峰值所在位置即可。
 * <p>
 * 你可以假设 nums[-1] = nums[n] = -∞。
 * <p>
 * 示例 1:
 * <p>
 * 输入: nums = [1,2,3,1]
 * 输出: 2
 * 解释: 3 是峰值元素，你的函数应该返回其索引 2。
 * 示例 2:
 * <p>
 * 输入: nums = [1,2,1,3,5,6,4]
 * 输出: 1 或 5
 * 解释: 你的函数可以返回索引 1，其峰值元素为 2；
 * 或者返回索引 5， 其峰值元素为 6。
 * 说明:
 * <p>
 * 你的解法应该是 O(logN) 时间复杂度的。
 */
public class A162_FindPeakElement {
    public int findPeakElement(int[] nums) {
        int left = 0;
        int right = nums.length - 1;
        while (right > left) {
            int middle = left + (right - left) / 2;
            //最左边和最右边都是负无穷值
            /**
             * O(logN)一般考虑二分搜索。有如下规律：

             规律一：如果nums[i] > nums[i+1]，则在i之前一定存在峰值元素

             规律二：如果nums[i] < nums[i+1]，则在i+1之后一定存在峰值元素
             * **/
            if (nums[middle + 1] > nums[middle]) {
                left = middle + 1;
            } else {
                right = middle;
            }
        }
        return left;
    }

    @Test
    public void test() {
        int[] nums = {1, 2, 3, 1};
        System.out.println("result = " + findPeakElement(nums));
    }


}
