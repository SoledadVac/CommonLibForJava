package leetcode.HashTable;

import com.alibaba.fastjson.JSONObject;
import com.kenai.jaffl.annotations.In;
import org.junit.Test;

import java.util.*;

/**
 * \* Created: liuhuichao
 * \* Date: 2019/8/20
 * \* Time: 29:28 PM
 * \* Description:前 K 个高频元素
 * \
 * 给定一个非空的整数数组，返回其中出现频率前 k 高的元素。
 * <p>
 * 示例 1:
 * <p>
 * 输入: nums = [1,1,1,2,2,3], k = 2
 * 输出: [1,2]
 * 示例 2:
 * <p>
 * 输入: nums = [1], k = 1
 * 输出: [1]
 * 说明：
 * <p>
 * 你可以假设给定的 k 总是合理的，且 1 ≤ k ≤ 数组中不相同的元素的个数。
 * 你的算法的时间复杂度必须优于 O(n log n) , n 是数组的大小。
 * <p>
 */
public class A347_TopKFrequentElements {

    public List<Integer> topKFrequent(int[] nums, int k) {
        List<Integer> result = new ArrayList<>();
        Map<Integer, Integer> data = new HashMap<>();
        for (int n : nums) {
            data.put(n, data.getOrDefault(n, 0) + 1);
        }
        PriorityQueue<Integer> queue = new PriorityQueue<>((n1, n2) -> (data.get(n2) - data.get(n1)));
        for (Integer key : data.keySet()) {
            queue.add(key);
        }
        for (int i = 0; i < k; i++) {
            int n = queue.poll();
            result.add(n);
        }
        return result;
    }

    public List<Integer> topKFrequent1(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i : nums) {
            map.put(i, map.getOrDefault(i, 0) + 1);
        }

        List<Integer>[] count = new ArrayList[nums.length + 1];
        for (int key : map.keySet()) {
            int freq = map.get(key);
            if (count[freq] == null) {
                count[freq] = new ArrayList<>();
            }
            count[freq].add(key);
        }

        List<Integer> result = new ArrayList<>(k);
        int remain = k;

        for (int i = nums.length; i > 0 && remain > 0; i--) {
            if (count[i] != null) {
                if (remain > count[i].size()) {
                    result.addAll(count[i]);
                    remain -= count[i].size();
                } else {
                    result.addAll(count[i].subList(0, remain));
                    remain = 0;
                }
            }
        }

        return result;
    }

    public List<Integer> topKFrequent2(int[] nums, int k) {
        List<Integer> result = new ArrayList<>();
        Map<Integer, Integer> data = new HashMap<>(); //key : 数字 ；V ：出现频率
        for (int n : nums) {
            data.put(n, data.getOrDefault(n, 0) + 1);
        }
        Map<Integer, List<Integer>> vk = new HashMap<>();
        for (Integer key : data.keySet()) {
            Integer freq = data.get(key);
            List<Integer> keyList = vk.get(freq);
            if (keyList == null) {
                keyList = new ArrayList<>();
            }
            keyList.add(key);
            vk.put(freq, keyList);
        }
        int maxShow = nums.length;
        int getNum = k;
        for (int i = maxShow; i > 0; i--) {
            if (!vk.containsKey(i)) {
                continue;
            }
            if (getNum > 0) {
                List<Integer> r = vk.get(i);
                result.addAll(r);
                getNum -= r.size();
            } else {
                return result;
            }
        }
        return result;
    }

    @Test
    public void test() {
        int[] nums = {1, 1, 1, 2, 2, 3};
        int k = 2;
        System.out.println(JSONObject.toJSONString(topKFrequent2(nums, k)));
    }
}
