package leetcode.HashTable;

import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

/**
 * \* Created: liuhuichao
 * \* Date: 2019/8/19
 * \* Time: 27:03 PM
 * \* Description: 快乐数
 * \编写一个算法来判断一个数是不是“快乐数”。
 * <p>
 * 一个“快乐数”定义为：对于一个正整数，每一次将该数替换为它每个位置上的数字的平方和，然后重复这个过程直到这个数变为 1，
 * 也可能是无限循环但始终变不到 1。如果可以变为 1，那么这个数就是快乐数。
 * <p>
 * 示例: 
 * <p>
 * 输入: 19
 * 输出: true
 * 解释:
 * 1^2 + 9^2 = 82
 * 8^2 + 2^2 = 68
 * 6^2 + 8^2 = 100
 * 1^2 + 0^2 + 0^2 = 1
 */
public class A202_HappyNumber {

    public boolean isHappy(int n) {
        Set<Integer> data = new HashSet<>();
        while (!data.contains(n)) {
            data.add(n);
            int sum = 0;
            while (sum == 0 || n / 10 != 0) {
                int a = (n % 10) * (n % 10);
                sum += a;
                n = n / 10;
                if (n < 10) {
                    sum += n * n;
                }
            }
            if (sum == 1) {
                return true;
            } else {
                n = sum;
            }
        }
        return false;
    }

    @Test
    public void test() {
        int n = 3;
        System.out.println("result = " + isHappy(n));
    }
}
