package leetcode.BitManipulation;

import org.junit.Test;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * \* Created: liuhuichao
 * \* Date: 2019/12/25
 * \* Time: 30:10 下午
 * \* Description: 子串的最大出现次数
 * \
 * 给你一个字符串 s ，请你返回满足以下条件且出现次数最大的 任意 子串的出现次数：
 * <p>
 * 子串中不同字母的数目必须小于等于 maxLetters 。
 * 子串的长度必须大于等于 minSize 且小于等于 maxSize 。
 *  
 * <p>
 * 示例 1：
 * <p>
 * 输入：s = "aababcaab", maxLetters = 2, minSize = 3, maxSize = 4
 * 输出：2
 * 解释：子串 "aab" 在原字符串中出现了 2 次。
 * 它满足所有的要求：2 个不同的字母，长度为 3 （在 minSize 和 maxSize 范围内）。
 * 示例 2：
 * <p>
 * 输入：s = "aaaa", maxLetters = 1, minSize = 3, maxSize = 3
 * 输出：2
 * 解释：子串 "aaa" 在原字符串中出现了 2 次，且它们有重叠部分。
 * 示例 3：
 * <p>
 * 输入：s = "aabcabcab", maxLetters = 2, minSize = 2, maxSize = 3
 * 输出：3
 * 示例 4：
 * <p>
 * 输入：s = "abcde", maxLetters = 2, minSize = 3, maxSize = 3
 * 输出：0
 *  
 * <p>
 * 提示：
 * <p>
 * 1 <= s.length <= 10^5
 * 1 <= maxLetters <= 26
 * 1 <= minSize <= maxSize <= min(26, s.length)
 * s 只包含小写英文字母。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/maximum-number-of-occurrences-of-a-substring
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class A1297_MaximumNumberofOccurrencesofaSubstring {
    public int maxFreq(String s, int maxLetters, int minSize, int maxSize) {
        //maxSize没用，不使用，只使用minSize进行滑动窗口
        if (s == null || s.length() == 0) {
            return 0;
        }
        int result = 0;
        Map<String, Integer> data = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            int beginIndex = i;
            int endindex = i + minSize;
            if (endindex - 1 > s.length() - 1) {
                break;
            }
            String temp = s.substring(beginIndex, endindex);
            Set<Character> temSet = new HashSet<>();
            for (int j = 0; j < temp.length(); j++) {
                temSet.add(temp.charAt(j));
            }
            if (temSet.size() > maxLetters) {
                continue;
            }
            data.put(temp, data.getOrDefault(temp, 0) + 1);
        }
        return data.values().stream().max(Integer::compareTo).orElse(0);

    }


    @Test
    public void test() {
        String s = "aababcaab";
        int maxLetters = 2;
        int minSize = 3;
        int maxSize = 4;
        System.out.println(maxFreq(s, maxLetters, minSize, maxSize));
    }
}
