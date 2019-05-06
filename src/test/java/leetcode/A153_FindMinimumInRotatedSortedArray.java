package leetcode;

import org.junit.Test;

/**
 * \* Created: liuhuichao
 * \* Date: 2019/5/5
 * \* Time: 6:37 PM
 * \* Description:寻找旋转排序数组中的最小值
 * \
 * 假设按照升序排序的数组在预先未知的某个点上进行了旋转。
 * <p>
 * ( 例如，数组 [0,1,2,4,5,6,7] 可能变为 [4,5,6,7,0,1,2] )。
 * <p>
 * 请找出其中最小的元素。
 * <p>
 * 你可以假设数组中不存在重复元素。
 * <p>
 * 示例 1:
 * <p>
 * 输入: [3,4,5,1,2]
 * 输出: 1
 * 示例 2:
 * <p>
 * 输入: [4,5,6,7,0,1,2]
 * 输出: 0
 */
public class A153_FindMinimumInRotatedSortedArray {

    public int findMin(int[] nums) {

        //螺旋数组的最小值，在二分之后对非递增数列中的最小值和递增队列中的最小值比较得到的最小值
        // 4,5,6,7,8,9,10,11,12,13,14,15,16,0,1,2
        int left = 0;
        int right = nums.length;
        int min = Integer.MAX_VALUE;
        while (right > left) {
            int mid = left + (right - left) / 2;
            //left-----mid-------right
            if (nums[left] < nums[mid]) {
                min = Math.min(nums[left], min);
                left = mid + 1;
            } else {
                right = mid;
                min = Math.min(nums[mid], min);
            }
        }
        if (left != nums.length) {
            return Math.min(nums[left], min);
        }
        return min;
    }

    @Test
    public void test() {
        int nums[] = {1, 2, 3, 4, 5};
        System.out.println("result = " + findMin(nums));
    }

}
