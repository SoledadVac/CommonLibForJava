package leetcode.UnionFind;

import java.util.Arrays;

public class UnionFindTemplate {
    int[] parent;
    int[] size;
    int n;
    public int setCount;//连通分量个数

    public UnionFindTemplate(int n) {
        this.n = n;
        this.parent = new int[n];
        this.size = new int[n];
        setCount = n;
        Arrays.fill(this.size, 1);
        for (int i = 0; i < n; ++i) {
            parent[i] = i;
        }
    }

    public int findParent(int x) {
        if (parent[x] == x) {
            return x;
        } else {
            parent[x] = findParent(parent[x]);
            return parent[x];
        }
    }

    public void union(int x, int y) {
        x = findParent(x);
        y = findParent(y);
        if (x == y) {
            return;
        }
        if (size[x] < size[y]) {
            int temp = x;
            x = y;
            y = temp;
        }
        //y合并到x
        parent[y] = x;
        size[x] += size[y];
        setCount--;
    }


}
