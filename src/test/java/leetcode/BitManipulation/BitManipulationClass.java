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


    /**
     * 数字转换为16进制
     *
     * @param num
     * @return
     */
    public String toHex(int num) {
        if (num == 0) {
            return "0";
        }
        String code = "";
        String hex = "0123456789abcdef";
        for (int i = 7; i >= 0; i--) {
            int move = 4 * i;
            int val = num >> (move) & 0xf;
            code += hex.charAt(val);
        }
        return code.replaceFirst("^0*", "");
    }

    /**
     * 数字的补码
     * 与从最高位到最低位均是1的数做异或
     * 只要找到比num最高1位的位置高一位（左边）就行了，此时该数必然大于num，
     * 如num=5（101B）时，这个数为8（1000B），
     * 将其减1，则得到与num有效二进制位数相同且各位都为1的数。如8-1=7（111B）
     * @param num
     * @return
     */
    public int findComplement(int num){
        long num0 = 1;

        while(num0 <= num)
            num0 = num0 << 1;

        num0 -= 1;

        return (int)num0 ^ num;
    }

    /**
     * 数字的补码
     * 反码 & （最高位是0，第二位开始均为1）
     * 0000 0101
     * 1111 1010
     * 1111 1010 - 1 = 1111   1001
     * 1111 1001 取反 = 1（符号位） 0000 0110 ( - 6)
     * 1... 0000 0110 & 0000 0011 = 0... 0000 0010 ( 2 )
     * @param num
     * @return
     */
    public int findComplement1(int num){
        int tmp = num,bit = 0;
        while(tmp > 0 ){
            tmp >>= 1;
            if(bit == 0)
                bit = 1;
            else
                bit = bit << 1;
        }
        return ~num & (bit - 1);
    }

    @Test
    public void testconsoleIntegerByte() {
        int num = -1;
        int[] result = consoleIntegerByte(num);
        System.out.println(JSONObject.toJSONString(result));
        // int numcopy = consoleIntegerFromByte(result);
        //System.out.println("numcopy = " + numcopy);
    }
}
