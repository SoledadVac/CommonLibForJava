package leetcode.BinarySearch;

import org.junit.Test;

/**
 * \* Created: liuhuichao
 * \* Date: 2019/4/29
 * \* Time: 6:17 PM
 * \* Description:猜数字大小
 * 我们正在玩一个猜数字游戏。 游戏规则如下：
 * 我从 1 到 n 选择一个数字。 你需要猜我选择了哪个数字。
 * 每次你猜错了，我会告诉你这个数字是大了还是小了。
 * 你调用一个预先定义好的接口 guess(int num)，它会返回 3 个可能的结果（-1，1 或 0）：
 * <p>
 * -1 : 我的数字比较小
 * 1 : 我的数字比较大
 * 0 : 恭喜！你猜对了！
 * 示例 :
 * <p>
 * 输入: n = 10, pick = 6
 * 输出: 6
 * \
 */
public class A374_GuessNumberHigherorLower {
    public int guessNumber(int n) {
        int l = 0;
        int r = n;
        int mid;
        if(guess(n) == 0) return n;
        while(l <= r){
            mid = l + (r - l) / 2;
            if(guess(mid) == -1){
                r = mid - 1;
            }else if(guess(mid) == 1){
                l = mid + 1;
            }else{
                return mid;
            }
        }
        return l;
    }

    int guess(int num) {

        return 0;
    }

    @Test
    public void test() {
        int n = 10;
        System.out.println("result = " + guessNumber(n));
    }


}
