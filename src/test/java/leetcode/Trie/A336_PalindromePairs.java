package leetcode.Trie;

import com.alibaba.fastjson.JSONObject;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * \* Created: liuhuichao
 * \* Date: 2019/9/3
 * \* Time: 29:50 PM
 * \* Description:  回文对
 * \
 * 给定一组唯一的单词， 找出所有不同 的索引对(i, j)，使得列表中的两个单词， words[i] + words[j] ，可拼接成回文串。
 * <p>
 * 示例 1:
 * <p>
 * 输入: ["abcd","dcba","lls","s","sssll"]
 * 输出: [[0,1],[1,0],[3,2],[2,4]]
 * 解释: 可拼接成的回文串为 ["dcbaabcd","abcddcba","slls","llssssll"]
 * 示例 2:
 * <p>
 * 输入: ["bat","tab","cat"]
 * 输出: [[0,1],[1,0]]
 * 解释: 可拼接成的回文串为 ["battab","tabbat"]
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/palindrome-pairs
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class A336_PalindromePairs {

    /**
     * 暴力破解下 -- 过多会超时
     *
     * @param words
     * @return
     */
    public List<List<Integer>> palindromePairs0(String[] words) {
        List<List<Integer>> res = new ArrayList<>();
        if (words == null || words.length < 1) {
            return res;
        }
        for (int i = 0; i < words.length; i++) {
            for (int j = i + 1; j < words.length; j++) {
                String w1 = words[i];
                String w2 = words[j];
                if (isRelatived(w1 + w2)) {
                    List<Integer> data = new ArrayList<>();
                    data.add(i);
                    data.add(j);
                    res.add(data);
                }
                if (isRelatived(w2 + w1)) {
                    List<Integer> data = new ArrayList<>();
                    data.add(j);
                    data.add(i);
                    res.add(data);
                }
            }
        }
        return res;
    }

    private Boolean isRelatived(String word) {
        if (word == null || word.isEmpty()) {
            return false;
        }
        int mid = word.length() / 2;
        for (int i = 0; i < mid; i++) {
            char left = word.charAt(i);
            char right = word.charAt(word.length() - i - 1);
            if (left != right) {
                return false;
            }
        }
        return true;
    }

    /**
     * Trie
     *
     * @param words
     * @return
     */
    public List<List<Integer>> palindromePairs(String[] words) {
        List<List<Integer>> res = new ArrayList<>();
        if (words == null || words.length < 1) {
            return res;
        }


        return res;
    }

    @Test
    public void test() {
        String[] words = {"abcd", "dcba", "lls", "s", "sssll"}; //[[0,1],[1,0],[3,2],[2,4]]
        System.out.println("result = " + JSONObject.toJSONString(palindromePairs(words)));
    }

}
