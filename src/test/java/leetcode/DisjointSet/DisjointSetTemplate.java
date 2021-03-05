package leetcode.DisjointSet;

/**
 * \* Created: liuhuichao
 * \* Date: 2021/3/5
 * \* Time: 34:42 上午
 * \* Description: 并查集模板
 * \
 */
public class DisjointSetTemplate {

    //原始数据
    private int[] data;
    //记录每个根结点对应的树的深度
    private int[] rank;

    /**
     * 初始化数据结构
     *
     * @param n
     */
    public void initData(int n) {
        data = new int[n];
        rank = new int[n];
        for (int i = 0; i < n; i++) {
            data[i] = i;
            rank[i] = 1;
        }
    }

    /**
     * 查询根节点
     *
     * @param id
     * @return
     */
    public int find0(int id) {
        if (data[id] == id) {
            return id;
        }
        return find0(data[id]);
    }


    /**
     * 合并
     * 把 from 合并到 into 中去，就是把 from 的双亲结点设为 into 的
     * 注意，这里是根结点的合并
     *
     * @param into
     * @param from
     */
    public void merge0(int into, int from) {
        data[find(data[from])] = find(data[into]);
    }

    /**
     * 查询根节点----使用路径压缩
     * 查找过程中，把途经的结点设置为根节点
     *
     * @param id
     * @return
     */
    public int find(int id) {
        if (data[id] == id) {
            return id;
        }
        data[id] = find(data[id]);
        return data[id];
    }

    /**
     * 按秩合并
     * 用rank[ ]数组来记录每个根结点对应的树的深度
     * （如果不是根结点，则rank中的元素大小表示的是以当前结点作为根结点的子树的深度）；
     * 一开始，把所有元素的rank设为1，即自己就为一颗树，且深度为1；
     * 合并的时候，比较两个根结点，把rank较小者合并到较大者中去。
     *
     * @param index1
     * @param index2
     */
    public void merge(int index1, int index2) {
        //查找根节点
        int r1 = find(data[index1]);
        int r2 = find(data[index2]);
        if (r1 == r2) {
            return;
        }
        //将深度小的合并到深度大的
        if (rank[r1] < rank[r2]) {
            data[r1] = r2;
        } else {
            if (rank[r1] == rank[r2]) {
                //深度相同，随便啦，但是合并过去深度+1了
                rank[r1]++;
            }
            data[r2] = r1;
        }
    }
}
