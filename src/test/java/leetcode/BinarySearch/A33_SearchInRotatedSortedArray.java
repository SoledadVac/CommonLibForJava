package leetcode.BinarySearch;

import org.junit.Test;

import java.util.Arrays;

/**
 * \* Created: liuhuichao
 * \* Date: 2019/4/30
 * \* Time: 10:50 AM
 * \* Description: 搜索旋转排序数组
 * <p>
 * 假设按照升序排序的数组在预先未知的某个点上进行了旋转。
 * <p>
 * ( 例如，数组 [0,1,2,4,5,6,7] 可能变为 [4,5,6,7,0,1,2] )。
 * <p>
 * 搜索一个给定的目标值，如果数组中存在这个目标值，则返回它的索引，否则返回 -1 。
 * <p>
 * 你可以假设数组中不存在重复的元素。
 * <p>
 * 你的算法时间复杂度必须是 O(log n) 级别。
 * <p>
 * 示例 1:
 * <p>
 * 输入: nums = [4,5,6,7,0,1,2], target = 0
 * 输出: 4
 * 示例 2:
 * <p>
 * 输入: nums = [4,5,6,7,0,1,2], target = 3
 * 输出: -1
 * \
 */
public class A33_SearchInRotatedSortedArray {


    public int search0(int[] nums, int target) {
        if (nums.length == 0) {
            return -1;
        }
        //每次对排好序的一端进行二分，二分，再二分。。。
        return searchBetween(nums, 0, nums.length - 1, target);
    }

    int searchBetween(int[] nums, int low, int high, int target) {
        //fk,有个边界值老是调用溢出，烦死人了，艾玛
        if (low > high) {
            return -1;
        }
        int mid = low + (high - low) / 2;
        if (nums[mid] == target) {
            return mid;
        }
        //判断哪一边是排好序的  low---mid---high
        if (nums[low] < nums[mid]) {
            //左边是排好序的，则针对左边进行二分查找
            if (nums[low] >= target && nums[mid] < target) {
                return searchBetween(nums, low, mid - 1, target);
            } else {
                return searchBetween(nums, mid + 1, high, target);
            }
        } else if (nums[low] > nums[mid]) {
            //右边是排好序的，针对右边进行二分查找
            if (nums[mid] < target && nums[high] >= target) {
                //在右边区间内
                return searchBetween(nums, mid + 1, high, target);
            } else {
                return searchBetween(nums, low, mid - 1, target);
            }
        } else {
            low++;
            return searchBetween(nums, low, high, target);
        }
    }


    /**
     * 1、一定有一端是排好序的（不是左端就是右端），因为旋转数组只有一个转折点
     * 2、没有排好序的那一端：
     * 如果是左边 -> mid位置的元素一定小于 left位置的元素
     * 如果是右边 -> mid位置的元素一定大于 right 位置的元素
     * 因为这是单调数列旋转 变成旋转数组， 如果没排好序（左边或右边），
     * 一定是大的数字旋转到左边或者小的数字旋转到右边
     * 1 2 3 4 5 6 7
     * 6 7 1 2 3 4 5
     * 5 6 7 1 2 3 4
     * 4 5 6 7 1 2 3     任意单调数组旋转规律都一样
     * 3 4 5 6 7 1 2
     * 2 3 4 5 6 7 1
     * 做题思路： 每次找到排好序的区间查看target 是否在里面 如果在里面直接缩小区间
     * 如果不在则找另一边排好序的区间 继续判断
     * 时间复杂度 O(log(n))
     *
     * @param nums
     * @param target
     * @return
     */
    public int search(int[] nums, int target) {
        int n = nums.length;
        if (n == 0) {
            return -1;
        }
        int low = 0;
        int high = n - 1;
        // low----mid----high
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (nums[mid] == target) {
                return mid;
            }
            //左边已排序
            if (nums[mid] > nums[low]) {
                if (nums[mid] > target && target >= nums[low]) {
                    high = mid - 1;
                } else {
                    low = mid + 1;
                }
            }
            //右边已排序
            else if (nums[mid] < nums[low]) {
                if (nums[mid] < target && target <= nums[high]) {
                    low = mid + 1;
                } else {
                    high = mid - 1;
                }
            } else {
                low++;
            }
        }
        return -1;
    }


    @Test
    public void test() {
        int[] nums = {3, 1};
        int target = 1;
        System.out.println("result = " + search0(nums, target));
    }
}
