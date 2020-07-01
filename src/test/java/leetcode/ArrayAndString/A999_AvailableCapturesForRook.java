package leetcode.ArrayAndString;

import org.junit.Test;

/**
 * \* Created: liuhuichao
 * \* Date: 2020/6/30
 * \* Time: 30:17 下午
 * \* Description: 可以被一步捕获的棋子数
 * \
 */
public class A999_AvailableCapturesForRook {

    public int numRookCaptures(char[][] board) {
        int sum = 0;
        //1，找到车的位置
        int m = 0;  //行位置
        int n = 0;  //列位置
        for (int i = 0; i < board.length; i++) {
            boolean find = false;
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] == 'R') {
                    m = i;
                    n = j;
                    find = true;
                    break;
                }
            }
            if (find) {
                break;
            }
        }
        //2, 横纵寻找p
        for (int i = n - 1; i >= 0; i--) {
            //横行左边
            if (board[m][i] == 'p') {
                sum++;
                break;
            }
            if (board[m][i] == '.') {
                continue;
            }
            break;
        }
        for (int i = n + 1; i < board[0].length; i++) {
            //横行右边
            if (board[m][i] == 'p') {
                sum++;
                break;
            }
            if (board[m][i] == '.') {
                continue;
            }
            break;
        }
        for (int i = m - 1; i >= 0; i--) {
            //竖向往上
            if (board[i][n] == 'p') {
                sum++;
                break;
            }
            if (board[i][n] == '.') {
                continue;
            }
            break;
        }
        for (int i = m + 1; i < board.length; i++) {
            //竖向往下
            if (board[i][n] == 'p') {
                sum++;
                break;
            }
            if (board[i][n] == '.') {
                continue;
            }
            break;
        }

        return sum;
    }

    @Test
    public void test() {
       /* char[][] board = {
                {'.', '.', '.', '.', '.', '.', '.', '.'},
                {'.', '.', '.', 'p', '.', '.', '.', '.'},
                {'.', '.', '.', 'R', '.', '.', '.', 'p'},
                {'.', '.', '.', '.', '.', '.', '.', '.'},
                {'.', '.', '.', '.', '.', '.', '.', '.'},
                {'.', '.', '.', 'p', '.', '.', '.', '.'},
                {'.', '.', '.', '.', '.', '.', '.', '.'},
                {'.', '.', '.', '.', '.', '.', '.', '.'}}; //3*/
       /* char[][] board = {
                {'.', '.', '.', '.', '.', '.', '.', '.'},
                {'.', 'p', 'p', 'p', 'p', 'p', '.', '.'},
                {'.', 'p', 'p', 'B', 'p', 'p', '.', '.'},
                {'.', 'p', 'B', 'R', 'B', 'p', '.', '.'},
                {'.', 'p', 'p', 'B', 'p', 'p', '.', '.'},
                {'.', 'p', 'p', 'p', 'p', 'p', '.', '.'},
                {'.', '.', '.', '.', '.', '.', '.', '.'},
                {'.', '.', '.', '.', '.', '.', '.', '.'}}; //0*/
        char[][] board = {
                {'.', '.', '.', '.', '.', '.', '.', '.'},
                {'.', '.', '.', 'p', '.', '.', '.', '.'},
                {'.', '.', '.', 'R', '.', '.', '.', 'p'},
                {'.', '.', '.', '.', '.', '.', '.', '.'},
                {'.', '.', '.', '.', '.', '.', '.', '.'},
                {'.', '.', '.', 'p', '.', '.', '.', '.'},
                {'.', '.', '.', '.', '.', '.', '.', '.'},
                {'.', '.', '.', '.', '.', '.', '.', '.'}}; // 3
        System.out.println(numRookCaptures(board));
    }
}
