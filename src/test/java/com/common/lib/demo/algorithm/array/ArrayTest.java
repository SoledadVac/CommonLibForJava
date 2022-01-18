package com.common.lib.demo.algorithm.array;

import com.alibaba.fastjson.JSONObject;
import org.junit.Test;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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

    /**
     * 给定一个含有 M x N 个元素的矩阵（M 行，N 列），请以对角线遍历的顺序返回这个矩阵中的所有元素，对角线遍历如下图所示。
     * <p>
     * 输入:
     * [
     * [ 1(0,0), 2(0,1), 3(0,2) ],
     * [ 4(1,0), 5(1,1), 6(1,2) ],
     * [ 7(2,0), 8(2,1), 9(2,2) ]
     * ]
     * <p>
     * 输出:  [1,2,4,7,5,3,6,8,9]
     *
     * @param matrix
     * @return
     */
    public int[] findDiagonalOrder(int[][] matrix) {
        if (matrix.length == 0) {
            return new int[0];
        }
        int row = matrix.length;
        int col = matrix[0].length;
        int totalNum = row * col;
        if (totalNum == 1) {
            return new int[]{matrix[0][0]};
        }
        int result[] = new int[totalNum];
        int currentRow = 0;
        int currentCol = 0;
        int currentIndex = 0; //标记结果数组中当前放置数据的位置
        //奇数坐标向左下遍历，偶数坐标向右上遍历
        while (currentCol < col && currentRow < row) {
            result[currentIndex] = matrix[currentRow][currentCol];
            if ((currentCol + currentRow) % 2 == 0) {
                //偶数
                if (currentCol == col - 1) {
                    currentRow++;
                } else if (currentRow - 1 < 0) {
                    currentCol++;
                } else {
                    currentRow--;
                    currentCol++;
                }

            } else {
                //奇数坐标
                if (currentRow == row - 1) {
                    currentCol++;
                } else if (currentCol - 1 < 0) {
                    currentRow++;
                } else {
                    currentRow++;
                    currentCol--;
                }
            }
            currentIndex++;
        }
        return result;
    }

    @Test
    public void findDiagonalOrderTest() {
        int[][] matrix = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        System.out.println(JSONObject.toJSONString(findDiagonalOrder(matrix)));
    }

    /**
     * 给定一个包含 m x n 个元素的矩阵（m 行, n 列），请按照顺时针螺旋顺序，返回矩阵中的所有元素。
     * 输入:
     * [
     * [ 1(0,0), 2(0,1), 3(0,2) ],
     * [ 8(1,0), 9(1,1), 4(1,2) ],
     * [ 7(2,0), 6(2,1), 5(2,2) ]
     * ]
     * <p>
     * 输出:  [1,2,3,6,9,8,7,4,5]
     * <p>
     * <p>
     * 输入：
     * [
     * [ 1(0,0),  2(0,1),   3(0,2),  4(0,3),  5(0,4)],
     * [ 16(1,0), 17(1,1),  18(1,2), 19(1,3), 6(1,4)],
     * [ 15(2,0), 24(2,1),  25(2,2), 20(2,3), 7(2,4)],
     * [ 14(3,0), 23(3,1),  22(3,2), 21(3,3), 8(3,4)]
     * [ 13(4,0), 12(4,1),  11(4,2), 10(4,3), 9(4,4)]
     * ]
     * <p>
     * <p>
     * 输入：
     * [
     * [1（0，0）,  2（0，1）,   3（0，2）,  4（0，3）],
     * [10（1，0）, 11（1，1）, 12（1，2）, 5（1，3）],
     * [9（2，0）,  8（2，1）,   7（2，2）,  6（2，3）]
     * ]
     *
     * @param matrix
     * @return
     */
    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> result = new ArrayList<>();
        if (matrix.length == 0) {
            return result;
        }
        int row = matrix.length;
        int col = matrix[0].length;
        int totalNum = row * col;
        if (totalNum == 1) {
            result.add(matrix[0][0]);
            return result;
        }
        int currentRow = 0;
        int currentCol = 0;
        int step = 0;
        int x0 = 0;
        int y0 = 0;
        int x2 = 0;
        int y2 = row - 1;
        while (currentCol < col && currentRow < row) {
            if (result.size() == totalNum) {
                return result;
            }
            //在每一个拐点，算好下一个区间
            if (step == 0) {
                //向右=0
                result.add(matrix[x0][y0]);
                if (y0 == x0) {
                    x2++;

                }


            }
            if (step == 1) {
                //向下=1

            }
            if (step == 2) {
                //向左=2

            }
            if (step == 3) {
                //向上=3

            }
        }
        return result;
    }

    @Test
    public void spiralOrderTest() {
        int[][] matrix = {{1, 2, 3, 4}, {10, 11, 12, 5}, {9, 8, 7, 6}};
        // int[][] matrix = {{1, 2, 3}, {8, 9, 4}, {7, 6, 5}};
        /*int[][] matrix = {
                {1, 2, 3, 4, 5},
                {16, 17, 18, 19, 6},
                {15, 24, 25, 20, 7},
                {14, 23, 22, 21, 8},
                {13, 12, 11, 10, 9}
        };*/
        System.out.println(JSONObject.toJSONString(spiralOrder(matrix)));
    }


    public char[] reverseString(char[] source) {
        int pLeft = 0;
        int pRight = source.length - 1;
        while (pLeft <= pRight) {
            char temp = source[pLeft];
            source[pLeft] = source[pRight];
            source[pRight] = temp;
            pLeft++;
            pRight--;
        }
        return source;
    }

}
