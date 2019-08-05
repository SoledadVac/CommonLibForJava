package leetcode.BinarySearch;

import org.junit.Test;

/**
 * \* Created: liuhuichao
 * \* Date: 2019/5/22
 * \* Time: 28:51 PM
 * \* Description:Pow(x, n)
 * \
 * <p>
 * 实现 pow(x, n) ，即计算 x 的 n 次幂函数。
 * <p>
 * 示例 1:
 * <p>
 * 输入: 2.00000, 10
 * 输出: 1024.00000
 * 示例 2:
 * <p>
 * 输入: 2.10000, 3
 * 输出: 9.26100
 * 示例 3:
 * <p>
 * 输入: 2.00000, -2
 * 输出: 0.25000
 * 解释: 2-2 = 1/22 = 1/4 = 0.25
 * 说明:
 * <p>
 * -100.0 < x < 100.0
 * n 是 32 位有符号整数，其数值范围是 [−231, 231 − 1] 。
 */
public class A50_PowXY {
    public double myPow(double x, int n) {
        if (n == 0) {
            return 1;
        }
        if (n == 1) {
            return x;
        }
        if (n == 2) {
            return x * x;
        }
        if (n > 0) {
            return n % 2 == 0 ? myPow(myPow(x, n / 2), 2) : myPow(myPow(x, n / 2), 2) * x;
        } else {
            return 1 / (n % 2 == 0 ? myPow(myPow(x, -n / 2), 2) : myPow(myPow(x, -n / 2), 2) * x);
        }
    }

    @Test
    public void test() {

       /* double x = 0.00001;
        int n = 2147483647;*/

        double x = 2;
        int n = -2147483647;
        System.out.println(myPow(x, n));
    }

}
