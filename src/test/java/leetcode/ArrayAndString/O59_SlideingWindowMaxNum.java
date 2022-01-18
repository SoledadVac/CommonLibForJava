package leetcode.ArrayAndString;

import io.netty.util.concurrent.DefaultThreadFactory;
import org.junit.Test;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.ReentrantLock;

/**
 * \* Created: liuhuichao
 * \* Date: 2021/3/8
 * \* Time: 28:38 下午
 * \* Description: 滑动窗口的最大值
 * \
 * 给定一个数组 nums 和滑动窗口的大小 k，请找出所有滑动窗口里的最大值。
 * <p>
 * 示例:
 * <p>
 * 输入: nums = [1,3,-1,-3,5,3,6,7], 和 k = 3
 * 输出: [3,3,5,5,6,7]
 * 解释:
 * <p>
 * 滑动窗口的位置                最大值
 * ---------------               -----
 * [1  3  -1] -3  5  3  6  7       3
 * 1 [3  -1  -3] 5  3  6  7       3
 * 1  3 [-1  -3  5] 3  6  7       5
 * 1  3  -1 [-3  5  3] 6  7       5
 * 1  3  -1  -3 [5  3  6] 7       6
 * 1  3  -1  -3  5 [3  6  7]      7
 *  
 * <p>
 * 提示：
 * <p>
 * 你可以假设 k 总是有效的，在输入数组不为空的情况下，1 ≤ k ≤ 输入数组的大小。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/hua-dong-chuang-kou-de-zui-da-zhi-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class O59_SlideingWindowMaxNum {

    /**
     * 暴力算法
     *
     * @param nums
     * @param k
     * @return
     */
    public int[] maxSlidingWindow0(int[] nums, int k) {
        if (nums.length == 0) {
            return new int[0];
        }
        if (nums.length == 1) {
            return new int[]{nums[0]};
        }
        if (k == 1) {
            return nums;
        }
        int resultLength;
        if (k >= nums.length) {
            resultLength = 1;
        } else {
            resultLength = nums.length - k + 1;
        }
        int[] result = new int[resultLength];
        int rIndex = 0;
        for (int i = 0; i <= nums.length - k; i++) {
            int max = nums[i];
            for (int index = i; index < i + k; index++) {
                if (index > nums.length - 1) {
                    break;
                }
                max = Math.max(max, nums[index]);
            }
            result[rIndex++] = max;
        }
        return result;
    }

    public int[] maxSlidingWindow(int[] nums, int k) {
        if (nums.length == 0) {
            return new int[0];
        }
        if (nums.length == 1) {
            return new int[]{nums[0]};
        }
        if (k == 1) {
            return nums;
        }
        int resultLength;
        if (k >= nums.length) {
            resultLength = 1;
        } else {
            resultLength = nums.length - k + 1;
        }
        int[] result = new int[resultLength];
        Deque<Integer> queue = new LinkedList<>();
        //未形成窗口前
        for (int i = 0; i < k; i++) {
            while (!queue.isEmpty() && nums[i] > queue.peekLast()) {
                queue.removeLast();
            }
            queue.add(nums[i]);
        }
        //形成窗口之后


        return result;
    }


    @Test
    public void test() throws InterruptedException {
    /*    int[] nums = {1, 3, -1, -3, 5, 3, 6, 7};
        int k = 3;
        System.out.println(JSONObject.toJSONString(maxSlidingWindow(nums, k)));*/

    }


}
