package leetcode.BinarySearchTree;

import org.junit.Test;

import java.util.*;

public class A207_CourseSchedule {

    @Test
    public void test() {
        //int numCourses=2;
        //int[][] prerequisites={{1,0},{0,1}};

        //[[0,10],[3,18],[5,5],[6,11],[11,14],[13,1],[15,1],[17,4]]
        //输出
        //  true
        //预期结果
        //  false
        //int numCourses=20;
        //int[][] prerequisites={{0,10},{3,18},{5,5},{6,11},{11,14},{13,1},{15,1},{17,4}};

        //[[1,0],[2,6],[1,7],[5,1],[6,4],[7,0],[0,5]]
        //int numCourses=8;
        //int[][] prerequisites={{1,0},{2,6},{1,7},{5,1},{6,4},{7,0},{0,5}};

        //[[1,0],[0,3],[0,2],[3,2],[2,5],[4,5],[5,6],[2,4]]
        int numCourses = 7;
        int[][] prerequisites = {{1, 0}, {0, 3}, {0, 2}, {3, 2}, {2, 5}, {4, 5}, {5, 6}, {2, 4}};
        System.out.println(canFinish(numCourses, prerequisites));
    }

    public boolean canFinish(int numCourses, int[][] prerequisites) {
        if (numCourses == 1 || prerequisites.length == 0) {
            return true;
        }
        int visited = 0;
        List<List<Integer>> edgeList = new LinkedList<>(); //index:toElement
        int[] inDegree = new int[numCourses]; //index:num
        for (int i = 0; i < numCourses; i++) {
            edgeList.add(new LinkedList<>());
        }
        for (int[] toFromArr : prerequisites) {
            int to = toFromArr[0];
            int from = toFromArr[1];
            edgeList.get(from).add(to);
            inDegree[to] += 1;
        }
        Queue<Integer> queue = new LinkedList<>();
        //先放入入度为0的点
        for (int i = 0; i < numCourses; i++) {
            if (inDegree[i] == 0) {
                queue.offer(i);
            }
        }
        while (!queue.isEmpty()) {
            ++visited;
            int item = queue.poll();
            for (int next : edgeList.get(item)) {
                --inDegree[next];
                if (inDegree[next] == 0) {
                    queue.offer(next);
                }
            }
        }
        return visited == numCourses;
    }


}
