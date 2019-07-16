package leetcode;

import org.junit.Test;

import java.util.Stack;

/**
 * \* Created: liuhuichao
 * \* Date: 2019/7/15
 * \* Time: 29:05 PM
 * \* Description: 目标和
 * \
 * 给定一个非负整数数组，a1, a2, ..., an, 和一个目标数，S。现在你有两个符号 + 和 -。
 * 对于数组中的任意一个整数，你都可以从 + 或 -中选择一个符号添加在前面。
 * <p>
 * 返回可以使最终数组和为目标数 S 的所有添加符号的方法数。
 * <p>
 * 示例 1:
 * <p>
 * 输入: nums: [1, 1, 1, 1, 1], S: 3
 * 输出: 5
 * 解释:
 * <p>
 * -1+1+1+1+1 = 3
 * +1-1+1+1+1 = 3
 * +1+1-1+1+1 = 3
 * +1+1+1-1+1 = 3
 * +1+1+1+1-1 = 3
 * <p>
 * 一共有5种方法让最终目标和为3
 * 注意:
 * <p>
 * 数组的长度不会超过20，并且数组中的值全为正数。
 * 初始的数组的和不会超过1000。
 * 保证返回的最终结果为32位整数。
 */
public class A494_TargetSum {

    int methodNum = 0;

    public int findTargetSumWays(int[] nums, int S) {
        findTarget(nums, S, 0);
        return methodNum;
    }

    void findTarget(int[] nums, int S, int i) {
        if (i == nums.length && S == 0) {
            methodNum++;
            return;
        }
        if (i == nums.length) {
            return;
        }
        int s0 = S - nums[i];
        int s1 = S + nums[i];
        findTarget(nums, s0, i + 1);
        findTarget(nums, s1, i + 1);
    }

    @Test
    public void test() {
        int[] nums = {1, 1, 1, 1, 1};
        int S = 3;
        System.out.println("result = " + findTargetSumWays(nums, S));
    }
}
