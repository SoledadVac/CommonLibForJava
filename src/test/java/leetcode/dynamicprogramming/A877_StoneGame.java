package leetcode.dynamicprogramming;


import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import com.google.inject.internal.cglib.proxy.$ProxyRefDispatcher;
import com.google.inject.internal.cglib.reflect.$FastClass;
import com.kenai.jaffl.annotations.In;
import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import com.sun.tools.javac.tree.JCTree;
import javafx.collections.transformation.SortedList;
import org.apache.commons.collections.EnumerationUtils;
import org.junit.Test;

import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

/**
 * \* Created: liuhuichao
 * \* Date: 2021/6/16
 * \* Time: 34:47 上午
 * \* Description: Stone Game
 * \
 */
public class A877_StoneGame {

    public boolean stoneGame(int[] piles) {
        if (piles.length == 2) {
            return true;
        }

        for (int i = 0; i < piles.length; i++) {

        }


        return false;
    }

    public int getMostValue(int[] piles) {
        return Math.max(piles[0], piles[piles.length - 1]);
    }


    public void test() {
        int[] piles = {5, 3, 4, 5};
        System.out.println(stoneGame(piles));
    }

    public int maximumRemovals(String s, String p, int[] removable) {
        //s = "abcacb", p = "ab", removable = [3,1,0]
        Map<Character, List<Integer>> charIndexMap = new HashMap<>();
        char[] chars = s.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            List<Integer> indexList = charIndexMap.getOrDefault(chars[i], new ArrayList<>());
            indexList.add(i);
            charIndexMap.put(chars[i], indexList);
        }
        for (int i = 0; i < removable.length; i++) {
            int removeIndex = removable[i];
            if (!p.contains(s.subSequence(removable[i], removable[i]))) {
                continue;
            }
            List<Integer> indexList = charIndexMap.get(s.charAt(removable[i]));
            if (indexList.size() <= 1) {
                return i;
            }
            indexList.removeIf(index -> index == removeIndex);
            charIndexMap.put(chars[i], indexList);
        }
        return removable.length;
    }


    public int maxLength(List<String> arr) {
        if (arr.size() == 1) {
            return arr.get(0).length();
        }

        int max = 0;
        for (int i = 0; i < arr.size() - 1; i++) {
            for (int j = i + 1; j < arr.size(); j++) {
                boolean can = true;
                int[] charNum = new int[26];
                for (char s : arr.get(i).toCharArray()) {
                    charNum[s - 'a']++;
                }
                for (char s : arr.get(j).toCharArray()) {
                    if (charNum[s - 'a'] >= 1) {
                        can = false;
                        break;
                    }
                }
                if (can) {
                    int i1 = i;
                    int i2 = j;
                    arr.removeIf(a -> a.equals(arr.get(i1)) || a.equals(arr.get(i2)));
                    max = Math.max(max, arr.get(i).length() + arr.get(j).length());
                }
            }
        }
        return max;
    }

    //n = 5, relation = [[0,2],[2,1],[3,4],[2,3],[1,4],[2,0],[0,4]], k = 3n = 5,
    // relation = [[0,2],[2,1],[3,4],[2,3],[1,4],[2,0],[0,4]], k = 3
    int ways;
    int k;
    int target;
    List<List<Integer>> data = new ArrayList<>();

    public int numWays(int n, int[][] relation, int k) {
        this.ways = 0;
        this.k = k;
        this.target = n - 1;
        for (int i = 0; i < n; i++) {
            data.add(new ArrayList<>());
        }
        for (int i = 0; i < relation.length; i++) {
            int v1 = relation[i][0];
            int v2 = relation[i][1];
            List<Integer> tails = data.get(v1);
            tails.add(v2);
        }
        dfs(0, 0);
        return ways;
    }

    public void dfs(int index, int dep) {
        if (dep == k) {
            if (index == target) {
                ways++;
                return;
            }
            return;
        }
        for (int i : data.get(index)) {
            dfs(i, dep + 1);
        }
    }


    //costs = [1,3,2,4,1], coins = 7 ->4
    public int maxIceCream(int[] costs, int coins) {
        int maxCount = 0;
        Arrays.sort(costs);
        for (int i = 0; i < costs.length; i++) {
            if (costs[i] <= coins) {
                coins = coins - costs[i];
                maxCount++;
            }
        }
        return maxCount;
    }

    //[10,9,2,5,3,7,101,18]
    public int lengthOfLIS(int[] nums) {
        int result = 1;
        int[] dp = new int[nums.length];
        Arrays.fill(dp, 1);
        for (int i = 1; i < nums.length; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[j] < nums[i]) {
                    dp[i] = Math.max(1 + dp[j], dp[i]);
                }
            }
            result = Math.max(result, dp[i]);
        }
        return result;
    }

    Map<Integer, Integer> climbData = new HashMap<>();

    public int climbStairs(int n) {
        climbData.put(1, 1);
        climbData.put(2, 2);
        if (climbData.containsKey(n)) {
            return climbData.get(n);
        }
        int v1 = 0;
        int v2 = 0;
        if (climbData.containsKey(n - 1)) {
            v1 = climbData.get(n - 1);
        } else {
            v1 = climbStairs(n - 1);
            climbData.put(n - 1, v1);
        }
        if (climbData.containsKey(n - 2)) {
            v2 = climbData.get(n - 2);
        } else {
            v2 = climbStairs(n - 2);
            climbData.put(n - 2, v2);
        }
        return v1 + v2;
    }


    //s = "abc", t = "ahbgdc"
    public boolean isSubsequence(String s, String t) {
        int sIndex = 0;
        int tIndex = 0;
        while (sIndex < s.length() && tIndex < t.length()) {
            if (s.charAt(sIndex) == t.charAt(tIndex)) {
                sIndex++;
            }
            tIndex++;
        }
        return sIndex == s.length();
    }


    public int rotatedDigits(int n) {
        int num = 0;
        for (int i = 1; i <= n; i++) {
            if (isGood(n, false)) {
                num++;
            }
        }
        return num;

    }

    public boolean isGood(int num, boolean isGood) {
        if (num == 0) {
            return isGood;
        }
        int d = num % 10;
        if (d == 3 || d == 4 || d == 7) {
            return false;
        }
        if (d == 0 || d == 8) {
            return isGood(num / 10, false);
        }
        return isGood(num / 10, true);

    }

    //向右变为 m * n+1
    //向下变为 m+1 * n
    public int uniquePaths(int m, int n) {
        int[][] table = new int[m][n];
        int[][] data = new int[m][n];
        for (int r = 0; r < m; r++) {
            for (int c = 0; c < n; c++) {
                if (r == 0 && c == 0) {
                    data[0][0] = 1;
                    continue;
                }
                if (r - 1 < 0) {
                    data[r][c] = data[r][c - 1];
                    continue;
                }
                if (c - 1 < 0) {
                    data[r][c] = data[r - 1][c];
                    continue;
                }
                //上边和左边都能过来
                data[r][c] = data[r][c - 1] + data[r - 1][c];
            }
        }
        return data[m - 1][n - 1];
    }

    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        if (obstacleGrid[0][0] == 1) {
            return 0;
        }
        int[][] data = new int[obstacleGrid.length][obstacleGrid[0].length];
        for (int r = 0; r < obstacleGrid.length; r++) {
            for (int c = 0; c < obstacleGrid[0].length; c++) {
                if (r == 0 && c == 0) {
                    data[0][0] = 1;
                    continue;
                }
                if (obstacleGrid[r][c] == 1) {
                    data[r][c] = 0;
                    continue;
                }
                if (r - 1 < 0) {
                    data[r][c] = data[r][c - 1];
                    continue;
                }
                if (c - 1 < 0) {
                    data[r][c] = data[r - 1][c];
                    continue;
                }
                //上边和左边都能过来
                data[r][c] = data[r][c - 1] + data[r - 1][c];
            }
        }

        return data[obstacleGrid.length - 1][obstacleGrid[0].length - 1];
    }


    public int minPathSum(int[][] grid) {
        int[][] data = new int[grid.length][grid[0].length];
        for (int r = 0; r < grid.length; r++) {
            for (int c = 0; c < grid[0].length; c++) {
                if (r == 0 && c == 0) {
                    data[0][0] = grid[0][0];
                    continue;
                }
                if (r - 1 < 0) {
                    data[r][c] = data[r][c - 1] + grid[r][c];
                    continue;
                }
                if (c - 1 < 0) {
                    data[r][c] = data[r - 1][c] + grid[r][c];
                    continue;
                }
                data[r][c] = Math.min(data[r][c - 1], data[r - 1][c]) + grid[r][c];
            }
        }
        return data[grid.length - 1][grid[0].length - 1];
    }

    public int minimumTotal0(List<List<Integer>> triangle) {
        //使用额外n方的空间
        int[][] dp = new int[triangle.size()][triangle.size()];
        dp[0][0] = triangle.get(0).get(0);
        for (int r = 1; r < triangle.size(); r++) {
            List<Integer> currentRowData = triangle.get(r);
            for (int c = 0; c < currentRowData.size(); c++) {
                if (c == 0) {
                    dp[r][c] = dp[r - 1][c] + currentRowData.get(c);
                    continue;
                }
                if (c == currentRowData.size() - 1) {
                    dp[r][c] = dp[r - 1][c - 1] + currentRowData.get(c);
                    continue;
                }
                dp[r][c] = Math.min(dp[r - 1][c], dp[r - 1][c - 1]) + currentRowData.get(c);
            }
        }
        int min = dp[triangle.size() - 1][0];
        for (int i = 0; i < triangle.size(); i++) {
            min = Math.min(min, dp[triangle.size() - 1][i]);
        }
        return min;
    }

    public int minimumTotal(List<List<Integer>> triangle) {
        for (int r = 1; r < triangle.size(); r++) {
            List<Integer> currentRowData = triangle.get(r);
            for (int c = 0; c < currentRowData.size(); c++) {
                if (c == 0) {
                    currentRowData.set(c, triangle.get(r - 1).get(c) + currentRowData.get(c));
                    continue;
                }
                if (c == currentRowData.size() - 1) {
                    currentRowData.set(c, triangle.get(r - 1).get(c - 1) + currentRowData.get(c));
                    continue;
                }
                currentRowData.set(c, Math.min(triangle.get(r - 1).get(c), triangle.get(r - 1).get(c - 1)) + currentRowData.get(c));
            }
        }
        int min = triangle.get(triangle.size() - 1).get(0);
        for (int i = 0; i < triangle.size(); i++) {
            min = Math.min(min, triangle.get(triangle.size() - 1).get(i));
        }
        return min;
    }

    /* 输入：matrix = [[2,1,3],[6,5,4],[7,8,9]]
     输出：13
     解释：下面是两条和最小的下降路径，用加粗标注：
             [[2,1,3],      [[2,1,3],
             [6,5,4],       [6,5,4],
             [7,8,9]]       [7,8,9]]*/

    /*   [[-19,57]
         [-40,-5]]  */
    public int minFallingPathSum0(int[][] matrix) {
        int[][] dp = new int[matrix.length][matrix[0].length];
        for (int r = 0; r < matrix.length; r++) {
            for (int c = 0; c < matrix[0].length; c++) {
                int curr = matrix[r][c];
                if (r == 0) {
                    dp[r][c] = curr;
                    continue;
                }
                dp[r][c] = dp[r - 1][c] + matrix[r][c];
                if (c != 0) {
                    dp[r][c] = Math.min(dp[r][c], dp[r - 1][c - 1] + matrix[r][c]);
                }
                if (c != matrix[0].length - 1) {
                    dp[r][c] = Math.min(dp[r][c], dp[r - 1][c + 1] + matrix[r][c]);
                }
            }
        }
        int min = dp[matrix.length - 1][0];
        for (int i = 0; i < dp[0].length; i++) {
            min = Math.min(min, dp[matrix.length - 1][i]);
        }
        return min;
    }

    public int minFallingPathSum(int[][] arr) {
        int[][] dp = new int[arr.length][arr[0].length];
        for (int r = 0; r < arr.length; r++) {
            for (int c = 0; c < arr[0].length; c++) {
                int curr = arr[r][c]; //当前值
                if (r == 0) {
                    //处理第一行
                    dp[r][c] = curr;
                    continue;
                }
                //获取dp除了当前列的最小值
                int minPrev = Integer.MAX_VALUE;
                for (int i = 0; i < dp[r - 1].length; i++) {
                    if (i == c) {
                        continue;
                    }
                    minPrev = Math.min(minPrev, dp[r - 1][i]);
                }
                dp[r][c] = curr + minPrev;
            }
        }
        int min = dp[arr.length - 1][0];
        for (int i = 1; i < dp[arr.length - 1].length; i++) {
            min = Math.min(min, dp[arr.length - 1][i]);
        }
        return min;
    }


    int[][] cache;
    static final int MOD = 1000000007;

    public int countRoutes0(int[] locations, int start, int finish, int fuel) {
        cache = new int[locations.length][fuel + 1];
        for (int[] row : cache) {
            Arrays.fill(row, -1);
        }
        return dfs(locations, start, finish, fuel);
    }

    public int dfs(int[] locations, int current, int finish, int leftFuel) {
        if (cache[current][leftFuel] != -1) {
            return cache[current][leftFuel];
        }
        cache[current][leftFuel] = 0;
        if (leftFuel <= 0 && current != finish) {
            cache[current][leftFuel] = 0;
            return 0;
        }
        for (int i = 0; i < locations.length; i++) {
            if (current == i) {
                continue;
            }
            int cost = Math.abs(locations[current] - locations[i]);
            if (cost > leftFuel) {
                continue;
            }
            cache[current][leftFuel] += dfs(locations, 0, finish, leftFuel - cost);
            cache[current][leftFuel] %= MOD;
        }
        if (current == finish) {
            cache[current][leftFuel] += 1;
            cache[current][leftFuel] %= MOD;
        }
        return cache[current][leftFuel];
    }


    Long[][][] cachePathNum;
    int pathMode = (int) 1e9 + 7;

    //m行 ，n列
    public int findPaths(int m, int n, int maxMove, int startRow, int startColumn) {
        cachePathNum = new Long[m][n][maxMove + 1];
        return (int) dfs(m, n, startRow, startColumn, maxMove);
    }

    public long dfs(int m, int n, int row, int col, int leftMove) {
        if (((0 > row || row >= m) || (0 > col || col >= n)) && leftMove >= 0) {
            return 1;
        }
        //结束条件
        if (leftMove <= 0) {
            //步数走完了还没出去
            return 0;
        }
        if (cachePathNum[row][col][leftMove] != null) {
            return cachePathNum[row][col][leftMove];
        }
        long result = 0;
        result += dfs(m, n, row - 1, col, leftMove - 1);
        result = result % pathMode;
        result += dfs(m, n, row + 1, col, leftMove - 1);
        result = result % pathMode;
        result += dfs(m, n, row, col - 1, leftMove - 1);
        result = result % pathMode;
        result += dfs(m, n, row, col + 1, leftMove - 1);
        result = result % pathMode;
        cachePathNum[row][col][leftMove] = result;
        return cachePathNum[row][col][leftMove];
    }

    public int findPaths1(int m, int n, int maxMove, int startRow, int startColumn) {
        int[][][] cachePathNum = new int[m][n][maxMove + 1];
        int pathMode = (int) 1e9 + 7;
        int[][] direction = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        for (int move = 1; move <= maxMove; move++) {
            for (int r = 0; r < m; r++) {
                for (int c = 0; c < n; c++) {
                    for (int dIndex = 0; dIndex < direction.length; dIndex++) {
                        int[] direc = direction[dIndex];
                        int x = r + direc[0];
                        int y = c + direc[1];
                        if (x < 0 || x >= m || y < 0 || y >= n) {
                            //find one
                            cachePathNum[r][c][move]++;
                            continue;
                        }
                        cachePathNum[r][c][move] += cachePathNum[x][y][move - 1];
                        cachePathNum[r][c][move] %= pathMode;
                    }
                }
            }
        }
        return cachePathNum[startRow][startColumn][maxMove];
    }

    int maxValue = 0;
    Map<Integer, Integer> pathMap = new HashMap<>();
    int mode = (int) 1e9 + 7;

    public int[] pathsWithMaxScore0(List<String> board) {
        char[][] charData = new char[board.size()][board.get(0).length()];
        for (int i = 0; i < board.size(); i++) {
            charData[i] = board.get(i).toCharArray();
        }
        dfs(charData, board.size() - 1, board.get(0).length() - 1, 0);
        return new int[]{maxValue, pathMap.getOrDefault(maxValue, 0)};
    }

    public void dfs(char[][] charData, int r, int c, int v) {
        if (r == 0 && c == 0) {
            //到达结尾
            int value = v % mode;
            int path = pathMap.getOrDefault(value, 0);
            pathMap.put(value, path + 1);
            maxValue = Math.max(maxValue, value);
            return;
        }
        if (charData[r][c] == 'X') {
            return;
        }
        if (Character.isDigit(charData[r][c])) {
            //如果是数字
            v += Character.digit(charData[r][c], 10);
        }
        if (r - 1 >= 0) {
            dfs(charData, r - 1, c, v); //上
        }
        if (c - 1 >= 0) {
            dfs(charData, r, c - 1, v); //左
        }
        if (r - 1 >= 0 && c - 1 >= 0) {
            dfs(charData, r - 1, c - 1, v); //左上
        }
    }

    public int[] pathsWithMaxScore(List<String> board) {
        int mode = (int) 1e9 + 7;
        char[][] charData = new char[board.size()][board.get(0).length()];
        for (int i = 0; i < board.size(); i++) {
            charData[i] = board.get(i).toCharArray();
        }
        int[][] dp = new int[board.size()][board.get(0).length()];
        int[][] route = new int[board.size()][board.get(0).length()];
        for (int i = 0; i < board.size(); i++) {
            //初始为-1  表示不可达
            Arrays.fill(dp[i], -1);
            Arrays.fill(route[i], 0);
        }
        for (int r = board.size() - 1; r >= 0; r--) {
            for (int c = board.get(0).length() - 1; c >= 0; c--) {
                if (charData[r][c] == 'X') {
                    dp[r][c] = -1;
                    route[r][c] = 0;
                    continue;
                }
                if (r == board.size() - 1 && c == board.get(0).length() - 1) {
                    dp[r][c] = 0;
                    route[r][c] = 1;
                    continue;
                }
                int max = -2;
                //from下
                if (r + 1 < board.size()) {
                    max = dp[r + 1][c];
                }
                //from右
                if (c + 1 < board.size()) {
                    max = Math.max(dp[r][c + 1], max);
                }
                //from 右下
                if (r + 1 < board.size() && c + 1 < board.size()) {
                    max = Math.max(dp[r + 1][c + 1], max);
                }

                //判断路径条数
                if (r + 1 < board.size() && max == dp[r + 1][c] && max != -1) {
                    route[r][c] += route[r + 1][c];
                }
                if (c + 1 < board.size() && max == dp[r][c + 1] && max != -1) {
                    route[r][c] += route[r][c + 1];
                }
                if (r + 1 < board.size() && c + 1 < board.size() && max == dp[r + 1][c + 1] && max != -1) {
                    route[r][c] += route[r + 1][c + 1];
                }
                max = max % mode;
                route[r][c] = route[r][c] % mode;
                if (r == 0 && c == 0) {
                    dp[r][c] = Math.max(max, 0);
                    continue;
                }
                if (max == -1) {
                    dp[r][c] = -1;
                    continue;
                }
                dp[r][c] = Character.digit(charData[r][c], 10) + max;
            }
        }
        return new int[]{dp[0][0], route[0][0]};
    }


    public int fib(int n) {
        if (n == 0) {
            return 0;
        }
        if (n == 1) {
            return 1;
        }
        int[] dp = new int[n + 1];
        dp[0] = 0;
        dp[1] = 1;
        for (int i = 2; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp[n];
    }

    public int fib1(int n) {
        if (n == 0) {
            return 0;
        }
        if (n == 1) {
            return 1;
        }
        int[] dp = new int[2];
        dp[1] = 1;
        for (int i = 2; i <= n; i++) {
            int current = dp[0] + dp[1];
            dp[0] = dp[1];
            dp[1] = current;
        }
        return dp[1];
    }

    public int tribonacci(int n) {
        if (n == 0) {
            return 0;
        }
        if (n == 1 || n == 2) {
            return 1;
        }
        int[] dp = new int[n + 1];
        dp[0] = 0;
        dp[1] = 1;
        dp[2] = 1;
        for (int i = 3; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2] + dp[i - 3];
        }
        return dp[n];
    }

    public int minCostClimbingStairs(int[] cost) {
        int[] dp = new int[cost.length + 1];
        dp[0] = 0;
        dp[1] = Math.min(cost[0], cost[1]);
        for (int i = 2; i < cost.length; i++) {
            dp[i] = Math.min(dp[i - 1] + cost[i], dp[i - 2] + cost[i - 1]);
        }
        return dp[cost.length - 1];
    }

    public int rob0(int[] nums) {
        if (nums.length == 1) {
            return nums[0];
        }
        if (nums.length == 2) {
            return Math.max(nums[0], nums[1]);
        }
        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        dp[1] = Math.max(nums[0], nums[1]);
        for (int i = 2; i < nums.length; i++) {
            dp[i] = Math.max(nums[i] + dp[i - 2], dp[i - 1]);
        }

        return dp[nums.length - 1];
    }

    public int rob(int[] nums) {
        if (nums.length == 1) {
            return nums[0];
        }
        if (nums.length == 2) {
            return Math.max(nums[0], nums[1]);
        }
        return Math.max(rob0(Arrays.copyOfRange(nums, 1, nums.length)),
                rob0(Arrays.copyOfRange(nums, 0, nums.length - 1)));
    }

    public int deleteAndEarn(int[] nums) {
        if (nums.length == 1) {
            return nums[0];
        }
        int[] data = new int[10001];
        for (int i = 0; i < nums.length; i++) {
            data[nums[i]] += nums[i];
        }
        int[] dp = new int[data.length];
        dp[0] = data[0];
        dp[1] = Math.max(data[0], data[1]);
        for (int i = 2; i < data.length; i++) {
            dp[i] = Math.max(data[i] + dp[i - 2], dp[i - 1]);
        }
        return dp[dp.length - 1];
    }

    public boolean canJump(int[] nums) {
        int canReach = 0;
        for (int index = 0; index < nums.length; index++) {
            if (index > canReach) {
                return false;
            }
            canReach = Math.max(canReach, nums[index] + index);
        }
        return false;
    }

    //2,3,1,1,4 -> 2
    public int jump(int[] nums) {
        int length = nums.length;
        int end = 0;
        int maxPosition = 0;
        int steps = 0;
        for (int i = 0; i < length - 1; i++) {
            maxPosition = Math.max(maxPosition, i + nums[i]);
            if (i == end) {
                end = maxPosition;
                steps++;
            }
        }
        return steps;
    }

    public int jump0(int[] nums) {
        int length = nums.length;
        int end = 0;
        int maxPosition = 0;
        int steps = 0;
        for (int i = 0; i < length - 1; i++) {
            maxPosition = Math.max(maxPosition, i + nums[i]);
            if (i == end) {
                end = maxPosition;
                steps++;
            }
        }
        return steps;
    }

    public String reverseVowels(String s) {
        Map<Integer, Integer> source = new HashMap<>();
        char[] vowels = {'a', 'e', 'i', 'o', 'u', 'A', 'E', 'I', 'O', 'U'};
        for (char v : vowels) {
            source.put(v - ' ', v - ' ');
        }
        char[] data = s.toCharArray();
        int start = 0;
        int end = s.length() - 1;
        while (start < end) {
            int sV = data[start] - ' ';
            int eV = data[end] - ' ';
            if (source.containsKey(sV) && source.containsKey(eV)) {
                data[start] = s.charAt(end);
                data[end] = s.charAt(start);
                start++;
                end--;
            }
            if (!source.containsKey(sV)) {
                start++;
            }
            if (!source.containsKey(eV)) {
                end--;
            }
        }
        return String.valueOf(data);
    }


    public int findContentChildren(int[] g, int[] s) {
        int sum = 0;
        Arrays.sort(g);
        Arrays.sort(s);
        int indexG = 0;
        int indexS = 0;
        while (indexG < g.length && indexS < s.length) {
            if (g[indexG] <= s[indexS]) {
                //能满足当前孩子
                indexG++;
                indexS++;
                sum++;
                continue;
            }
            indexS++;
        }
        return sum;
    }

    public int wiggleMaxLength(int[] nums) {
        if (nums.length == 1) {
            return 1;
        }
        if (nums.length == 2) {
            return nums[0] != nums[1] ? 2 : 0;
        }
        int up = 1;
        int down = 1;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] - nums[i - 1] > 0) {
                up = down + 1;
            }
            if (nums[i] - nums[i - 1] < 0) {
                down = up + 1;
            }
        }
        return Math.max(up, down);
    }

    public int integerBreak(int n) {
        //  dp[n] = max(dp[n-m] * dp[m], m * dp[n-m],j * (i - j))
        int[] dp = new int[n + 1];
        dp[0] = 0;
        dp[1] = 1;
        for (int i = 2; i <= n; i++) {
            for (int j = 1; j < i; j++) {
                dp[i] = Math.max(Math.max(Math.max(j * dp[i - j], dp[j] * dp[i - j]), j * (i - j)), dp[i]);
            }
        }
        return dp[n];
    }

    public int numTrees(int n) {
        // dp[n] = dp[n-1]+2
        int[] dp = new int[n + 1];
        dp[0] = 0;
        dp[1] = 1;
        dp[2] = 2;
        dp[3] = 5;


        return dp[n];
    }


    public int maxProduct(int[] nums) {
        int[] dpMax = new int[nums.length];
        int[] dpMin = new int[nums.length];
        dpMax[0] = nums[0];
        dpMin[0] = nums[0];
        for (int i = 1; i < nums.length; i++) {
            dpMax[i] = Math.max(dpMin[i - 1] * nums[i], Math.max(dpMax[i - 1] * nums[i], nums[i]));
            dpMin[i] = Math.min(dpMin[i - 1] * nums[i], Math.min(dpMax[i - 1] * nums[i], nums[i]));
        }
        int result = dpMax[0];
        for (int i = 1; i < nums.length; i++) {
            result = Math.max(result, dpMax[i]);
        }
        return result;
    }

    public boolean divisorGame(int n) {
        if (n == 1) {
            return false;
        }
        boolean[] dp = new boolean[n + 1];
        Arrays.fill(dp, false);
        dp[1] = false;
        dp[2] = true;
        for (int i = 3; i <= n; i++) {
            for (int j = i - 1; j >= 1; j--) {
                if ((i % j == 0) && !dp[i - j]) {
                    dp[i] = true;
                    break;
                }
            }
        }
        return dp[n];
    }


    long waysStepMode = 1000000007L;

    public int waysToStep(int n) {
        if (n == 1) {
            return 1;
        }
        if (n == 2) {
            return 2;
        }
        if (n == 3) {
            return 4;
        }
        long[] dp = new long[n + 1]; //dp[i]=dp[i-1]+dp[i-2]+dp[i-3];
        dp[1] = 1;
        dp[2] = 2;
        dp[3] = 4;
        for (int i = 4; i <= n; i++) {
            dp[i] = (dp[i - 1] + dp[i - 2] + dp[i - 3]) % waysStepMode;
        }
        return (int) (dp[n] % waysStepMode);
    }

    public int reverseBits(int num) {
        int reverse = 0;
        int current = 0;
        int max = 0;
        for (int i = 0; i < 32; i++) {
            if ((num & 1) == 1) {
                //1
                current++;
                reverse++;
            } else {
                //0
                reverse = current + 1;
                current = 0;
            }
            max = Math.max(reverse, max);
            num = num >> 1;
        }
        return max;
    }

    public int fib0(int n) {
        long mod = 1000000007L;
        if (n == 0) {
            return 0;
        }
        if (n == 1) {
            return 1;
        }
        long[] dp = new long[n + 1];
        dp[0] = 0;
        dp[1] = 1;
        for (int i = 2; i <= n; i++) {
            dp[i] = ((dp[i - 1] + dp[i - 2]) % mod);
        }
        return (int) dp[n];
    }

    public int numWays(int n) {
        long mod = 1000000007;
        if (n == 0) {
            return 1;
        }
        if (n == 1) {
            return 1;
        }
        if (n == 2) {
            return 2;
        }
        long[] dp = new long[n + 1];
        dp[0] = 1;
        dp[1] = 1;
        dp[2] = 2;
        for (int i = 3; i <= n; i++) {
            dp[i] = ((dp[i - 1] + dp[i - 2]) % mod);
        }
        return (int) dp[n];
    }

    public int massage(int[] nums) {
        if (nums.length == 1) {
            return nums[0];
        }
        if (nums.length == 2) {
            return Math.max(nums[0], nums[1]);
        }
        int[] dp = new int[nums.length];
        //dp[i] = max( nums[i]+dp[i-2], dp[i-1] )
        dp[0] = nums[0];
        dp[1] = Math.max(nums[0], nums[1]);
        for (int i = 2; i < nums.length; i++) {
            dp[i] = Math.max(nums[i] + dp[i - 2], dp[i - 1]);
        }
        return dp[nums.length - 1];
    }


    public int[] countBits(int n) {
        int[] data = new int[n + 1];
        for (int i = 0; i <= n; i++) {
            int sum = 0;
            int num = i;
            for (int j = 0; j < 32; j++) {
                if ((num & 1) == 1) {
                    sum++;
                }
                num = num >> 1;
            }
            data[i] = sum;
        }
        return data;
    }


    public int leastMinutes(int n) {
        int total = 1;
        int time = 0;
        while (total < n) {
            total = total * 2;
            time++;
        }
        return time + 1;
    }


    /**
     * @param V 最大容量
     * @param C 物品个数
     * @param v 价值数组
     * @param w 重量数组
     * @return
     */
    public int getMaxValue(int V, int C, int[] v, int[] w) {
        int[][] dp = new int[C][V + 1]; //[物品][重量]
        //初始化起始数据
        for (int fv = 0; fv <= V; fv++) {
            //初始化只有一件物品时候，如果能放入背包，则能取到此物品价值
            if (w[0] <= fv) {
                dp[0][fv] = v[0];
            }
        }
        for (int i = 1; i < C; i++) {
            for (int fv = 0; fv <= V; fv++) {
                //状态转移方程 ： dp[i][fv]   =  max( dp[i-1][fv]  ,  dp[i-1][fv-w[i]]+ v[i] )
                if (fv - w[i] >= 0) {
                    //该物品大于剩余容量，可以放入背包，此时比较放入该物品与不放该物品的值
                    dp[i][fv] = Math.max(dp[i - 1][fv], dp[i - 1][fv - w[i]] + v[i]);
                } else {
                    //改物品不能放入背包，直接取不放该物品的值
                    dp[i][fv] = dp[i - 1][fv];
                }
            }
        }
        return dp[C - 1][V];
    }


    public int getMaxValueUsingArray(int V, int C, int[] v, int[] w) {
        int[][] dp = new int[2][V + 1]; //[物品][重量]
        //初始化起始数据
        for (int fv = 0; fv <= V; fv++) {
            //初始化只有一件物品时候，如果能放入背包，则能取到此物品价值
            if (w[0] <= fv) {
                dp[0][fv] = v[0];
            }
        }
        for (int i = 1; i < C; i++) {
            for (int fv = 0; fv <= V; fv++) {
                //状态转移方程 ： dp[i][fv]   =  max( dp[i-1][fv]  ,  dp[i-1][fv-w[i]]+ v[i] )
                if (fv - w[i] >= 0) {
                    //该物品大于剩余容量，可以放入背包，此时比较放入该物品与不放该物品的值
                    dp[i & 1][fv] = Math.max(dp[(i - 1) & 1][fv], dp[(i - 1) & 1][fv - w[i]] + v[i]);
                } else {
                    //改物品不能放入背包，直接取不放该物品的值
                    dp[i & 1][fv] = dp[(i - 1) & 1][fv];
                }
            }
        }
        return dp[(C - 1) & 1][V];
    }


    public int getMaxValueUsingArray2(int V, int C, int[] v, int[] w) {
        int[] dp = new int[V + 1]; //[重量]，表示此重量时候能获得的最大价值
        //初始化起始数据
        Arrays.fill(dp, 0);
        for (int i = 0; i < C; i++) { //物品
            for (int fv = V; fv >= w[i]; fv--) { //重量
                //从后往前遍历，物品不会被重复放置
                dp[fv] = Math.max(dp[fv], dp[fv - w[i]] + v[i]);
            }
        }
        return dp[V];
    }


    @Test
    public void test11() {
        int V = 17;
        int C = 5;
        int[] v = {4, 5, 10, 11, 13};
        int[] w = {3, 4, 7, 8, 9};
        System.out.println(getMaxValueUsingArray2(V, C, v, w)); //24

        // 价值 v :  1    3    4
        // 重量 w ： 15   20   30
        // 背包容量：4

        // i :   0
        // fv ： 4     3     2     1    0
        // dp:  15    15    15    15    0

        // i :    1
        // fv ：  4     3     2     1    0
        // dp:   35    20    15    15   0

        // i :   2
        // fv ： 4     3     2     1    0
        // dp:  35    20    15    15    0
    }

    public boolean canPartition0(int[] nums) {
        int sum = 0;
        for (int n : nums) {
            sum += n;
        }
        if ((sum & 1) != 0) {
            return false;
        }
        int div = sum / 2; //容量
        int[][] dp = new int[nums.length][div + 1];  //[物品][重量]->当前值
        //只有一个，初始化背包
        for (int v = 0; v <= div; v++) {
            if (nums[0] <= v) {
                dp[0][v] = nums[0];
            }
        }
        for (int i = 1; i < nums.length; i++) {
            for (int v = 0; v <= div; v++) {
                dp[i][v] = dp[i - 1][v];
                if (dp[i][v] == div) {
                    return true;
                }
                if (nums[i] <= v) {
                    int value = dp[i - 1][v - nums[i]] + nums[i];
                    if (value == div) {
                        return true;
                    }
                    dp[i][v] = Math.max(dp[i][v], value);
                }
            }
        }
        return dp[nums.length - 1][div] == div;
    }

    public boolean canPartition(int[] nums) {
        int sum = 0;
        for (int n : nums) {
            sum += n;
        }
        if ((sum & 1) != 0) {
            return false;
        }
        int div = sum / 2; //容量
        int[] dp = new int[div + 1];  //[重量]->当前值
        for (int i = 0; i < nums.length; i++) {
            for (int v = div; v >= nums[i]; v--) {
                if (dp[v] == div) {
                    return true;
                }
                if (dp[v - nums[i]] + nums[i] == div) {
                    return true;
                }
                dp[v] = Math.max(dp[v], dp[v - nums[i]] + nums[i]);
            }
        }
        return dp[div] == div;
    }


    @Test
    public void testCanPartition() {
        int[] nums = {1, 5, 11, 5};
        System.out.println(canPartition(nums));
    }

    public int lastStoneWeightII(int[] stones) {
        int sum = 0;
        for (int s : stones) {
            sum += s;
        }
        int div = sum / 2;
        int[] dp = new int[div + 1];
        for (int i = 0; i < stones.length; i++) {
            for (int v = div; stones[i] <= v; v--) {
                dp[v] = Math.max(dp[v], dp[v - stones[i]] + stones[i]);
            }
        }
        return sum - 2 * dp[div];
    }


    @Test
    public void testLastStoneWeightII() {
        int[] nums = {31, 26, 33, 21, 40}; //  21 26 31 33 40
        System.out.println(lastStoneWeightII(nums));
    }

    public int findTargetSumWays(int[] nums, int target) {
        int sum = 0;
        for (int s : nums) {
            sum += s;
        }
        if (target > sum) {
            return 0;
        }
        //s1 = sum-s2
        //s1+s2 = sum
        //s1-s2 = target -> sum-2*s2=target -> s2=(sum-target)/2
        if (((sum - target) % 2) == 1) {
            return 0;
        }
        int s2 = (sum - target) / 2;
        int[] dp = new int[s2 + 1];  //填满V一共有多少种方法
        dp[0] = 1;
        for (int i = 0; i < nums.length; i++) {
            for (int v = s2; nums[i] <= v; v--) {
                dp[v] += dp[v - nums[i]];
            }
        }
        return dp[s2];
    }

    @Test
    public void testFindTargetSumWays() {
        int[] nums = {1, 0}; //
        int target = 1;
        System.out.println(findTargetSumWays(nums, target));
    }


    /**
     * @param strs
     * @param m    m 个 0
     * @param n    n 个 1
     * @return strs 的最大子集的大小
     */
    public int findMaxForm(String[] strs, int m, int n) {
        //先分析下。。。。。分析完了，分析过程省略，懒得写
        int[][] dp = new int[m + 1][n + 1];
        for (int i = 0; i < strs.length; i++) {
            int n0 = count01Num(strs[i], 0);
            int n1 = count01Num(strs[i], 1);
            for (int vm = m; vm >= n0; vm--) {
                for (int vn = n; vn >= n1; vn--) {
                    dp[vm][vn] = Math.max(dp[vm][vn], dp[vm - n0][vn - n1] + 1);
                }
            }
        }
        return dp[m][n];
    }

    private int count01Num(String s, int n) {
        char target = Character.forDigit(n, 9);
        int total = 0;
        for (char c : s.toCharArray()) {
            if (c == target) {
                total++;
            }
        }
        return total;
    }


    @Test
    public void testFindMaxForm() {
       /* String[] strs = {"10", "0001", "111001", "1", "0"};
        int m = 5;
        int n = 3;
        System.out.println(findMaxForm(strs, m, n));*/

        //["10","0","1"]
        //1
        //1
        String[] strs = {"10", "0", "1"};
        int m = 1;
        int n = 1;
        System.out.println(findMaxForm(strs, m, n));
    }


    /**
     * @param V 最大容量
     * @param C 物品个数
     * @param v 价值数组
     * @param w 重量数组
     * @return
     */
    public int getMaxValue1(int V, int C, int[] v, int[] w) {
        int[][] dp = new int[C][V + 1]; //[物品][重量]  ->  最大价值
        //初始化起始数据
        for (int fv = 0; fv <= V; fv++) {
            //初始化只有一件物品时候，使劲往里面放吧，能放几件放几件
            if (w[0] <= fv) {
                int N = fv / w[0];
                dp[0][fv] = N * v[0];
            }
        }
        for (int i = 1; i < C; i++) {
            for (int fv = 0; fv <= V; fv++) {
                //假设放入N个当前物品
                //状态转移方程 ： dp[i][fv]   =  max( dp[i-1][fv]  ,  dp[i-1][ fv- N*w[i] ] + N*v[i] )
                if (fv - w[i] >= 0) {
                    int N = fv / w[i];
                    //该物品大于剩余容量，可以放入背包，此时比较放入该物品与不放该物品的值
                    dp[i][fv] = Math.max(dp[i - 1][fv], dp[i - 1][fv - N * w[i]] + N * v[i]);
                } else {
                    //改物品不能放入背包，直接取不放该物品的值
                    dp[i][fv] = dp[i - 1][fv];
                }
            }
        }
        return dp[C - 1][V];
    }


    /**
     * @param V 最大容量
     * @param C 物品个数
     * @param v 价值数组
     * @param w 重量数组
     * @return
     */
    public int getMaxValue2(int V, int C, int[] v, int[] w) {
        int[] dp = new int[V + 1]; //[重量]  ->  最大价值
        for (int i = 0; i < C; i++) {
            for (int fv = 0; fv <= V; fv++) {
                if (fv - w[i] >= 0) {
                    dp[fv] = Math.max(dp[fv], dp[fv - w[i]] + v[i]);
                }
            }
        }
        return dp[V];
    }


    @Test
    public void testgetMaxValue1() {
        int V = 4;
        int C = 3;
        int[] v = {15, 20, 30};
        int[] w = {1, 3, 4};
        System.out.println(JSONObject.toJSONString(getMaxValue2(V, C, v, w))); //60
    }

    public int change(int amount, int[] coins) {
        int[] dp = new int[amount + 1]; // [重量] -> 方法数
        dp[0] = 1; //重量为0的时候，只有一种方法
        for (int i = 0; i < coins.length; i++) {
            for (int v = 1; v <= amount; v++) {
                if (coins[i] <= v) {
                    dp[v] += dp[v - coins[i]];
                }
            }
        }
        return dp[amount];
    }

    @Test
    public void testchange() {
        int amount = 5;
        int[] coins = {1, 2, 5};   //4
        System.out.println(change(amount, coins));
    }

    public int combinationSum4(int[] nums, int target) {
        int[] dp = new int[target + 1];
        dp[0] = 1;

        for (int v = 1; v <= target; v++) {
            for (int i = 0; i < nums.length; i++) {
                if (nums[i] <= v) {
                    dp[v] += dp[v - nums[i]];
                }
            }
        }
        return dp[target];
    }

    @Test
    public void testcombinationSum4() {
        int[] nums = {1, 2, 3};
        int target = 4;
        System.out.println(combinationSum4(nums, target)); // 7
    }

    /**
     * @param coins
     * @param amount
     * @return 计算并返回可以凑成总金额所需的最少的硬币个数 。如果没有任何一种硬币组合能组成总金额，返回 -1
     */
    public int coinChange(int[] coins, int amount) {
        int[] dp = new int[amount + 1]; //值为所需要硬币数
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;
        for (int i = 0; i < coins.length; i++) {
            for (int v = 1; v <= amount; v++) {
                if (v >= coins[i] && dp[v - coins[i]] != Integer.MAX_VALUE) {
                    dp[v] = Math.min(dp[v - coins[i]] + 1, dp[v]);
                }
            }
        }
        return dp[amount] == Integer.MAX_VALUE ? -1 : dp[amount];
    }

    @Test
    public void testcoinChange() {
        int[] coins = {1, 2, 5};
        int amount = 11;
        System.out.println(coinChange(coins, amount)); //3
    }

    /**
     * 给定正整数 n，找到若干个完全平方数（比如 1, 4, 9, 16, ...）使得它们的和等于 n。
     * 你需要让组成和的完全平方数的个数最少。
     * <p>
     * 给你一个整数 n ，返回和为 n 的完全平方数的 最少数量 。
     * <p>
     * 完全平方数 是一个整数，其值等于另一个整数的平方；
     * 换句话说，其值等于一个整数自乘的积。例如，1、4、9 和 16 都是完全平方数，而 3 和 11 不是。
     *
     * @param n
     * @return
     */
    public int numSquares(int n) {
        int[] dp = new int[n + 1]; //最少数量
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;
        //外层遍历背包，内层遍历物品
        for (int v = 1; v <= n; v++) {
            for (int i = 0; i * i <= v; i++) {
                if (dp[v - i * i] != Integer.MAX_VALUE) {
                    dp[v] = Math.min(dp[v], dp[v - i * i] + 1);
                }
            }
        }
        return dp[n] == Integer.MAX_VALUE ? -1 : dp[n];
    }

    @Test
    public void testnumSquares() {
        int n = 12;
        System.out.println(numSquares(n)); //3
    }


    public boolean wordBreak(String s, List<String> wordDict) {
        boolean[] dp = new boolean[s.length() + 1]; //长度为i时候背包能否被物品装满了
        dp[0] = true; //初始状态，空字符始终为true
        //先遍历背包，再遍历物品
        for (int v = 1; v <= s.length(); v++) {
            for (int i = 0; i < v; i++) {
                if (wordDict.contains(s.substring(i, v)) && dp[i]) {
                    dp[v] = true;
                }
            }
        }
        return dp[s.length()];
    }

    @Test
    public void testwordBreak() {
        String s = "applepenapple";
        List<String> wordDict = Lists.newArrayList("apple", "pen");
        //"applepenapple", wordDict = ["apple", "pen"]
        System.out.println(wordBreak(s, wordDict));
    }

    /**
     * @param V 最大容量
     * @param C 物品个数
     * @param v 价值数组
     * @param w 重量数组
     * @param s 物品个数
     * @return
     */
    public int getMaxValueWithLimitNums(int V, int C, int[] v, int[] w, int[] s) {
        int[] dp = new int[V + 1];
        for (int i = 0; i < C; i++) {  //遍历物品
            for (int fv = V; fv >= w[i]; fv--) {  //遍历重量
                for (int n = 1; n <= s[i] && fv - n * w[i] >= 0; n++) {
                    dp[fv] = Math.max(dp[fv], dp[fv - n * w[i]] + n * v[i]);
                }
            }
        }
        return dp[V];
    }

    @Test
    public void testgetMaxValueWithLimitNums() {
        int V = 5;
        int C = 2;
        int[] v = {1, 2};
        int[] w = {1, 2};
        int[] s = {2, 1};
        System.out.println(getMaxValueWithLimitNums(V, C, v, w, s));
    }


    /**
     * 最多完成两笔交易
     *
     * @param prices
     * @return
     */
    public int maxProfit0(int[] prices) {
        int buy1 = -prices[0];
        int sell1 = 0;
        int buy2 = -prices[0];
        int sell2 = 0;
        for (int i = 1; i < prices.length; i++) {
            buy1 = Math.max(buy1, -prices[i]);
            sell1 = Math.max(sell1, prices[i] + buy1);
            buy2 = Math.max(buy2, sell1 - prices[i]);
            sell2 = Math.max(sell2, prices[i] + buy2);
        }
        return sell2;
    }

    /**
     * 给定一个整数数组 prices ，它的第 i 个元素 prices[i] 是一支给定的股票在第 i 天的价格。
     * <p>
     * 设计一个算法来计算你所能获取的最大利润。你最多可以完成 k 笔交易。
     * <p>
     * 注意：你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）
     *
     * @param k
     * @param prices
     * @return
     */
    public int maxProfit(int k, int[] prices) {
        if (prices.length == 0 || k == 0) {
            return 0;
        }
        int[] buy = new int[k];
        int[] sell = new int[k];
        Arrays.fill(buy, -prices[0]);
        for (int i = 1; i < prices.length; i++) {
            for (int n = 0; n < k; n++) {
                if (n == 0) {
                    buy[n] = Math.max(buy[n], -prices[i]);
                } else {
                    buy[n] = Math.max(buy[n], sell[n - 1] - prices[i]);
                }
                sell[n] = Math.max(sell[n], prices[i] + buy[n]);
            }
        }
        return sell[k - 1];
    }

    @Test
    public void testmaxProfit2() {
        int k = 2;
        int[] prices = {2, 4, 1};
        System.out.println(maxProfit(k, prices)); //2
    }

    /**
     * 给定一个整数数组，其中第 i 个元素代表了第 i 天的股票价格 。​
     * <p>
     * 设计一个算法计算出最大利润。在满足以下约束条件下，你可以尽可能地完成更多的交易（多次买卖一支股票）:
     * <p>
     * 你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
     * 卖出股票后，你无法在第二天买入股票 (即冷冻期为 1 天)
     *
     * @param prices
     * @return
     */
    public int maxProfit(int[] prices) {
        int[][] dp = new int[prices.length][4]; //第i天状态为j -》所剩的最多现金
        //状态定义---
        //0: 买入了股票
        //1：卖出了股票，度过了冷却期一直没操作
        //2：今天卖出了股票
        //3：今天是冷却期间
        //初始化操作---
        dp[0][0] = -prices[0];
        dp[0][1] = 0;
        dp[0][2] = 0;
        dp[0][3] = 0;
        //---过程
        for (int i = 1; i < prices.length; i++) {
            //0: 买入了股票
            //--1:前一天就买入了
            //--2:今天才买入了股票[前一天是冷却期/前一天就保持卖出状态来着]
            dp[i][0] = Math.max(dp[i - 1][0],
                    Math.max(dp[i - 1][3] - prices[i],
                            dp[i - 1][1] - prices[i]));
            //1:卖出了股票
            //--1:两天前就卖出了股票，度过了冷冻期，一直没操作，今天保持卖出股票状态
            //--2:今天卖出了股票
            dp[i][1] = Math.max(dp[i - 1][3], dp[i - 1][1]);
            //2:今天卖出了股票=>昨天买入了股票
            dp[i][2] = dp[i - 1][0] + prices[i];
            //3:今天是冷却期
            dp[i][3] = dp[i - 1][2];
        }
        return Math.max(dp[prices.length - 1][1], Math.max(dp[prices.length - 1][2], dp[prices.length - 1][3]));
    }

    @Test
    public void testmaxProfit3() {
        int[] prices = {1, 2, 3, 0, 2};//3
        System.out.println(maxProfit(prices));
    }


    /**
     * 给定一个整数数组 prices，其中第 i 个元素代表了第 i 天的股票价格 ；整数 fee 代表了交易股票的手续费用。
     * <p>
     * 你可以无限次地完成交易，但是你每笔交易都需要付手续费。如果你已经购买了一个股票，在卖出它之前你就不能再继续购买股票了。
     * <p>
     * 返回获得利润的最大值。
     * <p>
     * 注意：这里的一笔交易指买入持有并卖出股票的整个过程，每笔交易你只需要为支付一次手续费。
     *
     * @param prices
     * @param fee
     * @return
     */
    public int maxProfit(int[] prices, int fee) {
        int status0;
        int status1;
        //0 ：持有股票；1：不持有股票
        status0 = -prices[0];
        status1 = 0;
        for (int i = 1; i < prices.length; i++) {
            status0 = Math.max(status1 - prices[i], status0);
            status1 = Math.max(status0 + prices[i] - fee, status1);
        }
        return Math.max(status0, status1);
    }

    @Test
    public void testmaxProfit4() {
        int[] prices = {1, 3, 2, 8, 4, 9};//3
        int fee = 2;
        System.out.println(maxProfit(prices, fee));
    }

    public int findLengthOfLCIS(int[] nums) {
        int result = 1;
        int[] dp = new int[nums.length];
        Arrays.fill(dp, 1);
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] > nums[i - 1]) {
                dp[i] = dp[i - 1] + 1;
            }
            result = Math.max(result, dp[i]);
        }
        return result;
    }

    public int findLengthOfLCIS1(int[] nums) {
        int result = 1;
        int n0 = 1;
        int n1 = 1;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] > nums[i - 1]) {
                n1 = n0 + 1;
            }
            n0 = n1;
            result = Math.max(result, n1);
        }
        return result;
    }

    public int maxSubArray(int[] nums) {
       /* int[] dp = new int[nums.length];
        dp[0] = nums[0];
        int result = Integer.MIN_VALUE;
        for (int i = 1; i < nums.length; i++) {
            if (dp[i - 1] < 0) {
                dp[i] = nums[i];
            } else {
                dp[i] = dp[i - 1] + nums[i];
            }
            result = Math.max(result, dp[i]);
        }
        return Math.max(result, dp[0]);*/

        int prev = nums[0];
        int result = Integer.MIN_VALUE;
        for (int i = 1; i < nums.length; i++) {
            int current;
            if (prev < 0) {
                current = nums[i];
            } else {
                current = prev + nums[i];
            }
            result = Math.max(result, current);
            prev = current;
        }
        return Math.max(result, prev);
    }

    public int trap(int[] height) {
        int[] dpLeftMax = new int[height.length];
        dpLeftMax[0] = height[0];
        for (int i = 1; i < height.length; i++) {
            dpLeftMax[i] = Math.max(height[i], dpLeftMax[i - 1]);
        }
        int[] dpRightMax = new int[height.length];
        dpRightMax[height.length - 1] = height[height.length - 1];
        for (int i = height.length - 2; i >= 0; i--) {
            dpRightMax[i] = Math.max(height[i], dpRightMax[i + 1]);
        }
        int result = 0;
        for (int i = 0; i < height.length; i++) {
            result += Math.min(dpLeftMax[i], dpRightMax[i]) - height[i];
        }
        return result;
    }

    public int longestCommonSubsequence(String text1, String text2) {
        int[][] dp = new int[text1.length()][text2.length()]; //[i][j] =》 长度为i，长度为j时候最长公共子序列长度
        dp[0][0] = text1.charAt(0) == text2.charAt(0) ? 1 : 0;
        for (int i = 1; i < text1.length(); i++) {
            if (text1.charAt(i) == text2.charAt(0)) {
                dp[i][0] = 1;
            } else {
                dp[i][0] = dp[i - 1][0];
            }
        }
        for (int j = 1; j < text2.length(); j++) {
            if (text1.charAt(0) == text2.charAt(j)) {
                dp[0][j] = 1;
            } else {
                dp[0][j] = dp[0][j - 1];
            }
        }
        for (int i = 0; i < text1.length(); i++) {
            for (int j = 0; j < text2.length(); j++) {
                if (i == 0 || j == 0) {
                    continue;
                }
                if (text1.charAt(i) == text2.charAt(j)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }
        return dp[text1.length() - 1][text2.length() - 1];
    }

    public String findLongestWord(String s, List<String> dictionary) {
        int maxLength = 0;
        String result = "";
        for (String d : dictionary) {
            boolean canFind = findLength(s, d);
            if (!canFind) {
                continue;
            }
            //find
            if (d.length() >= maxLength) {
                maxLength = d.length();
                if (result.equals("")) {
                    result = d;
                    continue;
                }
                if (d.length() == result.length()) {
                    result = d.compareTo(result) < 0 ? d : result;
                    continue;
                }
                result = d;
            }
        }
        return result;
    }

    public boolean findLength(String s, String d) {
        boolean result = false;
        int dIndex = 0;
        int sIndex = 0;
        while (dIndex < d.length()) {
            result = false;
            while (sIndex < s.length()) {
                if (s.charAt(sIndex) == d.charAt(dIndex)) {
                    result = true;
                    break;
                }
                sIndex++;
            }
            if (!result || (sIndex > s.length() - 1)) {
                return false;
            }
            dIndex++;
            sIndex++;
        }
        return result;
    }


    @Test
    public void testfindLongestWord() {
        //"abpcplea"
        //["ale","apple","monkey","plea", "abpcplaaa","abpcllllll","abccclllpppeeaaaa"]
        /*String s = "abpcplea";
        List<String> dictionary = Lists.newArrayList("ale", "apple", "monkey", "plea", "abpcplaaa", "abpcllllll", "abccclllpppeeaaaa");
        */

        //String s = "abpcplea";
        //List<String> dictionary = Lists.newArrayList("ale", "apple", "monkey", "plea");


        //String s = "abpcplea";
        // List<String> dictionary = Lists.newArrayList("ale", "apple", "monkey", "plea", "abpcplaaa", "abpcllllll", "abccclllpppeeaaaa"); //apple

        //String s = "aewfafwafjlwajflwajflwafj";
        //List<String> dictionary = Lists.newArrayList("apple", "ewaf", "awefawfwaf", "awef", "awefe", "ewafeffewafewf"); //"ewaf"

        String s = "aaa";
        List<String> dictionary = Lists.
                newArrayList("aaa", "aa", "a");
        System.out.println(findLongestWord(s, dictionary));
    }


    /**
     * 给两个整数数组 A 和 B ，返回两个数组中公共的、长度最长的子数组的长度。
     * 输入：
     * A: [1,2,3,2,1]
     * B: [3,2,1,4,7]
     * 输出：3
     * 解释：
     * 长度最长的公共子数组是 [3, 2, 1] 。
     *
     * @param nums1
     * @param nums2
     * @return
     */
    public int findLength(int[] nums1, int[] nums2) {
        /*int result = 0;
        int[][] dp = new int[nums1.length + 1][nums2.length + 1]; // [i][j] -》最长长度
        //初始化完成，开始规划
        for (int i = nums1.length - 1; i >= 0; i--) {
            for (int j = nums2.length - 1; j >= 0; j--) {
                if (nums1[i] == nums2[j]) {
                    dp[i][j] = dp[i + 1][j + 1] + 1;
                } else {
                    dp[i][j] = 0;
                }
                result = Math.max(result, dp[i][j]);
            }
        }
        return result;*/
        int result = 0;
        int prev = 0;
        //初始化完成，开始规划
        for (int i = nums1.length - 1; i >= 0; i--) {
            for (int j = nums2.length - 1; j >= 0; j--) {
                if (nums1[i] == nums2[j]) {
                    prev = prev + 1;
                } else {
                    prev = 0;
                }
                result = Math.max(result, prev);
            }
        }
        return result;
    }

    @Test
    public void testfindLength() {
        //[0,1,1,1,1]
        //[1,0,1,0,1]
        //2

        int[] nums1 = {0, 1, 1, 1, 1};
        int[] nums2 = {1, 0, 1, 0, 1};
        System.out.println(findLength(nums1, nums2));


    }

    /**
     * 在两条独立的水平线上按给定的顺序写下 nums1 和 nums2 中的整数。
     * <p>
     * 现在，可以绘制一些连接两个数字 nums1[i] 和 nums2[j] 的直线，这些直线需要同时满足满足：
     * <p>
     *  nums1[i] == nums2[j]
     * 且绘制的直线不与任何其他连线（非水平线）相交。
     * 请注意，连线即使在端点也不能相交：每个数字只能属于一条连线。
     * <p>
     * 以这种方法绘制线条，并返回可以绘制的最大连线数。
     *
     * @param nums1
     * @param nums2
     * @return
     */
    public int maxUncrossedLines(int[] nums1, int[] nums2) {
        int[][] dp = new int[nums1.length + 1][nums2.length + 1];
        for (int i = 1; i <= nums1.length; i++) {
            for (int j = 1; j <= nums2.length; j++) {
                if (nums1[i - 1] == nums2[j - 1]) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }
        return dp[nums1.length][nums2.length];
    }

    /**
     * 给定一个字符串 s 和一个字符串 t ，计算在 s 的子序列中 t 出现的个数。
     * <p>
     * 字符串的一个 子序列 是指，通过删除一些（也可以不删除）字符且不干扰剩余字符相对位置所组成的新字符串。
     * （例如，"ACE" 是 "ABCDE" 的一个子序列，而 "AEC" 不是）
     * <p>
     * 题目数据保证答案符合 32 位带符号整数范围。
     *
     * @param s
     * @param t
     * @return
     */
    public int numDistinct(String s, String t) {
        int[][] dp = new int[s.length() + 1][t.length() + 1]; // [i][j] 长度时候的个数
        for (int i = 0; i < s.length(); i++) {
            //t为空字符时候，全是1
            dp[i][0] = 1;
        }
        for (int i = 1; i <= s.length(); i++) {
            for (int j = 1; j <= t.length(); j++) {
                if (s.charAt(i - 1) == t.charAt(j - 1)) {
                    //当最后一个字符相等时候
                    //1,取前面的个数
                    //2,去掉最后一个字符的匹配数，即可以用最后一个匹配，也可以不用
                    dp[i][j] = dp[i - 1][j - 1] + dp[i - 1][j];
                } else {
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }
        return dp[s.length()][t.length()];
    }

    /**
     * 给定两个单词 word1 和 word2，找到使得 word1 和 word2 相同所需的最小步数，
     * 每步可以删除任意一个字符串中的一个字符。
     * 输入: "sea", "eat"
     * 输出: 2
     * 解释: 第一步将"sea"变为"ea"，第二步将"eat"变为"ea"
     *
     * @param word1
     * @param word2
     * @return
     */
    public int minDistance0(String word1, String word2) {
        //思路：找出最长公共子序列长度，然后两个字符分别减去该长度
        int[][] dp = new int[word1.length() + 1][word2.length() + 1];
        for (int i = 1; i <= word1.length(); i++) {
            for (int j = 1; j <= word2.length(); j++) {
                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }
        return word1.length() + word2.length() - 2 * dp[word1.length()][word2.length()];
    }


    @Test
    public void testnumDistinct() {
        //输入：s = "rabbbit", t = "rabbit"
        //输出：3
        //解释：
        //如下图所示, 有 3 种可以从 s 中得到 "rabbit" 的方案。
        //rabbbit
        //rabbbit
        //rabbbit
        //
        String s = "rabbbit";
        String t = "rabbit";
        System.out.println(numDistinct(s, t));
    }


    @Test
    public void testmaxUncrossedLines() {
        //2
        int[] nums1 = {1, 4, 2};
        int[] nums2 = {1, 2, 4};
        System.out.println(maxUncrossedLines(nums1, nums2));
    }


    /**
     * 给你两个单词 word1 和 word2，请你计算出将 word1 转换成 word2 所使用的最少操作数 。
     * <p>
     * 你可以对一个单词进行如下三种操作：
     * <p>
     * 插入一个字符
     * 删除一个字符
     * 替换一个字符
     *
     * @param word1
     * @param word2
     * @return
     */
    public int minDistance(String word1, String word2) {
        int result = 0;
        //插入一个字符: 1
        //删除一个字符：2
        //替换一个字符：3
        int[][] dp = new int[word1.length() + 1][word2.length() + 1]; //[i][j][如何变动]时候最小变动量
        //初始化
        for (int i = 0; i <= word1.length(); i++) {
            dp[i][0] = i;
        }
        for (int j = 0; j <= word2.length(); j++) {
            dp[0][j] = j;
        }
        for (int i = 1; i <= word1.length(); i++) {
            for (int j = 1; j <= word2.length(); j++) {
                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    //无需编辑
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    //不相等了，这时候需要编辑
                    //1,word1删除一个元素
                    dp[i][j] = dp[i - 1][j] + 1;
                    //2,word2删除一个元素
                    dp[i][j] = Math.min(dp[i][j - 1] + 1, dp[i][j]);
                    //3,word1替换一个元素
                    dp[i][j] = Math.min(dp[i - 1][j - 1] + 1, dp[i][j]);
                }
            }
        }
        return dp[word1.length()][word2.length()];
    }

    @Test
    public void testminDistance() {
        //输入：word1 = "horse", word2 = "ros"
        //输出：3

        //输入：word1 = "intention", word2 = "execution"
        //输出：5

        String word1 = "horse";
        String word2 = "ros";
        System.out.println(minDistance(word1, word2));//3


    }

    /**
     * 给你一个字符串 s ，请你统计并返回这个字符串中 回文子串 的数目。
     * <p>
     * 回文字符串 是正着读和倒过来读一样的字符串。
     * <p>
     * 子字符串 是字符串中的由连续字符组成的一个序列。
     * <p>
     * 具有不同开始位置或结束位置的子串，即使是由相同的字符组成，也会被视作不同的子串。
     * <p>
     * 输入：s = "abc"
     * 输出：3
     * 解释：三个回文子串: "a", "b", "c"
     * <p>
     * 输入：s = "aaa"
     * 输出：6
     * 解释：6个回文子串: "a", "a", "a", "aa", "aa", "aaa"
     *
     * @param s
     * @return
     */
    public int countSubstrings(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        int sum = 0;
        //一个也是回文字串
        boolean[][] dp = new boolean[s.length()][s.length()]; //[i][j]范围内是回文子串
        // [i][j] :表示从i-j这段内是不是回文串
        for (int j = 0; j < s.length(); j++) {
            for (int i = 0; i <= j; i++) {
                if (s.charAt(i) == s.charAt(j)) {
                    if ((i == j) || (i + 1 == j)) {
                        dp[i][j] = true;
                        sum++;
                        continue;
                    }
                    dp[i][j] = dp[i + 1][j - 1];
                    if (dp[i][j]) {
                        sum++;
                    }
                } else {
                    dp[i][j] = false;
                }
            }
        }
        return sum;
    }

    @Test
    public void testcountSubstrings() {
        //String s = "aaa";  //6
        String s = "aaaaa";  //15
        System.out.println(countSubstrings(s));
    }

    /**
     * 给你一个字符串 s ，找出其中最长的回文子序列，并返回该序列的长度。
     * <p>
     * 子序列定义为：不改变剩余字符顺序的情况下，删除某些字符或者不删除任何字符形成的一个序列。
     * <p>
     * 输入：s = "bbbab"
     * 输出：4
     * 解释：一个可能的最长回文子序列为 "bbbb" 。
     * <p>
     * <p>
     * 输入：s = "cbbd"
     * 输出：2
     * 解释：一个可能的最长回文子序列为 "bb" 。
     *
     * @param s
     * @return
     */
    public int longestPalindromeSubseq(String s) {
        int maxLength = 0;
        if (s == null || s.length() == 0) {
            return maxLength;
        }
        int[][] dp = new int[s.length()][s.length()]; //[i][j]范围内回文子串最长长度
        for (int j = 0; j < s.length(); j++) {
            for (int i = j; i >= 0; i--) {
                if (s.charAt(i) == s.charAt(j)) {
                    if (i == j) {
                        dp[i][j] = 1;
                        maxLength = Math.max(maxLength, dp[i][j]);
                        continue;
                    }
                    if (i + 1 == j) {
                        dp[i][j] = 2;
                        maxLength = Math.max(maxLength, dp[i][j]);
                        continue;
                    }
                    dp[i][j] = dp[i + 1][j - 1] + 2;
                    maxLength = Math.max(maxLength, dp[i][j]);
                } else {
                    if (i + 1 < s.length()) {
                        dp[i][j] = dp[i + 1][j];
                    }
                    if (j - 1 > 0) {
                        dp[i][j] = Math.max(dp[i][j - 1], dp[i][j]);
                    }
                    maxLength = Math.max(maxLength, dp[i][j]);
                }
            }
        }
        return maxLength;
    }


    @Test
    public void testlongestPalindromeSubseq() {
        //String s = "bbbab";  //4
        //String s = "abcabcabcabc";  //7
        String s = "euazbipzncptldueeuechubrcourfpftcebikrxhybkymimgvldiwqvkszfycvqyvtiwfckexmowcxztkfyzqovbtmzpxojfofbvwnncajvrvdbvjhcrameamcfmcoxryjukhpljwszknhiypvyskmsujkuggpztltpgoczafmfelahqwjbhxtjmebnymdyxoeodqmvkxittxjnlltmoobsgzdfhismogqfpfhvqnxeuosjqqalvwhsidgiavcatjjgeztrjuoixxxoznklcxolgpuktirmduxdywwlbikaqkqajzbsjvdgjcnbtfksqhquiwnwflkldgdrqrnwmshdpykicozfowmumzeuznolmgjlltypyufpzjpuvucmesnnrwppheizkapovoloneaxpfinaontwtdqsdvzmqlgkdxlbeguackbdkftzbnynmcejtwudocemcfnuzbttcoew";  //159
        //String s = "aabaa"; //5
        System.out.println(longestPalindromeSubseq(s));
    }


    /**
     * 给你一个只包含 '(' 和 ')' 的字符串，找出最长有效（格式正确且连续）括号子串的长度。
     * <p>
     * 输入：s = "(()"
     * 输出：2
     * 解释：最长有效括号子串是 "()"
     * <p>
     * <p>
     * <p>
     * 输入：s = ")()())"
     * 输出：4
     * 解释：最长有效括号子串是 "()()"
     *
     * @param s
     * @return
     */
    public int longestValidParentheses(String s) {
        int maxLength = 0;
        if (s == null || s.length() == 0) {
            return maxLength;
        }
        int[][] dp = new int[s.length()][s.length()];
        for (int i = 0; i < s.length() - 1; i++) {
            for (int j = i + 1; j < s.length(); j++) {
                if (s.charAt(j - 1) == '(' && s.charAt(j) == ')') {
                    if (j - 2 >= 0) {
                        dp[i][j] = dp[i][j - 2] + 2;
                    } else if (i + 1 == j) {
                        dp[i][j] = 2;
                    } else {
                        dp[i][j] = 0;
                    }

                } else {
                    dp[i][j] = dp[i][j - 1];
                }
                maxLength = Math.max(maxLength, dp[i][j]);
            }
        }
        return maxLength;
    }


    @Test
    public void testlongestValidParentheses() {
        //String s = "()"; //2
        String s = "()(())"; //6
        System.out.println(longestValidParentheses(s));
    }


    /**
     * 给定一个整数数组  nums，求出数组从索引 i 到 j（i ≤ j）范围内元素的总和，包含 i、j 两点。
     * <p>
     * 实现 NumArray 类：
     * <p>
     * NumArray(int[] nums) 使用数组 nums 初始化对象
     * int sumRange(int i, int j) 返回数组 nums 从索引 i 到 j（i ≤ j）范围内元素的总和，
     * 包含 i、j 两点（也就是 sum(nums[i], nums[i + 1], ... , nums[j])）
     */
    class NumArray {
        int[] dp;
        int[] nums;

        public NumArray(int[] nums) {
            this.nums = nums;
            dp = new int[nums.length];
            dp[0] = nums[0];
            for (int i = 1; i < nums.length; i++) {
                dp[i] = dp[i - 1] + nums[i];
            }
        }

        public int sumRange(int left, int right) {
            return dp[right] - dp[left] + nums[left];
        }
    }


    public int maximalSquare(char[][] matrix) {
        int maxSize = 0;
        int[][] dp = new int[matrix.length][matrix[0].length];  //[i][j] : 以[i][j]为起点最大正方形
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {


            }
        }
        return maxSize;
    }

    @Test
    public void testmaximalSquare() {
        //输入：matrix = [
        // ["1","0","1","0","0"],
        // ["1","0","1","1","1"],
        // ["1","1","1","1","1"],
        // ["1","0","0","1","0"]
        // ]
        //输出：4
        //
        //输入：matrix = [["0","1"],["1","0"]]
        //输出：1
        char[][] matrix = {};
        System.out.println(maximalSquare(matrix));
    }


    /**
     * 给定一个未排序的整数数组，找到最长递增子序列的个数。
     * 输入: [1,3,5,4,7]
     * 输出: 2
     * 解释: 有两个最长递增子序列，分别是 [1, 3, 4, 7] 和[1, 3, 5, 7]。
     * <p>
     * 输入: [2,2,2,2,2]
     * 输出: 5
     * 解释: 最长递增子序列的长度是1，并且存在5个子序列的长度为1，因此输出5。
     *
     * @param nums
     * @return
     */
    public int findNumberOfLIS(int[] nums) {
        int[] dp = new int[nums.length]; //以i数字结尾的最长递增序列长度
        int[] count = new int[nums.length];//最长出现次数
        Arrays.fill(count, 1);
        for (int i = 1; i < nums.length; i++) {
            dp[i] = 1;
            for (int j = 0; j < i; j++) {
                //区间 ： j - i （以i结尾，包含i）
                if (nums[j] < nums[i]) {
                    if (dp[i] >= dp[j] + 1) {


                    } else {

                    }
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
        }
        int maxLength = 0;
        for (int i = 0; i < nums.length; i++) {
            maxLength = Math.max(maxLength, dp[i]);
        }
        int total = 0;
        for (int i = 0; i < nums.length; i++) {
            if (maxLength == dp[i]) {
                total += count[i];
            }
        }
        return total;
    }

    @Test
    public void testfindNumberOfLIS() {
        int[] nums = {1, 2, 4, 3, 5, 4, 7, 2};//3
        System.out.println(findNumberOfLIS(nums));
    }

    public int maximumDifference(int[] nums) {
        int max = -1;
        for (int i = 0; i <= nums.length - 2; i++) {
            for (int j = i + 1; j <= nums.length - 1; j++) {
                if (nums[j] > nums[i]) {
                    max = Math.max(nums[j] - nums[i], max);
                }
            }
        }
        return max;
    }

    @Test
    public void testmaximumDifference() {
        int[] nums = {7, 1, 5, 4}; //4
        System.out.println(maximumDifference(nums));
    }

    public int subarraySum(int[] nums, int k) {
        if (nums.length == 1) {
            return nums[0] == 1 ? 1 : 0;
        }
        int[] prevSum = new int[nums.length + 1];
        prevSum[0] = 0;
        for (int i = 1; i < prevSum.length; i++) {
            prevSum[i] = prevSum[i - 1] + nums[i - 1];
        }
        Map<Integer, Integer> data = new HashMap<>();
        data.put(0, 1);
        int count = 0;
        for (int i = 0; i < prevSum.length; i++) {
            int sum = prevSum[i] - k;
            if (data.containsKey(sum)) {
                count += data.get(sum);
            }
            data.put(prevSum[i], data.getOrDefault(prevSum[i], 0) + 1);
        }
        return count;
    }

    class NumMatrix {
        int[][] matrix;
        int[][] sum; //和

        public NumMatrix(int[][] matrix) {
            this.matrix = matrix;
            sum = new int[matrix.length + 1][matrix[0].length + 1];
            for (int i = 1; i < sum.length; i++) {
                for (int j = 1; j < matrix[0].length + 1; j++) {
                    sum[i][j] = sum[i - 1][j] + sum[i][j - 1] - sum[i - 1][j - 1] + matrix[i - 1][j - 1];
                }
            }
        }

        public int sumRegion(int row1, int col1, int row2, int col2) {
            int r1 = row1 + 1;
            int c1 = col1 + 1;
            int r2 = row2 + 1;
            int c2 = col2 + 1;
            return sum[r2][c2] - sum[r1 - 1][c2] - (sum[r2][c1 - 1] - sum[r1 - 1][c1 - 1]);
        }
    }

    @Test
    public void testNumMatrix() {
        int[][] matrix = {
                {3, 0, 1, 4, 2},
                {5, 6, 3, 2, 1},
                {1, 2, 0, 1, 5},
                {4, 1, 0, 1, 7},
                {1, 0, 3, 0, 5}};
        NumMatrix numMatrix = new NumMatrix(matrix);
        System.out.println(numMatrix.sumRegion(2, 1, 4, 3)); //8
        System.out.println(numMatrix.sumRegion(1, 1, 2, 2)); //11
        System.out.println(numMatrix.sumRegion(1, 2, 2, 4)); //12
    }


    public long gridGame(int[][] grid) {
        long[][] sum = new long[2][grid[0].length + 1];
        for (int i = 1; i < sum[0].length; i++) {
            sum[0][i] = sum[0][i - 1] + grid[0][i - 1];
            sum[1][i] = sum[1][i - 1] + grid[1][i - 1];
        }
        long result = Long.MAX_VALUE;
        for (int i = 1; i < sum[0].length; i++) {
            result = Math.min(result, Math.max(sum[0][sum[0].length - 1] - sum[0][i], sum[1][i - 1]));
        }
        return result;
    }

    @Test
    public void testgridGame() {
        //int[][] grid = {{2, 5, 4}, {1, 5, 1}}; //4
        int[][] grid = {{1, 3, 1, 15}, {1, 3, 3, 1}}; //7
        System.out.println(gridGame(grid));
    }

    public int[] runningSum(int[] nums) {
        for (int i = 1; i < nums.length; i++) {
            nums[i] = nums[i - 1] + nums[i];
        }
        return nums;
    }

    public int sumOddLengthSubarrays(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            arr[i] = arr[i - 1] + arr[i];
        }
        int result = arr[arr.length - 1];
        for (int begin = 0; begin < arr.length; begin++) {
            for (int end = begin + 1; end < arr.length; end++) {
                if ((end - begin + 1) % 2 == 1) {
                    if (begin == 0) {
                        result += arr[end];
                    } else {
                        result += arr[end] - arr[begin - 1];
                    }
                }
            }
        }
        return result;
    }

    @Test
    public void testsumOddLengthSubarrays() {
        int[] arr = {1, 4, 2, 5, 3};
        System.out.println(sumOddLengthSubarrays(arr));
    }


    List<List<Integer>> result = new ArrayList<>();

    public List<List<Integer>> combine(int n, int k) {
        combineDataResult(n, k, 1, new ArrayList<>());
        return result;
    }

    public void combineDataResult(int n, int k, int startN, List<Integer> combineData) {
        if (combineData.size() == k) {
            result.add(new ArrayList<>(combineData));
            return;
        }
        for (int i = startN; i <= n - (k - combineData.size()) + 1; i++) {
            List<Integer> tmp = new ArrayList<>(combineData);
            tmp.add(i);
            combineDataResult(n, k, i + 1, tmp);
        }
    }


    @Test
    public void testcombine() {
        //[
        //  [2,4],
        //  [3,4],
        //  [2,3],
        //  [1,2],
        //  [1,3],
        //  [1,4],
        //]
        int n = 4;
        int k = 2;
        System.out.println(JSONObject.toJSONString(combine(4, 2)));
    }


    /**
     * 找出所有相加之和为 n 的 k 个数的组合。组合中只允许含有 1 - 9 的正整数，并且每种组合中不存在重复的数字。
     * <p>
     * 输入: k = 3, n = 7
     * 输出: [[1,2,4]]
     *
     * @param k
     * @param n
     * @return
     */
    List<List<Integer>> combinationSum3Result = new ArrayList<>();

    public List<List<Integer>> combinationSum3(int k, int n) {
        combinationSum3BackTracking(k, n, 1, 0, new ArrayList<>());
        return combinationSum3Result;
    }

    public void combinationSum3BackTracking(int k, int n, int start, int sum, List<Integer> data) {
        if (n == sum && k == data.size()) {
            combinationSum3Result.add(new ArrayList<>(data));
            return;
        }
        if (sum > n) {
            return;
        }
        if (data.size() >= k) {
            return;
        }
        for (int i = start; i <= Math.min(9, n); i++) {
            List<Integer> tmp = new ArrayList<>(data);
            tmp.add(i);
            combinationSum3BackTracking(k, n, i + 1, sum + i, new ArrayList<>(tmp));
        }
    }


    @Test
    public void testcombinationSum3() {
        int k = 3;
        int n = 7;
        System.out.println(JSONObject.toJSONString(combinationSum3(3, 7)));
    }

    public String destCity(List<List<String>> paths) {
        Map<String, String> route = new HashMap<>();
        for (List<String> p : paths) {
            route.put(p.get(0), p.get(1));
        }
        for (String v : route.values()) {
            if (!route.containsKey(v)) {
                return v;
            }
        }
        return "";
    }

    /**
     * 输入：digits = "23"
     * 输出：["ad","ae","af","bd","be","bf","cd","ce","cf"]
     * <p>
     * ["adg","adh","adi","aeg","aeh","aei","afg","afh","afi","bdg","bdh","bdi","beg","beh","bei","bfg","bfh","bfi","cdg","cdh","cdi","ceg","ceh","cei","cfg","cfh","cfi"]
     * ["adg","adh","adi","aeg","aeh","aei","afg","afh","afi","bdg","bdh","bdi","beg","beh","bei","bfg","bfh","bfi","cdg","cdh","cdi","ceg","ceh","cei","cfg","cfh","cfi","dg","dh","di","eg","eh","ei","fg","fh","fi"]
     *
     * @param digits
     * @return
     */
    public List<String> letterCombinations(String digits) {
        List<String> result = new ArrayList<>();
        if (digits == null || digits.length() == 0) {
            return result;
        }
        Map<Integer, String> data = new HashMap<>();
        data.put(2, "abc");
        data.put(3, "def");
        data.put(4, "ghi");
        data.put(5, "jkl");
        data.put(6, "mno");
        data.put(7, "pqrs");
        data.put(8, "tuv");
        data.put(9, "wxyz");
        List<String> letters = new ArrayList<>();
        char[] numChar = digits.toCharArray();
        for (char nc : numChar) {
            letters.add(data.get(Character.digit(nc, 10)));
        }
        //对于字母的回溯
        letterCombinationsBackTracking(letters, 0, "", result);
        return result;
    }

    public void letterCombinationsBackTracking(List<String> letters, int index, String item, List<String> result) {
        if (index == letters.size() - 1) {
            for (char c : letters.get(index).toCharArray()) {
                result.add(item + c);
            }
            return;
        }
        for (char c : letters.get(index).toCharArray()) {
            letterCombinationsBackTracking(letters, index + 1, item + c, result);
        }
    }

    /**
     * 给定一个无重复元素的正整数数组 candidates 和一个正整数 target ，找出 candidates 中所有可以使数字和为目标数 target 的唯一组合。
     * <p>
     * candidates 中的数字可以无限制重复被选取。如果至少一个所选数字数量不同，则两种组合是唯一的。 
     * <p>
     * 对于给定的输入，保证和为 target 的唯一组合数少于 150 个。
     * <p>
     * 输入: candidates = [2,3,6,7], target = 7
     * 输出: [[7],[2,2,3]]
     *
     * @param candidates
     * @param target
     * @return
     */

    List<List<Integer>> combinationSumResult = new ArrayList<>();

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        combinationSumBackTracking(candidates, target, 0, 0, new ArrayList<>());
        return combinationSumResult;
    }

    public void combinationSumBackTracking(int[] candidates, int target, int sum, int beginIndex, List<Integer> data) {
        if (sum == target) {
            combinationSumResult.add(new ArrayList<>(data));
            return;
        }
        if (sum > target || beginIndex >= candidates.length) {
            return;
        }
        combinationSumBackTracking(candidates, target, sum, beginIndex + 1, data);

        List<Integer> tmp = new ArrayList<>(data);
        tmp.add(candidates[beginIndex]);
        combinationSumBackTracking(candidates, target, sum + candidates[beginIndex], beginIndex, tmp);
    }

    @Test
    public void testcombinationSum() {
        //[2,7,6,3,5,1]
        //9

        //[[1,1,1,1,1,1,1,1,1],[1,1,1,1,1,1,1,2],[1,1,1,1,1,1,3],[1,1,1,1,2,3],[1,1,1,1,5],[1,1,1,6],[1,1,2,5],[1,1,7],[1,2,6],[1,3,5],[2,2,2,3],[2,2,5],[2,7],[3,3,3],[3,6]]
        //[[1,1,1,1,1,1,1,1,1],[1,1,1,1,1,1,1,2],[1,1,1,1,1,1,3],[1,1,1,1,1,2,2],[1,1,1,1,2,3],[1,1,1,1,5],[1,1,1,2,2,2],[1,1,1,3,3],[1,1,1,6],[1,1,2,2,3],[1,1,2,5],[1,1,7],[1,2,2,2,2],[1,2,3,3],[1,2,6],[1,3,5],[2,2,2,3],[2,2,5],[2,7],[3,3,3],[3,6]]

       /* int[] candidates = {2, 7, 6, 3, 5, 1};
        int target = 9;*/


        //[2,3,6,7]
        //7
        int[] candidates = {2, 3, 6, 7};
        int target = 7;
        System.out.println(combinationSum(candidates, target));
    }

    /**
     * 给你一个字符串 s，请你将 s 分割成一些子串，使每个子串都是 回文串 。返回 s 所有可能的分割方案。
     * <p>
     * 回文串 是正着读和反着读都一样的字符串。
     * <p>
     * 输入：s = "aab"
     * 输出：[["a","a","b"],["aa","b"]]
     *
     * @param s
     * @return
     */
    List<List<String>> partitionResult = new ArrayList<>();
    List<String> ans = new ArrayList<String>();
    boolean[][] dp;

    public List<List<String>> partition(String s) {
        //先动态规划计算出[i,j]之间是否为回文串
        int n = s.length();
        dp = new boolean[n][n];
        for (int i = 0; i < n; i++) {
            Arrays.fill(dp[i], true);
        }
        for (int i = n - 1; i >= 0; i--) {
            for (int j = i + 1; j < n; j++) {
                dp[i][j] = (s.charAt(i) == s.charAt(j)) && dp[i + 1][j - 1];
            }
        }
        partitionBackTracking(s, 0);
        return partitionResult;
    }

    public void partitionBackTracking(String s, int index) {
        if (index == s.length()) {
            partitionResult.add(new ArrayList<String>(ans));
            return;
        }
        for (int j = index; j < s.length(); j++) {
            if (dp[index][j]) {
                ans.add(s.substring(index, j + 1));
                partitionBackTracking(s, j + 1);
                ans.remove(ans.size() - 1);
            }
        }
    }


    int subsetXORSum = 0;

    public int subsetXORSum(int[] nums) {
        dfs(nums, 0, 0);
        return subsetXORSum;
    }

    public void dfs(int[] nums, int index, int subSum) {
        if (index == nums.length) {
            subsetXORSum += subSum;
            return;
        }

        dfs(nums, index + 1, subSum ^ nums[index]);
        dfs(nums, index + 1, subSum);
    }

    List<List<Integer>> subsetsResult = new ArrayList<>();
    List<Integer> sub = new ArrayList<>();

    public List<List<Integer>> subsets(int[] nums) {
        dfssubsets(nums, 0);
        return subsetsResult;
    }

    public void dfssubsets(int[] nums, int index) {
        if (index == nums.length) {
            subsetsResult.add(new ArrayList<>(sub));
            return;
        }
        sub.add(nums[index]);
        dfssubsets(nums, index + 1);
        sub.remove(sub.size() - 1);
        dfssubsets(nums, index + 1);
    }

    List<List<Integer>> subsetsWithDup = new ArrayList<>();

    public List<List<Integer>> subsetsWithDup(int[] nums) {
        Arrays.sort(nums);
        dfssubsetsWithDup(nums, 0, false, new ArrayList<>());
        return subsetsWithDup;

    }

    public void dfssubsetsWithDup(int[] nums, int index, boolean choosePrev, List<Integer> subsets) {
        if (index == nums.length) {
            subsetsWithDup.add(new ArrayList<Integer>(subsets));
            return;
        }
        //不选择当前
        dfssubsetsWithDup(nums, index + 1, false, subsets);
        //选择当前
        if (!choosePrev && index - 1 >= 0 && nums[index] == nums[index - 1]) {
            //重复的不选择
            return;
        }
        subsets.add(nums[index]);
        dfssubsetsWithDup(nums, index + 1, true, subsets);
        subsets.remove(subsets.size() - 1);
    }

    List<List<Integer>> findSubsequencesResult = new ArrayList<>();
    List<Integer> findSubsequencesItem = new ArrayList<>();

    public List<List<Integer>> findSubsequences(int[] nums) {
        dfsfindSubsequences(nums, 0, Integer.MIN_VALUE);
        return findSubsequencesResult;
    }

    public void dfsfindSubsequences(int[] nums, int index, int last) {
        if (nums.length == index) {
            if (findSubsequencesItem.size() == 0 || findSubsequencesItem.size() == 1) {
                return;
            }
            findSubsequencesResult.add(new ArrayList<>(findSubsequencesItem));
            return;
        }
        if (nums[index] > last) {
            findSubsequencesItem.add(nums[index]);
            dfsfindSubsequences(nums, index + 1, nums[index]);
            findSubsequencesItem.remove(findSubsequencesItem.size() - 1);
        }
        if (nums[index] != last) {
            dfsfindSubsequences(nums, index + 1, last);
        }
    }

    List<List<Integer>> permuteResult = new ArrayList<>();

    public List<List<Integer>> permute(int[] nums) {
        boolean[] used = new boolean[nums.length];
        Arrays.fill(used, false);
        permuteDFS(nums, used, new ArrayList<>());
        return permuteResult;

    }

    public void permuteDFS(int[] nums, boolean[] used, List<Integer> item) {
        if (item.size() == nums.length) {
            permuteResult.add(item);
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (used[i]) {
                continue;
            }
            used[i] = true;
            item.add(nums[i]);
            permuteDFS(nums, Arrays.copyOf(used, used.length), new ArrayList<>(item));
            item.remove(item.size() - 1);
            used[i] = false;
        }
    }


    /**
     * 给你一个整数数组 nums 和一个整数 k ，编写一个函数来判断该数组是否含有同时满足下述条件的连续子数组：
     * <p>
     * 子数组大小 至少为 2 ，且
     * 子数组元素总和为 k 的倍数。
     * 如果存在，返回 true ；否则，返回 false 。
     * <p>
     * 如果存在一个整数 n ，令整数 x 符合 x = n * k ，则称 x 是 k 的一个倍数。0 始终视为 k 的一个倍数。
     *
     * @param nums
     * @param k
     * @return
     */
    public boolean checkSubarraySum(int[] nums, int k) {
        int[] sum = new int[nums.length + 1];
        for (int i = 0; i < nums.length; i++) {
            sum[i + 1] = nums[i] + sum[i];
        }
        Map<Integer, Integer> cache = new HashMap<>();
        for (int i = 0; i < sum.length; i++) {
            int mod = sum[i] % k;
            if (cache.containsKey(mod) && i - cache.get(mod) >= 2) {
                return true;
            }
            if (!cache.containsKey(mod)) {
                cache.put(mod, i);
            }
        }
        return false;
    }


    @Test
    public void testcheckSubarraySum() {
        //[23,2,4,6,6]
        //7
        int[] nums = {23, 2, 4, 6, 6};
        int k = 7;
        System.out.println(checkSubarraySum(nums, k));
    }


    public int findMaxLength(int[] nums) {
        int[] sum = new int[nums.length + 1];
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 0) {
                sum[i + 1] = sum[i] - 1;
                continue;
            }
            if (nums[i] == 1) {
                sum[i + 1] = sum[i] + 1;
            }
        }
        int result = 0;
        Map<Integer, Integer> data = new HashMap<>(); //key:index  ; v:sum
        for (int i = 0; i < sum.length; i++) {
            if (data.containsKey(-sum[i])) {
                result = Math.max(i - data.get(-sum[i]), result);
                data.put(sum[i], Math.min(i, data.getOrDefault(sum[i], Integer.MAX_VALUE)));
            } else {
                data.put(sum[i], i);
            }
        }
        return result;
    }


    @Test
    public void testfindMaxLength() {
        int[] nums = {0, 0, 1, 0, 0, 0, 1, 1}; //
        System.out.println(findMaxLength(nums));
    }


}
