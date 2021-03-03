package leetcode;

import com.alibaba.fastjson.JSONObject;
import org.junit.Test;

/**
 * \* Created: liuhuichao
 * \* Date: 2021/2/23
 * \* Time: 26:07 下午
 * \* Description:Game of Life
 * \
 * 根据 百度百科 ，生命游戏，简称为生命，是英国数学家约翰·何顿·康威在 1970 年发明的细胞自动机。
 * <p>
 * 给定一个包含 m × n 个格子的面板，每一个格子都可以看成是一个细胞。每个细胞都具有一个初始状态：1 即为活细胞（live），
 * 或 0 即为死细胞（dead）。每个细胞与其八个相邻位置（水平，垂直，对角线）的细胞都遵循以下四条生存定律：
 * <p>
 * 如果活细胞周围八个位置的活细胞数少于两个，则该位置活细胞死亡；
 * 如果活细胞周围八个位置有两个或三个活细胞，则该位置活细胞仍然存活；
 * 如果活细胞周围八个位置有超过三个活细胞，则该位置活细胞死亡；
 * 如果死细胞周围正好有三个活细胞，则该位置死细胞复活；
 * 下一个状态是通过将上述规则同时应用于当前状态下的每个细胞所形成的，其中细胞的出生和死亡是同时发生的。
 * 给你 m x n 网格面板 board 的当前状态，返回下一个状态。
 * 输入：board = [[0,1,0],[0,0,1],[1,1,1],[0,0,0]]
 * 输出：[[0,0,0],[1,0,1],[0,1,1],[0,1,0]]
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/game-of-life
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/game-of-life
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class A289_GameOfLife {
    public void gameOfLife0(int[][] board) {
        int[][] temp = new int[board.length][board[0].length]; //原始数据
        for (int row = 0; row < board.length; row++) {
            for (int cell = 0; cell < board[0].length; cell++) {
                temp[row][cell] = board[row][cell];
            }
        }
        for (int row = 0; row < board.length; row++) {
            for (int cell = 0; cell < board[0].length; cell++) {
                int x = row;
                int y = cell;
                int liveNum = 0;
                //周围位置判断
                for (int x1 = x - 1; x1 <= x + 1; x1++) {
                    for (int y1 = y - 1; y1 <= y + 1; y1++) {
                        if (x1 == x && y1 == y) {
                            //去掉自身点
                            continue;
                        }
                        if (x1 >= 0 && x1 < board.length && y1 >= 0 && y1 < board[0].length) {
                            if (temp[x1][y1] == 1) {
                                liveNum++;
                            }
                        }
                    }
                }
                if (temp[row][cell] == 0 && liveNum == 3) {
                    board[row][cell] = 1;
                }
                if (temp[row][cell] == 1) {
                    if (liveNum == 2 || liveNum == 3) {
                        board[row][cell] = 1;
                    } else {
                        board[row][cell] = 0;
                    }
                }
            }
        }
    }

    /**
     * 原地算法
     * @param board
     */
    public void gameOfLife(int[][] board) {

        //定义活细胞死亡为 1 -> 2
        //定义死细胞变活为 0 -> -1
        for (int row = 0; row < board.length; row++) {
            for (int cell = 0; cell < board[0].length; cell++) {
                int x = row;
                int y = cell;
                int liveNum = 0;
                //周围位置判断
                for (int x1 = x - 1; x1 <= x + 1; x1++) {
                    for (int y1 = y - 1; y1 <= y + 1; y1++) {
                        if (x1 == x && y1 == y) {
                            //去掉自身点
                            continue;
                        }
                        if (x1 >= 0 && x1 < board.length && y1 >= 0 && y1 < board[0].length) {
                            if (board[x1][y1] == 1 || board[x1][y1] == 2) {
                                liveNum++;
                            }
                        }
                    }
                }
                if (board[row][cell] == 0 && liveNum == 3) {
                    board[row][cell] = -1;
                }
                if (board[row][cell] == 1) {
                    if (!(liveNum == 2 || liveNum == 3)) {
                        board[row][cell] = 2;
                    }
                }
            }
        }

        for (int row = 0; row < board.length; row++) {
            for (int cell = 0; cell < board[0].length; cell++) {
                if (board[row][cell] == 2) {
                    board[row][cell] = 0;
                }
                if (board[row][cell] == -1) {
                    board[row][cell] = 1;
                }
            }
        }
    }

    @Test
    public void test() {
        int[][] board = {
                {0, 1, 0},
                {0, 0, 1},
                {1, 1, 1},
                {0, 0, 0}
        };
        /*int[][] result = {
                {0, 0, 0},
                {1, 0, 1},
                {0, 1, 1},
                {0, 1, 0}
        };*/
        gameOfLife(board);
        System.out.println(JSONObject.toJSONString(board));
    }

}
