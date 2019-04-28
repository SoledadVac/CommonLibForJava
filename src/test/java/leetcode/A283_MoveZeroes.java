package leetcode;

import com.alibaba.fastjson.JSONObject;
import org.junit.Test;

/**
 * \* Created: liuhuichao
 * \* Date: 2019/4/26
 * \* Time: 3:53 PM
 * \* Description: 移动零
 * <p>
 * 给定一个数组 nums，编写一个函数将所有 0 移动到数组的末尾，同时保持非零元素的相对顺序。
 * <p>
 * 示例:
 * <p>
 * 输入: [0,1,0,3,12]
 * 输出: [1,3,12,0,0]
 * 说明:
 * <p>
 * 必须在原数组上操作，不能拷贝额外的数组。
 * 尽量减少操作次数。
 * <p>
 * \
 */
public class A283_MoveZeroes {

    public void moveZeroes(int[] nums) {
        int index = 0;
        for (int j = 1; j < nums.length; j++) {
            if (nums[index] == 0) {
                if (nums[j] != 0) {
                    nums[index] = nums[j];
                    nums[j] = 0;
                    index++;
                }
                continue;
            }
            index++;
        }
    }


    @Test
    public void test() {
        int[] nums = {1,0,1};//0, 1, 0, 3, 12
        moveZeroes(nums);
        System.out.println(JSONObject.toJSONString(nums));
    }
}
