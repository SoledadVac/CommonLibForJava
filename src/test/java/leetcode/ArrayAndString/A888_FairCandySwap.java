package leetcode.ArrayAndString;

import com.alibaba.fastjson.JSONObject;
import com.kenai.jaffl.annotations.In;
import org.junit.Test;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/**
 * \* Created: liuhuichao
 * \* Date: 2020/6/28
 * \* Time: 28:32 下午
 * \* Description: 公平的糖果交换
 * \
 * 爱丽丝和鲍勃有不同大小的糖果棒：A[i] 是爱丽丝拥有的第 i 块糖的大小，B[j] 是鲍勃拥有的第 j 块糖的大小。
 * <p>
 * 因为他们是朋友，所以他们想交换一个糖果棒，这样交换后，他们都有相同的糖果总量。（一个人拥有的糖果总量是他们拥有的糖果棒大小的总和。）
 * <p>
 * 返回一个整数数组 ans，其中 ans[0] 是爱丽丝必须交换的糖果棒的大小，ans[1] 是 Bob 必须交换的糖果棒的大小。
 * <p>
 * 如果有多个答案，你可以返回其中任何一个。保证答案存在。
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * 输入：A = [1,1], B = [2,2]
 * 输出：[1,2]
 * 示例 2：
 * <p>
 * 输入：A = [1,2], B = [2,3]
 * 输出：[1,2]
 * 示例 3：
 * <p>
 * 输入：A = [2], B = [1,3]
 * 输出：[2,3]
 * 示例 4：
 * <p>
 * 输入：A = [1,2,5], B = [2,4]
 * 输出：[5,4]
 *  
 * <p>
 * 提示：
 * <p>
 * 1 <= A.length <= 10000
 * 1 <= B.length <= 10000
 * 1 <= A[i] <= 100000
 * 1 <= B[i] <= 100000
 * 保证爱丽丝与鲍勃的糖果总量不同。
 * 答案肯定存在。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/fair-candy-swap
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class A888_FairCandySwap {


    @Deprecated
    /**
     * 渣代码，超时了
     */
    public int[] fairCandySwap0(int[] A, int[] B) {
        int[] result = new int[2];
        for (int a = 0; a < A.length; a++) {
            for (int b = 0; b < B.length; b++) {
                if (sum(A, a) + B[b] == sum(B, b) + A[a]) {
                    result[0] = A[a];
                    result[1] = B[b];
                }
            }
        }
        return result;
    }

    public int sum(int[] data, int expIndex) {
        int sum = 0;
        for (int i = 0; i < data.length; i++) {
            if (i == expIndex) {
                continue;
            }
            sum += data[i];
        }
        return sum;
    }

    @Deprecated
    /**
     * 渣代码，拿空间换时间了，还是超时了，看来得继续优化两层循环
     */
    public int[] fairCandySwap1(int[] A, int[] B) {
        int[] result = new int[2];
        HashMap<Integer, Integer> sumDataAMap = new HashMap<>(); // key : index ,value :sum
        HashMap<Integer, Integer> sumDataBMap = new HashMap<>();
        for (int a = 0; a < A.length; a++) {
            for (int b = 0; b < B.length; b++) {
                if (!sumDataAMap.containsKey(a)) {
                    sumDataAMap.put(a, sum(A, a));
                }
                if (!sumDataBMap.containsKey(b)) {
                    sumDataBMap.put(b, sum(B, b));
                }
                if (sumDataAMap.get(a) + B[b] == sumDataBMap.get(b) + A[a]) {
                    result[0] = A[a];
                    result[1] = B[b];
                }
            }
        }
        return result;
    }

    /**
     * 我尼玛，可算是过了，不过效果不太好
     * <p>
     * <p>
     * 执行用时：
     * 22 ms
     * , 在所有 Java 提交中击败了
     * 38.54%
     * 的用户
     * 内存消耗：
     * 41.1 MB
     * , 在所有 Java 提交中击败了
     * 30.00%
     * 的用户
     *
     * @param A
     * @param B
     * @return
     */
    public int[] fairCandySwap2(int[] A, int[] B) {
        int[] result = new int[2];
        int sumA = 0;
        int sumB = 0;
        HashMap<Integer, Integer> dataAMap = new HashMap<>(); // key : value , value : index
        HashMap<Integer, Integer> dataBMap = new HashMap<>();
        for (int a = 0; a < A.length; a++) {
            sumA += A[a];
            dataAMap.put(A[a], a);
        }
        for (int b = 0; b < B.length; b++) {
            sumB += B[b];
            dataBMap.put(B[b], b);
        }
        int half = (sumA + sumB) / 2;
        for (int a = 0; a < A.length; a++) {
            int v = A[a];
            int diff = sumA - half;
            if (dataBMap.containsKey(v - diff)) {
                result[0] = v;
                result[1] = v - diff;
                break;
            }
        }
        return result;
    }

    /**
     * 官方题解，把上面简化了一半，emm
     * @param A
     * @param B
     * @return
     */
    public int[] fairCandySwap(int[] A, int[] B) {
        int sumA = 0;
        int sumB = 0;
        Set<Integer> bSet = new HashSet<>();
        for (int a = 0; a < A.length; a++) {
            sumA += A[a];
        }
        for (int b = 0; b < B.length; b++) {
            sumB += B[b];
            bSet.add(B[b]);
        }
        int half = (sumA - sumB) / 2;
        for (int a = 0; a < A.length; a++) {
            if (bSet.contains(A[a] - half)) {
                return new int[]{A[a], A[a] - half};
            }
        }
        return null;
    }

    @Test
    public void test() {
        int[] A = {1, 2, 5};
        int[] B = {2, 4};
        System.out.println(JSONObject.toJSONString(fairCandySwap(A, B)));
    }
}
