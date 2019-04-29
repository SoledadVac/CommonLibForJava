package leetcode;

import org.junit.Test;

/**
 * \* Created: liuhuichao
 * \* Date: 2019/4/29
 * \* Time: 5:47 PM
 * \* Description: X的平方根
 * \
 * 实现 int sqrt(int x) 函数。
 * <p>
 * 计算并返回 x 的平方根，其中 x 是非负整数。
 * <p>
 * 由于返回类型是整数，结果只保留整数的部分，小数部分将被舍去。
 * <p>
 * 示例 1:
 * <p>
 * 输入: 4
 * 输出: 2
 * 示例 2:
 * <p>
 * 输入: 8
 * 输出: 2
 * 说明: 8 的平方根是 2.82842...,
 * 由于返回类型是整数，小数部分将被舍去。
 */
public class A69_SqrtX {

    /**
     * 感觉这个就是在1到X的范围内，折半查找平方X的正整数
     *
     * @param x
     * @return
     */
    public int mySqrt(int x) {
        if (x <= 0) {
            return 0;
        }
        int begin = 1;
        int end = x;
        while (end >= begin) {
            int mid = (begin + end) / 2;
            int xSq = Double.valueOf(Math.sqrt(x)).intValue();
            if (mid == xSq) {
                return mid;
            } else if (mid > xSq) {
                end = mid - 1;
            } else {
                begin = mid + 1;
            }
        }
        return 0;
    }


    @Test
    public void test() {
        int x = 7; // 1,2,3,4,5,6,7,8
        System.out.println("result = " + mySqrt(x));
    }
}
