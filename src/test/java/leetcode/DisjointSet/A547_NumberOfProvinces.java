package leetcode.DisjointSet;

import com.google.inject.internal.cglib.core.$ProcessArrayCallback;
import org.junit.Test;

/**
 * \* Created: liuhuichao
 * \* Date: 2021/3/27
 * \* Time: 12:38 下午
 * \* Description:省份数量
 * \
 * 有 n 个城市，其中一些彼此相连，另一些没有相连。如果城市 a 与城市 b 直接相连，且城市 b 与城市 c 直接相连，那么城市 a 与城市 c 间接相连。
 * <p>
 * 省份 是一组直接或间接相连的城市，组内不含其他没有相连的城市。
 * <p>
 * 给你一个 n x n 的矩阵 isConnected ，其中 isConnected[i][j] = 1 表示第 i 个城市和第 j 个城市直接相连，而 isConnected[i][j] = 0 表示二者不直接相连。
 * <p>
 * 返回矩阵中 省份 的数量。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/number-of-provinces
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class A547_NumberOfProvinces {
    public int findCircleNum(int[][] isConnected) {
        int provinces = isConnected.length;
        int[] parent = new int[provinces];
        for (int i = 0; i < provinces; i++) {
            parent[i] = i;
        }
        for (int i = 0; i < provinces; i++) {
            for (int j = i + 1; j < provinces; j++) {
                if (isConnected[i][j] == 1) {
                    union(parent, i, j);
                }
            }
        }
        int circles = 0;
        for (int i = 0; i < provinces; i++) {
            if (parent[i] == i) {
                circles++;
            }
        }
        return circles;
    }

    /**
     * 查找
     *
     * @param parent
     * @param index
     * @return
     */
    public int find(int[] parent, int index) {
        if (parent[index] == index) {
            return index;
        }
        parent[index] = find(parent, parent[index]);
        return parent[index];
    }

    /**
     * 合并根节点
     *
     * @param parent
     * @param index1
     * @param index2
     */
    public void union(int[] parent, int index1, int index2) {
        parent[find(parent, parent[index1])] = find(parent, parent[index2]);
    }

    @Test
    public void test() {
        int[][] isConnected = {
                {1, 1, 1},
                {1, 1, 1},
                {1, 1, 1}};
       /* int[][] isConnected = {
                {1, 1, 0, 1},
                {1, 1, 0, 1},
                {0, 0, 1, 1},
                {0, 0, 1, 1}
        };*/
        System.out.println(findCircleNum(isConnected));
    }
}
