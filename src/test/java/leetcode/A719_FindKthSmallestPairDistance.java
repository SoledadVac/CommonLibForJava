package leetcode;

import org.junit.Test;

import java.util.Arrays;

/**
 * \* Created: liuhuichao
 * \* Date: 2019/5/24
 * \* Time: 26:32 PM
 * \* Description:  找出第 k 小的距离对
 * \
 * 给定一个整数数组，返回所有数对之间的第 k 个最小距离。一对 (A, B) 的距离被定义为 A 和 B 之间的绝对差值。
 * <p>
 * 示例 1:
 * <p>
 * 输入：
 * nums = [1,3,1]
 * k = 1
 * 输出：0
 * 解释：
 * 所有数对如下：
 * (1,3) -> 2
 * (1,1) -> 0
 * (3,1) -> 2
 * 因此第 1 个最小距离的数对是 (1,1)，它们之间的距离为 0。
 * 提示:
 * <p>
 * 2 <= len(nums) <= 10000.
 * 0 <= nums[i] < 1000000.
 * 1 <= k <= len(nums) * (len(nums) - 1) / 2.
 */
public class A719_FindKthSmallestPairDistance {
    public int smallestDistancePair(int[] nums, int k) {
        Arrays.sort(nums);
        int tempDist = 0;
        int tempK = 0;




        return -1;
    }

    @Test
    public void test() {
        int[] nums = {1, 3, 1};
        int k = 1;
        System.out.println(smallestDistancePair(nums, k));
    }


}
