package leetcode.BinarySearch;

import com.alibaba.fastjson.JSONObject;
import com.sun.org.apache.regexp.internal.RE;
import org.junit.Test;

/**
 * \* Created: liuhuichao
 * \* Date: 2019/5/6
 * \* Time: 6:30 PM
 * \* Description:在排序数组中查找元素的第一个和最后一个位置
 * 给定一个按照升序排列的整数数组 nums，和一个目标值 target。找出给定目标值在数组中的开始位置和结束位置。
 * <p>
 * 你的算法时间复杂度必须是 O(log n) 级别。
 * <p>
 * 如果数组中不存在目标值，返回 [-1, -1]。
 * <p>
 * 示例 1:
 * <p>
 * 输入: nums = [5, 7, 7, 8, 8, 10], target = 8
 * 输出: [3,4]
 * 示例 2:
 * <p>
 * 输入: nums = [5,7,7,8,8,10], target = 6
 * 输出: [-1,-1]
 * \
 */
public class A34_FindFirstandLastPositionofElementinSortedArray {

    public int[] searchRange(int[] nums, int target) {
        if (nums.length == 0) {
            return new int[]{-1, -1};
        }
        //  5,7,7,8,8,8,8,8,10
        int begin = -1;
        int end = -1;
        int left = 0;
        int right = nums.length - 1;
        //left----middle---right
        //left--begin---end---right
        int mid;
        while (left + 1 < right) {
            mid = left + (right - left) / 2;
            if (nums[mid] == target) {
                begin = mid;
                end = mid;
                while (begin - 1 >= left && nums[begin - 1] == target) {
                    begin--;
                }
                while (end + 1 <= right && nums[end + 1] == target) {
                    end++;
                }
                return new int[]{begin, end};
            } else if (nums[mid] < target) {
                left = mid;
            } else {
                right = mid;
            }
        }
        if (nums[left] == target) {
            begin = left;
            end = left;
        }
        if (nums[right] == target) {
            if (begin == -1) {
                begin = right;
            }
            end = Math.max(end, right);
        }
        return new int[]{begin, end};
    }


    @Test
    public void test() {
        int nums[] = {1, 4}; //5,7,7,8,8,8,8,8,10
        int target = 4;
        System.out.println("result = " + JSONObject.toJSONString(searchRange(nums, target)));

    }

}
