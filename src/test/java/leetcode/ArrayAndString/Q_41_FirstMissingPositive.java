package leetcode.ArrayAndString;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * \* Created: liuhuichao
 * \* Date: 2021/3/3
 * \* Time: 28:25 下午
 * \* Description:  缺失的第一个正数
 * \
 * 给你一个未排序的整数数组 nums ，请你找出其中没有出现的最小的正整数。
 * 进阶：你可以实现时间复杂度为 O(n) 并且只使用常数级别额外空间的解决方案吗？
 * 示例 1：
 * <p>
 * 输入：nums = [1,2,0]
 * 输出：3
 * 示例 2：
 * <p>
 * 输入：nums = [3,4,-1,1]
 * 输出：2
 * 示例 3：
 * <p>
 * 输入：nums = [7,8,9,11,12]
 * 输出：1
 *  
 * <p>
 * 提示：
 * <p>
 * 0 <= nums.length <= 300
 * -231 <= nums[i] <= 231 - 1
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/first-missing-positive
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Q_41_FirstMissingPositive {

    /**
     * 使用临时数组
     *
     * @param nums
     * @return
     */
    public int firstMissingPositive1(int[] nums) {
        int[] temp = new int[nums.length];
        for (int index = 0; index < nums.length; index++) {
            if (nums[index] > nums.length || nums[index] < 1) {
                continue;
            }
            temp[nums[index] - 1] = nums[index];
        }
        for (int index = 0; index < temp.length; index++) {
            if (temp[index] == 0) {
                return index + 1;
            }
        }
        return nums.length + 1;
    }

    /**
     * 使用map
     *
     * @param nums
     * @return
     */
    public int firstMissingPositive2(int[] nums) {
        Map<Integer, Integer> data = new HashMap<>();
        for (int index = 0; index < nums.length; index++) {
            data.put(nums[index], index);
        }
        for (int index = 1; index <= nums.length + 1; index++) {
            if (data.get(index) == null) {
                return index;
            }
        }
        return nums.length + 1;
    }

    /**
     * 原地算法
     *
     * @param nums
     * @return
     */
    public int firstMissingPositive3(int[] nums) {
        //将负数先改成范围之外的数字
        for (int index = 0; index < nums.length; index++) {
            if (nums[index] < 1) {
                nums[index] = nums.length + 1;
            }
        }
        //使用-号作为标记此index有没有数字
        for (int index = 0; index < nums.length; index++) {
            int abs = Math.abs(nums[index]);
            if (abs < nums.length + 1 && abs > 0) {
                nums[abs - 1] = -nums[abs - 1];
            }
        }
        for (int index = 0; index < nums.length; index++) {
            if (nums[index] >= 0) {
                return index + 1;
            }
        }
        return nums.length + 1;
    }

    /**
     * 原地算法--交换
     *
     * @param nums
     * @return
     */
    public int firstMissingPositive(int[] nums) {
        //将负数先改成范围之外的数字
        for (int index = 0; index < nums.length; index++) {
            while (nums[index] > 0 && nums[index] <= nums.length && nums[index] != nums[nums[index] - 1]) {
                int temp = nums[nums[index] - 1];
                nums[nums[index] - 1] = nums[index];
                nums[index] = temp;
            }
        }

        for (int index = 0; index < nums.length; index++) {
            if (nums[index] < 1 || nums[index] > nums.length || nums[index] != index + 1) {
                return index + 1;
            }
        }
        return nums.length + 1;
    }

    @Test
    public void test() {
        //int[] nums = {1, 2, 0}; //3
        //int[] nums = {7, 8, 9, 11, 12}; //1
        //int[] nums = {3, 4, -1, 1}; //2
        //int[] nums = {1};
        int[] nums = {-1, 4, 2, 1, 9, 10}; //3
        //int[] nums = {1, 1}; //2
        System.out.println(firstMissingPositive(nums));
    }

}
