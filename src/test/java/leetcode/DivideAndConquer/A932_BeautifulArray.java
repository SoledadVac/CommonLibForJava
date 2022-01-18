package leetcode.DivideAndConquer;

import com.alibaba.fastjson.JSONObject;
import com.kenai.jaffl.annotations.In;
import org.assertj.core.util.Lists;
import org.junit.Test;

import java.util.*;

/**
 * \* Created: liuhuichao
 * \* Date: 2020/1/28
 * \* Time: 3:23 下午
 * \* Description: 漂亮数组
 * \
 * 对于某些固定的 N，如果数组 A 是整数 1, 2, ..., N 组成的排列，使得：
 * <p>
 * 对于每个 i < j，都不存在 k 满足 i < k < j 使得 A[k] * 2 = A[i] + A[j]。
 * <p>
 * 那么数组 A 是漂亮数组。
 * <p>
 *  
 * <p>
 * 给定 N，返回任意漂亮数组 A（保证存在一个）。
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * 输入：4
 * 输出：[2,1,4,3]
 * 示例 2：
 * <p>
 * 输入：5
 * 输出：[3,1,2,5,4]
 *  
 * <p>
 * 提示：
 * <p>
 * 1 <= N <= 1000
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/beautiful-array
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class A932_BeautifulArray {

    public int[] beautifulArray(int N) {
        //build data
        int[] result = new int[N];  //1...N
        for (int i = 1; i <= N; i++) {
            result[i - 1] = i;
        }
        if (N == 1 || N == 2) {
            return result;
        }

        return result;
    }


    @Test
    public void test() {
        /*int N = 4;
        System.out.println(JSONObject.toJSONString(beautifulArray(N)));*/
    }
}
