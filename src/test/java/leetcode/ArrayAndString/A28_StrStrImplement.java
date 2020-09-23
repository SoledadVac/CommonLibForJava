package leetcode.ArrayAndString;

import com.alibaba.fastjson.JSONObject;
import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;

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
        int i = 0; //源字符index
        int j = 0; //被查找串index
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
                // aafaaa ,到最后一个a时候，匹配失败，len=2,根据前面匹配的公共串，看看公共串的公共串能不能对折
                len = result[len - 1];
            }
        }
        return result;
    }


    /**
     * ----robin carp--------
     **/
    public int charToIntVale(char s) {
        return s - 'a';
    }

    /**
     * robin carp 实现
     *
     * @param haystack
     * @param needle
     * @return
     */
    public int strStr3(String haystack, String needle) {
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
        long module = (long) Math.pow(2, 31); //控制在2的31次之下
        int baseNum = 26;
        //先计算初始hash值
        long hashHaystack = 0;
        long hashNeedle = 0;
        int l = needle.length();
        for (int index = 0; index < l; index++) {
            hashNeedle = (hashNeedle * baseNum + charToIntVale(needle.charAt(index))) % module;
            hashHaystack = ((hashHaystack * baseNum) + charToIntVale(haystack.charAt(index))) % module;
        }
        if (hashHaystack == hashNeedle) {
            return 0;
        }
        // const value to be used often : a**L % modulus
        long aL = 1;
        for (int i = 1; i <= l; ++i) aL = (aL * baseNum) % module;

        for (int index = 1; index < haystack.length() - l + 1; index++) {
            hashHaystack = (hashHaystack * baseNum -
                    charToIntVale(haystack.charAt(index - 1)) * aL
                    + charToIntVale(haystack.charAt(index + l - 1))) % module;
            if (hashHaystack == hashNeedle) {
                return index;
            }
        }
        return -1;
    }


    /**
     * ----Boyer Moore--------
     **/

    /**
     * 生成坏字符查找表
     *
     * @param pattern
     * @return
     */
    public int[] generateBadWordsLookForTables(String pattern) {
        int[] data = new int[256];
        for (int i = 0; i < 255; i++) {
            data[i] = -1;
        }
        for (int i = 0; i < pattern.length(); i++) {
            int v = pattern.charAt(i);
            data[v] = i;
        }
        return data;
    }


    public int[] generateSuffix(String pattern) {
        int patternLength = pattern.length();
        int[] suffix = new int[patternLength]; //value为前缀出现位置
        for (int i = 0; i < patternLength; i++) {
            suffix[i] = -1;
        }
        for (int i = 0; i < patternLength - 1; i++) {
            int j = i;
            int k = 0; //长度
            while (j >= 0 && pattern.charAt(j) == pattern.charAt(patternLength - 1 - k)) {
                j--;
                k++;
                suffix[k] = j + 1;
            }
        }
        return suffix;
    }


    public int moveByGoodStr(int j, int patternLength, int[] suffix) {
        int k = patternLength - 1 - j; //好后缀的长度
        if (suffix[k] != -1) {
            //在前面匹配到了好后缀
            return j - suffix[k] + 1;
        }
        //没匹配到，从j+2开始往后看能不能匹配到好后缀
        for (int r = j + 2; r <= patternLength - 1; r++) {
            if (suffix[patternLength - r] == 0) {
                return r;
            }
        }
        return patternLength;
    }

    /**
     * 方法主体
     *
     * @param main
     * @param pattern
     * @return
     */
    public int strStr4(String main, String pattern) {
        if (main.length() < pattern.length()) {
            return -1;
        }
        if ("".equals(main) && "".equals(pattern)) {
            return 0;
        }
        if (main.length() < 1) {
            return -1;
        }
        if (pattern.length() < 1) {
            return 0;
        }
        int mainLength = main.length();
        int patternLength = pattern.length();
        int[] badWordsTable = generateBadWordsLookForTables(pattern);
        int[] suffix = generateSuffix(pattern); //value为前缀出现位置

        int i = 0; //目前匹配的头部index
        while (i <= mainLength - patternLength) {
            int indexPattern;
            for (indexPattern = patternLength - 1; indexPattern >= 0; indexPattern--) {
                //从后往前匹配
                if (main.charAt(i + indexPattern) != pattern.charAt(indexPattern)) {
                    break;
                }
            }
            if (indexPattern < 0) {
                //匹配成功
                return i;
            }
            int badMove = indexPattern - badWordsTable[main.charAt(i + indexPattern)];
            int goodMove = 0;
            if (indexPattern < patternLength - 1) {
                goodMove = moveByGoodStr(indexPattern, patternLength, suffix);
            }
            i = i + Math.max(badMove, goodMove);
        }
        return -1;
    }


    /**Sunday 算法-----------**/
    /**
     * 计算偏移表
     *
     * @param pattern
     * @return
     */
    int[] generateShiftTable(String pattern) {
        int[] data = new int[256];
        int patternLength = pattern.length();
        for (int i = 0; i < 255; i++) {
            data[i] = patternLength + 1;
        }
        for (int i = 0; i < patternLength; i++) {
            int v = pattern.charAt(i);
            data[v] = patternLength - i;
        }
        return data;
    }


    public int strStr5(String main, String pattern) {
        if (main.length() < pattern.length()) {
            return -1;
        }
        if ("".equals(main) && "".equals(pattern)) {
            return 0;
        }
        if (main.length() < 1) {
            return -1;
        }
        if (pattern.length() < 1) {
            return 0;
        }
        int[] shiftData = generateShiftTable(pattern);
        int index = 0;
        while (index + pattern.length() <= main.length()) {
            String mainTemp = main.substring(index, index + pattern.length());
            if (mainTemp.equals(pattern)) {
                return index;
            }
            if (index + pattern.length() >= main.length()) {
                return -1;
            }
            //根据偏移表偏移
            char r = main.charAt(index + pattern.length());
            index += shiftData[r];
        }
        return index + pattern.length() >= main.length() ? -1 : index;
    }


    @Test
    public void test() {
        String haystack = "hello";
        String needle = "ll";
        System.out.println(strStr5(haystack, needle));
    }

    @Test
    public void test0() {
        System.out.println(JSONObject.toJSONString(generateSuffix("bba")));
    }


}
