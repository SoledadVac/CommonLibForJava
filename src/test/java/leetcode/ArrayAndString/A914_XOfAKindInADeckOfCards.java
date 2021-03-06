package leetcode.ArrayAndString;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * \* Created: liuhuichao
 * \* Date: 2020/6/30
 * \* Time: 28:14 下午
 * \* Description: 卡牌分组
 * \
 * 给定一副牌，每张牌上都写着一个整数。
 * <p>
 * 此时，你需要选定一个数字 X，使我们可以将整副牌按下述规则分成 1 组或更多组：
 * <p>
 * 每组都有 X 张牌。
 * 组内所有的牌上都写着相同的整数。
 * 仅当你可选的 X >= 2 时返回 true。
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * 输入：[1,2,3,4,4,3,2,1]
 * 输出：true
 * 解释：可行的分组是 [1,1]，[2,2]，[3,3]，[4,4]
 * 示例 2：
 * <p>
 * 输入：[1,1,1,2,2,2,3,3]
 * 输出：false
 * 解释：没有满足要求的分组。
 * 示例 3：
 * <p>
 * 输入：[1]
 * 输出：false
 * 解释：没有满足要求的分组。
 * 示例 4：
 * <p>
 * 输入：[1,1]
 * 输出：true
 * 解释：可行的分组是 [1,1]
 * 示例 5：
 * <p>
 * 输入：[1,1,2,2,2,2]
 * 输出：true
 * 解释：可行的分组是 [1,1]，[2,2]，[2,2]
 * <p>
 * 提示：
 * <p>
 * 1 <= deck.length <= 10000
 * 0 <= deck[i] < 10000
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/x-of-a-kind-in-a-deck-of-cards
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class A914_XOfAKindInADeckOfCards {

    /**
     * 有点烂，但是能过，后半段最好还是求几个数是否存在最大公约数
     *
     * @param deck
     * @return
     */
    public boolean hasGroupsSizeX0(int[] deck) {
        if (deck.length <= 1) {
            return false;
        }
        if (deck.length == 2) {
            return deck[0] == deck[1];
        }
        //长度大于2
        //先统计下每个数字个数
        Map<Integer, Integer> data = new HashMap<>();
        for (int i = 0; i < deck.length; i++) {
            data.put(deck[i], data.getOrDefault(deck[i], 0) + 1);
        }
        int X = Integer.MIN_VALUE;
        for (Integer key : data.keySet()) {
            X = Math.max(data.get(key), X);
        }
        boolean can = true;
        for (int i = 2; i <= X; i++) {
            can = true;
            for (Integer key : data.keySet()) {
                if (data.get(key) % i != 0) {
                    can = false;
                    break;
                }
            }
            if (can) {
                return true;
            }
        }
        return false;
    }

    public boolean hasGroupsSizeX(int[] deck) {
        if (deck.length <= 1) {
            return false;
        }
        if (deck.length == 2) {
            return deck[0] == deck[1];
        }
        //长度大于2
        //先统计下每个数字个数
        Map<Integer, Integer> data = new HashMap<>();
        for (int i = 0; i < deck.length; i++) {
            data.put(deck[i], data.getOrDefault(deck[i], 0) + 1);
        }
        int x = 0;
        for (Integer v : data.values()) {
            x = gcd(x, v);
            if (x == 1) {
                return false;
            }
        }
        return true;
    }

    public int gcd(int a, int b) {
        //gcd(a,b) = gcd(b,a mod b)
        return b == 0 ? a : gcd(b, a % b);
    }

    @Test
    public void test() {
        //int[] deck = {1, 2, 3, 4, 4, 3, 2, 1};
        //int[] deck = {1, 1, 2, 2, 2, 2};
        //int[] deck = {1, 1, 1, 1, 2, 2, 2, 2, 2, 2};
        //int[] deck = {1, 1, 1, 2, 2, 2, 3, 3};
        int[] deck = {1, 1, 2, 2, 2, 2};
        System.out.println(hasGroupsSizeX(deck));
    }
}
