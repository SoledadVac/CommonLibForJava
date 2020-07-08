package leetcode.ArrayAndString;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * \* Created: liuhuichao
 * \* Date: 2019/2/19
 * \* Time: 5:41 PM
 * \* Description: 最长回文子串
 * <p>
 * 给定一个字符串 s，找到 s 中最长的回文子串。你可以假设 s 的最大长度为 1000。
 * <p>
 * 示例 1：
 * <p>
 * 输入: "babad"
 * 输出: "bab"
 * 注意: "aba" 也是一个有效答案。
 * 示例 2：
 * <p>
 * 输入: "cbbd"
 * 输出: "bb"
 */
public class A5_LongestPalindromicSubstring {

    /**
     * 最长公共子串
     * ---------暴力破解方法（三次方的时间复杂度。。额。。）
     *
     * @param s
     * @return
     */
    public String longestPalindrome(String s) {
        if (s == null || s.length() < 1) {
            return "";
        }
        if (s.length() == 1) {
            return s;
        }
        int length = s.length();
        String result = s.substring(0, 1);
        for (int i = 0; i < length; i++) {
            if (i == length - 1) {
                break;
            }
            for (int j = i + 1; j < length; j++) {
                if (ifStringIsBalanced(s, i, j)) {
                    if (j - i + 1 > result.length()) {
                        result = s.substring(i, j) + s.charAt(j);
                    }
                }

            }
        }
        return result;

    }

    /**
     * 包含起始位置和结束位置，判断是否对称
     *
     * @param s
     * @param begin 起始位置
     * @param end   结束位置
     * @return
     */
    private boolean ifStringIsBalanced(String s, int begin, int end) {
        if (begin == end) {
            return true;
        }
        String spiltString = s.substring(begin, end) + s.charAt(end);
        for (int i = 0; i < spiltString.length() - 1; i++) {
            if (spiltString.charAt(i) != spiltString.charAt(spiltString.length() - 1 - i)) {
                return false;
            }
        }
        return true;
    }


    /**
     * 最长公共子串 --- 动态规划法
     *
     * @param s
     * @return
     */
    public String longestPalindrome1(String s) {
        if (s == null || s.length() < 1) {
            return "";
        }
        if (s.length() == 1) {
            return s;
        }
        int n = s.length();//总长度
        boolean[][] dpTable = new boolean[n][n];//表格
        int begin = 0;
        int end = 0;
        for (int i = n - 1; i >= 0; i--) {
            for (int j = 0; j < n; j++) {
                if (i > j) {
                    // (i,j)代表这个区间内，并且需要j>=i
                    continue;
                }
                //初始化值
                if (i == j) {
                    dpTable[i][j] = true;
                    if ((j - i) > (end - begin)) {
                        begin = i;
                        end = j;
                    }
                    continue;
                }
                if (s.charAt(i) != s.charAt(j)) {
                    //头尾部不相等肯定不是回文
                    dpTable[i][j] = false;
                    continue;
                }
                if (i + 1 == j) {
                    //两个连续字母
                    dpTable[i][j] = true;
                    if ((j - i) > (end - begin)) {
                        begin = i;
                        end = j;
                    }
                    continue;
                }
                //头尾部相等，需要判断中间部分是不是
                if (dpTable[i + 1][j - 1]) {
                    dpTable[i][j] = true;
                    if ((j - i) > (end - begin)) {
                        begin = i;
                        end = j;
                    }
                    continue;
                }
                dpTable[i][j] = false;
            }
        }
        return s.substring(begin, end + 1);
    }


    @Test
    public void test() {
        //babad
        //bb
        //babad

        //String s0 = "bab"; // bab
        String s0 = "aaaa"; // bab
        System.out.println(longestPalindrome1(s0));

      /*  String s0 = "aledadi"; // idadela
        //           0123456   // 0123456

        System.out.println(longestPalindrome1(s0));*/

    }


}
