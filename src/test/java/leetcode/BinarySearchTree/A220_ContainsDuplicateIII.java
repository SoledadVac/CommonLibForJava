package leetcode.BinarySearchTree;

import org.junit.Test;

import java.util.TreeSet;

/**
 * \* Created: liuhuichao
 * \* Date: 2019/8/29
 * \* Time: 29:44 PM
 * \* Description: 存在重复元素 III
 * \
 * 给定一个整数数组，判断数组中是否有两个不同的索引 i 和 j，使得 nums [i] 和 nums [j] 的差的绝对值最大为 t，并且 i 和 j 之间的差的绝对值最大为 ķ。
 * <p>
 * 示例 1:
 * <p>
 * 输入: nums = [1,2,3,1], k = 3, t = 0
 * 输出: true
 * 示例 2:
 * <p>
 * 输入: nums = [1,0,1,1], k = 1, t = 2
 * 输出: true
 * 示例 3:
 * <p>
 * 输入: nums = [1,5,9,1,5,9], k = 2, t = 3
 * 输出: false
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/contains-duplicate-iii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class A220_ContainsDuplicateIII {

    /**
     * 看到的一个答案
     *
     * @param nums
     * @param k
     * @param t
     * @return
     */
    public boolean containsNearbyAlmostDuplicate1(int[] nums, int k, int t) {
        if (nums.length <= 1 || k == 0 || k == 10000)
            return false;
        int[] stack = new int[k];

        for (int i = 0; i < nums.length; i++) {
            int length = i < k ? i : k;
            for (int j = 0; j < length; j++) {
                if (Math.abs((long) stack[j] - (long) nums[i]) <= t)
                    return true;
            }
            stack[i % k] = nums[i];
        }
        return false;
    }


    public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
        // g <= x <= s (x为当前数字，g为相差t的最小数，s为相差t的最大数字)
        TreeSet<Integer> set = new TreeSet<>();
        for (int i = 0; i < nums.length; i++) {
            Integer g = set.floor(nums[i]);
            if (g != null && g + t >= nums[i]) {
                return true;
            }
            Integer s = set.ceiling(nums[i]);
            if (s != null && nums[i] + t >= s) {
                return true;
            }
            set.add(nums[i]);
            if (set.size() > k) {
                //维持K个
                set.remove(nums[i - k]);
            }
        }
        return false;
    }

    @Test
    public void test() {
        int[] nums = {1, 2, 3, 1};
        int k = 3; //i 和 j 之间的差的绝对值最大为 ķ
        int t = 0; //nums [i] 和 nums [j] 的差的绝对值最大为 t
        boolean res = containsNearbyAlmostDuplicate1(nums, k, t);
        System.out.println(res);
    }

}
