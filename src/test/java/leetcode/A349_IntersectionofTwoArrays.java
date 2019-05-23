package leetcode;

import com.alibaba.fastjson.JSONObject;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * \* Created: liuhuichao
 * \* Date: 2019/5/23
 * \* Time: 3:59 PM
 * \* Description:两个数组的交集
 * \
 * 给定两个数组，编写一个函数来计算它们的交集。
 * <p>
 * 示例 1:
 * <p>
 * 输入: nums1 = [1,2,2,1], nums2 = [2,2]
 * 输出: [2]
 * 示例 2:
 * <p>
 * 输入: nums1 = [4,9,5], nums2 = [9,4,9,8,4]
 * 输出: [9,4]
 * 说明:
 * <p>
 * 输出结果中的每个元素一定是唯一的。
 * 我们可以不考虑输出结果的顺序。
 */
public class A349_IntersectionofTwoArrays {

    public int[] intersection(int[] nums1, int[] nums2) {
        int length1 = nums1.length;
        int length2 = nums2.length;
        if (length1 == 0 || length2 == 0) {
            return new int[0];
        }
        //使得 num1 的长度为短的长度
        if (nums2.length < nums1.length) {
            int[] temp = nums1;
            nums1 = nums2;
            nums2 = temp;
        }
        int[] result = new int[nums1.length];
        int indexResult = 0;
        Map<Integer, Integer> map1 = new HashMap<>();
        Map<Integer, Integer> map2 = new HashMap<>();
        for (int index2 = 0; index2 < nums2.length; index2++) {
            if (map2.containsKey(nums2[index2])) {
                continue;
            } else {
                map2.put(nums2[index2], index2);
            }
        }
        for (int index1 = 0; index1 < nums1.length; index1++) {
            if (map1.containsKey(nums1[index1])) {
                continue;
            } else {
                map1.put(nums1[index1], index1);
            }
            if (map2.containsKey(nums1[index1])) {
                result[indexResult] = nums1[index1];
                indexResult++;
            }
        }
        return Arrays.copyOf(result, indexResult);
    }


    @Test
    public void test() {
        int[] nums1 = {4, 9, 5};
        int[] nums2 = {9, 4, 9, 8, 4};
        System.out.println(JSONObject.toJSONString(intersection(nums1, nums2)));
    }

}
