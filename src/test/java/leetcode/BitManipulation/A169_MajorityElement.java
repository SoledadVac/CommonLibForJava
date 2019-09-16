package leetcode.BitManipulation;

import org.junit.Test;

/**
 * \* Created: liuhuichao
 * \* Date: 2019/9/16
 * \* Time: 35:43 AM
 * \* Description: 求众数
 * \
 * 给定一个大小为 n 的数组，找到其中的众数。众数是指在数组中出现次数大于 ⌊ n/2 ⌋ 的元素。
 * <p>
 * 你可以假设数组是非空的，并且给定的数组总是存在众数。
 * <p>
 * 示例 1:
 * <p>
 * 输入: [3,2,3]
 * 输出: 3
 * 示例 2:
 * <p>
 * 输入: [2,2,1,1,1,2,2]
 * 输出: 2
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/majority-element
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class A169_MajorityElement {

    /**
     * 摩尔投票法
     *
     * @param nums
     * @return
     */
    public int majorityElement(int[] nums) {
        /**
         * 如果为0，result赋值为当前值
         * 如果==result,count + 1
         * 如果不等于result ,count -1
         * 因为众数出现次数大于二分之一，所以循环完一次之后，result即为众数
         * **/
        Integer result = null;
        int count = 0;
        for (int i = 0; i < nums.length; i++) {
            if (count == 0) {
                result = nums[i];
                count = 1;
                continue;
            }
            if (result == nums[i]) {
                count++;
                continue;
            }
            count--;
        }
        return result;
    }

    /**
     * 位运算
     *
     * @param nums
     * @return
     */
    public int majorityElement1(int[] nums) {
        /**
         * 统计每一位出现的0 ，1 次数，如果这位上0出现的次数多，则为0；反之，则为1
         * **/
        int result = 0;
        for (int i = 0; i < 32; i++) {
            int zeroCount = 0, oneCount = 0;
            for (int j = 0; j < nums.length; j++) {
                int vaule = nums[j] >> i & 1;
                if (vaule == 0) {
                    zeroCount++;
                }
                if (vaule == 1) {
                    oneCount++;
                }
            }
            if (zeroCount > oneCount) {
                result = 0 << i | result;
            } else {
                result = 1 << i | result;
            }
        }
        return result;
    }


    @Test
    public void test() {
        int[] nums = {3, 2, 3};
        System.out.println("result = " + majorityElement1(nums));
    }

}
