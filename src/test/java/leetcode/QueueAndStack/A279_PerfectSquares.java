package leetcode.QueueAndStack;

import org.junit.Test;

import java.util.ArrayDeque;
import java.util.LinkedList;
import java.util.Queue;


/**
 * \* Created: liuhuichao
 * \* Date: 2019/7/10
 * \* Time: 35:50 AM
 * \* Description: 完全平方数
 * \
 * 给定正整数 n，找到若干个完全平方数（比如 1, 4, 9, 16, ...）使得它们的和等于 n。你需要让组成和的完全平方数的个数最少。
 * <p>
 * 示例 1:
 * <p>
 * 输入: n = 12
 * 输出: 3
 * 解释: 12 = 4 + 4 + 4
 * 示例 2:
 * <p>
 * 输入: n = 13
 * 输出: 2
 * 解释: 13 = 4 + 9
 * <p>
 */
public class A279_PerfectSquares {

    public int numSquares(int n) {
        Queue<Integer> q = new ArrayDeque<>();
        q.add(n);
        int step = 0;
        while (!q.isEmpty()) {
            step++;
            int size = q.size();
            for (int j = 0; j < size; j++) {
                int item = q.poll();
                int endLine = (int) Math.sqrt(item);
                for (int i = 1; i <= endLine; i++) {
                    if (i * i <= item) {
                        if (item - i * i == 0) {
                            return step;
                        }
                        q.add(item - i * i);
                    }
                }
            }
        }
        return -1;
    }

    @Test
    public void test() {
        long begin = System.currentTimeMillis();
        int n = 7168;
        System.out.println("result= " + numSquares(n));
        long end = System.currentTimeMillis();
        System.out.println("time = " + (end - begin));
    }

    public boolean isCanSqurt(int n) {
        int s = (int) Math.sqrt(n);
        return s * s == n;
    }

    @Test
    public void test1() {


    }
}
