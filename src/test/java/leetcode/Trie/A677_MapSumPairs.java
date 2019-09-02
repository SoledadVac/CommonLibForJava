package leetcode.Trie;

import org.junit.Test;

import java.util.*;

/**
 * \* Created: liuhuichao
 * \* Date: 2019/9/2
 * \* Time: 28:47 PM
 * \* Description: Map Sum Pairs
 * \
 * 实现一个 MapSum 类里的两个方法，insert 和 sum。
 * <p>
 * 对于方法 insert，你将得到一对（字符串，整数）的键值对。字符串表示键，整数表示值。如果键已经存在，那么原来的键值对将被替代成新的键值对。
 * <p>
 * 对于方法 sum，你将得到一个表示前缀的字符串，你需要返回所有以该前缀开头的键的值的总和。
 * <p>
 * 示例 1:
 * <p>
 * 输入: insert("apple", 3), 输出: Null
 * 输入: sum("ap"), 输出: 3
 * 输入: insert("app", 2), 输出: Null
 * 输入: sum("ap"), 输出: 5
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/map-sum-pairs
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class A677_MapSumPairs {


    class TrieNode {

        Boolean isWord;
        Integer value;
        Character c;
        List<TrieNode> next;

        TrieNode() {
            isWord = false;
        }

        TrieNode(Character c) {
            isWord = false;
            this.c = c;
        }
    }


    class MapSum {

        private Map<Character, TrieNode> data = new HashMap<>();

        /**
         * Initialize your data structure here.
         */
        public MapSum() {

        }

        public void insert(String key, int val) {
            if (key == null || key.isEmpty()) {
                return;
            }
            char[] words = key.toCharArray();
            char head = words[0];
            TrieNode currt = data.get(head);
            if (currt == null) {
                currt = new TrieNode(head);
                data.put(head, currt);
            }
            for (int i = 1; i < words.length; i++) {
                char c = words[i];
                if (currt.next == null) {
                    currt.next = new ArrayList<>();
                }
                TrieNode target = currt.next.stream().filter(n -> n.c == c).findFirst().orElse(null);
                if (target == null) {
                    target = new TrieNode(c);
                    currt.next.add(target);
                }
                currt = target;
            }
            currt.isWord = true;
            currt.value = val;
        }

        public int sum(String prefix) {
            if (prefix == null || prefix.isEmpty()) {
                return 0;
            }
            char[] words = prefix.toCharArray();
            char head = words[0];
            TrieNode currt = data.get(head);
            if (currt == null) {
                return 0;
            }
            if (words.length != 1) {
                for (int i = 1; i < words.length; i++) {
                    char c = words[i];
                    if (currt.next == null) {
                        return 0;
                    }
                    TrieNode target = currt.next.stream().filter(n -> n.c == c).findFirst().orElse(null);
                    if (target == null) {
                        return 0;
                    }
                    currt = target;
                }
            }
            //计算此结点[currt]为根节点的所有子结点的val值的和
            //层次遍历下
            LinkedList<TrieNode> list = new LinkedList<>();
            list.add(currt);
            int result = 0;
            while (!list.isEmpty()) {
                int size = list.size();
                for (int i = 0; i < size; i++) {
                    TrieNode n = list.pop();
                    if (n.isWord) {
                        result += n.value;
                    }
                    if (n.next != null) {
                        list.addAll(n.next);
                    }
                }
            }
            return result;

        }
    }

    @Test
    public void test() {
        MapSum mapSum = new MapSum();
        mapSum.insert("apple", 3);
        int s1 = mapSum.sum("ap");
        System.out.println("s1 = " + s1);
        mapSum.insert("app", 2);
        int s2 = mapSum.sum("ap");
        System.out.println("s2 = " + s2);
    }

}
