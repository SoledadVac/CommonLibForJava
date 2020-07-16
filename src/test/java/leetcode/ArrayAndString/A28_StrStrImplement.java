package leetcode.ArrayAndString;

import com.alibaba.fastjson.JSONObject;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * \* Created: liuhuichao
 * \* Date: 2019/4/23
 * \* Time: 11:25 AM
 * \* Description: 实现 strStr() 函数
 * <p>
 * 给定一个 haystack 字符串和一个 needle 字符串，在 haystack 字符串中找出 needle 字符串出现的第一个位置 (从0开始)。如果不存在，则返回  -1。
 * <p>
 * 示例 1:
 * <p>
 * 输入: haystack = "hello", needle = "ll"
 * 输出: 2
 * 示例 2:
 * <p>
 * 输入: haystack = "aaaaa", needle = "bba"
 * 输出: -1
 * 说明:
 * <p>
 * 当 needle 是空字符串时，我们应当返回什么值呢？这是一个在面试中很好的问题。
 * <p>
 * 对于本题而言，当 needle 是空字符串时我们应当返回 0 。这与C语言的 strstr() 以及 Java的 indexOf() 定义相符。
 * <p>
 * \
 */
public class A28_StrStrImplement {

    //先写个复杂度为N方的 ： 击败了百分之七。艾玛。。
    public int strStr(String haystack, String needle) {
        if (haystack.length() < needle.length()) {
            return -1;
        }
        if ("".equals(haystack) && "".equals(needle)) {
            return 0;
        }
        if (haystack == null || haystack.length() < 1) {
            return -1;
        }
        if (needle == null || needle.length() < 1) {
            return 0;
        }
        char[] haystackChar = haystack.toCharArray();
        char[] needleChar = needle.toCharArray();
        for (int i = 0; i < haystackChar.length; i++) {
            if (isMatch(haystackChar, i, needleChar)) {
                return i;
            }
        }
        return -1;
    }

    private boolean isMatch(char[] source, int beginIndex, char[] needle) {
        for (int i = 0; i < needle.length; i++) {
            if (i + beginIndex > source.length - 1) {
                return false;
            }
            if (source[i + beginIndex] != needle[i]) {
                return false;
            }
        }
        return true;
    }

    //再写个双指针的吧，艾玛，上面那个时间复杂度太高了 : 超过百分之八十二点六，完美
    public int strStr1(String haystack, String needle) {
        if (haystack.length() < needle.length()) {
            return -1;
        }
        if ("".equals(haystack) && "".equals(needle)) {
            return 0;
        }
        if (haystack == null || haystack.length() < 1) {
            return -1;
        }
        if (needle == null || needle.length() < 1) {
            return 0;
        }
        char[] haystackChar = haystack.toCharArray();
        char[] needleChar = needle.toCharArray();
        int begin = 0;
        int indexHaystack = 0;
        int indexNeedle = 0;
        while (indexHaystack != haystackChar.length && indexNeedle != needleChar.length) {
            if (haystackChar[indexHaystack] == needleChar[indexNeedle]) {
                if (indexNeedle == needleChar.length - 1) {
                    return indexHaystack - needleChar.length + 1;
                }
                indexHaystack++;
                indexNeedle++;
            } else {
                begin++;
                indexHaystack = begin;
                indexNeedle = 0;
            }
        }
        return -1;
    }


    /**
     * 获取最长公共前后缀数组
     * <p>
     * 例如对于字符串 abcba：
     * <p>
     * 前缀：它的前缀包括：a, ab, abc, abcb，不包括本身；
     * 后缀：它的后缀包括：bcba, cba, ba, a，不包括本身；
     * 最长公共前缀后缀：abcba 的前缀和后缀中只有 a 是公共部分，字符串 a 的长度为 1。
     *
     * @param needle
     * @return
     */
    public int[] getCommonLong(String needle) {
        int[] result = new int[needle.length()];
        result[0] = -1;
        for (int i = 1; i < needle.length(); i++) {
            int index = i - 1;
            if (index == 0) {
                //一个字母时候，为0
                result[i] = 0;
                continue;
            }
            //大于等于两个时候
            int begin = 0;


           /* int begin = 0;
            int end = index;

            while (begin < index && end > 0 && begin < end) {
                if (needle.charAt(begin) == needle.charAt(end)) {
                    result[i]++;
                }
                begin++;
                end--;
            }*/
        }
        return result;
    }

    /**
     * KMP
     *
     * @param haystack
     * @param needle
     * @return
     */
    public int strStr2(String haystack, String needle) {
        if (haystack.length() < needle.length()) {
            return -1;
        }
        if ("".equals(haystack) && "".equals(needle)) {
            return 0;
        }
        if (haystack.length() < 1) {
            return -1;
        }
        if (needle.length() < 1) {
            return 0;
        }
        int[] next = getNext(needle);
        int i = 0;
        int j = 0;
        while (i < haystack.length() && j < needle.length()) {
            if (haystack.charAt(i) == needle.charAt(j)) {
                i++;
                j++;
            } else {
                //回退
                if (j == 0 || i == 0) {
                    //第一个
                    i++;
                    j++;
                }
                j = next[j - 1];
            }
        }
        return j == needle.length() ? i - j : -1;
    }

    /**
     * 获取最长公共前后缀长度
     *
     * @param s
     * @return
     */
    private int[] getNext(String s) {
        int[] result = new int[s.length()];
        int len = 0, i = 1;
        while (i < s.length()) {
            if (s.charAt(i) == s.charAt(len)) {
                result[i++] = ++len;
            } else if (len == 0) {
                //匹配失败，匹配长度为 0，无公共串
                result[i++] = 0;
            } else {
                //匹配失败，看看公共串有没有前缀和后缀相等的部分，有的话，相等部分的后一个字母比较
                len = result[len - 1];
            }
        }
        return result;
    }

    @Test
    public void test0() {
        String needle = "ACTGPACY"; // -1 ,0 ,0,0,0,0,1,2
        System.out.println(JSONObject.toJSONString(getNext(needle)));
    }

    @Test
    public void test() {
        int result = strStr2("mississippi", "issip"); //4
        System.out.println("result= " + result);
    }

}
