package leetcode.ArrayAndString;

import com.alibaba.fastjson.JSONObject;
import org.junit.Test;

/**
 * \* Created: liuhuichao
 * \* Date: 2020/6/28
 * \* Time: 26:43 下午
 * \* Description: 按奇偶排序数组
 * \
 * 给定一个非负整数数组 A，返回一个数组，在该数组中， A 的所有偶数元素之后跟着所有奇数元素。
 * <p>
 * 你可以返回满足此条件的任何数组作为答案。
 * <p>
 *  
 * <p>
 * 示例：
 * <p>
 * 输入：[3,1,2,4]
 * 输出：[2,4,3,1]
 * 输出 [4,2,3,1]，[2,4,1,3] 和 [4,2,1,3] 也会被接受。
 *  
 * <p>
 * 提示：
 * <p>
 * 1 <= A.length <= 5000
 * 0 <= A[i] <= 5000
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/sort-array-by-parity
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class A905_SortArrayByParity {

    public int[] sortArrayByParity(int[] A) {
        int begin = 0;
        int end = A.length - 1;
        while (begin < end) {
            if (A[begin] % 2 == 0) {
                //偶数
                begin++;
                continue;
            } else {
                if (A[end] % 2 == 0) {
                    int temp = A[begin];
                    A[begin] = A[end];
                    A[end] = temp;
                    begin++;
                    end--;
                    continue;
                } else {
                    // A[end] 也是奇数
                    end--;
                    continue;
                }
            }
        }
        return A;
    }

    @Test
    public void test() {
        int[] A = {3, 1, 2, 4};
        System.out.println(JSONObject.toJSONString(sortArrayByParity(A)));
    }
}
