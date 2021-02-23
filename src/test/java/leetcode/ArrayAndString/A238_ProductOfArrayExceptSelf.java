package leetcode.ArrayAndString;

import com.alibaba.fastjson.JSONObject;
import com.google.gson.JsonObject;
import org.junit.Test;

/**
 * \* Created: liuhuichao
 * \* Date: 2021/2/23
 * \* Time: 34:32 上午
 * \* Description: Product of Array Except Self
 * \
 * 给你一个长度为 n 的整数数组 nums，其中 n > 1，
 * 返回输出数组 output ，其中 output[i] 等于 nums 中除 nums[i] 之外其余各元素的乘积。
 * <p>
 *  
 * <p>
 * 示例:
 * <p>
 * 输入: [1,2,3,4]
 * 输出: [24,12,8,6]
 * <p>
 * 提示：题目数据保证数组之中任意元素的全部前缀元素和后缀（甚至是整个数组）的乘积都在 32 位整数范围内。
 * <p>
 * 说明: 请不要使用除法，且在 O(n) 时间复杂度内完成此题。
 * <p>
 * 进阶：
 * 你可以在常数空间复杂度内完成这个题目吗？（ 出于对空间复杂度分析的目的，输出数组不被视为额外空间。）
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/product-of-array-except-self
 */
public class A238_ProductOfArrayExceptSelf {

    public int[] productExceptSelf(int[] nums) {
        int[] result = new int[nums.length];
        int[] leftProduct = new int[nums.length];
        int[] rightProduct = new int[nums.length];
        //计算左数组乘积
        for (int index = 0; index < nums.length; index++) {
            if (index == 0) {
                leftProduct[index] = 1;
                continue;
            }
            leftProduct[index] = nums[index - 1] * leftProduct[index - 1];
        }
        //计算右数组乘积
        for (int index = nums.length - 1; index >= 0; index--) {
            if (index == nums.length - 1) {
                rightProduct[index] = 1;
                continue;
            }
            rightProduct[index] = nums[index + 1] * rightProduct[index + 1];
        }
        for (int index = 0; index < nums.length; index++) {
            result[index] = leftProduct[index] * rightProduct[index];
        }
        return result;
    }

    /**
     * 利用结果数组，优化空间复杂度
     *
     * @param nums
     * @return
     */
    public int[] productExceptSelf1(int[] nums) {
        int[] result = new int[nums.length];
        //计算左数组乘积
        for (int index = 0; index < nums.length; index++) {
            if (index == 0) {
                result[index] = 1;
                continue;
            }
            result[index] = nums[index - 1] * result[index - 1];
        }
        //计算右数组乘积
        int rightValue = 1;
        for (int index = nums.length - 1; index >= 0; index--) {
            if (index == nums.length - 1) {
                rightValue *= nums[index];
                continue;
            }
            result[index] *= rightValue;
            rightValue *= nums[index];
        }

        return result;
    }

    @Test
    public void test() {
        int[] nums = {1, 2, 3, 4};
        System.out.println(JSONObject.toJSONString(productExceptSelf1(nums)));
    }

}
