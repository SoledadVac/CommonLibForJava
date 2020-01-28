package leetcode.DivideAndConquer;

import com.alibaba.fastjson.JSONObject;
import com.google.gson.JsonObject;
import org.junit.Test;

import java.util.Arrays;
import java.util.Random;

/**
 * \* Created: liuhuichao
 * \* Date: 2020/1/27
 * \* Time: 4:02 下午
 * \* Description:最接近原点的 K 个点
 * \
 * 我们有一个由平面上的点组成的列表 points。需要从中找出 K 个距离原点 (0, 0) 最近的点。
 * <p>
 * （这里，平面上两点之间的距离是欧几里德距离。）
 * <p>
 * 你可以按任何顺序返回答案。除了点坐标的顺序之外，答案确保是唯一的。
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * 输入：points = [[1,3],[-2,2]], K = 1
 * 输出：[[-2,2]]
 * 解释：
 * (1, 3) 和原点之间的距离为 sqrt(10)，
 * (-2, 2) 和原点之间的距离为 sqrt(8)，
 * 由于 sqrt(8) < sqrt(10)，(-2, 2) 离原点更近。
 * 我们只需要距离原点最近的 K = 1 个点，所以答案就是 [[-2,2]]。
 * 示例 2：
 * <p>
 * 输入：points = [[3,3],[5,-1],[-2,4]], K = 2
 * 输出：[[3,3],[-2,4]]
 * （答案 [[-2,4],[3,3]] 也会被接受。）
 *  
 * <p>
 * 提示：
 * <p>
 * 1 <= K <= points.length <= 10000
 * -10000 < points[i][0] < 10000
 * -10000 < points[i][1] < 10000
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/k-closest-points-to-origin
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class A973_KClosestPointstoOrigin {

    int[][] points;

    public int[][] kClosest(int[][] points, int K) {
        this.points = points;
        quickSelect(0, this.points.length - 1, K - 1);
        return Arrays.copyOf(this.points, K);
    }

    int quickSelect(int left, int right, int K) {
        if (left == right) {
            return left;
        }
        Random random = new Random();
        int p = random.nextInt(right - left) + left;
        int nextP = partition(left, right, p);
        if (nextP == K) {
            return nextP;
        } else if (nextP < K) {
            return quickSelect(nextP + 1, right, K);
        }
        return quickSelect(left, nextP - 1, K);
    }


    /**
     * 分割
     *
     * @param left
     * @param right
     * @param position
     * @return
     */
    int partition(int left, int right, int position) {
        int[] pV = this.points[position]; //当前分隔位
        double disV = Math.abs(Math.pow(pV[0], 2)) + Math.abs(Math.pow(pV[1], 2));
        swip(right, position); //先将分隔位的值换到最后
        int result = left;
        for (int i = left; i <= right; i++) {
            int[] v = this.points[i];
            double dis = Math.abs(Math.pow(v[0], 2)) + Math.abs(Math.pow(v[1], 2));
            if (dis < disV) {
                //小于的放在左边
                swip(result, i);
                result++;
            }
        }
        swip(result, right);
        return result;
    }

    void swip(int i, int j) {
        int t0 = this.points[i][0], t1 = this.points[i][1];
        this.points[i][0] = this.points[j][0];
        this.points[i][1] = this.points[j][1];
        this.points[j][0] = t0;
        this.points[j][1] = t1;
    }

    @Test
    public void test() {
        int[][] points = {{-2, -5}, {8, 5}, {-10, -3}, {-7, -1}, {2, -2}, {2, 8}}; //
        int K = 5;
        System.out.println(JSONObject.toJSONString(kClosest(points, K)));
    }
}
