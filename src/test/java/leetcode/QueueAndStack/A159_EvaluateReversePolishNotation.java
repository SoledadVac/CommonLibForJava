package leetcode.QueueAndStack;

import org.junit.Test;

import java.util.Stack;

/**
 * \* Created: liuhuichao
 * \* Date: 2019/7/11
 * \* Time: 29:29 PM
 * \* Description:逆波兰表达式求值
 * \
 * 根据逆波兰表示法，求表达式的值。
 * <p>
 * 有效的运算符包括 +, -, *, / 。每个运算对象可以是整数，也可以是另一个逆波兰表达式。
 * <p>
 * 说明：
 * <p>
 * 整数除法只保留整数部分。
 * 给定逆波兰表达式总是有效的。换句话说，表达式总会得出有效数值且不存在除数为 0 的情况。
 * 示例 1：
 * <p>
 * 输入: ["2", "1", "+", "3", "*"]
 * 输出: 9
 * 解释: ((2 + 1) * 3) = 9
 * 示例 2：
 * <p>
 * 输入: ["4", "13", "5", "/", "+"]
 * 输出: 6
 * 解释: (4 + (13 / 5)) = 6
 * 示例 3：
 * <p>
 * 输入: ["10", "6", "9", "3", "+", "-11", "*", "/", "*", "17", "+", "5", "+"]
 * 输出: 22
 * 解释:
 * ((10 * (6 / ((9 + 3) * -11))) + 17) + 5
 * = ((10 * (6 / (12 * -11))) + 17) + 5
 * = ((10 * (6 / -132)) + 17) + 5
 * = ((10 * 0) + 17) + 5
 * = (0 + 17) + 5
 * = 17 + 5
 * = 22
 */
public class A159_EvaluateReversePolishNotation {
    public int evalRPN(String[] tokens) {
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < tokens.length; i++) {
            switch (tokens[i]) {
                case "+":
                    Integer n0 = stack.pop();
                    Integer n1 = stack.pop();
                    Integer r1 = n0 + n1;
                    stack.push(r1);
                    break;
                case "-":
                    Integer n2 = stack.pop();
                    Integer n3 = stack.pop();
                    Integer r2 = n3 - n2;
                    stack.push(r2);
                    break;
                case "*":
                    Integer n4 = stack.pop();
                    Integer n5 = stack.pop();
                    Integer r3 = n4 * n5;
                    stack.push(r3);
                    break;
                case "/":
                    Integer n6 = stack.pop();
                    Integer n7 = stack.pop();
                    Integer r4 = n7 / n6;
                    stack.push(r4);
                    break;
                default:
                    stack.push(Integer.valueOf(tokens[i]));
                    break;
            }
        }

        return stack.pop();

    }


    @Test
    public void test() {
        String[] tokens = {"10", "6", "9", "3", "+", "-11", "*", "/", "*", "17", "+", "5", "+"};
        System.out.println("result = " + evalRPN(tokens));
    }
}
