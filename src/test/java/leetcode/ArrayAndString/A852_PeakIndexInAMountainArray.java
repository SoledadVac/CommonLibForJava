package leetcode.ArrayAndString;

import org.junit.Test;

/**
 * \* Created: liuhuichao
 * \* Date: 2021/6/15
 * \* Time: 35:23 上午
 * \* Description: Peak Index in a Mountain Array
 * \
 */
public class A852_PeakIndexInAMountainArray {
    public int peakIndexInMountainArray(int[] arr) {
        int left = 0;
        int right = left + 2;
        while (left <= right && right <= arr.length - 1) {
            if (left == right) {
                return left;
            }
            int mid = (right - left) / 2 + left;
            if (arr[mid] > arr[left] && arr[mid] > arr[right]) {
                return mid;
            }
            if (arr[left] < arr[mid] && arr[mid] < arr[right]) {
                //上升区间
                left = mid;
                right++;
            }
        }
        return -1;
    }

    @Test
    public void test() {
        int[] arr = {24,69,100,99,79,78,67,36,26,19};
        System.out.println(peakIndexInMountainArray(arr));
    }
}
