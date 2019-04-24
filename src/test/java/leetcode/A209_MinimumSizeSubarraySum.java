package leetcode;

import org.junit.Test;

import java.util.Arrays;

/**
 * \* Created: liuhuichao
 * \* Date: 2019/4/24
 * \* Time: 10:51 AM
 * \* Description:长度最小的子数组
 * 给定一个含有 n 个正整数的数组和一个正整数 s ，找出该数组中满足其和 ≥ s 的长度最小的连续子数组。如果不存在符合条件的连续子数组，返回 0。
 * <p>
 * 示例:
 * <p>
 * 输入: s = 7, nums = [2,3,1,2,4,3]
 * 输出: 2
 * 解释: 子数组 [4,3] 是该条件下的长度最小的连续子数组。
 * 进阶:
 * <p>
 * 如果你已经完成了O(n) 时间复杂度的解法, 请尝试 O(n log n) 时间复杂度的解法。
 * \
 */
public class A209_MinimumSizeSubarraySum {

    //O(n) 时间复杂度的解法
    public int minSubArrayLen(int s, int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        int result = Integer.MAX_VALUE;
        int begin = 0;
        int end = 0;
        int sum = 0;
        while (begin <= end && end < nums.length) {
            sum += nums[end];
            if (sum >= s) {
                result = Math.min(result, end - begin + 1);
                sum = 0;
                begin++;
                end = begin;
                continue;
            }
            if (end != nums.length - 1) {
                end++;
            } else {
                sum = 0;
                begin++;
                end = begin;
            }
        }

        if (result == Integer.MAX_VALUE) {
            return 0;
        }
        return result;
    }

    //TODO:  O(n log n) 时间复杂度的解法,目前无思路
    public int minSubArrayLen1(int s, int[] nums) {
        return 0;
    }

    @Test
    public void test() {
       /* int s = 213;
        int[] nums = {12, 28, 83, 4, 25, 26, 25, 2, 25, 25, 25, 12};*/
        int s = 7;
        int[] nums = {2, 3, 1, 2, 4, 3};
        System.out.println("result= " + minSubArrayLen(s, nums));//213
    }
}
