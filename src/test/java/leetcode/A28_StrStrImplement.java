package leetcode;

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


    @Test
    public void test() {
        int result = strStr1("mississippi", "issip"); //4
        System.out.println("result= " + result);
    }

}
