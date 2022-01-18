package leetcode.ArrayAndString;

import com.alibaba.fastjson.JSONObject;
import org.junit.Test;

import java.util.Arrays;
import java.util.TreeMap;

/**
 * \* Created: liuhuichao
 * \* Date: 2020/5/26
 * \* Time: 30:02 下午
 * \* Description: 合并两个有序数组
 * \
 * 给你两个有序整数数组 nums1 和 nums2，请你将 nums2 合并到 nums1 中，使 nums1 成为一个有序数组。
 * <p>
 *  
 * <p>
 * 说明:
 * <p>
 * 初始化 nums1 和 nums2 的元素数量分别为 m 和 n 。
 * 你可以假设 nums1 有足够的空间（空间大小大于或等于 m + n）来保存 nums2 中的元素。
 *  
 * <p>
 * 示例:
 * <p>
 * 输入:
 * nums1 = [1,2,3,0,0,0], m = 3
 * nums2 = [2,5,6],       n = 3
 * <p>
 * 输出: [1,2,2,3,5,6]
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/merge-sorted-array
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class A88_MergeSortedArray {
    public void merge0(int[] nums1, int m, int[] nums2, int n) {
        TreeMap<Integer, Integer> treeMap = new TreeMap<>(); // key:数字，value: 个数
        for (int i = 0; i < m; i++) {
            if (treeMap.containsKey(nums1[i])) {
                treeMap.put(nums1[i], treeMap.get(nums1[i]) + 1);
            } else {
                treeMap.put(nums1[i], 1);
            }
        }
        for (int i = 0; i < n; i++) {
            if (treeMap.containsKey(nums2[i])) {
                treeMap.put(nums2[i], treeMap.get(nums2[i]) + 1);
            } else {
                treeMap.put(nums2[i], 1);
            }
        }
        int index = 0;
        for (Integer key : treeMap.keySet()) {
            int count = treeMap.get(key);
            while (count != 0) {
                nums1[index] = key;
                index++;
                count--;
            }
        }
    }

    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int indexm = 0;
        int indexn = 0;
        int[] n1Copy = nums1.clone();
        int indexNew = -1;
        while (indexm < m || indexn < n) {
            indexNew++;
            if (indexm == m) {
                nums1[indexNew] = nums2[indexn];
                indexn++;
                continue;
            }

            if (indexn == n) {
                nums1[indexNew] = n1Copy[indexm];
                indexm++;
                continue;
            }
            if (n1Copy[indexm] >= nums2[indexn]) {
                nums1[indexNew] = nums2[indexn];
                indexn++;
            } else {
                nums1[indexNew] = n1Copy[indexm];
                indexm++;
            }
        }

    }


    /**
     * 将 nums2 合并到 nums1 中，使 nums1 成为一个有序数组
     *
     * @param nums1
     * @param m     : nums1实际长度
     * @param nums2
     * @param n     : nums2实际长度
     */
    public void merge1(int[] nums1, int m, int[] nums2, int n) {
        int[] num1Copy = new int[m + n]; //用于存放nums1原始数据
        System.arraycopy(nums1, 0, num1Copy, 0, m);
        int p1 = 0; //num1Copy 指针
        int p2 = 0; //nums2 指针
        int p = 0;//nums1 指针
        while (p1 < m && p2 < n) {
            if (num1Copy[p1] > nums2[p2]) {
                nums1[p++] = nums2[p2++];
            } else {
                nums1[p++] = num1Copy[p1++];
            }
        }
        if (p1 < m) {
            System.arraycopy(num1Copy, p1, nums1, p, m - p1);
        }
        if (p2 < n) {
            System.arraycopy(nums2, p2, nums1, p, n - p2);
        }
    }

    @Test
    public void test() {
        int[] nums1 = {1, 2, 3, 0, 0, 0};
        int m = 3;
        int[] nums2 = {5, 6, 7};
        int n = 3;
        merge1(nums1, m, nums2, n);
        System.out.println(JSONObject.toJSON(nums1));
    }

}
