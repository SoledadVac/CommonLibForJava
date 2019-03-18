package com.common.lib.demo.algorithm.array;

import com.alibaba.fastjson.JSONObject;
import org.junit.Test;

import java.math.BigInteger;
import java.util.Arrays;

/**
 * \* Created: liuhuichao
 * \* Date: 2019/3/18
 * \* Time: 6:13 PM
 * \* Description:
 * \
 */
public class ArrayTest {

    /**
     * 给定一个整数类型的数组 nums，请编写一个能够返回数组“中心索引”的方法。
     * <p>
     * 我们是这样定义数组中心索引的：数组中心索引的左侧所有元素相加的和等于右侧所有元素相加的和。
     * <p>
     * 如果数组不存在中心索引，那么我们应该返回 -1。如果数组有多个中心索引，那么我们应该返回最靠近左边的那一个。
     *
     * @param nums
     * @return
     */
    public int pivotIndex(int[] nums) {
        int sumLeft = 0;
        for (int i = 0; i < nums.length; i++) {
            if (i == nums.length - 1) {
                if (sumLeft == 0) {
                    return i;
                }
                return -1;
            }
            int sumRight = 0;
            for (int j = i + 1; j < nums.length; j++) {
                sumRight += nums[j];
            }
            if (sumLeft == sumRight) {
                return i;
            }
            sumLeft += nums[i];
        }
        return -1;
    }

    @Test
    public void pivotIndexTest() {
        int data[] = {1, 7, 3, 6, 5, 6};
        System.out.println(JSONObject.toJSONString(pivotIndex(data)));
    }

    /**
     * 在一个给定的数组nums中，总是存在一个最大元素 。
     * <p>
     * 查找数组中的最大元素是否至少是数组中每个其他数字的两倍。
     * <p>
     * 如果是，则返回最大元素的索引，否则返回-1。
     *
     * @param nums
     * @return
     */
    public int dominantIndex(int[] nums) {
        //只要保证最大的数字是第二大字的两倍，那么其他所有的肯定成立
        if (nums.length == 1) {
            return 0;
        }
        int max = Integer.MIN_VALUE;
        int secondMax = Integer.MIN_VALUE;
        int maxIndex = -1;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > max) {
                secondMax = max;
                max = nums[i];
                maxIndex = i;
            }
            if (nums[i] > secondMax && nums[i] < max) {
                secondMax = nums[i];
            }
        }
        if (secondMax == 0) {
            return maxIndex;
        }
        if (max / secondMax >= 2) {
            return maxIndex;
        }
        return -1;
    }

    @Test
    public void dominantIndexTest() {
        int data[] = {0, 0, 3, 2};
        System.out.println(JSONObject.toJSONString(dominantIndex(data)));

    }

    /**
     * 给定一个由整数组成的非空数组所表示的非负整数，在该数的基础上加一。
     * <p>
     * 最高位数字存放在数组的首位， 数组中每个元素只存储一个数字。
     * <p>
     * 你可以假设除了整数 0 之外，这个整数不会以零开头。
     *
     * @param digits
     * @return
     */
    public int[] plusOne(int[] digits) {
        int jinWei = 1;
        for (int i = digits.length - 1; i >= 0; i--) {
            int sum = digits[i] + jinWei;
            if (sum / 10 == 0) {
                digits[i] = sum;
                return digits;
            } else {
                jinWei = sum / 10;
                digits[i] = sum % 10;
            }
        }
        if (jinWei != 0) {
            int[] newArray = new int[digits.length + 1];
            newArray[0] = jinWei;
            for (int i = 1; i <= digits.length; i++) {
                newArray[i] = digits[i - 1];
            }
            return newArray;
        }
        return digits;
    }

    @Test
    public void plusOneTest() {
        int data[] = {9, 9, 9, 9};
        System.out.println(JSONObject.toJSONString(plusOne(data)));
    }

}
