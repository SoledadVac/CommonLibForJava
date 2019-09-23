package leetcode.BitManipulation;

import org.junit.Test;

/**
 * \* Created: liuhuichao
 * \* Date: 2019/9/23
 * \* Time: 28:13 PM
 * \* Description: 整数替换
 * \
 * 给定一个正整数 n，你可以做如下操作：
 * <p>
 * 1. 如果 n 是偶数，则用 n / 2替换 n。
 * 2. 如果 n 是奇数，则可以用 n + 1或n - 1替换 n。
 * n 变为 1 所需的最小替换次数是多少？
 * <p>
 * 示例 1:
 * <p>
 * 输入:
 * 8
 * <p>
 * 输出:
 * 3
 * <p>
 * 解释:
 * 8 -> 4 -> 2 -> 1
 * 示例 2:
 * <p>
 * 输入:
 * 7
 * <p>
 * 输出:
 * 4
 * <p>
 * 解释:
 * 7 -> 8 -> 4 -> 2 -> 1
 * 或
 * 7 -> 6 -> 3 -> 2 -> 1
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/integer-replacement
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class A397_IntegerReplacement {

    /**
     * 递归，有时候会超时
     *
     * @param n
     * @return
     */
    public int integerReplacement0(int n) {
        int count = 0;
        while (n != 1) {
            count++;
            if (n % 2 == 0) {
                n = n >>> 1;
                continue;
            }
            //奇数
            return count + Math.min(integerReplacement0(n + 1), integerReplacement0(n - 1));
        }
        return count;
    }

    public int integerReplacement(int n) {
        int count = 0;
        while (n != 1) {
            //偶数情况
            if ((n & 1) == 0) {
                n = n >>> 1;
                count++;
                continue;
            }
            if ((n & 2) == 0) {
                n--;
                count++;
                continue;
            }
            //奇数情况
            if (n == 3) {
                n--;
                count++;
                continue;
            }
            n++;
            count++;
        }
        return count;
    }

    @Test
    public void test() {
        int n = 1234;
        System.out.println("result = " + integerReplacement(n));
    }
}
