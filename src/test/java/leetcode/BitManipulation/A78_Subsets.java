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

    /**
     * 二进制匹配
     *
     * @param nums
     * @return
     */
    public List<List<Integer>> subsets1(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        int length = 1 << nums.length; //子集的总个数
        for (int i = 0; i < length; i++) {
            List<Integer> list = new ArrayList<>();
            for (int j = 0; j < nums.length; j++) {
                //如果当前的i值的二进制位跟当前数组所在 nums 的index相与不为1，则说明当前num[j]在此子集中
                if (((1 << j) & i) != 0) {
                    list.add(nums[j]);
                }
            }
            res.add(list);
        }
        return res;
    }


    @Test
    public void test() {
        int[] nums = {1, 2, 3};
        System.out.println("result = " + JSONObject.toJSONString(subsets1(nums)));
    }

}
