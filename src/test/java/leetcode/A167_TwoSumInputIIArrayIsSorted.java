package leetcode;

import com.alibaba.fastjson.JSONObject;
import org.junit.Test;

/**
 * \* Created: liuhuichao
 * \* Date: 2019/4/23
 * \* Time: 6:21 PM
 * \* Description:  两数之和 II - 输入有序数组
 * 给定一个已按照升序排列 的有序数组，找到两个数使得它们相加之和等于目标数。
 * <p>
 * 函数应该返回这两个下标值 index1 和 index2，其中 index1 必须小于 index2。
 * <p>
 * 说明:
 * <p>
 * 返回的下标值（index1 和 index2）不是从零开始的。
 * 你可以假设每个输入只对应唯一的答案，而且你不可以重复使用相同的元素。
 * 示例:
 * <p>
 * 输入: numbers = [2, 7, 11, 15], target = 9
 * 输出: [1,2]
 * 解释: 2 与 7 之和等于目标数 9 。因此 index1 = 1, index2 = 2 。
 * \
 */
public class A167_TwoSumInputIIArrayIsSorted {

    public int[] twoSum(int[] numbers, int target) {
        int[] result = {0, 0};
        int begin = 1;
        int end = numbers.length;
        while (begin >= 1 && end <= numbers.length && begin < end) {
            int firstVal = numbers[begin - 1];
            int secondVal = numbers[end - 1];
            if (firstVal + secondVal == target) {
                result[0] = begin;
                result[1] = end;
                return result;
            }
            if (firstVal + secondVal < target) {
                begin++;
            }
            if (firstVal + secondVal > target) {
                end--;
            }
        }
        return result;
    }

    @Test
    public void test() {
        int[] numbers = {2, 5, 7, 11};
        int target = 9;
        int[] result = twoSum(numbers, target);
        System.out.println(JSONObject.toJSONString(result));
    }

}
