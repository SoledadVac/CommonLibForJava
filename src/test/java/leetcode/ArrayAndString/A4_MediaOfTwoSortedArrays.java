package leetcode.ArrayAndString;

import org.junit.Test;

/**
 * \* Created: liuhuichao
 * \* Date: 2019/2/17
 * \* Time: 5:37 PM
 * \* Description: 寻找两个有序数组的中位数
 * <p>
 * 给定两个大小为 m 和 n 的有序数组 nums1 和 nums2。
 * <p>
 * 请你找出这两个有序数组的中位数，并且要求算法的时间复杂度为 O(log(m + n))。
 * <p>
 * 你可以假设 nums1 和 nums2 不会同时为空。
 * <p>
 * 示例 1:
 * <p>
 * nums1 = [1, 3]
 * nums2 = [2]
 * <p>
 * 则中位数是 2.0
 * 示例 2:
 * <p>
 * nums1 = [1, 2]
 * nums2 = [3, 4]
 * <p>
 * 则中位数是 (2 + 3)/2 = 2.5
 */
public class A4_MediaOfTwoSortedArrays {

    /**
     * 先写个简单的，不考虑时间复杂度的
     * 假设数组长度为n,则下标为 0 ~ n-1;
     * 对于奇数长度的数组，中位数的下标为 ： n / 2
     * 对于偶数长度的数组，中位数的下标为 ： n / 2 和 (n / 2 -1) 这两个数的均值
     *
     * @param nums1
     * @param nums2
     * @return
     */
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int resultArrayLength = (nums1.length + nums2.length) / 2 + 1; //无需取两个长度相加
        int[] resultData = new int[resultArrayLength];
        //从两个数组中从小到大查找中位数
        int nums1Index = 0, nums2Index = 0;
        int resultIndex = 0;
        while (resultIndex < resultArrayLength) {
            if (nums1Index < nums1.length && nums2Index < nums2.length) {
                if (nums1[nums1Index] < nums2[nums2Index]) {
                    resultData[resultIndex++] = nums1[nums1Index];
                    nums1Index++;
                } else {
                    resultData[resultIndex++] = nums2[nums2Index];
                    nums2Index++;
                }
                continue;
            }
            if (nums1Index < nums1.length) {
                resultData[resultIndex++] = nums1[nums1Index];
                nums1Index++;
                continue;
            }
            if (nums2Index < nums2.length) {
                resultData[resultIndex++] = nums2[nums2Index];
                nums2Index++;
                continue;
            }
        }
        if ((nums1.length + nums2.length) % 2 == 0) {
            //总长度为偶数个
            int t1 = resultData[(nums1.length + nums2.length) / 2];
            int t2 = resultData[(nums1.length + nums2.length) / 2 - 1];
            return (t1 + t2) / 2.0;
        } else {
            //总长度为奇数个
            return resultData[(nums1.length + nums2.length) / 2];
        }
    }

    /**
     * 减小程序的时间复杂度
     * --------折半查找中位数
     * <p>
     * <p>
     * A：{1，2，4}
     * B：{3，11，12}
     * <p>
     * A：{1，6}
     * B：{3，4，5}
     * <p>
     * A：{1，2，3}
     * B：{4，5，6}
     *
     * @param A
     * @param B
     * @return
     */
    public double findMedianSortedArrays1(int[] A, int[] B) {
        int m = A.length;
        int n = B.length;
        if (m > n) { // to ensure m<=n
            int[] temp = A;
            A = B;
            B = temp;
            int tmp = m;
            m = n;
            n = tmp;
        }
        int iMin = 0, iMax = m, halfLen = (m + n + 1) / 2;
        while (iMin <= iMax) {
            int i = (iMin + iMax) / 2;
            int j = halfLen - i;
            if (i < iMax && B[j - 1] > A[i]) {
                iMin = i + 1; // i is too small
            } else if (i > iMin && A[i - 1] > B[j]) {
                iMax = i - 1; // i is too big
            } else { // i is perfect
                int maxLeft = 0;
                if (i == 0) {
                    maxLeft = B[j - 1];
                } else if (j == 0) {
                    maxLeft = A[i - 1];
                } else {
                    maxLeft = Math.max(A[i - 1], B[j - 1]);
                }
                if ((m + n) % 2 == 1) {
                    return maxLeft;
                }

                int minRight = 0;
                if (i == m) {
                    minRight = B[j];
                } else if (j == n) {
                    minRight = A[i];
                } else {
                    minRight = Math.min(B[j], A[i]);
                }

                return (maxLeft + minRight) / 2.0;
            }
        }
        return 0.0;
    }

    @Test
    public void test() {
       /* int[] nums1 = {1, 2, 4};
        int[] nums2 = {3, 11, 12};*/
        int[] nums1 = {1, 6};
        int[] nums2 = {3, 4, 5};
        double result = findMedianSortedArrays1(nums1, nums2);
        System.out.println("result = " + result);
    }

}
