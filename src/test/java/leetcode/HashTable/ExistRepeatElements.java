package leetcode.HashTable;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * \* Created: liuhuichao
 * \* Date: 2019/8/19
 * \* Time: 26:34 PM
 * \* Description: 存在重复元素
 * \
 * 给定一个整数数组，判断是否存在重复元素。
 * <p>
 * 如果任何值在数组中出现至少两次，函数返回 true。如果数组中每个元素都不相同，则返回 false。
 * <p>
 * 示例 1:
 * <p>
 * 输入: [1,2,3,1]
 * 输出: true
 * 示例 2:
 * <p>
 * 输入: [1,2,3,4]
 * 输出: false
 * 示例 3:
 * <p>
 * 输入: [1,1,1,3,3,4,3,2,4,2]
 * 输出: true
 */
public class ExistRepeatElements {

    public boolean containsDuplicate(int[] nums) {
        if (nums == null | nums.length < 1) {
            return false;
        }
        Set<Integer> data = new HashSet<>();
        for (int n : nums) {
            data.add(n);
        }
        if (data.size() != nums.length) {
            return true;
        }
        return false;
    }

    @Test
    public void test() {
        int[] nums = {1, 2, 3, 1};
        System.out.println("result = " + containsDuplicate(nums));
    }
}
