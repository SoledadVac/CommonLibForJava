package com.common.lib.demo.algorithm.array;


/**
 * \* Created: liuhuichao
 * \* Date: 2019/5/6
 * \* Time: 30:06 PM
 * \* Description: 二分法查找的模板
 * \
 */
public class BinarySearchDemo {

    public static int earch1(int[] nums, int target) {
        for (int index = 0; index < nums.length; index++) {
            if (nums[index] == target) {
                return index;
            }
        }
        return -1;
    }

    /**
     * 关键属性:
     * * 二分查找的最基础和最基本的形式。
     * * 查找条件可以在不与元素的两侧进行比较的情况下确定（或使用它周围的特定元素）。
     * * 不需要后处理，因为每一步中，你都在检查是否找到了元素。如果到达末尾，则知道未找到该元素。
     * *********************************************************************************
     * 区分语法:
     * * 初始条件：left = 0, right = length-1
     * * 终止：left > right
     * * 向左查找：right = mid-1
     * * 向右查找：left = mid+1
     *
     * @param nums
     * @param target
     * @return
     */
    public static int binarySearch1(int[] nums, int target) {
        if (nums == null || nums.length == 0)
            return -1;

        int left = 0, right = nums.length - 1;
        while (left <= right) {
            // Prevent (left + right) overflow
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        // End Condition: left > right
        return -1;
    }

    /**
     * 关键属性：
     * * 一种实现二分查找的高级方法。
     * * 查找条件需要访问元素的直接右邻居。
     * * 使用元素的右邻居来确定是否满足条件，并决定是向左还是向右。
     * * 保证查找空间在每一步中至少有 2 个元素。
     * * 需要进行后处理。 当你剩下 1 个元素时，循环 / 递归结束。 需要评估剩余元素是否符合条件。
     * *********************************************************************************
     * 区分语法:
     * * 初始条件：left = 0, right = length
     * * 终止：left == right
     * * 向左查找：right = mid
     * * 向右查找：left = mid+1
     *
     * @param nums
     * @param target
     * @return
     */
    public static int binarySearch2(int[] nums, int target) {
        if (nums == null || nums.length == 0)
            return -1;

        int left = 0, right = nums.length;
        while (left < right) {
            // Prevent (left + right) overflow
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] < target) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }

        // Post-processing:
        // End Condition: left == right
        if (left != nums.length && nums[left] == target) return left;
        return -1;
    }

    /**
     * 关键属性：
     * * 实现二分查找的另一种方法。
     * * 搜索条件需要访问元素的直接左右邻居。
     * * 使用元素的邻居来确定它是向右还是向左。
     * * 保证查找空间在每个步骤中至少有 3 个元素。
     * * 需要进行后处理。 当剩下 2 个元素时，循环 / 递归结束。 需要评估其余元素是否符合条件。
     * *********************************************************************************
     * 区分语法:
     * * 初始条件：left = 0, right = length-1
     * * 终止：left + 1 == right
     * * 向左查找：right = mid
     * * 向右查找：left = mid
     *
     * @param nums
     * @param target
     * @return
     */
    public static int binarySearch3(int[] nums, int target) {
        if (nums == null || nums.length == 0)
            return -1;
        //left---mid ----target
        int left = 0, right = nums.length - 1;
        while (left + 1 < right) {
            // Prevent (left + right) overflow
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] < target) {
                left = mid;
            } else {
                right = mid;
            }
        }
        // Post-processing:
        // End Condition: left + 1 == right
        if (nums[left] == target) return left;
        if (nums[right] == target) return right;
        return -1;
    }

}
