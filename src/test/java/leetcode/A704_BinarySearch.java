package leetcode;

import org.junit.Test;

/**
 * \* Created: liuhuichao
 * \* Date: 2019/4/29
 * \* Time: 5:08 PM
 * \* Description:二分查找
 * <p>
 * 给定一个 n 个元素有序的（升序）整型数组 nums 和一个目标值 target  ，写一个函数搜索 nums 中的 target，如果目标值存在返回下标，否则返回 -1。
 * <p>
 * <p>
 * 示例 1:
 * <p>
 * 输入: nums = [-1,0,3,5,9,12], target = 9
 * 输出: 4
 * 解释: 9 出现在 nums 中并且下标为 4
 * 示例 2:
 * <p>
 * 输入: nums = [-1,0,3,5,9,12], target = 2
 * 输出: -1
 * 解释: 2 不存在 nums 中因此返回 -1
 * <p>
 * <p>
 * 提示：
 * <p>
 * 你可以假设 nums 中的所有元素是不重复的。
 * n 将在 [1, 10000]之间。
 * nums 的每个元素都将在 [-9999, 9999]之间。
 * \
 */
public class A704_BinarySearch {
    public int search(int[] nums, int target) {
        if (nums.length == 0) {
            return -1;
        }
        int beginIndex = 0;
        int endIndex = nums.length - 1;
        if (nums.length == 1) {
            if (target == nums[0]) {
                return 0;
            } else {
                return -1;
            }
        }
        while (endIndex - beginIndex != 1 && beginIndex != endIndex) {
            int middleIndex = (beginIndex + endIndex) / 2;
            int middle = nums[middleIndex];
            if (middle > target) {
                endIndex = middleIndex;
            } else if (middle < target) {
                beginIndex = middleIndex;
            } else {
                return middleIndex;
            }
        }
        if (endIndex - beginIndex == 1) {
            if (nums[beginIndex] == target) {
                return beginIndex;
            }
            if (nums[endIndex] == target) {
                return endIndex;
            }
        }
        return -1;
    }


    @Test
    public void test() {
        int nums[] = {2, 5};
        System.out.println("result = " + search(nums, 5));
    }

}
