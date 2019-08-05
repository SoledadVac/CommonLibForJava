package leetcode.ArrayAndString;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Maps;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * \* Created: liuhuichao
 * \* Date: 2019/2/15
 * \* Time: 4:04 PM
 * \* Description: 1, 两数和问题
 * <p>
 * 给定一个整数数组 nums 和一个目标值 target，请你在该数组中找出和为目标值的那 两个 整数，并返回他们的数组下标。
 * <p>
 * 你可以假设每种输入只会对应一个答案。但是，你不能重复利用这个数组中同样的元素。
 * <p>
 * 示例:
 * <p>
 * 给定 nums = [2, 7, 11, 15], target = 9
 * <p>
 * 因为 nums[0] + nums[1] = 2 + 7 = 9
 * 所以返回 [0, 1]
 * <p>
 * \
 */
public class A1_TwoSumTarget {

    /**
     * 暴力破解
     *
     * @param nums
     * @param target
     * @return
     */
    public int[] twoSum(int[] nums, int target) {
        int[] result = new int[2];
        for (int i = 0; i < nums.length; i++) {
            int left = target - nums[i];
            result[0] = i;
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[j] == left) {
                    result[1] = j;
                    break;
                }
            }
            if (result[1] != 0) {
                break;
            }
            continue;
        }
        return result;
    }

    /**
     * 一遍hash
     *
     * @param nums
     * @param target
     * @return
     */
    public int[] twoSum2(int[] nums, int target) {
        Map<Integer, Integer> temp = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int left = target - nums[i];
            if (temp.containsKey(left)) {
                return new int[]{temp.get(left), i};
            }
            temp.put(nums[i], i);
        }
        throw new IllegalArgumentException("can not find by target");
    }

    @Test
    public void test() {
        int[] nums = {3, 3};
        int[] result = twoSum2(nums, 6);
        System.out.println(JSONObject.toJSONString(result));
    }


}
