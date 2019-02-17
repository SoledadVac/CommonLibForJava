package leetcode;

import org.junit.Test;

import java.util.*;
import java.util.stream.Stream;

/**
 * \* Created: liuhuichao
 * \* Date: 2019/2/16
 * \* Time: 9:25 PM
 * \* Description:
 * \
 * 给定一个字符串，请你找出其中不含有重复字符的 最长子串 的长度。
 * <p>
 * 示例 1:
 * <p>
 * 输入: "abcabcbb"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
 * 示例 2:
 * <p>
 * 输入: "bbbbb"
 * 输出: 1
 * 解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。
 * 示例 3:
 * <p>
 * 输入: "pwwkew"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是 "wke"，所以其长度为 3。
 * 请注意，你的答案必须是 子串 的长度，"pwke" 是一个子序列，不是子串。
 */
public class A3_CharactersLength {

    /**
     * 暴力破解方法 （时间复杂度：二次方）
     *
     * @param s
     * @return
     */
    public int lengthOfLongestSubstring(String s) {
        if (s == null || s.equals("")) {
            return 0;
        }
        int maxLength = 0;
        char[] dataSource = s.toCharArray();
        List<Character> maxCharas = new ArrayList<>();
        for (int i = 0; i < dataSource.length; i++) {
            char begin = dataSource[i];
            List<Character> tempChars = new ArrayList<>();
            tempChars.add(begin);
            for (int j = i + 1; j < dataSource.length; j++) {
                if (!tempChars.contains(dataSource[j])) {
                    tempChars.add(dataSource[j]);
                } else {
                    break;
                }
            }
            if (tempChars.size() > maxCharas.size()) {
                maxCharas = tempChars;
                maxLength = tempChars.size();
            }
        }
        return maxLength;
    }

    /**
     * leetCode官方暴力破解版本：字符串过长时间复杂度的缺点会越明显（时间复杂度：三次方），时间过长
     *
     * @param s
     * @return
     */
    public int lengthOfLongestSubstring0(String s) {
        if (s == null || s.equals("")) {
            return 0;
        }
        int maxLength = 0;
        for (int i = 0; i < s.length(); i++) {
            for (int j = i + 1; j <= s.length(); j++) {
                if (allUnique(s, i, j)) {
                    maxLength = Math.max(maxLength, j - i);
                }
            }
        }
        return maxLength;
    }

    public boolean allUnique(String s, int begin, int end) {
        Set<Character> containor = new HashSet<>();
        for (int i = begin; i < end; i++) {
            Character c = s.charAt(i);
            if (containor.contains(c)) {
                return false;
            } else {
                containor.add(c);
            }
        }
        return true;
    }


    /**
     * 滑动窗口
     *
     * @param s
     * @return
     */
    public int lengthOfLongestSubstring1(String s) {
        if (s == null || s.equals("")) {
            return 0;
        }
        int totalLength = s.length();
        int maxLength = 0;
        int begin = 0;
        int end = 0;
        Set<Character> containor = new HashSet<>();
        while (begin < totalLength && end < totalLength) {
            if (!containor.contains(s.charAt(end))) {
                containor.add(s.charAt(end++));
                maxLength = Math.max(maxLength, end - begin);
            } else {
                containor.remove(s.charAt(begin++));
            }
        }
        return maxLength;
    }

    /**
     * 优化的滑动窗口
     * 重复之后手动跳转开头到下一个不重复字符
     *
     * @param s
     * @return
     */
    public int lengthOfLongestSubstring2(String s) {
        if (s == null || s.equals("")) {
            return 0;
        }
        int totalLength = s.length();
        int maxLength = 0;
        Map<Character, Integer> containor = new HashMap<>();
        // try to extend the range [begin, end]
        for (int begin = 0, end = 0; end < totalLength; end++) {
            if (containor.containsKey(s.charAt(end))) {
                begin = Math.max(containor.get(s.charAt(end)), begin);
            }
            maxLength = Math.max(maxLength, end - begin + 1);
            containor.put(s.charAt(end), end + 1);
        }
        return maxLength;
    }

    @Test
    public void test() {
        String testData = "jkabkbja";
        int length = lengthOfLongestSubstring2(testData);
        System.out.println("length=  " + length);

    }

}
