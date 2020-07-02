package leetcode.ArrayAndString;

import com.alibaba.fastjson.JSONObject;
import com.kenai.jaffl.annotations.In;
import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * \* Created: liuhuichao
 * \* Date: 2020/7/2
 * \* Time: 25:58 下午
 * \* Description:合并区间
 * \
 * 给出一个区间的集合，请合并所有重叠的区间。
 * <p>
 * 示例 1:
 * <p>
 * 输入: [[1,3],[2,6],[8,10],[15,18]]
 * 输出: [[1,6],[8,10],[15,18]]
 * 解释: 区间 [1,3] 和 [2,6] 重叠, 将它们合并为 [1,6].
 * 示例 2:
 * <p>
 * 输入: [[1,4],[4,5]]
 * 输出: [[1,5]]
 * 解释: 区间 [1,4] 和 [4,5] 可被视为重叠区间。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/merge-intervals
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class A56_MergeIntervals {

    public int[][] merge0(int[][] intervals) {
        Set<Integer> indexSet = new HashSet<>();
        List<int[]> data = new ArrayList<>();
        for (int x = 0; x < intervals.length; x++) {
            for (int i = x + 1; i < intervals.length; i++) {
                if (indexSet.contains(x)) {
                    continue;
                }
                if (((intervals[x][1] >= intervals[i][0] && intervals[i][1] >= intervals[x][1])
                        || (intervals[i][1] >= intervals[x][0] && intervals[i][1] <= intervals[x][1]))) {
                    indexSet.add(x);
                    indexSet.add(i);
                    data.add(new int[]{Math.min(intervals[x][0], intervals[i][0]), Math.max(intervals[x][1], intervals[i][1])});
                }
            }
            if (indexSet.contains(x)) {
                continue;
            }
            data.add(new int[]{intervals[x][0], intervals[x][1]});
        }
        if (data.size() == intervals.length) {
            return data.toArray(new int[data.size()][2]);
        }
        return merge(data.toArray(new int[data.size()][2]));
    }

    public int[][] merge(int[][] intervals) {
        int[][] res = new int[intervals.length][2];
        int index = -1;
        Arrays.sort(intervals, Comparator.comparingInt(d -> d[0]));
        for (int[] item : intervals) {
            //跟上一个比较
            if (index == -1 || item[0] > res[index][1]) {
                res[++index] = item;
                continue;
            }
            //如果在范围内的话,合并到结果的上一个值
            res[index][1] = Math.max(res[index][1], item[1]);
        }

        return Arrays.copyOf(res, index + 1);
    }

    @Test
    public void test() {
        //int[][] intervals = {{1, 3}, {2, 6}, {8, 10}, {15, 18}}; //[[1,6],[8,10],[15,18]]
        //int[][] intervals = {{1, 4}, {0, 4}}; //[0,4]
        //int[][] intervals = {{1, 4}, {0, 0}}; //[1,4],[0,0]
        //int[][] intervals = {{1, 4}, {0, 1}}; //[0,4]
        //int[][] intervals = {{1, 4}, {0, 2}, {3, 5}}; //[0,5]
        int[][] intervals = {{2, 3}, {5, 5}, {2, 2}, {3, 4}, {3, 4}}; //[[2,4],[5,5]]
        System.out.println(JSONObject.toJSONString(merge(intervals)));
    }
}
