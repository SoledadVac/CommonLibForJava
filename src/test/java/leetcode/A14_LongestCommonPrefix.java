package leetcode;

import org.junit.Test;

/**
 * \* Created: liuhuichao
 * \* Date: 2019/4/23
 * \* Time: 3:28 PM
 * \* Description: 最长公共前缀
 * <p>
 * 编写一个函数来查找字符串数组中的最长公共前缀。
 * <p>
 * 如果不存在公共前缀，返回空字符串 ""。
 * <p>
 * 示例 1:
 * <p>
 * 输入: ["flower","flow","flight"]
 * 输出: "fl"
 * 示例 2:
 * <p>
 * 输入: ["dog","racecar","car"]
 * 输出: ""
 * 解释: 输入不存在公共前缀。
 * 说明:
 * <p>
 * 所有输入只包含小写字母 a-z 。
 * <p>
 * \
 */
public class A14_LongestCommonPrefix {

    public String longestCommonPrefix(String[] strs) {
        String result = "";
        if (strs == null || strs.length == 0) {
            return result;
        }
        if (strs.length == 1) {
            return strs[0];
        }
        int minLength = 0;
        String minStr = "";
        for (int i = 0; i < strs.length; i++) {
            if (minLength == 0 || strs[i].length() < minLength) {
                minLength = strs[i].length();
                minStr = strs[i];
            }
        }
        for (int i = 1; i < minLength + 1; i++) {
            String tempPrefix = minStr.substring(0, i);
            boolean isWith = isBeginWith(strs, tempPrefix);
            if (isWith) {
                result = tempPrefix;
            } else {
                return result;
            }
        }


        return result;
    }

    private boolean isBeginWith(String[] source, String prefix) {
        for (int i = 0; i < source.length; i++) {
            boolean isWith = source[i].startsWith(prefix);
            if (!isWith) {
                return false;
            }
        }
        return true;
    }

    @Test
    public void test() {
        String[] strs = {"c", "c"};
        System.out.println("result = " + longestCommonPrefix(strs));
    }

}
