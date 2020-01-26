package leetcode.DivideAndConquer;

import org.junit.Test;

/**
 * \* Created: liuhuichao
 * \* Date: 2020/1/17
 * \* Time: 30:11 下午
 * \* Description:最大子序和
 * \
 * 给定一个整数数组 nums ，找到一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。
 * <p>
 * 示例:
 * <p>
 * 输入: [-2,1,-3,4,-1,2,1,-5,4],
 * 输出: 6
 * 解释: 连续子数组 [4,-1,2,1] 的和最大，为 6。
 * 进阶:
 * <p>
 * 如果你已经实现复杂度为 O(n) 的解法，尝试使用更为精妙的分治法求解。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/maximum-subarray
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class A53_MaximumSubarray {

    public int maxSubArray(int[] nums) {
        return maxArray(nums, 0, nums.length - 1);
    }

    private int maxArray(int[] nums, int left, int right) {
        if (left == right) {
            return nums[left];
        }
        int mid = (left + right) / 2;
        int leftV = maxArray(nums, left, mid);
        int rightV = maxArray(nums, mid + 1, right);
        int crossV = crossNum(nums, left, right, mid);

        return Math.max(crossV, Math.max(leftV, rightV));
    }

    private int crossNum(int[] nums, int left, int right, int position) {
        if (left == right) {
            return nums[left];
        }
        int letfMaxV = Integer.MIN_VALUE;  //左半边部分的最大值,包含position位置
        int rightMaxV = Integer.MIN_VALUE; //右半边部分最大值
        int currentLeft = 0;
        for (int i = position; i >= left; i--) {
            currentLeft += nums[i];
            letfMaxV = Math.max(currentLeft, letfMaxV);
        }
        int currentRight = 0;
        for (int i = position + 1; i <= right; i++) {
            currentRight += nums[i];
            rightMaxV = Math.max(currentRight, rightMaxV);
        }
        return letfMaxV + rightMaxV;
    }


    public int maxSubArray0(int[] nums) {
        int maxV = nums[0];
        int currentV = nums[0];
        for (int i = 1; i < nums.length; i++) {
            currentV = Math.max(nums[i], currentV + nums[i]);
            maxV=Math.max(maxV,currentV);
        }
        return maxV;
    }

    @Test
    public void test() {
        int[] nums = {-2, 1, -3, 4, -1, 2, 1, -5, 4};
        System.out.println(maxSubArray0(nums));
    }

}
