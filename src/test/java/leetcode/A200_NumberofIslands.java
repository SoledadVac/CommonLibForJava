package leetcode;

import org.junit.Test;

import java.util.LinkedList;
import java.util.Queue;

/**
 * \* Created: liuhuichao
 * \* Date: 2019/5/31
 * \* Time: 27:37 PM
 * \* Description: 岛屿数量
 * \
 * 给定一个由 '1'（陆地）和 '0'（水）组成的的二维网格，计算岛屿的数量。
 * 一个岛被水包围，并且它是通过水平方向或垂直方向上相邻的陆地连接而成的。你可以假设网格的四个边均被水包围。
 * <p>
 * 示例 1:
 * <p>
 * 输入:
 * 11110
 * 11010
 * 11000
 * 00000
 * <p>
 * 输出: 1
 * 示例 2:
 * <p>
 * 输入:
 * 11000
 * 11000
 * 00100
 * 00011
 * <p>
 * 输出: 3
 * 输入:
 * 11000
 * 11010
 * 00100
 * 00011
 * <p>
 * 输出: 3
 */
public class A200_NumberofIslands {

    public static int[][] directions = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

    public int numIslands(char[][] grid) {
        int rowNum = grid.length;
        if (rowNum == 0) {
            return -1;
        }
        int colNum = grid[0].length;
        if (colNum == 0) {
            return -1;
        }
        Queue<int[]> q = new LinkedList<>();
        Queue<int[]> visited = new LinkedList<>();
        int totalIsland = 0;
        for (int row = 0; row < rowNum; row++) {
            for (int col = 0; col < colNum; col++) {
                if (grid[row][col] == 1) {
                    q.add(new int[]{row, col});
                    totalIsland++;
                }
            }
        }
        while (!q.isEmpty()) {
            int[] cur = q.poll();
            visited.add(cur);
            for (int[] dir : directions) {
                int x = dir[0];
                int y = dir[1];
                int newX = x + cur[0];
                int newY = y + cur[1];
                if (newX < 0 || newY < 0 || newX >= rowNum || newY >= colNum || grid[newX][newY] == 0) {
                    continue;
                }
                if (grid[newX][newY] == 1) {
                    if (!checkInQueue(visited, newX, newY)) {
                        visited.add(new int[]{newX, newY});
                        totalIsland--;
                    }
                }
            }
        }
        return totalIsland;
    }

    boolean checkInQueue(Queue<int[]> visited, int x, int y) {
        //return visited.stream().anyMatch(temp -> temp[0] == x && temp[1] == y); //貌似网页编辑器不支持啊
        for (int[] temp : visited) {
            if (temp[0] == x && temp[1] == y) {
                return true;
            }
        }
        return false;
    }

    @Test
    public void test() {
        // char[][] grid = {{1, 1, 1, 1, 0}, {1, 1, 0, 1, 0}, {1, 1, 0, 0, 0}, {0, 0, 0, 0, 0}};
        // char[][] grid = {{1, 1, 0, 1, 0}, {1, 1, 0, 0, 0}, {0, 0, 1, 0, 0}, {0, 0, 0, 1, 1}};
        char[][] grid = {{1, 1, 1, 1, 0}, {1, 1, 0, 1, 0}, {1, 1, 0, 0, 0}, {0, 0, 0, 0, 0}};
        int result = numIslands(grid);
        System.out.println("result = " + result);
    }


}
