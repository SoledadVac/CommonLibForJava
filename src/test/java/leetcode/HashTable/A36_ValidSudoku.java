package leetcode.HashTable;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * \* Created: liuhuichao
 * \* Date: 2019/8/20
 * \* Time: 35:31 AM
 * \* Description: Valid Sudoku
 * \
 * 判断一个 9x9 的数独是否有效。只需要根据以下规则，验证已经填入的数字是否有效即可。
 * <p>
 * 数字 1-9 在每一行只能出现一次。
 * 数字 1-9 在每一列只能出现一次。
 * 数字 1-9 在每一个以粗实线分隔的 3x3 宫内只能出现一次。
 * <p>
 * 上图是一个部分填充的有效的数独。
 * <p>
 * 数独部分空格内已填入了数字，空白格用 '.' 表示。
 * <p>
 * 示例 1:
 * <p>
 * 输入:
 * [
 * ["5","3",".",".","7",".",".",".","."],
 * ["6",".",".","1","9","5",".",".","."],
 * [".","9","8",".",".",".",".","6","."],
 * ["8",".",".",".","6",".",".",".","3"],
 * ["4",".",".","8",".","3",".",".","1"],
 * ["7",".",".",".","2",".",".",".","6"],
 * [".","6",".",".",".",".","2","8","."],
 * [".",".",".","4","1","9",".",".","5"],
 * [".",".",".",".","8",".",".","7","9"]
 * ]
 * 输出: true
 * 示例 2:
 * <p>
 * 输入:
 * [
 *   ["8","3",".",".","7",".",".",".","."],
 *   ["6",".",".","1","9","5",".",".","."],
 *   [".","9","8",".",".",".",".","6","."],
 *   ["8",".",".",".","6",".",".",".","3"],
 *   ["4",".",".","8",".","3",".",".","1"],
 *   ["7",".",".",".","2",".",".",".","6"],
 *   [".","6",".",".",".",".","2","8","."],
 *   [".",".",".","4","1","9",".",".","5"],
 *   [".",".",".",".","8",".",".","7","9"]
 * ]
 * 输出: false
 * 解释: 除了第一行的第一个数字从 5 改为 8 以外，空格内其他数字均与 示例1 相同。
 * 但由于位于左上角的 3x3 宫内有两个 8 存在, 因此这个数独是无效的。
 * 说明:
 * <p>
 * 一个有效的数独（部分已被填充）不一定是可解的。
 * 只需要根据以上规则，验证已经填入的数字是否有效即可。
 * 给定数独序列只包含数字 1-9 和字符 '.' 。
 * 给定数独永远是 9x9 形式的。
 */

public class A36_ValidSudoku {

    /**
     * 这个是我写的渣渣。。
     *
     * @param board
     * @return
     */
    public boolean isValidSudoku(char[][] board) {
        //数字 1-9 在每一行只能出现一次。
        //数字 1-9 在每一列只能出现一次。
        //数字 1-9 在每一个以粗实线分隔的 3x3 宫内只能出现一次。
        List<Map<Integer, Integer>> rowData = new ArrayList<>();
        List<Map<Integer, Integer>> colData = new ArrayList<>();
        List<Map<Integer, Integer>> data = new ArrayList<>(); // key: box_index = (row / 3) * 3 + columns / 3
        for (int i = 0; i < 9; i++) {
            rowData.add(new HashMap<>());
            colData.add(new HashMap<>());
            data.add(new HashMap<>());
        }
        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board[0].length; col++) {
                //行的检验
                char c = board[row][col];
                int num;
                if (c == '.') {
                    num = -1;
                } else {
                    num = Character.digit(c, 10);
                }
                Map<Integer, Integer> rowMap = rowData.get(row);
                if (rowMap == null) {
                    rowMap = new HashMap<>();
                }
                if (rowMap.containsKey(num) && num != -1) {
                    return false;
                }
                rowMap.put(num, col);
                rowData.set(row, rowMap);
                //列的检验
                Map<Integer, Integer> colMap = colData.get(col);
                if (colMap == null) {
                    colMap = new HashMap<>();
                }
                if (colMap.containsKey(num) && num != -1) {
                    return false;
                }
                colMap.put(num, row);
                colData.set(col, colMap);
                //3*3的检查
                int boxIndex = (row / 3) * 3 + col / 3;
                Map<Integer, Integer> box = data.get(boxIndex);
                if (box.containsKey(num) && num != -1) {
                    return false;
                }
                box.put(num, box.getOrDefault(num, 0) + 1);
                data.set(boxIndex, box);
            }

        }
        return true;
    }

    public boolean isValidSudoku1(char[][] board) {
        int[] rows = new int[9];
        int[] cols = new int[9];
        int[] blks = new int[9];
        for (int ri = 0; ri < 9; ++ri) {
            for (int ci = 0; ci < 9; ++ci) {
                if (board[ri][ci] != '.') {
                    int bi = ri / 3 * 3 + ci / 3;
                    int uvb = 1 << (board[ri][ci] - '0');
                    if ((uvb & (rows[ri] | cols[ci] | blks[bi])) != 0)
                        return false;
                    rows[ri] |= uvb;
                    cols[ci] |= uvb;
                    blks[bi] |= uvb;
                }
            }
        }
        return true;
    }

    @Test
    public void test() {
        char[][] board = {
                {'5', '3', '.', '.', '7', '.', '.', '.', '.'},
                {'6', '.', '.', '1', '9', '5', '.', '.', '.'},
                {'.', '9', '8', '.', '.', '.', '.', '6', '.'},
                {'8', '.', '.', '.', '6', '.', '.', '.', '3'},
                {'4', '.', '.', '8', '.', '3', '.', '.', '1'},
                {'7', '.', '.', '.', '2', '.', '.', '.', '6'},
                {'.', '6', '.', '.', '.', '.', '2', '8', '.'},
                {'.', '.', '.', '4', '1', '9', '.', '.', '5'},
                {'.', '.', '.', '.', '8', '.', '.', '7', '9'}
        };
        System.out.println("result = " + isValidSudoku1(board));

    }

}
