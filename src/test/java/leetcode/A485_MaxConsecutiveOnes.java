package leetcode;

import org.junit.Test;

import java.util.Map;

/**
 * \* Created: liuhuichao
 * \* Date: 2019/4/23
 * \* Time: 7:53 PM
 * \* Description:最大连续1的个数
 * 给定一个二进制数组， 计算其中最大连续1的个数。
 * <p>
 * 示例 1:
 * <p>
 * 输入: [1,1,0,1,1,1]
 * 输出: 3
 * 解释: 开头的两位和最后的三位都是连续1，所以最大连续1的个数是 3.
 * 注意：
 * <p>
 * 输入的数组只包含 0 和1。
 * 输入数组的长度是正整数，且不超过 10,000。
 * <p>
 * \
 */
public class A485_MaxConsecutiveOnes {

    public int findMaxConsecutiveOnes(int[] nums) {
        int max = 0;
        int begin = 0;
        int end = 0;
        while (end < nums.length) {
            if (nums[begin] == 1 && nums[end] == 1) {
                max = Math.max(end - begin + 1, max);
            } else {
                begin = end + 1;
            }
            end++;
        }
        return max;
    }

    @Test
    public void test() {
        int[] nums = {1, 0, 1, 1, 0, 1};
        System.out.println("result = " + findMaxConsecutiveOnes(nums));
    }

}
