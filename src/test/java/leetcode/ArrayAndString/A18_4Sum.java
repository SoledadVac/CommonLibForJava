package leetcode.ArrayAndString;

import com.alibaba.fastjson.JSONObject;
import com.google.gson.JsonObject;
import netscape.javascript.JSObject;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

/**
 * \* Created: liuhuichao
 * \* Date: 2019/10/22
 * \* Time: 28:24 PM
 * \* Description:  四数之和
 * \
 * 给定一个包含 n 个整数的数组 nums 和一个目标值 target，判断 nums 中是否存在四个元素 a，b，c 和 d ，
 * 使得 a + b + c + d 的值与 target 相等？找出所有满足条件且不重复的四元组。
 * <p>
 * 注意：
 * <p>
 * 答案中不可以包含重复的四元组。
 * <p>
 * 示例：
 * <p>
 * 给定数组 nums = [1, 0, -1, 0, -2, 2]，和 target = 0。
 * <p>
 * 满足要求的四元组集合为：
 * [
 * [-1,  0, 0, 1],
 * [-2, -1, 1, 2],
 * [-2,  0, 0, 2]
 * ]
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/4sum
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class A18_4Sum {

    public List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> result = new ArrayList<>();
        if (nums.length == 0) {
            return result;
        }
        HashMap<Integer, List<List<Integer>>> data = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                List<Integer> indexs = new ArrayList<>();
                int sum = nums[i] + nums[j];
                indexs.add(i);
                indexs.add(j);
                List<List<Integer>> indexesList = data.getOrDefault(sum, new ArrayList<>());
                indexesList.add(indexs);
                data.put(sum, indexesList);
            }
        }
        for (Integer key : data.keySet()) {
            List<List<Integer>> indexesList = data.get(key);
            int left = target - key;
            List<List<Integer>> leftList = data.get(left);
            if (leftList == null) {
                continue;
            }
            for (List<Integer> lList : indexesList) {
                int index1 = lList.get(0);
                int index2 = lList.get(1);
                for (List<Integer> rList : leftList) {
                    int index3 = rList.get(0);
                    int index4 = rList.get(1);
                    if ((index1 != index3) && (index1 != index4) && (index2 != index3) && (index2 != index4)) {
                        int tempArr[] = {nums[index1], nums[index2], nums[index3], nums[index4]};
                        Arrays.sort(tempArr);
                        List<Integer> availableResult = Arrays.asList(tempArr[0],tempArr[1],tempArr[2],tempArr[3]);
                        if (!result.contains(availableResult)) {
                            result.add(availableResult);
                        }
                    }
                }
            }
        }
        return result;
    }

    @Test
    public void test() {
        int[] nums = {1, 0, -1, 0, -2, 2};
        int target = 0;
        System.out.println(JSONObject.toJSONString(fourSum(nums, target)));
    }
}
