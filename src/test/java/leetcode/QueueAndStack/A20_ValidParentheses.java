package leetcode.QueueAndStack;

import org.junit.Test;

import java.util.Stack;

/**
 * \* Created: liuhuichao
 * \* Date: 2019/7/11
 * \* Time: 34:38 AM
 * \* Description: 有效的括号
 * \
 * 给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串，判断字符串是否有效。
 * <p>
 * 有效字符串需满足：
 * <p>
 * 左括号必须用相同类型的右括号闭合。
 * 左括号必须以正确的顺序闭合。
 * 注意空字符串可被认为是有效字符串。
 * <p>
 * 示例 1:
 * <p>
 * 输入: "()"
 * 输出: true
 * 示例 2:
 * <p>
 * 输入: "()[]{}"
 * 输出: true
 * 示例 3:
 * <p>
 * 输入: "(]"
 * 输出: false
 * 示例 4:
 * <p>
 * 输入: "([)]"
 * 输出: false
 * 示例 5:
 * <p>
 * 输入: "{[]}"
 * 输出: true
 * <p>
 */
public class A20_ValidParentheses {

    public boolean isValid(String s) {
        if (s.equals("")) {
            return true;
        }
        char[] sChars = s.toCharArray();
        if (sChars.length <= 0 || sChars.length % 2 != 0) {
            return false;
        }
        Stack<Character> stack = new Stack<>();
        for (char c : sChars) {
            if (c == ' ') {
                continue;
            }
            if (c == '(' || c == '{' || c == '[') {
                stack.push(c);
                continue;
            }
            if (stack.isEmpty()) {
                return false;
            }
            if (c == ')' && stack.peek() == '(') {
                stack.pop();
                continue;
            }
            if (c == '}' && stack.peek() == '{') {
                stack.pop();
                continue;
            }
            if (c == ']' && stack.peek() == '[') {
                stack.pop();
                continue;
            }
            return false;
        }
        return stack.isEmpty();
    }

    @Test
    public void test() {
        String s = "";
        System.out.println("result = " + isValid(s));
    }

    @Test
    public void test1() {

    }
}
