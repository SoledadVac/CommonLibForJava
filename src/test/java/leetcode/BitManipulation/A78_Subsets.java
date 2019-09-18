package leetcode.BitManipulation;

import com.alibaba.fastjson.JSONObject;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * \* Created: liuhuichao
 * \* Date: 2019/9/18
 * \* Time: 35:15 AM
 * \* Description: 子集
 * \
 * 给定一组不含重复元素的整数数组 nums，返回该数组所有可能的子集（幂集）。
 * <p>
 * 说明：解集不能包含重复的子集。
 * <p>
 * 示例:
 * <p>
 * 输入: nums = [1,2,3]
 * 输出:
 * [
 * [3],
 *   [1],
 *   [2],
 *   [1,2,3],
 *   [1,3],
 *   [2,3],
 *   [1,2],
 *   []
 * ]
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/subsets
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class A78_Subsets {

    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            List<Integer> item = new ArrayList<>();
            item.add(nums[i]);
            result.add(item);
            add(nums[i], result);
        }
        result.add(new ArrayList<>());
        return result;
    }

    public void add(int num, List<List<Integer>> result) {
        List<List<Integer>> numRes = new ArrayList<>();
        for (List<Integer> item : result) {
            List<Integer> temp = new ArrayList<>();
            temp.addAll(item);
            if (!temp.contains(num)) {
                temp.add(num);
                numRes.add(temp);
            }
        }
        if (!numRes.isEmpty()) {
            result.addAll(numRes);
        }
    }


    @Test
    public void test() {
        int[] nums = {1, 2, 3};
        System.out.println("result = " + JSONObject.toJSONString(subsets(nums)));
    }

}
