package leetcode.ArrayAndString;

import com.alibaba.fastjson.JSONObject;
import org.junit.Test;

/**
 * \* Created: liuhuichao
 * \* Date: 2020/6/28
 * \* Time: 27:03 下午
 * \* Description: 按奇偶排序数组 II
 * \
 * 给定一个非负整数数组 A， A 中一半整数是奇数，一半整数是偶数。
 * <p>
 * 对数组进行排序，以便当 A[i] 为奇数时，i 也是奇数；当 A[i] 为偶数时， i 也是偶数。
 * <p>
 * 你可以返回任何满足上述条件的数组作为答案。
 * <p>
 *  
 * <p>
 * 示例：
 * <p>
 * 输入：[4,2,5,7]
 * 输出：[4,5,2,7]
 * 解释：[4,7,2,5]，[2,5,4,7]，[2,7,4,5] 也会被接受。
 *  
 * <p>
 * 提示：
 * <p>
 * 2 <= A.length <= 20000
 * A.length % 2 == 0
 * 0 <= A[i] <= 1000
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/sort-array-by-parity-ii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class A922_SortArrayByParityII {

    public int[] sortArrayByParityII(int[] A) {
        int begin = 0;
        int end = A.length - 1;
        while (end > 0 && begin < A.length - 1) {
            if (A[begin] % 2 == 0) {
                begin += 2;
                continue;
            }
            if (A[end] % 2 != 0) {
                end -= 2;
                continue;
            }
            int temp = A[begin];
            A[begin] = A[end];
            A[end] = temp;
            begin += 2;
            end -= 2;
        }
        return A;
    }

    @Test
    public void test() {
        // int[] A = {4, 2, 5, 7};
        //int[] A = {2, 3};
        int[] A = {2, 0, 3, 4, 1, 3};
        System.out.println(JSONObject.toJSONString(sortArrayByParityII(A)));
    }
}
