package leetcode.ArrayAndString;

import org.apache.commons.collections.EnumerationUtils;
import org.junit.Test;

/**
 * \* Created: liuhuichao
 * \* Date: 2020/5/29
 * \* Time: 26:42 下午
 * \* Description: 非递减数列
 * \
 * 给你一个长度为 n 的整数数组，请你判断在 最多 改变 1 个元素的情况下，该数组能否变成一个非递减数列。
 * <p>
 * 我们是这样定义一个非递减数列的： 对于数组中所有的 i (1 <= i < n)，总满足 array[i] <= array[i + 1]。
 * <p>
 *  
 * <p>
 * 示例 1:
 * <p>
 * 输入: nums = [4,2,3]
 * 输出: true
 * 解释: 你可以通过把第一个4变成1来使得它成为一个非递减数列。
 * 示例 2:
 * <p>
 * 输入: nums = [4,2,1]
 * 输出: false
 * 解释: 你不能在只改变一个元素的情况下将其变为非递减数列。
 *  
 * <p>
 * 说明：
 * <p>
 * 1 <= n <= 10 ^ 4
 * - 10 ^ 5 <= nums[i] <= 10 ^ 5
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/non-decreasing-array
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class A665_NonDecreasingArray {

    public boolean checkPossibility(int[] nums) {
        boolean result = true;
        int count = 0;
        for (int i = 0; i < nums.length; i++) {
            if (i == nums.length - 1) {
                continue;
            }
            if (nums[i] <= nums[i + 1]) {
                //没毛病
                continue;
            }
            // nums[i] > nums[i + 1] ,此时需要将i位置的数字变小，或者将i+1处的数字变大
            //1，如果将i位置数字变小，需要判断前一位
            if (i == 0) {
                //没有前一位，随便改
                count++;
                continue;
            }
            //存在前一位时候，判断前一位大小，如果前一位（i-1）的值等于当前i位置的值，则i位置不能动;如果不等于则可以
            if (nums[i - 1] < nums[i + 1]) {
                count++;
                continue;
            }
            // 2,将i+1处的数字变大
            if (i + 1 == nums.length - 1) {
                //最后一位随便改
                count++;
                continue;
            }
            if (nums[i] <= nums[i + 2]) {
                count++;
            } else {
                return false;
            }
        }
        if (count > 1) {
            result = false;
        }
        return result;
    }

    @Test
    public void test() {
        // int[] nums = {3, 4, 2, 3};
        int[] nums = {4, 2, 3};
        //int[] nums = {4, 2, 1};
        //int[] nums = {2, 3, 3, 2, 4};
        //int[] nums = {3, 4, 2, 3};
        System.out.println(checkPossibility(nums));
    }
}
