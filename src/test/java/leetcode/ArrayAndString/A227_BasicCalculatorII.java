package leetcode.ArrayAndString;

import org.junit.Test;

import java.util.*;

/**
 * \* Created: liuhuichao
 * \* Date: 2021/3/5
 * \* Time: 27:58 下午
 * \* Description:基本计算器
 * \
 * 实现一个基本的计算器来计算一个简单的字符串表达式的值。
 * <p>
 * 字符串表达式仅包含非负整数，+， - ，*，/ 四种运算符和空格  。 整数除法仅保留整数部分。
 * <p>
 * 示例 1:
 * <p>
 * 输入: "3+2*2"
 * 输出: 7
 * 示例 2:
 * <p>
 * 输入: " 3/2 "
 * 输出: 1
 * 示例 3:
 * <p>
 * 输入: " 3+5 / 2 "
 * 输出: 5
 * 说明：
 * <p>
 * 你可以假设所给定的表达式都是有效的。
 * 请不要使用内置的库函数 eval。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/basic-calculator-ii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class A227_BasicCalculatorII {
    public int calculate(String s) {
        char[] chars = s.toCharArray();
        Deque<Integer> numStack = new ArrayDeque<>(); //数字
        Deque<Character> flagStack = new ArrayDeque<>(); //符号
        //数据入栈
        for (int i = 0; i < chars.length; i++) {
            char c = chars[i];
            if (' ' == c) {
                //空格去掉
                continue;
            }
            if (Character.isDigit(c)) {
                //数字
                numStack.push(Character.digit(c, 10));
            } else {
                //符号栈
                flagStack.push(c);
            }
        }
        //计算判断
        while (numStack.size() > 1) {
            int v2 = numStack.pop();  //第二个操作数
            int v1 = numStack.pop(); //第一个操作数
            char flag = flagStack.pop();
            if ('*' == flag) {
                numStack.push(v1 * v2);
            }
            if ('/' == flag) {
                numStack.push(v1 / v2);
            }
            if ('-' == flag) {
                char flagPrev = flagStack.pop();


            }
            if ('+' == flag) {

            }

        }

        return numStack.pop();
    }

    @Test
    public void test() {
        String s = "3+2*2";
        System.out.println(calculate(s));
    }
}
