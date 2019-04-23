package leetcode;

import org.junit.Test;

import java.util.Arrays;

/**
 * \* Created: liuhuichao
 * \* Date: 2019/4/23
 * \* Time: 5:43 PM
 * \* Description: 数组拆分 I
 * 给定长度为 2n 的数组, 你的任务是将这些数分成 n 对, 例如 (a1, b1), (a2, b2), ..., (an, bn) ，使得从1 到 n 的 min(ai, bi) 总和最大。
 * <p>
 * 示例 1:
 * <p>
 * 输入: [1,4,3,2]
 * <p>
 * 输出: 4
 * 解释: n 等于 2, 最大总和为 4 = min(1, 2) + min(3, 4).
 * 提示:
 * <p>
 * n 是正整数,范围在 [1, 10000].
 * 数组中的元素范围在 [-10000, 10000].
 * \
 */
public class A561_ArrayPartitionI {

    public int arrayPairSum(int[] nums) {
        //没思路。。。艾玛。。
        //在LeetCode上看到一个分析，艾玛：
        /**
         Assume in each pair i, bi >= ai.
         Denote Sm = min(a1, b1) + min(a2, b2) + ... + min(an, bn).
         The biggest Sm is the answer of this problem. Given 1, Sm = a1 + a2 + ... + an.
         Denote Sa = a1 + b1 + a2 + b2 + ... + an + bn. Sa is constant for a given input.
         Denote di = |ai - bi|. Given 1, di = bi - ai. Denote Sd = d1 + d2 + ... + dn.
         So Sa = a1 + a1 + d1 + a2 + a2 + d2 + ... + an + an + dn = 2Sm + Sd => Sm = (Sa - Sd) / 2.
         To get the max Sm, given Sa is constant, we need to make Sd as small as possible.
         So this problem becomes finding pairs in an array that makes sum of di (distance between ai and bi) as small as possible.
         Apparently, sum of these distances of adjacent elements is the smallest. If that's not intuitive enough, see attached picture.
         Case 1 has the smallest Sd.
         * **/
        //结论就是：每组中an与bn的差值最小的时候，能取到最大的值
        Arrays.sort(nums);
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            //只需要取坐标为偶数的即可
            if (i % 2 == 0) {
                sum += nums[i];
            }
        }

        return sum;

    }

    @Test
    public void test() {
        int[] nums = {1, 4, 3, 2};
        System.out.println(arrayPairSum(nums));
    }
}
