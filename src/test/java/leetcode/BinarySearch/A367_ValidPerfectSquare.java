package leetcode.BinarySearch;

import org.junit.Test;

/**
 * \* Created: liuhuichao
 * \* Date: 2019/5/22
 * \* Time: 29:52 PM
 * \* Description:有效的完全平方数
 * \
 * 给定一个正整数 num，编写一个函数，如果 num 是一个完全平方数，则返回 True，否则返回 False。
 * <p>
 * 说明：不要使用任何内置的库函数，如  sqrt。
 * <p>
 * 示例 1：
 * <p>
 * 输入：16
 * 输出：True
 * 示例 2：
 * <p>
 * 输入：14
 * 输出：False
 */
public class A367_ValidPerfectSquare {
    public boolean isPerfectSquare(int num) {
        if (num < 0) {
            return false;
        }
        int left = 1;
        int right = num;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (Math.pow(mid, 2) == num) {
                return true;
            } else if (Math.pow(mid, 2) > num) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return false;

    }

    @Test
    public void test() {
        int num = 2147395600; //46340.00
        System.out.println(isPerfectSquare(num));
    }

}
