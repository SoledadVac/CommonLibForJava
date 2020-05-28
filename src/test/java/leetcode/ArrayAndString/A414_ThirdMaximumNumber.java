package leetcode.ArrayAndString;

import com.kenai.jaffl.annotations.In;
import org.junit.Test;

/**
 * \* Created: liuhuichao
 * \* Date: 2020/5/28
 * \* Time: 35:53 上午
 * \* Description: 第三大的数
 * \
 * <p>
 * 给定一个非空数组，返回此数组中第三大的数。如果不存在，则返回数组中最大的数。要求算法时间复杂度必须是O(n)。
 * <p>
 * 示例 1:
 * <p>
 * 输入: [3, 2, 1]
 * <p>
 * 输出: 1
 * <p>
 * 解释: 第三大的数是 1.
 * 示例 2:
 * <p>
 * 输入: [1, 2]
 * <p>
 * 输出: 2
 * <p>
 * 解释: 第三大的数不存在, 所以返回最大的数 2 .
 * 示例 3:
 * <p>
 * 输入: [2, 2, 3, 1]
 * <p>
 * 输出: 1
 * <p>
 * 解释: 注意，要求返回第三大的数，是指
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/third-maximum-number
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class A414_ThirdMaximumNumber {
    public int thirdMax(int[] nums) {
        int first = Integer.MIN_VALUE;
        int second = Integer.MIN_VALUE;
        int third = Integer.MIN_VALUE;
        boolean isContinIntMin = false;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] - Integer.MIN_VALUE == 0) {
                isContinIntMin = true;
            }
            if (i == 0) {
                first = nums[i];
                continue;
            }
            if (nums[i] > first) {
                third = second;
                second = first;
                first = nums[i];
                continue;
            }
            if (nums[i] > second && nums[i] < first) {
                third = second;
                second = nums[i];
                continue;
            }
            if (nums[i] > third && nums[i] < second) {
                third = nums[i];
            }
        }
        if (nums.length == 2 || second == third) {
            return first;
        }
        if (!isContinIntMin && third == Integer.MIN_VALUE) {
            return first;
        }
        return third;
    }

    @Test
    public void test() {
        int[] nums = {1, 1, 2};
        System.out.println(thirdMax(nums));
    }


}
