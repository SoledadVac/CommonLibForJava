package leetcode.ArrayAndString;

import com.jcraft.jsch.MAC;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 * \* Created: liuhuichao
 * \* Date: 2020/6/29
 * \* Time: 30:36 下午
 * \* Description: 到最近的人的最大距离
 * \
 * 在一排座位（ seats）中，1 代表有人坐在座位上，0 代表座位上是空的。
 * <p>
 * 至少有一个空座位，且至少有一人坐在座位上。
 * <p>
 * 亚历克斯希望坐在一个能够使他与离他最近的人之间的距离达到最大化的座位上。
 * <p>
 * 返回他到离他最近的人的最大距离。
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * 输入：[1,0,0,0,1,0,1]
 * 输出：2
 * 解释：
 * 如果亚历克斯坐在第二个空位（seats[2]）上，他到离他最近的人的距离为 2 。
 * 如果亚历克斯坐在其它任何一个空位上，他到离他最近的人的距离为 1 。
 * 因此，他到离他最近的人的最大距离是 2 。
 * 示例 2：
 * <p>
 * 输入：[1,0,0,0]
 * 输出：3
 * 解释：
 * 如果亚历克斯坐在最后一个座位上，他离最近的人有 3 个座位远。
 * 这是可能的最大距离，所以答案是 3 。
 *  
 * <p>
 * 提示：
 * <p>
 * 2 <= seats.length <= 20000
 * seats 中只含有 0 和 1，至少有一个 0，且至少有一个 1。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/maximize-distance-to-closest-person
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class A849_MaximizeDistanceToClosestPerson {

    public int maxDistToClosest(int[] seats) {
        int max = -1;
        int pre = -1;
        for (int i = 0; i < seats.length; i++) {
            if (seats[i] == 0 && i != seats.length - 1) {
                continue;
            }
            if (pre == -1) {
                //考虑前面为0的情况
                max = i;
            }
            if (i == seats.length - 1 && seats[seats.length - 1] == 0) {
                //考虑末尾为0的情况
                max = Math.max(max, i - pre);
            }
            // = 1
            max = Math.max(max, (i - pre) / 2);
            pre = i;
        }
        return max;
    }

    @Test
    public void test() {
        //int[] seats = {0, 1, 0, 0, 0, 0};//4
        //int[] seats = {1, 0, 0, 0, 1, 0, 1};//2
        //int[] seats = {1, 0, 0, 0}; //3
        //int[] seats = {0, 0, 1, 0, 1, 1};//2
        int[] seats = {0, 1, 0, 1, 0};//1
        System.out.println(maxDistToClosest(seats));
    }
}
