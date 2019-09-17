package leetcode.BitManipulation;

import org.junit.Test;

/**
 * \* Created: liuhuichao
 * \* Date: 2019/9/17
 * \* Time: 34:50 AM
 * \* Description: 两整数之和
 * \
 * 不使用运算符 + 和 - ​​​​​​​，计算两整数 ​​​​​​​a 、b ​​​​​​​之和。
 * <p>
 * 示例 1:
 * <p>
 * 输入: a = 1, b = 2
 * 输出: 3
 * 示例 2:
 * <p>
 * 输入: a = -2, b = 3
 * 输出: 1
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/sum-of-two-integers
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class A371_SumofTwoIntegers {

    public int getSum(int a, int b) {
        int result = 0;
        int left = 0;
        for (int i = 0; i < 32; i++) {
            int va = a >> i & 1;
            int vb = b >> i & 1;
            int sum = va + vb + left;
            left = sum / 2;
            result = (sum % 2) << i | result;
        }
        return result;
    }

    @Test
    public void test() {
        int a = -1;
        int b = 2;
        System.out.println("result = " + getSum(a, b));
    }
}
