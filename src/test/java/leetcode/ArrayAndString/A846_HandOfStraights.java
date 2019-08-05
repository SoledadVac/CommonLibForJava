package leetcode.ArrayAndString;

import org.junit.Test;

import java.util.*;

/**
 * \* Created: liuhuichao
 * \* Date: 2019/3/13
 * \* Time: 4:30 PM
 * \* Description: Tag=Map
 * <p>
 * 爱丽丝有一手（hand）由整数数组给定的牌。
 * <p>
 * 现在她想把牌重新排列成组，使得每个组的大小都是 W，且由 W 张连续的牌组成。
 * <p>
 * 如果她可以完成分组就返回 true，否则返回 false。
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：hand = [1,2,3,6,2,3,4,7,8], W = 3
 * 输出：true
 * 解释：爱丽丝的手牌可以被重新排列为 [1,2,3]，[2,3,4]，[6,7,8]。
 * 示例 2：
 * <p>
 * 输入：hand = [1,2,3,4,5], W = 4
 * 输出：false
 * 解释：爱丽丝的手牌无法被重新排列成几个大小为 4 的组。
 * <p>
 * <p>
 * 提示：
 * <p>
 * 1 <= hand.length <= 10000
 * 0 <= hand[i] <= 10^9
 * 1 <= W <= hand.length
 * <p>
 * <p>
 * <p>
 * \
 */
public class A846_HandOfStraights {

    public boolean isNStraightHand(int[] hand, int W) {
        int length = hand.length;
        if (length % W != 0) {
            return false;
        }
        Arrays.sort(hand);
        Map<Integer, Integer> recordMap = new HashMap<>();
        for (int i : hand) {
            recordMap.put(i, recordMap.getOrDefault(i, 0) + 1);
        }
        for (int data : hand) {
            if (recordMap.get(data) > 0) {
                for (int j = 0; j < W; j++) {
                    if (recordMap.getOrDefault(data + j,0) > 0) {
                        recordMap.put(data + j, recordMap.get(data + j) - 1);
                    } else {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    @Test
    public void test() {
        /*int[] hand = {1, 2, 3, 6, 2, 3, 4, 7, 8};
        int W = 3;*/
        int[] hand = {5,1};
        int W = 2;
        boolean result=isNStraightHand(hand,W);
        System.out.println(result);
    }


}
