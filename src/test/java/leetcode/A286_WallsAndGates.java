package leetcode;

import com.alibaba.fastjson.JSONObject;
import org.junit.Test;

import java.util.LinkedList;
import java.util.Queue;

/**
 * \* Created: liuhuichao
 * \* Date: 2019/7/3
 * \* Time: 29:32 PM
 * \* Description:墙和门
 * \
 * <p>
 * You are given a m x n 2D grid initialized with these three possible values.
 * <p>
 * -1 - A wall or an obstacle.
 * 0 - A gate.
 * INF - Infinity means an empty room. We use the value 231 - 1 = 2147483647 to represent INF as you may assume that the distance to a gate is less than 2147483647.
 * Fill each empty room with the distance to its nearest gate. If it is impossible to reach a gate, it should be filled with INF.
 * <p>
 * For example, given the 2D grid:
 * INF  -1   0    INF
 * INF  INF  INF  -1
 * INF  -1   INF  -1
 * 0   -1    INF  INF
 * After running your function, the 2D grid should be:
 * 3  -1   0   1
 * 2   2   1  -1
 * 1  -1   2  -1
 * 0  -1   3   4
 */
public class A286_WallsAndGates {

    public static int[][] directions = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};


    public void wallsAndGates(int[][] rooms) {
        int rowNum = rooms.length;
        if (rowNum == 0) {
            return;
        }
        int colNum = rooms[0].length;
        if (colNum == 0) {
            return;
        }
        Queue<int[]> q = new LinkedList<>();
        for (int row = 0; row < rowNum; row++) {
            for (int col = 0; col < colNum; col++) {
                if (rooms[row][col] == 0) {
                    q.add(new int[]{row, col});
                }
            }
        }
        //从终点到各个其他点的BFS
        while (!q.isEmpty()) {
            int[] current = q.poll();
            int row = current[0];
            int col = current[1];
            for (int[] dir : directions) {
                int x = dir[0];
                int y = dir[1];
                int newX = row + x;
                int newY = col + y;
                if (newX < 0 || newY < 0 || newX >= rowNum || newY >= colNum || rooms[newX][newY] != Integer.MAX_VALUE) {
                    continue;
                }
                //如果为空房间
                rooms[newX][newY] = rooms[row][col] + 1;
                q.add(new int[]{newX, newY});
            }
        }
    }

    @Test
    public void test() {
        int[][] rooms = new int[4][4];
        rooms[0][0] = Integer.MAX_VALUE;
        rooms[0][1] = -1;
        rooms[0][2] = 0;
        rooms[0][3] = Integer.MAX_VALUE;
        rooms[1][0] = Integer.MAX_VALUE;
        rooms[1][1] = Integer.MAX_VALUE;
        rooms[1][2] = Integer.MAX_VALUE;
        rooms[1][3] = -1;
        rooms[2][0] = Integer.MAX_VALUE;
        rooms[2][1] = -1;
        rooms[2][2] = Integer.MAX_VALUE;
        rooms[2][3] = -1;
        rooms[3][0] = 0;
        rooms[3][1] = -1;
        rooms[3][2] = Integer.MAX_VALUE;
        rooms[3][3] = Integer.MAX_VALUE;
        wallsAndGates(rooms);
        System.out.println(JSONObject.toJSONString(rooms));
    }
}
