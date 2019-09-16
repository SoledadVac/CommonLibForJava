package leetcode.BitManipulation;

import com.alibaba.fastjson.JSONObject;
import org.junit.Test;


/**
 * \* Created: liuhuichao
 * \* Date: 2019/9/16
 * \* Time: 27:47 PM
 * \* Description: 常用位运算
 * \
 */
public class BitManipulationClass {

    /**
     * 逐位输出整数的每一位
     *
     * @param num
     * @return
     */
    public int[] consoleIntegerByte(int num) {
        int[] result = new int[32];
        for (int i = 31; i >= 0; i--) {
            result[31 - i] = num >> i & 1;
        }
        return result;
    }

    /**
     * 将一个byte数组转换成整数
     *
     * @param source
     * @return
     */
    public int consoleIntegerFromByte(int[] source) {
        int res = 0;
        for (int i = 0; i < 32; i++) {
            res = source[i] << (31 - i) | res;
        }
        return res;
    }

    /**
     * 二进制翻转
     * 0010 --> 0100
     *
     * @param n
     * @return
     */
    public int reverseBits(int n) {
        int result = 0;
        for (int i = 0; i < 32; i++) {
            int val = n >> i & 1;
            result = val << (31 - i) | result;
        }
        return result;
    }


    @Test
    public void testconsoleIntegerByte() {
        int num = 2;
        int[] result = consoleIntegerByte(num);
        System.out.println(JSONObject.toJSONString(result));
        int numcopy = consoleIntegerFromByte(result);
        System.out.println("numcopy = " + numcopy);
    }
}
