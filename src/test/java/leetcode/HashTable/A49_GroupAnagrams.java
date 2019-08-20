package leetcode.HashTable;

import com.alibaba.fastjson.JSONObject;
import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;

/**
 * \* Created: liuhuichao
 * \* Date: 2019/8/20
 * \* Time: 35:15 AM
 * \* Description: 字母异位词分组
 * \
 * 给定一个字符串数组，将字母异位词组合在一起。字母异位词指字母相同，但排列不同的字符串。
 * <p>
 * 示例:
 * <p>
 * 输入: ["eat", "tea", "tan", "ate", "nat", "bat"],
 * 输出:
 * [
 * ["ate","eat","tea"],
 * ["nat","tan"],
 * ["bat"]
 * ]
 * 说明：
 * <p>
 * 所有输入均为小写字母。
 * 不考虑答案输出的顺序。
 * <p>
 */
public class A49_GroupAnagrams {

    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> data = new HashMap();
        for (String s : strs) {
            String key = getKey(s);
            List<String> words;
            if (data.containsKey(key)) {
                words = data.get(key);
            } else {
                words = new ArrayList<>();
            }
            words.add(s);
            data.put(key, words);
        }
        return new ArrayList<>(data.values());
    }

    private String getKey(String s) {
        char[] chars = s.toCharArray();
        Arrays.sort(chars);
        return new String(chars);
    }

    @Test
    public void test() {
        String[] strs = {"eat", "tea", "tan", "ate", "nat", "bat"};
        System.out.println(" result = " + JSONObject.toJSONString(groupAnagrams(strs)));
    }

}
