package leetcode.ArrayAndString;

import com.sun.org.apache.regexp.internal.RE;
import org.junit.Test;

/**
 * \* Created: liuhuichao
 * \* Date: 2020/5/12
 * \* Time: 30:40 下午
 * \* Description: 将数组分成和相等的三个部分
 * \
 * 给你一个整数数组 A，只有可以将其划分为三个和相等的非空部分时才返回 true，否则返回 false。
 * <p>
 * 形式上，如果可以找出索引 i+1 < j 且满足 (A[0] + A[1] + ... + A[i] == A[i+1] + A[i+2] + ... + A[j-1] == A[j] + A[j-1] + ... + A[A.length - 1]) 
 * 就可以将数组三等分。
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * 输入：[0,2,1,-6,6,-7,9,1,2,0,1]
 * 输出：true
 * 解释：0 + 2 + 1 = -6 + 6 - 7 + 9 + 1 = 2 + 0 + 1
 * 示例 2：
 * <p>
 * 输入：[0,2,1,-6,6,7,9,-1,2,0,1]
 * 输出：false
 * 示例 3：
 * <p>
 * 输入：[3,3,6,5,-2,2,5,1,-9,4]
 * 输出：true
 * 解释：3 + 3 = 6 = 5 - 2 + 2 + 5 + 1 - 9 + 4
 *  
 * <p>
 * 提示：
 * <p>
 * 3 <= A.length <= 50000
 * -10^4 <= A[i] <= 10^4
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/partition-array-into-three-parts-with-equal-sum
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class A1013_PartitionArrayIntoThreePartsWithEqualSum {

    /**
     * 先写个N方的，emm,待改
     *
     * @param A
     * @return
     */
    public boolean canThreePartsEqualSum(int[] A) {
        int leftSum = 0;
        for (int i1 = 0; i1 < A.length - 2; i1++) {
            leftSum += A[i1];
            int rightSum = 0;
            for (int i2 = A.length - 1; i2 > 0; i2--) {
                rightSum += A[i2];
                if (leftSum != rightSum) {
                    continue;
                }
                int midSum = addRange(A, i1 + 1, i2 - 1);
                if ((leftSum == midSum && midSum == rightSum) && (i1 + 1 < i2)) {
                    return true;
                }
            }
        }
        return false;
    }

    private int addRange(int[] A, int index1, int index2) {
        int result = 0;
        for (int i = index1; i <= index2; i++) {
            result += A[i];
        }
        return result;
    }

    /**
     * @param A
     * @return
     */
    public boolean canThreePartsEqualSum1(int[] A) {
        int sum = 0;
        for (int a : A) {
            sum += a;
        }
        if (sum % 3 != 0) {
            return false;
        }
        int itemSum = sum / 3;
        int leftSum = 0;
        for (int i1 = 0; i1 < A.length - 2; i1++) {
            leftSum += A[i1];
            if (leftSum != itemSum) {
                continue;
            }
            int rightSum = 0;
            for (int i2 = A.length - 1; i2 > 0; i2--) {
                rightSum += A[i2];
                if (leftSum != rightSum) {
                    continue;
                }
                if (i1 + 1 >= i2) {
                    continue;
                }
                int midSum = addRange(A, i1 + 1, i2 - 1);
                if (leftSum == midSum) {
                    return true;
                }
            }
        }
        return false;
    }


    @Test
    public void test() {
        int[] A = {0, 2, 1, -6, 6, -7, 9, 1, 2, 0, 1};
        System.out.println(canThreePartsEqualSum1(A));
    }
}
