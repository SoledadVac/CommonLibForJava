package leetcode.ArrayAndString;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * \* Created: liuhuichao
 * \* Date: 2020/5/12
 * \* Time: 27:38 下午
 * \* Description: 总持续时间可被 60 整除的歌曲
 * \
 * 在歌曲列表中，第 i 首歌曲的持续时间为 time[i] 秒。
 * <p>
 * 返回其总持续时间（以秒为单位）可被 60 整除的歌曲对的数量。形式上，我们希望索引的数字 i 和 j 满足  i < j 且有 (time[i] + time[j]) % 60 == 0。
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * 输入：[30,20,150,100,40]
 * 输出：3
 * 解释：这三对的总持续时间可被 60 整数：
 * (time[0] = 30, time[2] = 150): 总持续时间 180
 * (time[1] = 20, time[3] = 100): 总持续时间 120
 * (time[1] = 20, time[4] = 40): 总持续时间 60
 * 示例 2：
 * <p>
 * 输入：[60,60,60]
 * 输出：3
 * 解释：所有三对的总持续时间都是 120，可以被 60 整数。
 *  
 * <p>
 * 提示：
 * <p>
 * 1 <= time.length <= 60000
 * 1 <= time[i] <= 500
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/pairs-of-songs-with-total-durations-divisible-by-60
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class A1010_PairsOfSongsWithTotalDurationsDivisibleBy60 {

    public int numPairsDivisibleBy60(int[] time) {
        int num = 0;
        Map<Integer, List<Integer>> data = new HashMap<>();
        for (int i = 0; i < time.length; i++) {
            List<Integer> v = data.get(time[i] % 60);
            if (v == null) {
                v = new ArrayList<>();
            }
            v.add(i);
            data.put(time[i] % 60, v);
        }
        for (int j = 0; j < time.length; j++) {
            final int index2 = j;
            int dif = time[j] % 60 == 0 ? 0 : 60 - time[j] % 60;
            if (data.containsKey(dif)) {
                List<Integer> value = data.get(dif);
                long size = value.stream().filter(v -> v > index2).count();
                num += size;
            }
        }
        return num;
    }

    public int numPairsDivisibleBy60_1(int[] time) {
        if (time.length < 2) {
            return 0;
        }
        int num = 0;
        int[] data = new int[60];
        for (int t : time) {
            int mod = t % 60;
            int dif = 60 - mod;
            num += data[dif % 60];
            data[mod]++;
        }
        return num;
    }

    @Test
    public void test() {
        int[] time = {60, 60};
        System.out.println(numPairsDivisibleBy60_1(time));
    }

}
