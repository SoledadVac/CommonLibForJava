package leetcode.ArrayAndString;

import com.alibaba.fastjson.JSONObject;
import org.junit.Test;

import java.util.*;

/**
 * \* Created: liuhuichao
 * \* Date: 2019/5/23
 * \* Time: 29:48 PM
 * \* Description:两个数组的交集 II
 * \
 * 给定两个数组，编写一个函数来计算它们的交集。
 * <p>
 * 示例 1:
 * <p>
 * 输入: nums1 = [1,2,2,1], nums2 = [2,2]
 * 输出: [2,2]
 * 示例 2:
 * <p>
 * 输入: nums1 = [4,9,5], nums2 = [9,4,9,8,4]
 * 输出: [4,9]
 * 说明：
 * <p>
 * 输出结果中每个元素出现的次数，应与元素在两个数组中出现的次数一致。
 * 我们可以不考虑输出结果的顺序。
 * 进阶:
 * <p>
 * 如果给定的数组已经排好序呢？你将如何优化你的算法？
 * 如果 nums1 的大小比 nums2 小很多，哪种方法更优？
 * 如果 nums2 的元素存储在磁盘上，磁盘内存是有限的，并且你不能一次加载所有的元素到内存中，你该怎么办？
 */
public class A350_IntersectionofTwoArraysII {
    public int[] intersect(int[] nums1, int[] nums2) {
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
        Map<Integer, List<Integer>> map2 = new HashMap<>();
        for (int index2 = 0; index2 < nums2.length; index2++) {
            if (map2.containsKey(nums2[index2])) {
                List<Integer> idexs = map2.get(nums2[index2]);
                idexs.add(index2);
            } else {
                List<Integer> idexs = new ArrayList<>();
                idexs.add(index2);
                map2.put(nums2[index2], idexs);
            }
        }
        for (int index1 = 0; index1 < nums1.length; index1++) {
            if (map2.containsKey(nums1[index1])) {
                List<Integer> idexs = map2.get(nums1[index1]);
                if (idexs != null && idexs.size() > 0) {
                    result[indexResult] = nums1[index1];
                    indexResult++;
                    idexs.remove(0);
                    map2.put(nums1[index1], idexs);
                }

            }
        }
        return Arrays.copyOf(result, indexResult);
    }

    @Test
    public void test() {
        int[] nums1 = {3, 1, 2};
        int[] nums2 = {1, 1};
        System.out.println(JSONObject.toJSONString(intersect(nums1, nums2)));
    }

}
