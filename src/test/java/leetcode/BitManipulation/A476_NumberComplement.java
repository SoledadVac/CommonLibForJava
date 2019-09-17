package leetcode.BitManipulation;

import org.junit.Test;

/**
 * \* Created: liuhuichao
 * \* Date: 2019/9/17
 * \* Time: 27:10 PM
 * \* Description: 数字的补数
 * \
 * 给定一个正整数，输出它的补数。补数是对该数的二进制表示取反。
 * <p>
 * 注意:
 * <p>
 * 给定的整数保证在32位带符号整数的范围内。
 * 你可以假定二进制数不包含前导零位。
 * 示例 1:
 * <p>
 * 输入: 5
 * 输出: 2
 * 解释: 5的二进制表示为101（没有前导零位），其补数为010。所以你需要输出2。
 * 示例 2:
 * <p>
 * 输入: 1
 * 输出: 0
 * 解释: 1的二进制表示为1（没有前导零位），其补数为0。所以你需要输出0。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/number-complement
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class A476_NumberComplement {

    public int findComplement(int num) {
        int result = 0;
        String source = "";
        for (int i = 31; i >= 0; i--) {
            int val = num >> i & 1;
            source += val;
        }
        source = source.replaceFirst("^0*", "");
        for (int i = source.length() - 1; i >= 0; i--) {
            int val = Character.digit(source.charAt(i), 2);
            if (val == 0) {
                result = 1 << (source.length() - 1 - i) | result;
            }
        }
        return result;
    }

    @Test
    public void test() {
        int num = 5;
        System.out.println("result = " + findComplement(num));
    }
}
