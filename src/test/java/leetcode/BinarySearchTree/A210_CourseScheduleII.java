package leetcode.BinarySearchTree;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class A210_CourseScheduleII {


    public int[] findOrder(int numCourses, int[][] prerequisites) {
        int[] result = null;
        if (numCourses == 1) {
            return new int[]{0};
        }
        if (prerequisites.length == 0) {
            result = new int[numCourses];
            for (int i = 0; i < numCourses; i++) {
                result[i] = i;
            }
            return result;
        }
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
        if (!queue.isEmpty()) {
            result = new int[numCourses];
        } else {
            return new int[]{};
        }
        int index = 0;
        int visit = 0;
        while (!queue.isEmpty()) {
            int item = queue.poll();
            result[index++] = item;
            visit++;
            for (int next : edgeList.get(item)) {
                --inDegree[next];
                if (inDegree[next] == 0) {
                    queue.offer(next);
                }
            }
        }
        if (visit != numCourses) {
            return new int[]{};
        }
        return result;
    }
}
