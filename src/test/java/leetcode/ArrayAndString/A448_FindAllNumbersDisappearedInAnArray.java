package leetcode.ArrayAndString;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.collections.EnumerationUtils;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.TreeMap;

/**
 * \* Created: liuhuichao
 * \* Date: 2020/5/28
 * \* Time: 27:14 下午
 * \* Description: 找到所有数组中消失的数字
 * \
 * 给定一个范围在  1 ≤ a[i] ≤ n ( n = 数组大小 ) 的 整型数组，数组中的元素一些出现了两次，另一些只出现一次。
 * <p>
 * 找到所有在 [1, n] 范围之间没有出现在数组中的数字。
 * <p>
 * 您能在不使用额外空间且时间复杂度为O(n)的情况下完成这个任务吗? 你可以假定返回的数组不算在额外空间内。
 * <p>
 * 示例:
 * <p>
 * 输入:
 * [4,3,2,7,8,2,3,1]
 * <p>
 * 输出:
 * [5,6]
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/find-all-numbers-disappeared-in-an-array
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class A448_FindAllNumbersDisappearedInAnArray {

    //一个超出时间限制的方法，时间复杂度为N方，不符合要求
    public List<Integer> findDisappearedNumbers0(int[] nums) {
        List<Integer> result = new ArrayList<>();
        for (int i = 1; i < nums.length + 1; i++) { //范围
            boolean isContain = false;
            for (int j = 0; j < nums.length; j++) {
                if (i == nums[j]) {
                    isContain = true;
                }
            }
            if (!isContain) {
                result.add(i);
            }
        }
        return result;
    }

    /**
     * @param nums
     * @return
     */
    public List<Integer> findDisappearedNumbers1(int[] nums) {
        List<Integer> result = new ArrayList<>();
        HashMap<Integer, Integer> dataMap = new HashMap<>();
        for (int i = 1; i < nums.length + 1; i++) { //范围
            dataMap.put(nums[i - 1], i - 1);
        }
        for (int i = 1; i < nums.length + 1; i++) {
            if (!dataMap.containsKey(i)) {
                result.add(i);
            }
        }
        return result;
    }

    public List<Integer> findDisappearedNumbers(int[] nums) {
        List<Integer> result = new ArrayList<>(nums.length);
        for (int i = 0; i < nums.length; i++) {
            int v = Math.abs(nums[i]);
            if (nums[v - 1] > 0) {
                nums[v - 1] = nums[v - 1] * -1;
            }
        }
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > 0) {
                result.add(i + 1);
            }
        }
        return result;
    }

    @Test
    public void test() {
        int[] nums = {4, 3, 2, 7, 8, 2, 3, 1};
        System.out.println(JSONObject.toJSON(findDisappearedNumbers(nums))); // 5,6
    }

}
