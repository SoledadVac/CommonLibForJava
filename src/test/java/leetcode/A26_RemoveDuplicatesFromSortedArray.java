package leetcode;

import com.alibaba.fastjson.JSONObject;
import org.junit.Test;

/**
 * \* Created: liuhuichao
 * \* Date: 2019/4/24
 * \* Time: 5:12 PM
 * \* Description: 删除排序数组中的重复项
 * <p>
 * 给定一个排序数组，你需要在原地删除重复出现的元素，使得每个元素只出现一次，返回移除后数组的新长度。
 * <p>
 * 不要使用额外的数组空间，你必须在原地修改输入数组并在使用 O(1) 额外空间的条件下完成。
 * <p>
 * 示例 1:
 * <p>
 * 给定数组 nums = [1,1,2],
 * <p>
 * 函数应该返回新的长度 2, 并且原数组 nums 的前两个元素被修改为 1, 2。
 * <p>
 * 你不需要考虑数组中超出新长度后面的元素。
 * 示例 2:
 * <p>
 * 给定 nums = [0,0,1,1,1,2,2,3,3,4],
 * <p>
 * 函数应该返回新的长度 5, 并且原数组 nums 的前五个元素被修改为 0, 1, 2, 3, 4。
 * <p>
 * 你不需要考虑数组中超出新长度后面的元素。
 * 说明:
 * <p>
 * 为什么返回数值是整数，但输出的答案是数组呢?
 * <p>
 * 请注意，输入数组是以“引用”方式传递的，这意味着在函数里修改输入数组对于调用者是可见的。
 * <p>
 * 你可以想象内部操作如下:
 * <p>
 * // nums 是以“引用”方式传递的。也就是说，不对实参做任何拷贝
 * int len = removeDuplicates(nums);
 * <p>
 * // 在函数里修改输入数组对于调用者是可见的。
 * // 根据你的函数返回的长度, 它会打印出数组中该长度范围内的所有元素。
 * for (int i = 0; i < len; i++) {
 * print(nums[i]);
 * }
 * <p>
 * \
 */
public class A26_RemoveDuplicatesFromSortedArray {

    /**
     * 额，空间复杂度满足了，但是，时间复杂度太大，超出时间限制，废弃了。。往下看
     *
     * @param nums
     * @return
     */
    public int removeDuplicates(int[] nums) {
        int totalLength = nums.length;
        int begin = 0;
        int end = begin + 1;
        while (end > begin && end < totalLength) {
            if (nums[begin] == nums[end]) {
                //数组向前平移
                for (int i = end; i < totalLength; i++) {
                    if (i == totalLength - 1) {
                        continue;
                    }
                    nums[i] = nums[i + 1];
                }
                totalLength = totalLength - (end - begin);
                if (nums[begin] == nums[end]) {
                    continue;
                }
                begin = end;
                end = begin + 1;
            } else {
                end++;
            }
            if (end == nums.length - 1) {
                end = begin + 1;
            }
        }
        return totalLength;
    }

    /**
     * 减少移动次数，降低时间复杂度
     *
     * @param nums
     * @return
     */
    public int removeDuplicates0(int[] nums) {
        int totalLength = nums.length;
        int begin = 0;
        int end = begin + 1;
        boolean isFind = false;
        while (end > begin && end < totalLength) {
            if (nums[begin] == nums[end]) {
                if (end == totalLength - 1) {
                    totalLength = totalLength - (end - begin);
                    break;
                }
                isFind = true;
                end++;
                continue;
            }
            if (nums[begin] != nums[end] && isFind) {
                isFind = false;
                moveData(nums, end, begin + 1, totalLength - 1);
                totalLength = totalLength - (end - 1 - begin);
                begin++;
                end = begin + 1;
                continue;
            }
            end++;
            if (end == totalLength - 1) {
                if (isFind) {
                    totalLength = totalLength - (end - begin);
                }
                begin++;
                end = begin + 1;
            }
        }
        return totalLength;
    }

    /**
     * 数组本身的平移
     *
     * @param nums             源数组
     * @param moveBeginIndex   从index将数组往前移动
     * @param moveToBeginIndex 移动到的起始位置
     * @param endIndex         本数组移动的终止位置
     * @return
     */
    private int[] moveData(int[] nums, int moveBeginIndex, int moveToBeginIndex, int endIndex) {
        int moveStep = moveBeginIndex - moveToBeginIndex;
        for (int i = moveBeginIndex; i <= endIndex; i++) {
            nums[i - moveStep] = nums[i];
        }
        return nums;
    }

    @Test
    public void testremoveData() {
        int[] nums = {0, 0, 0, 1, 1, 2, 2, 3, 3, 4};
        int[] result = moveData(nums, 3, 1, 9);
        System.out.println("result = " + JSONObject.toJSONString(result));
    }

    @Test
    public void test() {
        int[] nums = {0, 0, 0, 1, 1, 2, 2, 3, 3, 4}; //5 //0, 0, 0, 1, 1, 2, 2, 3, 3, 4
        System.out.println("result = " + removeDuplicates0(nums));
        System.out.println("result = " + JSONObject.toJSONString(nums));
    }
}
