package leetcode.ArrayAndString;

import com.alibaba.fastjson.JSONObject;
import com.kenai.jaffl.annotations.In;
import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;

public class A47_PermutationsII {

    List<List<Integer>> permuteResult = new ArrayList<>();

    public void permuteDFS(int[] nums, boolean[] used, List<Integer> item) {
        if (item.size() == nums.length) {
            permuteResult.add(item);
            return;
        }
        Set<Integer> floorSet=new HashSet<>();
        for (int i = 0; i < nums.length; i++) {
            if (used[i] || floorSet.contains(nums[i])) {
                continue;
            }
            used[i] = true;
            floorSet.add(nums[i]);
            item.add(nums[i]);
            permuteDFS(nums, Arrays.copyOf(used, used.length), new ArrayList<>(item));
            item.remove(item.size()-1);
            used[i] = false;
        }
    }

    public List<List<Integer>> permuteUnique(int[] nums) {
        boolean[] used = new boolean[nums.length];
        Arrays.fill(used, false);
        Arrays.sort(nums);
        permuteDFS(nums, used, new ArrayList<>());
        return permuteResult;
    }




    @Test
    public void test(){
        //Input: nums = [1,1,2] Output: [[1,1,2], [1,2,1], [2,1,1]]
        //int[] nums={1,1,2};
        //测试用例:[3,3,1,2,3,2,3,1]
        //	stdout:
        int[] nums={1,2,2};
        System.out.println(permuteUnique(nums));
    }



}
