package leetcode.BitManipulation;

import org.junit.Test;

/**
 * \* Created: liuhuichao
 * \* Date: 2019/9/17
 * \* Time: 34:38 AM
 * \* Description: 4的幂
 * \
 * 给定一个整数 (32 位有符号整数)，请编写一个函数来判断它是否是 4 的幂次方。
 * <p>
 * 示例 1:
 * <p>
 * 输入: 16
 * 输出: true
 * 示例 2:
 * <p>
 * 输入: 5
 * 输出: false
 * 进阶：
 * 你能不使用循环或者递归来完成本题吗？
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/power-of-four
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class A342_PowerofFour {

    public boolean isPowerOfFour(int num) {
        if (num <= 0) {
            return false;
        }
        int count = 0;
        //转换成校验2的偶数位
        for (int i = 0; i < 32; i++) {
            int val = num >> i & 1;
            if (i % 2 != 0) {
                //奇数位上面
                if (val == 1) {
                    return false;
                }
                continue;
            }
            //偶数位上面
            if (val == 1) {
                count++;
            }
            if (count > 1) {
                return false;
            }
        }
        return true;
    }

    @Test
    public void test() {
        int num = 5;
        System.out.println("result = " + isPowerOfFour(num));
    }
}
