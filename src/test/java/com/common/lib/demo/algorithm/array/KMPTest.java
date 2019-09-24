package com.common.lib.demo.algorithm.array;

import org.junit.Test;

/**
 * \* Created: liuhuichao
 * \* Date: 2019/9/4
 * \* Time: 35:43 AM
 * \* Description: kmp
 * \
 */
public class KMPTest {


    /**
     * 暴力方法
     *
     * @param target
     * @param s
     * @return
     */
    public int findStr(String target, String s) {
        if (target == null || target.length() == 0 || s == null || s.length() == 0 || target.length() < s.length()) {
            return -1;
        }
        int targetPointer = 0;
        int sPointer = 0;
        int beginIndex = 0;
        while (targetPointer != target.length() && sPointer != s.length()) {
            if (target.charAt(targetPointer++) != s.charAt(sPointer++)) {
                beginIndex++;
                sPointer = 0;
                targetPointer = beginIndex;
            }
        }
        if (beginIndex != target.length() && sPointer == s.length()) {
            return beginIndex;
        }
        return -1;
    }

    /**
     * kmp
     * 在简单的一次匹配失败后，我们会想将模式串尽量的右移和主串进行匹配。
     * 右移的距离在KMP算法中是如此计算的：在已经匹配的模式串子串中，
     * 找出最长的相同的前缀和后缀，然后移动使它们重叠。
     *
     * @param target
     * @param s
     * @return
     */
    public int findStr1(String target, String s) {
        if (target == null || target.length() == 0 || s == null || s.length() == 0 || target.length() < s.length()) {
            return -1;
        }

        //todo : 待补充KMP实现
        return -1;
    }


    @Test
    public void test() {
        String target = "gogooglego";
        String s = "google";
        System.out.println("result = " + findStr1(target, s));
    }
}
