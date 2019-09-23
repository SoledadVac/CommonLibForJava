package leetcode.BitManipulation;

import com.alibaba.fastjson.JSONObject;
import org.junit.Test;

/**
 * \* Created: liuhuichao
 * \* Date: 2019/9/23
 * \* Time: 26:37 PM
 * \* Description: 只出现一次的数字 III
 * \
 * 给定一个整数数组 nums，其中恰好有两个元素只出现一次，其余所有元素均出现两次。 找出只出现一次的那两个元素。
 * <p>
 * 示例 :
 * <p>
 * 输入: [1,2,1,3,2,5]
 * 输出: [3,5]
 * 注意：
 * <p>
 * 结果输出的顺序并不重要，对于上面的例子， [5, 3] 也是正确答案。
 * 你的算法应该具有线性时间复杂度。你能否仅使用常数空间复杂度来实现？
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/single-number-iii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class A260_SingleNumberIII {

    public int[] singleNumber(int[] nums) {
        int sign = 0;
        //取得数组中两个唯一数的按位异或结果
        for (int i = 0; i < nums.length; i++) {
            sign ^= nums[i];
        }
        //获取区分两个唯一数的比特位所代表的值
        //也可以写成：sign &= (~sign) + 1
        sign &= -sign;
        int[] result = new int[2];
        //通过标识，区分两个数组
        for (int i = 0; i < nums.length; i++) {
            if ((nums[i] & sign) == sign)
                result[0] ^= nums[i];
            else
                result[1] ^= nums[i];

        }
        return result;
    }

    @Test
    public void test() {
        int[] nums = {1, 2, 1, 3, 2, 5};
        System.out.println(JSONObject.toJSONString(singleNumber(nums)));
    }
}
