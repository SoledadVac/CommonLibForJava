package leetcode.BitManipulation;

import org.junit.Test;

/**
 * \* Created: liuhuichao
 * \* Date: 2019/9/23
 * \* Time: 35:46 AM
 * \* Description:  数字范围按位与
 * \
 * 给定范围 [m, n]，其中 0 <= m <= n <= 2147483647，返回此范围内所有数字的按位与（包含 m, n 两端点）。
 * <p>
 * 示例 1: 
 * <p>
 * 输入: [5,7]
 * 输出: 4
 * 示例 2:
 * <p>
 * 输入: [0,1]
 * 输出: 0
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/bitwise-and-of-numbers-range
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class A201_BitwiseANDofNumbersRange {

    /**
     * 暴力会超时
     *
     * @param m
     * @param n
     * @return
     */
    @Deprecated
    public int rangeBitwiseAnd0(int m, int n) {
        if (m == n) {
            return m;
        }
        int res = m;
        for (int i = m + 1; i <= n; i++) {
            res &= i;
        }
        return res;
    }

    public int rangeBitwiseAnd(int m, int n) {
        int offset = 0;
        for (; m != n; ++offset) {
            m >>= 1;
            n >>= 1;
        }
        return n << offset;
    }

    @Test
    public void test() {
        long begin = System.currentTimeMillis();
        int m = 0;
        int n = 2147483647;
        System.out.println("result = " + rangeBitwiseAnd(m, n));
        long end = System.currentTimeMillis();
        System.out.println("time = " + (end - begin));
    }
}
