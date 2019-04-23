package leetcode;

import org.junit.Test;

/**
 * \* Created: liuhuichao
 * \* Date: 2019/4/22
 * \* Time: 6:16 PM
 * \* Description:二进制求和
 * <p>
 * 给定两个二进制字符串，返回他们的和（用二进制表示）。
 * <p>
 * 输入为非空字符串且只包含数字 1 和 0。
 * <p>
 * 输入: a = "1010", b = "1011"
 * 输出: "10101"
 * \
 */
public class A67_BinaryAdd {

    public String addBinary(String a, String b) {
        StringBuilder result = new StringBuilder();
        char[] aChar = a.toCharArray();
        char[] bChar = b.toCharArray();
        int indexA = aChar.length - 1;
        int indexB = bChar.length - 1;
        int loopNum = Math.max(indexA, indexB);

        Integer jinWei = 0;
        while (loopNum >= 0) {
            Integer item = jinWei;
            if (indexA != -1) {
                item += Character.digit(aChar[indexA], 2);
            }
            if (indexB != -1) {
                item += Character.digit(bChar[indexB], 2);
            }
            if (item >= 2) {
                result.append(item % 2);
                jinWei = 1;
            } else {
                jinWei = 0;
                result.append(item);
            }
            if (indexA >= 0) {
                indexA--;
            }
            if (indexB >= 0) {
                indexB--;
            }
            loopNum--;
        }
        if (jinWei != 0) {
            result.append(jinWei);
        }
        result.reverse();
        return result.toString();
    }


    @Test
    public void test() {
        String result = addBinary("1111", "1111");
        System.out.println("result= " + result); // "11110"

    }
}
