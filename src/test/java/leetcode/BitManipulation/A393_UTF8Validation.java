package leetcode.BitManipulation;

import com.google.inject.internal.asm.$Attribute;
import org.junit.Test;

/**
 * \* Created: liuhuichao
 * \* Date: 2019/9/23
 * \* Time: 27:47 PM
 * \* Description: UTF-8 编码验证
 * \
 * UTF-8 中的一个字符可能的长度为 1 到 4 字节，遵循以下的规则：
 * <p>
 * 对于 1 字节的字符，字节的第一位设为0，后面7位为这个符号的unicode码。
 * 对于 n 字节的字符 (n > 1)，第一个字节的前 n 位都设为1，第 n+1 位设为0，后面字节的前两位一律设为10。剩下的没有提及的二进制位，全部为这个符号的unicode码。
 * 这是 UTF-8 编码的工作方式：
 * <p>
 * Char. number range  |        UTF-8 octet sequence
 * (hexadecimal)    |              (binary)
 * --------------------+---------------------------------------------
 * 0000 0000-0000 007F | 0xxxxxxx
 * 0000 0080-0000 07FF | 110xxxxx 10xxxxxx
 * 0000 0800-0000 FFFF | 1110xxxx 10xxxxxx 10xxxxxx
 * 0001 0000-0010 FFFF | 11110xxx 10xxxxxx 10xxxxxx 10xxxxxx
 * 给定一个表示数据的整数数组，返回它是否为有效的 utf-8 编码。
 * <p>
 * 注意:
 * 输入是整数数组。只有每个整数的最低 8 个有效位用来存储数据。这意味着每个整数只表示 1 字节的数据。
 * <p>
 * 示例 1:
 * <p>
 * data = [197, 130, 1], 表示 8 位的序列: 11000101 10000010 00000001.
 * <p>
 * 返回 true 。
 * 这是有效的 utf-8 编码，为一个2字节字符，跟着一个1字节字符。
 * 示例 2:
 * <p>
 * data = [235, 140, 4], 表示 8 位的序列: 11101011 10001100 00000100.
 * <p>
 * 返回 false 。
 * 前 3 位都是 1 ，第 4 位为 0 表示它是一个3字节字符。
 * 下一个字节是开头为 10 的延续字节，这是正确的。
 * 但第二个延续字节不以 10 开头，所以是不符合规则的。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/utf-8-validation
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class A393_UTF8Validation {

    public boolean validUtf8(int[] data) {
        if (data.length == 0) {
            return false;
        }
        if (data.length == 1) {
            return (data[0] >> 7) == 0;
        }
        int end = 0;
        for (int i = 0; i < data.length; i++) {
            if (i < end) {
                continue;
            }
            end = valid(data, i);
            if (end == -1) {
                return false;
            }
        }
        return true;
    }

    public int valid(int[] data, int begin) {
        int v = data[begin];
        if ((v >> 7) == 0) {
            return begin + 1;
        }
        int n = 0;
        int vBegin = data[begin];
        int front = (1 << 7) | (1 << 6) | (1 << 5) | (1 << 4);
        for (int move = 3; move <= 5; move++) {
            if ((vBegin >> move) == (front >> move)) {
                n = 7 - move;
                break;
            } else {
                front = front ^ (1 << (move + 1));
            }
        }
        if (n == 0) {
            return -1;
        }
        for (int index = begin + 1; index <= begin + n - 1; index++) {
            int value = data[index];
            int f = 1 << 7;
            if ((f >> 6) != (value >> 6)) {
                return -1;
            }
        }
        return begin + n;
    }

    @Test
    public void test() {
        //int[] data = {240, 162, 138, 147, 145}; //false
        //int[] data = {197, 130, 1}; //true
        //int[] data = {235, 140, 4};
        // int[] data = {228, 189, 160, 229, 165, 189, 13, 10};//true
        //int[] data = {237};
        int[] data = {39, 89, 227, 83, 132, 95, 10, 0};//true
        System.out.println("result = " + validUtf8(data));
    }

}
