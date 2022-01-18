package leetcode.Sort;

import com.alibaba.fastjson.JSONObject;
import org.junit.Test;

import java.util.Arrays;

/**
 * \* Created: liuhuichao
 * \* Date: 2021/3/25
 * \* Time: 31:51 下午
 * \* Description: 最小K个数
 * \
 */
public class M_17_14_SmallestKLCCI {

    public int[] smallestK0(int[] arr, int k) {
        int[] result = new int[k];
        if (k == 0 || arr.length == 0) {
            return result;
        }
        Arrays.sort(arr);
        System.arraycopy(arr, 0, result, 0, k);
        return result;
    }


    public int[] smallestK(int[] arr, int k) {
        int[] result = new int[k];
        if (k == 0 || arr.length == 0) {
            return result;
        }
        

        return result;
    }

    @Test
    public void test() {
        int[] arr = {1, 3, 5, 7, 2, 4, 6, 8}; //[1,2,3,4]
        int k = 4;
        System.out.println(JSONObject.toJSONString(smallestK(arr, k)));

    }
}
