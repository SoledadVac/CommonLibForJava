package leetcode.HashTable;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * \* Created: liuhuichao
 * \* Date: 2019/8/19
 * \* Time: 30:23 PM
 * \* Description: 存在重复元素 II
 * \
 * 给定一个整数数组和一个整数 k，判断数组中是否存在两个不同的索引 i 和 j，使得 nums [i] = nums [j]，并且 i 和 j 的差的绝对值最大为 k。
 * <p>
 * 示例 1:
 * <p>
 * 输入: nums = [1,2,3,1], k = 3
 * 输出: true
 * 示例 2:
 * <p>
 * 输入: nums = [1,0,1,1], k = 1
 * 输出: true
 * 示例 3:
 * <p>
 * 输入: nums = [1,2,3,1,2,3], k = 2
 * 输出: false
 */
public class A219_ContainsDuplicateII {

    public boolean containsNearbyDuplicate(int[] nums, int k) {
        if (nums == null || nums.length == 0) {
            return false;
        }
        Map<Integer, List<Integer>> data = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            List<Integer> indexs = data.get(nums[i]);
            if (indexs == null) {
                indexs = new ArrayList<>();
                indexs.add(i);
                data.put(nums[i], indexs);
                continue;
            }
            final int nowIndex = i;
            long count = indexs.stream().filter(item -> Math.abs(item - nowIndex) <= k).count();
            if (count > 0) {
                return true;
            }
            indexs.add(i);
        }
        return false;
    }

    @Test
    public void test() {
        int[] nums = {1, 2, 3, 4, 5, 6, 7, 8, 9, 9};
        int k = 3;

        //[1,2,3,1,2,3]
        //2

        //[1,2,3,4,5,6,7,8,9,9]
        //3
        System.out.println("result = " + containsNearbyDuplicate(nums, k));
    }
}
