package leetcode;

import org.junit.Test;

import java.util.Arrays;

/**
 * \* Created: liuhuichao
 * \* Date: 2019/5/23
 * \* Time: 31:41 PM
 * \* Description: 寻找重复数
 * \
 * <p>
 * 给定一个包含 n + 1 个整数的数组 nums，其数字都在 1 到 n 之间（包括 1 和 n），可知至少存在一个重复的整数。假设只有一个重复的整数，找出这个重复的数。
 * <p>
 * 示例 1:
 * <p>
 * 输入: [1,3,4,2,2]
 * 输出: 2
 * 示例 2:
 * <p>
 * 输入: [3,1,3,4,2]
 * 输出: 3
 * 说明：
 * <p>
 * 不能更改原数组（假设数组是只读的）。
 * 只能使用额外的 O(1) 的空间。
 * 时间复杂度小于 O(n2) 。
 * 数组中只有一个重复的数字，但它可能不止重复出现一次。
 */
public class A287_FindtheDuplicateNumber {
    public int findDuplicate(int[] nums) {
        Arrays.sort(nums);
        int left = 0;
        int right = left + 1;
        for (int i = 0; i < nums.length; i++) {
            if (nums[left] != nums[right]) {
                left++;
                right = left + 1;
            } else {
                return nums[i];
            }
        }
        return -1;
    }

    @Test
    public void test() {
        int[] nums = {1, 3, 4, 2, 2};
        System.out.println(findDuplicate(nums));

    }


}
