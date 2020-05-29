package leetcode.ArrayAndString;

import org.apache.commons.collections.EnumerationUtils;
import org.junit.Test;

import java.util.Arrays;

/**
 * \* Created: liuhuichao
 * \* Date: 2020/5/28
 * \* Time: 29:50 下午
 * \* Description: 子数组最大平均数 I
 * \给定 n 个整数，找出平均数最大且长度为 k 的连续子数组，并输出该最大平均数。
 * <p>
 * 示例 1:
 * <p>
 * 输入: [1,12,-5,-6,50,3], k = 4
 * 输出: 12.75
 * 解释: 最大平均数 (12-5-6+50)/4 = 51/4 = 12.75
 *  
 * <p>
 * 注意:
 * <p>
 * 1 <= k <= n <= 30,000。
 * 所给数据范围 [-10,000，10,000]。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/maximum-average-subarray-i
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class A643_MaximumAverageSubarrayI {
    // 时间复杂度大，但是能通过
    public double findMaxAverage0(int[] nums, int k) {
        if (nums.length == 1) {
            return nums[0];
        }
        double sum = 0;
        for (int i = 0; i < nums.length; i++) {
            if (i == 0) {
                sum = sumRange(nums, i, i + k - 1);
                continue;
            }
            if (i + k - 1 > nums.length - 1) {
                break;
            }
            sum = Math.max(sum, sumRange(nums, i, i + k - 1));
        }
        return sum / k;
    }

    int sumRange(int[] nums, int begin, int end) {
        int sum = 0;
        for (int i = begin; i <= end; i++) {
            sum += nums[i];
        }
        return sum;
    }

    /**
     * 执行用时 :
     * 6 ms
     * , 在所有 Java 提交中击败了
     * 33.71%
     * 的用户
     * 内存消耗 :
     * 43.9 MB
     * , 在所有 Java 提交中击败了
     * 16.67%
     * 的用户
     *
     * @param nums
     * @param k
     * @return
     */
    public double findMaxAverage1(int[] nums, int k) {
        //构造 sum
        double result;
        int[] sum = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            if (i == 0) {
                sum[0] = nums[0];
                continue;
            }
            sum[i] = nums[i] + sum[i - 1];
        }
        result = sum[k - 1];
        for (int i = 1; i < nums.length - 1; i++) {
            if (i + k - 1 > nums.length - 1) {
                break;
            }
            result = Math.max(result, sum[i + k - 1] - sum[i - 1]);
        }
        return result / k;
    }


    public double findMaxAverage(int[] nums, int k) {
        int sum = 0; //初始化为前K个数的和
        for (int i = 0; i < k; i++) {
            sum += nums[i];
        }
        double result = sum;
        for (int i = k; i < nums.length; i++) {
            sum += nums[i] - nums[i - k];
            result = Math.max(result, sum);
        }
        return result / k;
    }

    @Test
    public void test() {
        int[] nums = {0, 4, 0, 3, 2};
        int k = 1;
        System.out.println(findMaxAverage(nums, k));
    }
}
