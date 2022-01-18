package leetcode.Random;

import org.apache.hadoop.yarn.webapp.hamlet.Hamlet;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * \* Created: liuhuichao
 * \* Date: 2021/6/15
 * \* Time: 4:56 下午
 * \* Description:
 * \
 */
public class A527_RandomPickWithWeight {

    class Solution {
        List<Integer> pSumList = new ArrayList<>();
        int total = 0;

        public Solution(int[] w) {
            for (int v : w) {
                total += v;
                pSumList.add(total);
            }
        }

        public int pickIndex() {
            int r = new Random().nextInt(total);
            int left = 0;
            int right = pSumList.size() - 1;
            while (left < right) {
                int mid = (left + right) / 2;
                if (r >= pSumList.get(mid)) {
                    left = mid + 1;
                } else {
                    right = mid;
                }
            }
            return left;
        }
    }

    @Test
    public void test() {
        Solution solution = new Solution(new int[]{1, 3});
        System.out.println(solution.pickIndex());
    }
}
