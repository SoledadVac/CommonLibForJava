package leetcode.DivideAndConquer;

import org.junit.Test;

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Random;

/**
 * \* Created: liuhuichao
 * \* Date: 2020/1/27
 * \* Time: 11:32 上午
 * \* Description: 数组中的第K个最大元素
 * \
 * 在未排序的数组中找到第 k 个最大的元素。请注意，你需要找的是数组排序后的第 k 个最大的元素，而不是第 k 个不同的元素。
 * <p>
 * 示例 1:
 * <p>
 * 输入: [3,2,1,5,6,4] 和 k = 2
 * 输出: 5
 * 示例 2:
 * <p>
 * 输入: [3,2,3,1,2,4,5,5,6] 和 k = 4
 * 输出: 4
 * 说明:
 * <p>
 * 你可以假设 k 总是有效的，且 1 ≤ k ≤ 数组的长度。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/kth-largest-element-in-an-array
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class A215_KthLargestElementInAnArray {

    /**
     * 先排序直接取
     *
     * @param nums
     * @param k
     * @return
     */
    public int findKthLargest0(int[] nums, int k) {
        Arrays.sort(nums);
        return nums[nums.length - k];
    }

    /**
     * 小顶堆
     *
     * @param nums
     * @param k
     * @return
     */
    public int findKthLargest1(int[] nums, int k) {
        PriorityQueue<Integer> queue = new PriorityQueue<>(k);
        for (int i = 0; i < nums.length; i++) {
            queue.add(nums[i]);
            if (queue.size() > k) {
                queue.poll();
            }
        }
        return queue.poll();
    }


    int[] nums;

    /**
     * 分治法
     * 寻找length - k 位置的数字
     *
     * @param
     * @param k
     * @return
     */
    public int findKthLargest(int[] numArr, int k) {
        this.nums = numArr;
        return quickSelect(0, nums.length - 1, nums.length - k);
    }

    /**
     * 快速选择法
     *
     * @param left
     * @param right
     * @param smallK
     * @return
     */
    private int quickSelect(int left, int right, int smallK) {
        if (left == right) {
            return nums[left];
        }
        Random random_num = new Random();
        int pivotIndex = left + random_num.nextInt(right - left);
        pivotIndex = partition(left, right, pivotIndex);
        if (pivotIndex == smallK) {
            return nums[pivotIndex];
        } else if (smallK > pivotIndex) {
            return quickSelect(pivotIndex + 1, right, smallK);
        }
        return quickSelect(left, pivotIndex - 1, smallK);
    }

    /**
     * 分区计算
     *
     * @param left
     * @param right
     * @param pivotIndex
     * @return 调整到，左边值<=分隔位 && 右边值>=分隔位，然后返回分隔位的位置
     * <p>
     * 3, 2, 1, 5, 6, 4 ，pick 1 ,value =2
     */
    private int partition(int left, int right, int pivotIndex) {
        int pivot = this.nums[pivotIndex]; //随机选定分割数字的值
        swip(right, pivotIndex); //把先定位移动到最右边
        int result = left; //起始位
        //将小于pivot的元素都交换到前面去
        for (int i = left; i <= right; i++) {
            if (nums[i] < pivot) {
                swip(i, result);
                result++;
            }
        }
        swip(result, right);
        return result; //返回此时的分割元素位置
    }

    private void swip(int index1, int index2) {
        int v1 = this.nums[index1];
        this.nums[index1] = this.nums[index2];
        this.nums[index2] = v1;
    }

    @Test
    public void test() {
        int[] nums = {3, 2, 1, 5, 6, 4};
        int k = 2;
        System.out.println(findKthLargest(nums, k));//5
    }
}
