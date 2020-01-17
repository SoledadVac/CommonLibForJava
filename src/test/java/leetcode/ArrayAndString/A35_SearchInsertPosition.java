package leetcode.ArrayAndString;

import com.google.inject.internal.cglib.core.$MethodInfo;
import org.junit.Test;

/**
 * \* Created: liuhuichao
 * \* Date: 2020/1/17
 * \* Time: 27:10 下午
 * \* Description: 探索插入位置
 * \
 * 给定一个排序数组和一个目标值，在数组中找到目标值，并返回其索引。如果目标值不存在于数组中，返回它将会被按顺序插入的位置。
 * <p>
 * 你可以假设数组中无重复元素。
 * <p>
 * 示例 1:
 * <p>
 * 输入: [1,3,5,6], 5
 * 输出: 2
 * 示例 2:
 * <p>
 * 输入: [1,3,5,6], 2
 * 输出: 1
 * 示例 3:
 * <p>
 * 输入: [1,3,5,6], 7
 * 输出: 4
 * 示例 4:
 * <p>
 * 输入: [1,3,5,6], 0
 * 输出: 0
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/search-insert-position
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class A35_SearchInsertPosition {
    /**
     * 时间复杂度为n的解法
     *
     * @param nums
     * @param target
     * @return
     */
    public int searchInsert(int[] nums, int target) {
        int result = nums.length;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == target) {
                return i;
            }
            if (nums[i] > target) {
                return i;
            }
        }
        return result;
    }

    /**
     * 时间复杂度为nlogn解法
     *
     * @param nums
     * @param target
     * @return
     */
    public int searchInsert0(int[] nums, int target) {
        if (nums.length == 0) {
            return -1;
        }
        if (nums.length == 1) {
            if (target > nums[0]) {
                return 1;
            } else {
                return 0;
            }
        }
        int left = 0;
        int right = nums.length - 1;
        while (left <= right) {
            //int mid = (left + right) / 2;
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) {
                return mid;
            }
            if (right - left == 1 && nums[left] < target && nums[right] > target) {
                return right;
            }
            if (right - left == 1 && nums[left] >= target) {
                return left;
            }
            if (right - left == 1 && nums[right] == target) {
                return right;
            }
            if (right - left == 1 && nums[right] < target) {
                return right + 1;
            }
            if (nums[mid] < target) {
                left = mid;
            }
            if (nums[mid] > target) {
                right = mid;
            }
        }
        return -1;
    }

    @Test
    public void test() {
        int[] nums = {1, 3, 5, 6};
        int target = 7;
        System.out.println(searchInsert0(nums, target));
    }
}
