package leetcode.ArrayAndString;

import com.google.inject.internal.cglib.reflect.$FastClass;
import org.junit.Test;

/**
 * \* Created: liuhuichao
 * \* Date: 2020/5/29
 * \* Time: 30:34 下午
 * \* Description: 1比特与2比特字符
 * \
 * 有两种特殊字符。第一种字符可以用一比特0来表示。第二种字符可以用两比特(10 或 11)来表示。
 * <p>
 * 现给一个由若干比特组成的字符串。问最后一个字符是否必定为一个一比特字符。给定的字符串总是由0结束。
 * <p>
 * 示例 1:
 * <p>
 * 输入:
 * bits = [1, 0, 0]
 * 输出: True
 * 解释:
 * 唯一的编码方式是一个两比特字符和一个一比特字符。所以最后一个字符是一比特字符。
 * 示例 2:
 * <p>
 * 输入:
 * bits = [1, 1, 1, 0]
 * 输出: False
 * 解释:
 * 唯一的编码方式是两比特字符和两比特字符。所以最后一个字符不是一比特字符。
 * 注意:
 * <p>
 * 1 <= len(bits) <= 1000.
 * bits[i] 总是0 或 1.
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/1-bit-and-2-bit-characters
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class A717_1BitAnd2BitCharacters {
    public boolean isOneBitCharacter(int[] bits) {
        int index = 0;
        while (index < bits.length) {
            if (index == bits.length - 1) {
                //这里是最后一个比特字符
                if (bits[index] == 0) {
                    return true;
                }
            }
            if (bits[index] == 0) {
                //0的时候直接跳过
                index++;
                continue;
            }
            if (bits[index] == 1) {
                if (index + 1 == bits.length - 1) {
                    return false;
                }
                index += 2;
            }
        }
        return false;
    }

    @Test
    public void test() {
        //int[] bits = {1, 0, 0};
        //int[] bits = {1, 1, 1, 0};
        int[] bits = {0, 0};
        System.out.println(isOneBitCharacter(bits));
    }

}
