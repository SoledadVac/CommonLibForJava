package leetcode.QueueAndStack;

import com.alibaba.fastjson.JSONObject;
import org.junit.Test;

import java.util.Stack;

/**
 * \* Created: liuhuichao
 * \* Date: 2019/7/11
 * \* Time: 29:48 PM
 * \* Description:每日温度
 * \
 * 根据每日 气温 列表，请重新生成一个列表，对应位置的输入是你需要再等待多久温度才会升高超过该日的天数。如果之后都不会升高，请在该位置用 0 来代替。
 * <p>
 * 例如，给定一个列表 temperatures = [73, 74, 75, 71, 69, 72, 76, 73]，你的输出应该是 [1, 1, 4, 2, 1, 1, 0, 0]。
 * <p>
 * 提示：气温 列表长度的范围是 [1, 30000]。每个气温的值的均为华氏度，都是在 [30, 100] 范围内的整数。
 */
public class A739_DailyTemperatures {

    public int[] dailyTemperatures(int[] T) {
        int[] result = new int[T.length];
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < T.length; i++) {
            while (!stack.isEmpty()) {
                int item = stack.peek();
                if (T[i] > T[item]) {
                    result[item] = i - item;
                    stack.pop();
                } else {
                    break;
                }
            }
            stack.push(i);
        }
        return result;
    }

    @Test
    public void test() {
        int[] T = {73, 74, 75, 71, 69, 72, 76, 73};
        System.out.println("result = " + JSONObject.toJSONString(dailyTemperatures(T)));
    }
}
