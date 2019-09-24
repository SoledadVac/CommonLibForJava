package leetcode.BitManipulation;

import org.junit.Test;

/**
 * \* Created: liuhuichao
 * \* Date: 2019/9/23
 * \* Time: 28:49 PM
 * \* Description: 最大异或值
 * \
 * 给定一个非空数组，数组中元素为 a0, a1, a2, … , an-1，其中 0 ≤ ai < 231 。
 * <p>
 * 找到 ai 和aj 最大的异或 (XOR) 运算结果，其中0 ≤ i,  j < n 。
 * <p>
 * 你能在O(n)的时间解决这个问题吗？
 * <p>
 * 示例:
 * <p>
 * 输入: [3, 10, 5, 25, 2, 8]
 * <p>
 * 输出: 28
 * <p>
 * 解释: 最大的结果是 5 ^ 25 = 28.
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/maximum-xor-of-two-numbers-in-an-array
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class A421_MaximumXORofTwoNumbersinanArray {

    public int findMaximumXOR(int[] nums) {
        int res = 0;
        // todo : 最大异或值



        return res;
    }

    @Test
    public void test() {
        int[] nums = {3, 10, 5, 25, 2, 8};
        System.out.println("result = " + findMaximumXOR(nums));
    }
}
