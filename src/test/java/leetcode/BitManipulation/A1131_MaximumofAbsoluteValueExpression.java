package leetcode.BitManipulation;

import org.junit.Test;

/**
 * \* Created: liuhuichao
 * \* Date: 2019/12/25
 * \* Time: 30:50 下午
 * \* Description: 绝对值表达式的最大值
 * \
 * 给你两个长度相等的整数数组，返回下面表达式的最大值：
 * <p>
 * |arr1[i] - arr1[j]| + |arr2[i] - arr2[j]| + |i - j|
 * <p>
 * 其中下标 i，j 满足 0 <= i, j < arr1.length。
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * 输入：arr1 = [1,2,3,4], arr2 = [-1,4,5,6]
 * 输出：13
 * 示例 2：
 * <p>
 * 输入：arr1 = [1,-2,-5,0,10], arr2 = [0,-2,-1,-7,-4]
 * 输出：20
 *  
 * <p>
 * 提示：
 * <p>
 * 2 <= arr1.length == arr2.length <= 40000
 * -10^6 <= arr1[i], arr2[i] <= 10^6
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/maximum-of-absolute-value-expression
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class A1131_MaximumofAbsoluteValueExpression {
    /**
     * 超出时间限制
     *
     * @param arr1
     * @param arr2
     * @return
     */
    @Deprecated
    public int maxAbsValExp0(int[] arr1, int[] arr2) {
        int max = 0;
        for (int i = 0; i < arr1.length; i++) {
            for (int j = 0; j < arr1.length; j++) {
                max = Math.max(max, Math.abs(arr1[i] - arr1[j]) + Math.abs(arr2[i] - arr2[j]) + Math.abs(i - j));
            }
        }
        return max;
    }

    public int maxAbsValExp(int[] arr1, int[] arr2) {
        int result = 0;
        int[][] direction = {{-1, -1}, {-1, 1}, {1, -1}, {1, 1}};
        //在每个方向上，获取到最大的结果
        for (int d = 0; d < 4; d++) {
            int maxv = -(int) Math.pow(10, 6);
            int minv = (int) Math.pow(10, 6);
            for (int i = 0; i < arr1.length; i++) {
                int value = arr1[i] * direction[d][0] + arr2[i] * direction[d][1] + i;
                maxv = Math.max(maxv, value);
                minv = Math.min(minv, value);
               // System.out.println("d = " + d + " i=" + i + " max=" + maxv + " min=" + maxv);
            }
            result = Math.max(result, maxv - minv);
            //System.out.println("result = " + result);
        }
        return result;
    }

    @Test
    public void test() {
        int[] arr1 = {1, 2, 3, 4};
        int[] arr2 = {-1, 4, 5, 6}; //13
       /* int[] arr1 = {1, -2, -5, 0, 10};
        int[] arr2 = {0, -2, -1, -7, -4}; //20*/
        System.out.println(maxAbsValExp(arr1, arr2));

    }
}
