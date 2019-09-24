package leetcode.BitManipulation;

import org.junit.Test;

import java.util.*;

/**
 * \* Created: liuhuichao
 * \* Date: 2019/9/23
 * \* Time: 29:09 PM
 * \* Description:  子数组按位或操作
 * \
 * 我们有一个非负整数数组 A。
 * <p>
 * 对于每个（连续的）子数组 B = [A[i], A[i+1], ..., A[j]] （ i <= j），我们对 B 中的每个元素进行按位或操作，获得结果 A[i] | A[i+1] | ... | A[j]。
 * <p>
 * 返回可能结果的数量。 （多次出现的结果在最终答案中仅计算一次。）
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * 输入：[0]
 * 输出：1
 * 解释：
 * 只有一个可能的结果 0 。
 * 示例 2：
 * <p>
 * 输入：[1,1,2]
 * 输出：3
 * 解释：
 * 可能的子数组为 [1]，[1]，[2]，[1, 1]，[1, 2]，[1, 1, 2]。
 * 产生的结果为 1，1，2，1，3，3 。
 * 有三个唯一值，所以答案是 3 。
 * 示例 3：
 * <p>
 * 输入：[1,2,4]
 * 输出：6
 * 解释：
 * 可能的结果是 1，2，3，4，6，以及 7 。
 *  
 * <p>
 * 提示：
 * <p>
 * 1 <= A.length <= 50000
 * 0 <= A[i] <= 10^9
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/bitwise-ors-of-subarrays
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class A898_BitwiseORsofSubarrays {

    public int subarrayBitwiseORs(int[] A) {
        Set<Integer> res = new HashSet<>();
        Set<Integer> cur = new HashSet<>();
        cur.add(0);
        for (int a : A) {
            Set<Integer> cur2 = new HashSet();
            for (int y: cur)
                cur2.add(a | y);
            cur2.add(a);
            cur = cur2;
            res.addAll(cur);
        }
        return res.size();
    }

    @Test
    public void test() {
        int[] A = {599, 187, 674, 261, 883, 475, 329, 979, 17, 685, 528, 538, 873, 118, 361, 16, 417, 353, 533, 652, 916, 548, 858, 1006, 685, 85, 819, 1022, 522, 669, 375, 319, 138, 133, 230, 314, 830, 976, 825, 982, 222, 172, 687, 30, 899, 597, 546, 233, 216, 779};
        System.out.println("result = " + subarrayBitwiseORs(A));
    }

}
