package leetcode.BitManipulation;

import org.junit.Test;

/**
 * \* Created: liuhuichao
 * \* Date: 2019/9/23
 * \* Time: 28:53 PM
 * \* Description: 汉明距离总和
 * \
 * 两个整数的 汉明距离 指的是这两个数字的二进制数对应位不同的数量。
 * <p>
 * 计算一个数组中，任意两个数之间汉明距离的总和。
 * <p>
 * 示例:
 * <p>
 * 输入: 4, 14, 2
 * <p>
 * 输出: 6
 * <p>
 * 解释: 在二进制表示中，4表示为0100，14表示为1110，2表示为0010。（这样表示是为了体现后四位之间关系）
 * 所以答案为：
 * HammingDistance(4, 14) + HammingDistance(4, 2) + HammingDistance(14, 2) = 2 + 2 + 2 = 6.
 * 注意:
 * <p>
 * 数组中元素的范围为从 0到 10^9。
 * 数组的长度不超过 10^4。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/total-hamming-distance
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class A477_TotalHammingDistance {
    public int hammingDistance(int x, int y) {
        int result = 0;
        for (int i = 0; i < 32; i++) {
            int vx = x >> i & 1;
            int vy = y >> i & 1;
            if (vx != vy) {
                result++;
            }
        }
        return result;
    }

    /**
     * 太暴力了，超时了，艾玛
     *
     * @param nums
     * @return
     */
    public int totalHammingDistance0(int[] nums) {
        int res = 0;
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                res += hammingDistance(nums[i], nums[j]);
            }
        }
        return res;
    }

    public int totalHammingDistance(int[] nums) {
        int res = 0;
        for (int i = 0; i < 32; i++) {
            int zeroNum = 0;
            int oneNumm = 0;
            for (int j = 0; j < nums.length; j++) {
                int v = nums[j] >> i & 1;
                if (v == 0) {
                    zeroNum++;
                } else {
                    oneNumm++;
                }
            }
            res += zeroNum * oneNumm;
        }
        return res;
    }

    @Test
    public void test() {
        int[] nums = {4, 14, 2};
        System.out.println("result = " + totalHammingDistance(nums));
    }
}
