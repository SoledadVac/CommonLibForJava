package leetcode.BinarySearchTree;

public class A463_IslandPerimeter {
    public int islandPerimeter(int[][] grid) {
        int total = 0;
        for (int row = 0; row < grid.length; row++) {
            for (int col = 0; col < grid[0].length; col++) {
                if (grid[row][col] == 0) {
                    continue;
                }
                //上边
                if (row - 1 < 0 || grid[row - 1][col] == 0) {
                    total++;
                }
                //下边
                if (row + 1 >= grid.length || grid[row + 1][col] == 0) {
                    total++;
                }
                //左边
                if (col - 1 < 0 || grid[row][col - 1] == 0) {
                    total++;
                }
                //右边
                if (col + 1 >= grid[0].length || grid[row][col + 1] == 0) {
                    total++;
                }
            }
        }
        return total;
    }
}
