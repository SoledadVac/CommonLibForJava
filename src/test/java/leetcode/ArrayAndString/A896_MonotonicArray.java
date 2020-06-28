package leetcode.ArrayAndString;

import org.junit.Test;

/**
 * \* Created: liuhuichao
 * \* Date: 2020/6/28
 * \* Time: 29:26 下午
 * \* Description: 单调数列
 * 如果数组是单调递增或单调递减的，那么它是单调的。
 * <p>
 * 如果对于所有 i <= j，A[i] <= A[j]，那么数组 A 是单调递增的。 如果对于所有 i <= j，A[i]> = A[j]，那么数组 A 是单调递减的。
 * <p>
 * 当给定的数组 A 是单调数组时返回 true，否则返回 false。
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * 输入：[1,2,2,3]
 * 输出：true
 * 示例 2：
 * <p>
 * 输入：[6,5,4,4]
 * 输出：true
 * 示例 3：
 * <p>
 * 输入：[1,3,2]
 * 输出：false
 * 示例 4：
 * <p>
 * 输入：[1,2,4,5]
 * 输出：true
 * 示例 5：
 * <p>
 * 输入：[1,1,1]
 * 输出：true
 *  
 * <p>
 * 提示：
 * <p>
 * 1 <= A.length <= 50000
 * -100000 <= A[i] <= 100000
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/monotonic-array
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * \
 */
public class A896_MonotonicArray {
    /**
     * 执行用时：
     * 1 ms
     * , 在所有 Java 提交中击败了
     * 100.00%
     * 的用户
     * 内存消耗：
     * 47.9 MB
     * , 在所有 Java 提交中击败了
     * 100.00%
     * 的用户
     *
     * @param A
     * @return
     */
    public boolean isMonotonic(int[] A) {
        int diff = 0;
        for (int i = 1; i < A.length; i++) {
            int dif = A[i] - A[i - 1];
            if (dif == 0) {
                continue;
            }
            dif = dif > 0 ? 1 : -1;
            if (diff == 0) {
                diff = dif;
            }
            if (dif != diff) {
                return false;
            }
        }
        return true;
    }

    @Test
    public void test() {
        int[] A = {6, 5, 4, 4};
        System.out.println(isMonotonic(A));
    }
}
