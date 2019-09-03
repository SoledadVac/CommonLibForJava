package leetcode.Trie;

import org.junit.Test;

/**
 * \* Created: liuhuichao
 * \* Date: 2019/9/3
 * \* Time: 34:12 AM
 * \* Description: 添加与搜索单词 - 数据结构设计
 * \
 * 设计一个支持以下两种操作的数据结构：
 * <p>
 * void addWord(word)
 * bool search(word)
 * search(word) 可以搜索文字或正则表达式字符串，字符串只包含字母 . 或 a-z 。 . 可以表示任何一个字母。
 * <p>
 * 示例:
 * <p>
 * addWord("bad")
 * addWord("dad")
 * addWord("mad")
 * search("pad") -> false
 * search("bad") -> true
 * search(".ad") -> true
 * search("b..") -> true
 * 说明:
 * <p>
 * 你可以假设所有单词都是由小写字母 a-z 组成的。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/add-and-search-word-data-structure-design
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class A211_AddandSearchWordDatastructuredesign {

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

    class WordDictionary {

        TrieNode root; //空的跟结点

        /**
         * Initialize your data structure here.
         */
        public WordDictionary() {
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

        /**
         * Returns if the word is in the data structure. A word could contain the dot character '.' to represent any one letter.
         */
        public boolean search(String word) {
            if (word == null || word.isEmpty()) {
                return false;
            }
            return searchWord(root, word, 0);
        }


        private boolean searchWord(TrieNode current, String word, int index) {
            if (index >= word.length()) {
                return current.isWord;
            }
            if (word.charAt(index) == '.') {
                for (int i = 0; i < 26; i++) {
                    if (current.next[i] != null && searchWord(current.next[i], word, index + 1)) {
                        return true;
                    }
                }
                return false;
            } else {
                //普通字母
                char c = word.charAt(index);
                int chidIndex = getIndex(c);
                if (current.next[chidIndex] != null && searchWord(current.next[chidIndex], word, index + 1)) {
                    return true;
                }
                return false;
            }
        }
    }

    @Test
    public void test() {
        WordDictionary wordDictionary = new WordDictionary();
        wordDictionary.addWord("bad");
        wordDictionary.addWord("dad");
        wordDictionary.addWord("mad");
        wordDictionary.addWord("oolcerrrrrrr");
        boolean s1 = wordDictionary.search("pad");   //false
        System.out.println("s1 = " + s1);
        boolean s2 = wordDictionary.search("bad");   // true
        System.out.println("s2 = " + s2);
        boolean s3 = wordDictionary.search(".ad");   // true
        System.out.println("s3 = " + s3);
        boolean s4 = wordDictionary.search("b..");   // true
        System.out.println("s4 = " + s4);
        boolean s5 = wordDictionary.search("..lcer");   // true
        System.out.println("s5 = " + s5);
    }

}
