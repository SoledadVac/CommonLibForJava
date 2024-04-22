package leetcode.Dfs;

import leetcode.UnionFind.UnionFindTemplate;

import java.util.*;

public class A1319_NumberOfOperationsToMakeNetworkConnected {


    public int makeConnected(int n, int[][] connections) {
        if (connections.length < n - 1) {
            return -1;
        }
        UnionFindTemplate uf = new UnionFindTemplate(n);

        //0,1,2,3....n
        for (int i = 0; i < connections.length; i++) {
            int from = connections[i][0];
            int to = connections[i][1];
            uf.union(from, to);
        }
        return uf.setCount - 1;
    }


}
