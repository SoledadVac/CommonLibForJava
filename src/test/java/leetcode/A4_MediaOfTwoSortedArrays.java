package leetcode;

import com.alibaba.fastjson.JSONObject;
import org.junit.Test;

/**
 * \* Created: liuhuichao
 * \* Date: 2019/2/17
 * \* Time: 5:37 PM
 * \* Description:
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
     * 获取数组的中位数
     *
     * @param num
     * @return
     */
    private double findMidNum(int[] num) {
        int length = num.length;
        if (length == 0) {
            return 0;
        }
        if (length % 2 == 0) {
            //总长度为偶数个
            int t1 = num[length / 2];
            int t2 = num[length / 2 - 1];
            return (t1 + t2) / 2.0;
        } else {
            //总长度为奇数个
            return num[length / 2];
        }
    }

    private double findMidNumAdd(int[] num, int length) {
        if (length % 2 == 0) {
            // 1   //   2,3,4
            //总长度为偶数个
            int t1 = num[length / 2 - 1];
            int t2 = num[length / 2];
            return (t1 + t2) / 2.0;
        } else {
            // 1   //   2,3,4,5
            //总长度为奇数个
            return num[length / 2];
        }
    }

    private int[] getNumBetween(int[] num, int begin, int end) {
        if (begin == 0 && end == 0) {
            return num;
        }
        int[] newNum = new int[end - begin + 1];
        int j = 0;
        for (int i = begin; i <= end; i++) {
            newNum[j++] = num[i];
        }
        return newNum;
    }

    /**
     * 减小程序的时间复杂度
     * --------折半查找中位数
     * <p>
     * A：{11}
     * B：{6}
     *
     * @param nums1
     * @param nums2
     * @return
     */
    public double findMedianSortedArrays1(int[] nums1, int[] nums2) {
        int totalLength = nums1.length + nums2.length;
        int[] temp;
        //保持num2长度较长
        if (nums1.length > nums2.length) {
            temp = nums1;
            nums1 = nums2;
            nums2 = temp;
        }
        //如果是偶数，留下了两位
        for (int i = 0; i < nums1.length; i++) {




        }


        //如果是奇数，留下了一位


        return 0;
    }

    @Test
    public void test() {
        int[] nums1 = {1, 2, 4};
        int[] nums2 = {3, 11, 12};
        double result = findMedianSortedArrays1(nums1, nums2);
        System.out.println("result = " + result);
    }

}
