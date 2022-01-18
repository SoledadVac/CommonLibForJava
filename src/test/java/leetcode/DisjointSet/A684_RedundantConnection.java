package leetcode.DisjointSet;

import com.alibaba.fastjson.JSONObject;
import org.junit.Test;

/**
 * \* Created: liuhuichao
 * \* Date: 2021/3/27
 * \* Time: 7:41 下午
 * \* Description: 冗余连接
 * \
 * 在本问题中, 树指的是一个连通且无环的无向图。
 * <p>
 * 输入一个图，该图由一个有着N个节点 (节点值不重复1, 2, ..., N) 的树及一条附加的边构成。附加的边的两个顶点包含在1到N中间，这条附加的边不属于树中已存在的边。
 * <p>
 * 结果图是一个以边组成的二维数组。每一个边的元素是一对[u, v] ，满足 u < v，表示连接顶点u 和v的无向图的边。
 * <p>
 * 返回一条可以删去的边，使得结果图是一个有着N个节点的树。如果有多个答案，则返回二维数组中最后出现的边。答案边 [u, v] 应满足相同的格式 u < v。
 * <p>
 * 示例 1：
 * <p>
 * 输入: [[1,2], [1,3], [2,3]]
 * 输出: [2,3]
 * 解释: 给定的无向图为:
 * 1
 * / \
 * 2 - 3
 * 示例 2：
 * <p>
 * 输入: [[1,2], [2,3], [3,4], [1,4], [1,5]]
 * 输出: [1,4]
 * 解释: 给定的无向图为:
 * 5 - 1 - 2
 * |   |
 * 4 - 3
 * 注意:
 * <p>
 * 输入的二维数组大小在 3 到 1000。
 * 二维数组中的整数在1到N之间，其中N是输入数组的大小。
 * 更新(2017-09-26):
 * 我们已经重新检查了问题描述及测试用例，明确图是无向 图。对于有向图详见冗余连接II。对于造成任何不便，我们深感歉意。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/redundant-connection
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class A684_RedundantConnection {
    public int[] findRedundantConnection(int[][] edges) {
        int[] result = new int[edges[0].length];
        int[] parent = new int[edges.length];
        //根节点置为自身
        for (int i = 0; i < parent.length; i++) {
            parent[i] = i;
        }
        for (int i = 0; i < edges.length; i++) {
            int index1 = edges[i][0] - 1;
            int index2 = edges[i][1] - 1;
            int oldIndex2Root = find(parent, index2);
            parent[index2] = find(parent, index1);
            if (oldIndex2Root == parent[index2]) {
                result = new int[]{edges[i][0], edges[i][1]};
            }
        }
        return result;
    }


    /**
     * 查找根节点
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

    @Test
    public void test() {
        //int[][] edges = {{1, 2}, {1, 3}, {2, 3}};
        //int[][] edges = {{1, 2}, {2, 3}, {3, 4}, {1, 4}, {1, 5}};
        int[][] edges = {{1, 4}, {3, 4}, {1, 3}, {1, 2}, {4, 5}};
        System.out.println(JSONObject.toJSONString(findRedundantConnection(edges)));
    }
}