package leetcode.BitManipulation;

import org.junit.Test;

/**
 * \* Created: liuhuichao
 * \* Date: 2019/9/17
 * \* Time: 27:41 PM
 * \* Description: 交替位二进制数
 * \
 * 给定一个正整数，检查他是否为交替位二进制数：换句话说，就是他的二进制数相邻的两个位数永不相等。
 * <p>
 * 示例 1:
 * <p>
 * 输入: 5
 * 输出: True
 * 解释:
 * 5的二进制数是: 101
 * 示例 2:
 * <p>
 * 输入: 7
 * 输出: False
 * 解释:
 * 7的二进制数是: 111
 * 示例 3:
 * <p>
 * 输入: 11
 * 输出: False
 * 解释:
 * 11的二进制数是: 1011
 *  示例 4:
 * <p>
 * 输入: 10
 * 输出: True
 * 解释:
 * 10的二进制数是: 1010
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/binary-number-with-alternating-bits
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class A693_BinaryNumberwithAlternatingBits {

    public boolean hasAlternatingBits(int n) {
        Boolean isBeginCheck = false;
        int prev = 0;
        for (int i = 31; i >= 0; i--) {
            int val = n >> i & 1;
            if (val == 1 && isBeginCheck == false) {
                prev = 1;
                isBeginCheck = true;
                continue;
            }
            if (!isBeginCheck) {
                continue;
            }
            if (val != prev) {
                prev = val;
            } else {
                return false;
            }
        }
        return true;
    }

    @Test
    public void test() {
        int n = 2;
        System.out.println("result = " + hasAlternatingBits(n));
    }


}
