package leetcode.Trie;

import org.apache.hadoop.yarn.webapp.hamlet.Hamlet;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * \* Created: liuhuichao
 * \* Date: 2019/9/2
 * \* Time: 27:23 PM
 * \* Description: Implement Trie (Prefix Tree)
 * \
 * 实现一个 Trie (前缀树)，包含 insert, search, 和 startsWith 这三个操作。
 * <p>
 * 示例:
 * <p>
 * Trie trie = new Trie();
 * <p>
 * trie.insert("apple");
 * trie.search("apple");   // 返回 true
 * trie.search("app");     // 返回 false
 * trie.startsWith("app"); // 返回 true
 * trie.insert("app");
 * trie.search("app");     // 返回 true
 * 说明:
 * <p>
 * 你可以假设所有的输入都是由小写字母 a-z 构成的。
 * 保证所有输入均为非空字符串。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/implement-trie-prefix-tree
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class A208_ImplementTrie {

    class TrieNode {

        Boolean isWord;
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

    class Trie {

        private Map<Character, TrieNode> data = new HashMap<>();

        /**
         * Initialize your data structure here.
         */
        public Trie() {

        }

        /**
         * Inserts a word into the trie.
         */
        public void insert(String word) {
            if (word == null || word.isEmpty()) {
                return;
            }
            if (search(word)) {
                return;
            }
            char[] words = word.toCharArray();
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
        }

        /**
         * Returns if the word is in the trie.
         */
        public boolean search(String word) {
            if (word == null || word.isEmpty()) {
                return false;
            }
            char[] words = word.toCharArray();
            char head = words[0];
            TrieNode currt = data.get(head);
            if (currt == null) {
                return false;
            }
            for (int i = 1; i < words.length; i++) {
                char c = words[i];
                if (currt.next == null || currt.next.size() == 0) {
                    return false;
                }
                List<TrieNode> next = currt.next;
                TrieNode t = next.stream().filter(n -> n.c == c).findFirst().orElse(null);
                if (t == null) {
                    return false;
                }
                currt = t;
            }
            return currt.isWord;
        }

        /**
         * Returns if there is any word in the trie that starts with the given prefix.
         */
        public boolean startsWith(String prefix) {
            if (prefix == null || prefix.isEmpty()) {
                return false;
            }
            char[] words = prefix.toCharArray();
            char head = words[0];
            TrieNode currt = data.get(head);
            if (currt == null) {
                return false;
            }
            for (int i = 1; i < words.length; i++) {
                char c = words[i];
                if (currt.next == null || currt.next.size() == 0) {
                    return false;
                }
                List<TrieNode> next = currt.next;
                TrieNode t = next.stream().filter(n -> n.c == c).findFirst().orElse(null);
                if (t == null) {
                    return false;
                }
                currt = t;
            }
            return true;
        }
    }

    @Test
    public void test() {
        Trie trie = new Trie();
        trie.insert("apple");
        boolean s1 = trie.search("apple");   // 返回 true
        System.out.println("s1 = " + s1);
        boolean s2 = trie.search("app");     // 返回 false
        System.out.println("s2 = " + s2);
        boolean s3 = trie.startsWith("app");         // 返回 true
        System.out.println("s3 = " + s3);
        trie.insert("app");
        boolean s4 = trie.search("app");     // 返回 true
        System.out.println("s4 = " + s4);
    }

}
