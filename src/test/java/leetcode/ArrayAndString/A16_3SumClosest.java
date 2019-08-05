package leetcode.ArrayAndString;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * \* Created: liuhuichao
 * \* Date: 2019/3/15
 * \* Time: 5:11 PM
 * \* Description:
 * 给定一个包括 n 个整数的数组 nums 和 一个目标值 target。找出 nums 中的三个整数，使得它们的和与 target 最接近。返回这三个数的和。假定每组输入只存在唯一答案。
 * <p>
 * 例如，给定数组 nums = [-1，2，1，-4], 和 target = 1.
 * <p>
 * 与 target 最接近的三个数的和为 2. (-1 + 2 + 1 = 2).
 * \
 */
public class A16_3SumClosest {

    /**
     * 暴力破解方法
     *
     * @param nums
     * @param target
     * @return
     */
    public int threeSumClosest(int[] nums, int target) {
        Integer sum = null;//用于存放接近的和
        Integer distance = null;
        if (nums.length <= 3) {
            int total = 0;
            for (int i = 0; i < nums.length; i++) {
                total += nums[i];
            }
            return total;
        }
        List<Integer> dataList = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            dataList.add(nums[i]);
        }
        //三指针遍历--艾玛，写着比较简单，就是这时间复杂度，emmmmm......
        for (int begin = 0; begin <= nums.length - 3; begin++) {
            for (int middle = begin + 1; middle <= nums.length - 2; middle++) {
                for (int end = nums.length - 1; end > middle; end--) {
                    int tempSum = nums[begin] + nums[middle] + nums[end];
                    int tempDistence = Math.abs(target - tempSum);
                    if (sum == null) {
                        sum = tempSum;
                        distance = tempDistence;
                    } else {
                        sum = distance < tempDistence ? sum : tempSum;
                        distance = distance < tempDistence ? distance : tempDistence;
                    }
                }
            }
        }
        return sum;
    }


    public int threeSumClosest1(int[] nums, int target) {
        Integer sum = null;//用于存放接近的和
        if (nums.length <= 3) {
            int total = 0;
            for (int i = 0; i < nums.length; i++) {
                total += nums[i];
            }
            return total;
        }



        return sum;
    }

    @Test
    public void test() {
        int nums[] = {0, 2, 1, -3};
        int target = 1;
        System.out.println(threeSumClosest(nums, target));

    }
}
