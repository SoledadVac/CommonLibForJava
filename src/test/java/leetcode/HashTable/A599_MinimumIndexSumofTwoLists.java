package leetcode.HashTable;

import com.alibaba.fastjson.JSONObject;
import org.junit.Test;

import java.util.*;

/**
 * \* Created: liuhuichao
 * \* Date: 2019/8/19
 * \* Time: 28:44 PM
 * \* Description: 两个列表的最小索引总和
 * \
 * 假设Andy和Doris想在晚餐时选择一家餐厅，并且他们都有一个表示最喜爱餐厅的列表，每个餐厅的名字用字符串表示。
 * <p>
 * 你需要帮助他们用最少的索引和找出他们共同喜爱的餐厅。 如果答案不止一个，则输出所有答案并且不考虑顺序。 你可以假设总是存在一个答案。
 * <p>
 * 示例 1:
 * <p>
 * 输入:
 * ["Shogun", "Tapioca Express", "Burger King", "KFC"]
 * ["Piatti", "The Grill at Torrey Pines", "Hungry Hunter Steakhouse", "Shogun"]
 * 输出: ["Shogun"]
 * 解释: 他们唯一共同喜爱的餐厅是“Shogun”。
 * 示例 2:
 * <p>
 * 输入:
 * ["Shogun", "Tapioca Express", "Burger King", "KFC"]
 * ["KFC", "Shogun", "Burger King"]
 * 输出: ["Shogun"]
 * 解释: 他们共同喜爱且具有最小索引和的餐厅是“Shogun”，它有最小的索引和1(0+1)。
 * 提示:
 * <p>
 * 两个列表的长度范围都在 [1, 1000]内。
 * 两个列表中的字符串的长度将在[1，30]的范围内。
 * 下标从0开始，到列表的长度减1。
 * 两个列表都没有重复的元素。
 * <p>
 */
public class A599_MinimumIndexSumofTwoLists {
    public String[] findRestaurant(String[] list1, String[] list2) {
        if (list1 == null || list2 == null) {
            return null;
        }
        Map<String, Integer> m1 = new HashMap<>();
        Map<String, Integer> resultMap = new HashMap<>();
        for (int i = 0; i < list1.length; i++) {
            m1.put(list1[i], i);
        }
        int minIndex = -1;
        for (int i = 0; i < list2.length; i++) {
            if (!m1.containsKey(list2[i])) {
                continue;
            }
            //相同的
            int min = i + m1.get(list2[i]);
            if (resultMap.keySet().size() == 0) {
                minIndex = min;
                resultMap.put(list2[i], min);
                continue;
            }
            if (min == minIndex) {
                resultMap.put(list2[i], min);
                continue;
            }
            if (min < minIndex) {
                resultMap.clear();
                resultMap.put(list2[i], min);
                minIndex = min;
            }
        }
        Set<String> keys = resultMap.keySet();
        if (keys.size() == 0) {
            return null;
        }
        return keys.toArray(new String[keys.size()]);
    }

    @Test
    public void test() {
        String[] list1 = {"Shogun", "Tapioca Express", "Burger King", "KFC"};
        String[] list2 = {"Piatti", "The Grill at Torrey Pines", "Hungry Hunter Steakhouse", "Shogun"};
        System.out.println("result = " + JSONObject.toJSONString(findRestaurant(list1, list2)));
    }
}
