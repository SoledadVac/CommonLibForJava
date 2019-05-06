package leetcode;

import org.junit.Test;

/**
 * \* Created: liuhuichao
 * \* Date: 2019/4/30
 * \* Time: 5:49 PM
 * \* Description: First Bad Version
 * \
 * You are a product manager and currently leading a team to develop a new product. Unfortunately,
 * the latest version of your product fails the quality check. Since each version is developed based on the previous version,
 * all the versions after a bad version are also bad.
 * <p>
 * Suppose you have n versions [1, 2, ..., n] and you want to find out the first bad one,
 * which causes all the following ones to be bad.
 * <p>
 * You are given an API bool isBadVersion(version) which will return whether version is bad.
 * Implement a function to find the first bad version. You should minimize the number of calls to the API.
 * <p>
 * Example:
 * <p>
 * Given n = 5, and version = 4 is the first bad version.
 * <p>
 * call isBadVersion(3) -> false
 * call isBadVersion(5) -> true
 * call isBadVersion(4) -> true
 * <p>
 * Then 4 is the first bad version.
 */
public class A278_FirstBadVersion {


    int binarySearch(int[] nums, int target) {
        if (nums == null || nums.length == 0)
            return -1;

        int left = 0, right = nums.length;//右边界是个取不到地方
        // 1，2，3，4，5
        while (left < right) {
            // Prevent (left + right) overflow
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] < target) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }

        // Post-processing:
        // End Condition: left == right
        if (left != nums.length && nums[left] == target) return left;
        return -1;
    }


    public int firstBadVersion(int n) {
        int left = 1;
        long right = (long) n + 1;
        while (right > left) {
            long mid = left + (right - left) / 2;
            if (isBadVersion((int) mid - 1) == false && isBadVersion((int) mid)) {
                return (int) mid;
            }
            if (isBadVersion((int) mid - 1) && isBadVersion((int) mid)) {
                right = mid;
            } else {
                left = (int) mid + 1;
            }
        }
        if (left != n + 1 && isBadVersion(left) == false) {
            return left;
        }
        return 1;
    }

    public int firstBadVersion0(int n) {
        int left = 1;
        int right = n;
        while (right >= left) {
            int mid = left + (right - left) / 2;
            if (isBadVersion(mid)) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }

    boolean isBadVersion(int n) {
        return n >= 2147483647;
    }

    @Test
    public void test() {
        int n = 2147483647;  // 2126753390
        System.out.println("result = " + firstBadVersion0(n));
    }
}
