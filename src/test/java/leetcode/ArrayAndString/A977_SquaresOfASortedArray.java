package leetcode.ArrayAndString;

import com.alibaba.fastjson.JSONObject;
import org.junit.Test;

import java.util.Arrays;
import java.util.Queue;
import java.util.TreeMap;

/**
 * \* Created: liuhuichao
 * \* Date: 2020/6/30
 * \* Time: 29:50 下午
 * \* Description: 有序数组的平方
 * \
 * 给定一个按非递减顺序排序的整数数组 A，返回每个数字的平方组成的新数组，要求也按非递减顺序排序。
 * 示例 1：
 * <p>
 * 输入：[-4,-1,0,3,10]
 * 输出：[0,1,9,16,100]
 * 示例 2：
 * <p>
 * 输入：[-7,-3,2,3,11]
 * 输出：[4,9,9,49,121]
 *  
 * <p>
 * 提示：
 * <p>
 * 1 <= A.length <= 10000
 * -10000 <= A[i] <= 10000
 * A 已按非递减顺序排序。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/squares-of-a-sorted-array
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class A977_SquaresOfASortedArray {

    public int[] sortedSquares(int[] A) {
        int[] result = new int[A.length];
        for (int i = 0; i < result.length; i++) {
            result[i] = A[i] * A[i];
        }
        Arrays.sort(result);
        return result;
    }

    public int[] sortedSquares1(int[] A) {
        int[] result = new int[A.length];
        TreeMap<Integer, Integer> data = new TreeMap<>();
        for (int i = 0; i < A.length; i++) {
            int v = A[i] * A[i];
            data.put(v, data.getOrDefault(v, 0) + 1);
        }
        int index = 0;
        for (Integer key : data.keySet()) {
            for (int i = 0; i < data.get(key); i++) {
                result[index] = key;
                index++;
            }
        }
        return result;
    }


    @Test
    public void test() {
        int[] A = {-4, -1, 0, 3, 10};
        System.out.println(JSONObject.toJSONString(sortedSquares(A)));
    }
}
