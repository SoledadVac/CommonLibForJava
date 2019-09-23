package leetcode.Trie;

import com.alibaba.fastjson.JSONObject;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * \* Created: liuhuichao
 * \* Date: 2019/9/3
 * \* Time: 28:24 PM
 * \* Description: 单词搜索 II
 * \
 * 给定一个二维网格 board 和一个字典中的单词列表 words，找出所有同时在二维网格和字典中出现的单词。
 * <p>
 * 单词必须按照字母顺序，通过相邻的单元格内的字母构成，其中“相邻”单元格是那些水平相邻或垂直相邻的单元格。同一个单元格内的字母在一个单词中不允许被重复使用。
 * <p>
 * 示例:
 * <p>
 * 输入:
 * words = ["oath","pea","eat","rain"] and board =
 * [
 * ['o','a','a','n'],
 * ['e','t','a','e'],
 * ['i','h','k','r'],
 * ['i','f','l','v']
 * ]
 * <p>
 * 输出: ["eat","oath"]
 * 说明:
 * 你可以假设所有输入都由小写字母 a-z 组成。
 * <p>
 * 提示:
 * <p>
 * 你需要优化回溯算法以通过更大数据量的测试。你能否早点停止回溯？
 * 如果当前单词不存在于所有单词的前缀中，则可以立即停止回溯。什么样的数据结构可以有效地执行这样的操作？
 * 散列表是否可行？为什么？ 前缀树如何？如果你想学习如何实现一个基本的前缀树，请先查看这个问题： 实现Trie（前缀树）。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/word-search-ii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class A212_WordSearchII {

    class TrieNode {

        Boolean isWord;
        Character c;
        TrieNode[] next;

        TrieNode() {
            this.isWord = false;
            this.next = new TrieNode[26];
        }

        TrieNode(Character c) {
            this.isWord = false;
            this.c = c;
            this.next = new TrieNode[26];
        }
    }

    class Trie {
        TrieNode root; //空的跟结点

        public Trie() {
            root = new TrieNode();
        }

        /**
         * 获取数组中位置
         *
         * @param c
         * @return
         */
        private int getIndex(Character c) {
            return c - 'a';
        }

        /**
         * Adds a word into the data structure.
         */
        public void addWord(String word) {
            if (word == null || word.isEmpty()) {
                return;
            }
            char[] words = word.toCharArray();
            TrieNode currt = root;
            for (int i = 0; i < words.length; i++) {
                int index = getIndex(words[i]);
                TrieNode trieNode = currt.next[index];
                if (trieNode == null) {
                    trieNode = new TrieNode(words[i]);
                    currt.next[index] = trieNode;
                }
                currt = trieNode;
            }
            currt.isWord = true;

        }

        public boolean search(String word) {
            if (word == null || word.isEmpty()) {
                return false;
            }
            return searchWord(root, word, 0);
        }

        public boolean searchWord(TrieNode current, String word, int index) {
            if (index >= word.length()) {
                return current.isWord;
            }
            char c = word.charAt(index);
            int chidIndex = getIndex(c);
            if (current.next[chidIndex] != null && searchWord(current.next[chidIndex], word, index + 1)) {
                return true;
            }
            return false;
        }

    }

    public List<String> findWords(char[][] board, String[] words) {
        List<String> res = new ArrayList<>();
        if (board == null || board.length == 0 || words == null || words.length == 0) {
            return res;
        }
        // build trie
        Trie trie = new Trie();
        for (String s : words) {
            trie.addWord(s);
        }



        return res;

    }

    @Test
    public void test() {
        char[][] board = {
                {'o', 'a', 'a', 'n'},
                {'e', 't', 'a', 'e'},
                {'i', 'h', 'k', 'r'},
                {'i', 'f', 'l', 'v'}
        };
        String[] words = {"oath", "pea", "eat", "rain"};
        System.out.println("result = " + JSONObject.toJSONString(findWords(board, words)));
        //todo : 单词搜索 II
    }
}
