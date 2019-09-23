package leetcode.Trie;

import org.junit.Test;

/**
 * \* Created: liuhuichao
 * \* Date: 2019/9/3
 * \* Time: 27:38 PM
 * \* Description: 数组中两个数的最大异或值
 * \
 * 给定一个非空数组，数组中元素为 a0, a1, a2, … , an-1，其中 0 ≤ ai < 231 。
 * <p>
 * 找到 ai 和aj 最大的异或 (XOR) 运算结果，其中0 ≤ i,  j < n 。
 * <p>
 * 你能在O(n)的时间解决这个问题吗？
 * <p>
 * 示例:
 * <p>
 * 输入: [3, 10, 5, 25, 2, 8]
 * <p>
 * 输出: 28
 * <p>
 * 解释: 最大的结果是 5 ^ 25 = 28.
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/maximum-xor-of-two-numbers-in-an-array
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class A421_MaximumXORofTwoNumbersinanArray {


    class TrieNode {
        TrieNode[] child = new TrieNode[2];
        Byte data;
    }

    class Trie {
        TrieNode root = new TrieNode();

        /**
         * 插入num构建树
         *
         * @param value
         */
        public void insert(int value) {
            TrieNode cur = root;
            for (int i = 31; i >= 0; i--) {
                int v = value >> i & 1;
                if (cur.child[v] == null) {
                    cur.child[v] = new TrieNode();
                }
                cur = cur.child[v];
            }
        }

        public int getMaxXorNum(int value) {
            TrieNode cur = root;
            int result = 0;
            for (int i = 31; i >= 0; i--) {
                int v = (value >> i) & 1;
                int path = v;
                if (i == 31) {
                    if (cur.child[v] == null) {
                        path = 1 ^ v;//~v
                    }
                } else {
                    if (cur.child[1 ^ v] != null) {
                        path = 1 ^ v;//~v
                    }
                }
                cur = cur.child[path];
                result = result | ((path ^ v) << i);
            }
            return result;
        }
    }

    public int findMaximumXOR(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        Trie tree = new Trie();
        int result = Integer.MIN_VALUE;
        for (int i = 0; i < nums.length; i++) {
            tree.insert(nums[i]);
        }
        for (int i = 0; i < nums.length; i++) {
            result = Math.max(result, tree.getMaxXorNum(nums[i]));
        }
        return result;
    }

    @Test
    public void test() {
        //todo : 数组中两个数的最大异或值
        int[] nums = {3, 10, 5, 25, 2, 8};
        System.out.println("result = " + findMaximumXOR(nums));
    }
}
