package leetcode;

import com.alibaba.fastjson.JSONObject;
import javafx.util.Pair;
import org.junit.Test;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

/**
 * \* Created: liuhuichao
 * \* Date: 2019/7/17
 * \* Time: 30:50 PM
 * \* Description: 01 Matrix
 * \
 * 给定一个由 0 和 1 组成的矩阵，找出每个元素到最近的 0 的距离。
 * <p>
 * 两个相邻元素间的距离为 1 。
 * <p>
 * 示例 1:
 * 输入:
 * <p>
 * 0 0 0
 * 0 1 0
 * 0 0 0
 * 输出:
 * <p>
 * 0 0 0
 * 0 1 0
 * 0 0 0
 * 示例 2:
 * 输入:
 * <p>
 * 0 0 0
 * 0 1 0
 * 1 1 1
 * 输出:
 * <p>
 * 0 0 0
 * 0 1 0
 * 1 2 1
 * 注意:
 * <p>
 * 给定矩阵的元素个数不超过 10000。
 * 给定矩阵中至少有一个元素是 0。
 * 矩阵中的元素只在四个方向上相邻: 上、下、左、右。
 * <p>
 */
public class A542_01Matrix {

    public int[][] updateMatrix(int[][] matrix) {
        for (int r = 0; r < matrix.length; r++) {
            for (int c = 0; c < matrix[0].length; c++) {
                if (matrix[r][c] == 1) {
                    //对每个点进行bfs
                    matrix[r][c] = search(matrix, r, c);
                }
            }
        }
        return matrix;
    }

    int search(int[][] matrix, int r, int c) {
        int deep = 0;
        Queue<Pair<Integer, Integer>> queue = new LinkedList<>();
        Set<Pair<Integer, Integer>> visited = new HashSet<>();
        queue.add(new Pair<>(r, c));
        visited.add(new Pair<>(r, c));
        while (!queue.isEmpty()) {
            int size = queue.size();
            deep++;
            for (int i = 0; i < size; i++) {
                Pair<Integer, Integer> item = queue.poll();
                int x1 = item.getKey() - 1;
                int x2 = item.getKey() + 1;
                int y1 = item.getValue() - 1;
                int y2 = item.getValue() + 1;
                if (
                        ((x1 >= 0 && x1 < matrix.length) && (matrix[x1][item.getValue()] == 0)) ||
                                ((x2 >= 0 && x2 < matrix.length) && (matrix[x2][item.getValue()] == 0)) ||
                                ((y1 >= 0 && y1 < matrix[0].length) && (matrix[item.getKey()][y1] == 0)) ||
                                ((y2 >= 0 && y2 < matrix[0].length) && (matrix[item.getKey()][y2] == 0))
                        ) {
                    return deep;
                }
                if (x1 >= 0 && x1 < matrix.length && !visited.contains(new Pair<>(x1, c))) {
                    visited.add(new Pair<>(x1, c));
                    queue.add(new Pair<>(x1, c));
                }
                if (x2 >= 0 && x2 < matrix.length && !visited.contains(new Pair<>(x2, c))) {
                    visited.add(new Pair<>(x2, c));
                    queue.add(new Pair<>(x2, c));
                }
                if (y1 >= 0 && y1 < matrix[0].length && !visited.contains(new Pair<>(r, y1))) {
                    visited.add(new Pair<>(r, y1));
                    queue.add(new Pair<>(r, y1));
                }
                if (y2 >= 0 && y2 < matrix[0].length && !visited.contains(new Pair<>(r, y2))) {
                    visited.add(new Pair<>(r, y2));
                    queue.add(new Pair<>(r, y2));
                }
            }
        }
        return -1;
    }


    @Test
    public void test() {
        // int[][] matrix = {{0, 0, 0}, {0, 1, 0}, {1, 1, 1}}; //[[0,0,0],[0,1,0],[1,2,1]]

        //输入：
        //[[0,0,1,0,1,1,1,0,1,1],
        // [1,1,1,1,0,1,1,1,1,1],
        // [1,1,1,1,1,0,0,0,1,1],
        // [1,0,1,0,1,1,1,0,1,1],
        // [0,0,1,1,1,0,1,1,1,1],
        // [1,0,1,1,1,1,1,1,1,1],
        // [1,1,1,1,0,1,0,1,0,1],
        // [0,1,0,0,0,1,0,0,1,1],
        // [1,1,1,0,1,1,0,1,0,1],
        // [1,0,1,1,1,0,1,1,1,0]]
        //输出：
        //[[0,0,1,0,1,2,1,0,1,2],
        // [1,1,2,1,0,1,1,1,2,3],
        // [2,1,2,1,1,0,0,0,1,2],
        // [1,0,1,0,1,1,1,0,1,2],
        // [0,0,1,1,1,0,1,1,2,3],
        // [1,0,1,2,1,1,1,2,1,2],
        // [1,1,1,1,0,1,0,1,0,1],
        // [0,1,0,0,0,1,0,0,1,2],
        // [1,1,1,0,1,1,0,1,0,1],
        // [1,0,1,1,1,0,1,2,1,0]]

        int[][] matrix = {
                {0, 0, 1, 0, 1, 1, 1, 0, 1, 1},
                {1, 1, 1, 1, 0, 1, 1, 1, 1, 1},
                {1, 1, 1, 1, 1, 0, 0, 0, 1, 1},
                {1, 0, 1, 0, 1, 1, 1, 0, 1, 1},
                {0, 0, 1, 1, 1, 0, 1, 1, 1, 1},
                {1, 0, 1, 1, 1, 1, 1, 1, 1, 1},
                {1, 1, 1, 1, 0, 1, 0, 1, 0, 1},
                {0, 1, 0, 0, 0, 1, 0, 0, 1, 1},
                {1, 1, 1, 0, 1, 1, 0, 1, 0, 1},
                {1, 0, 1, 1, 1, 0, 1, 1, 1, 0},
        };
        System.out.println(JSONObject.toJSONString(updateMatrix(matrix)));
    }

}
