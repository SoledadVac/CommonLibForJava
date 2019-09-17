package leetcode.BitManipulation;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * \* Created: liuhuichao
 * \* Date: 2019/9/17
 * \* Time: 27:52 PM
 * \* Description: 二进制表示中质数个计算置位
 * \
 * 给定两个整数 L 和 R ，找到闭区间 [L, R] 范围内，计算置位位数为质数的整数个数。
 * <p>
 * （注意，计算置位代表二进制表示中1的个数。例如 21 的二进制表示 10101 有 3 个计算置位。还有，1 不是质数。）
 * <p>
 * 示例 1:
 * <p>
 * 输入: L = 6, R = 10
 * 输出: 4
 * 解释:
 * 6 -> 110 (2 个计算置位，2 是质数)
 * 7 -> 111 (3 个计算置位，3 是质数)
 * 9 -> 1001 (2 个计算置位，2 是质数)
 * 10-> 1010 (2 个计算置位，2 是质数)
 * 示例 2:
 * <p>
 * 输入: L = 10, R = 15
 * 输出: 5
 * 解释:
 * 10 -> 1010 (2 个计算置位, 2 是质数)
 * 11 -> 1011 (3 个计算置位, 3 是质数)
 * 12 -> 1100 (2 个计算置位, 2 是质数)
 * 13 -> 1101 (3 个计算置位, 3 是质数)
 * 14 -> 1110 (3 个计算置位, 3 是质数)
 * 15 -> 1111 (4 个计算置位, 4 不是质数)
 * 注意:
 * <p>
 * L, R 是 L <= R 且在 [1, 10^6] 中的整数。
 * R - L 的最大值为 10000。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/prime-number-of-set-bits-in-binary-representation
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class A762_PrimeNumberofSetBitsinBinaryRepresentation {

    public int countPrimeSetBits(int L, int R) {
        int result = 0;
        List<Integer> zhishu = new ArrayList();
        zhishu.add(2);
        zhishu.add(3);
        zhishu.add(5);
        zhishu.add(7);
        zhishu.add(11);
        zhishu.add(13);
        zhishu.add(17);
        zhishu.add(19);
        for (int i = L; i <= R; i++) {
            int count = Integer.bitCount(i);
            if (zhishu.contains(count)) {
                result++;
            }

        }
        return result;
    }

    @Test
    public void test() {
        int L = 6;
        int R = 10;
        System.out.println("result = " + countPrimeSetBits(L, R));
    }
}
