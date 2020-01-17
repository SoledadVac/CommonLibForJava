package leetcode.Recursion;

import com.kenai.jaffl.annotations.In;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * \* Created: liuhuichao
 * \* Date: 2020/1/17
 * \* Time: 29:51 下午
 * \* Description:  第 N 个泰波那契数
 * \
 * 泰波那契序列 Tn 定义如下： 
 * <p>
 * T0 = 0, T1 = 1, T2 = 1, 且在 n >= 0 的条件下 Tn+3 = Tn + Tn+1 + Tn+2
 * <p>
 * 给你整数 n，请返回第 n 个泰波那契数 Tn 的值。
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * 输入：n = 4
 * 输出：4
 * 解释：
 * T_3 = 0 + 1 + 1 = 2
 * T_4 = 1 + 1 + 2 = 4
 * 示例 2：
 * <p>
 * 输入：n = 25
 * 输出：1389537
 *  
 * <p>
 * 提示：
 * <p>
 * 0 <= n <= 37
 * 答案保证是一个 32 位整数，即 answer <= 2^31 - 1。
 * 在真实的面试中遇到过这道题？
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/n-th-tribonacci-number
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class A1137_NthTribonacciNumber {

    Map<Integer, Integer> dataTemp = new HashMap<>();

    public int tribonacci(int n) {
        if (n == 0) {
            dataTemp.put(0, 0);
            return 0;
        }
        if (n == 1) {
            dataTemp.put(1, 1);
            return 1;
        }
        if (n == 2) {
            dataTemp.put(2, 1);
            return 1;
        }
        int n1;
        int n2;
        int n3;
        if (dataTemp.get(n - 1) != null) {
            n1 = dataTemp.get(n - 1);
        } else {
            n1 = tribonacci(n - 1);
            dataTemp.put(n - 1, n1);
        }
        if (dataTemp.get(n - 2) != null) {
            n2 = dataTemp.get(n - 2);
        } else {
            n2 = tribonacci(n - 2);
            dataTemp.put(n - 2, n2);
        }
        if (dataTemp.get(n - 3) != null) {
            n3 = dataTemp.get(n - 3);
        } else {
            n3 = tribonacci(n - 3);
            dataTemp.put(n - 3, n3);
        }
        return n1 + n2 + n3;
    }

    @Test
    public void test() {
        int n = 25;
        System.out.println(tribonacci(n));
    }

}
