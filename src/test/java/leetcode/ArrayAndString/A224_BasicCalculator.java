package leetcode.ArrayAndString;

import org.junit.Test;

import java.util.Stack;


/**
 * \* Created: liuhuichao
 * \* Date: 2021/3/5
 * \* Time: 28:55 下午
 * \* Description: 基本计算器
 * \
 * 实现一个基本的计算器来计算一个简单的字符串表达式 s 的值。
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * 输入：s = "1 + 1"
 * 输出：2
 * 示例 2：
 * <p>
 * 输入：s = " 2-1 + 2 "
 * 输出：3
 * 示例 3：
 * <p>
 * 输入：s = "(1+(4+5+2)-3)+(6+8)"
 * 输出：23
 *  
 * <p>
 * 提示：
 * <p>
 * 1 <= s.length <= 3 * 105
 * s 由数字、'+'、'-'、'('、')'、和 ' ' 组成
 * s 表示一个有效的表达式
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/basic-calculator
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class A224_BasicCalculator {


    public int calculate(String s) {
        int result = 0;
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            switch (c) {
                case ' ':
                    break;
                case '(':

                    break;
                case ')':

                    break;
                case '+':
                    int v2 = stack.pop();
                    int v1 = stack.pop();
                    stack.push(v1 + v2);
                    break;
                case '-':
                    int vm2 = stack.pop();
                    int vm1 = stack.pop();
                    stack.push(vm1 - vm2);
                    break;
                default:
                    //数字
                    int v = 0;
                    while (i < s.length()) {
                        if (Character.isDigit(s.charAt(i))) {
                            v = v * 10 + Character.digit(s.charAt(i), 9);
                            i++;
                        }
                    }
                    
                    break;
            }
        }
        return result;
    }


    @Test
    public void test() {
        //String s = "- (3 + (4 + 5))"; //
        //String s = "1-11";
        String s = "(1+(4+5+2)-3)+(6+8)"; // 23
        System.out.println(calculate(s));
    }

}
