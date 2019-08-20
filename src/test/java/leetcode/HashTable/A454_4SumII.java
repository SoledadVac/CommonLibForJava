package leetcode.HashTable;

import com.kenai.jaffl.annotations.In;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * \* Created: liuhuichao
 * \* Date: 2019/8/20
 * \* Time: 27:56 PM
 * \* Description: 四数相加 II
 * \
 * 给定四个包含整数的数组列表 A , B , C , D ,计算有多少个元组 (i, j, k, l) ，使得 A[i] + B[j] + C[k] + D[l] = 0。
 * <p>
 * 为了使问题简单化，所有的 A, B, C, D 具有相同的长度 N，且 0 ≤ N ≤ 500 。所有整数的范围在 -228 到 228 - 1 之间，最终结果不会超过 231 - 1 。
 * <p>
 * 例如:
 * <p>
 * 输入:
 * A = [ 1, 2]
 * B = [-2,-1]
 * C = [-1, 2]
 * D = [ 0, 2]
 * <p>
 * 输出:
 * 2
 * <p>
 * 解释:
 * 两个元组如下:
 * 1. (0, 0, 0, 1) -> A[0] + B[0] + C[0] + D[1] = 1 + (-2) + (-1) + 2 = 0
 * 2. (1, 1, 0, 0) -> A[1] + B[1] + C[0] + D[0] = 2 + (-1) + (-1) + 0 = 0
 * <p>
 */
public class A454_4SumII {
    public int fourSumCount(int[] A, int[] B, int[] C, int[] D) {
        int result = 0;
        if (A.length == 0) {
            return result;
        }
        Map<Integer, Integer> firstMap = new HashMap<>(); // key :sum ; value : 出现count
        for (int a = 0; a < A.length; a++) {
            for (int b = 0; b < B.length; b++) {
                int sum = A[a] + B[b];
                firstMap.put(sum, firstMap.getOrDefault(sum, 0) + 1);
            }
        }
        for (int c = 0; c < C.length; c++) {
            for (int d = 0; d < D.length; d++) {
                int sum = -(C[c] + D[d]);
                if (firstMap.containsKey(sum)) {
                    result = result + firstMap.get(sum);
                }

            }
        }
        return result;
    }

    @Test
    public void test() {

        int[] A = {-1, -1};
        int[] B = {-1, 1};
        int[] C = {-1, 1};
        int[] D = {1, -1};

        System.out.println("result = " + fourSumCount(A, B, C, D));
    }

}
