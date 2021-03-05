package leetcode.ArrayAndString;

import com.kenai.jaffl.annotations.In;
import org.junit.Test;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * \* Created: liuhuichao
 * \* Date: 2021/3/4
 * \* Time: 30:07 下午
 * \* Description: 最长连续序列
 * \
 * 给定一个未排序的整数数组 nums ，找出数字连续的最长序列（不要求序列元素在原数组中连续）的长度。
 * <p>
 *  
 * <p>
 * 进阶：你可以设计并实现时间复杂度为 O(n) 的解决方案吗？
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：nums = [100,4,200,1,3,2]
 * 输出：4
 * 解释：最长数字连续序列是 [1, 2, 3, 4]。它的长度为 4。
 * 示例 2：
 * <p>
 * 输入：nums = [0,3,7,2,5,8,4,6,0,1]
 * 输出：9
 *  
 * <p>
 * 提示：
 * <p>
 * 0 <= nums.length <= 104
 * -109 <= nums[i] <= 109
 * <p>
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/longest-consecutive-sequence
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class A128_LongestConsecutiveSequence {

    public int longestConsecutive(int[] nums) {
        //放置结点深度
        int maxLength = 0;
        Set<Integer> datas = new HashSet<>();  // 值 ：index
        //初始化设置
        for (int i = 0; i < nums.length; i++) {
            datas.add(nums[i]);
        }
        //计算每个值的层级
        for (int i = 0; i < nums.length; i++) {
            //以当前值为起始值，往下加1，直到加到某个值不存在时候停止，记录下此时累加的长度
            int v = nums[i] + 1;
            int r = 1;
            while (datas.contains(v)) {
                v++;
                r++;
            }
            if (r > maxLength) {
                maxLength = r;
            }
        }
        return maxLength;
    }

    @Test
    public void test() {
        int[] nums = {100, 4, 200, 1, 3, 2};
        System.out.println(longestConsecutive(nums));
    }


}
