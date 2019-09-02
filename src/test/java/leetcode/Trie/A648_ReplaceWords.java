package leetcode.Trie;

import com.google.common.collect.Lists;
import org.junit.Test;

import java.util.*;

/**
 * \* Created: liuhuichao
 * \* Date: 2019/9/2
 * \* Time: 30:05 PM
 * \* Description: 单词替换
 * \
 * 在英语中，我们有一个叫做 词根(root)的概念，
 * 它可以跟着其他一些词组成另一个较长的单词——我们称这个词为 继承词(successor)。
 * 例如，词根an，跟随着单词 other(其他)，可以形成新的单词 another(另一个)。
 * <p>
 * 现在，给定一个由许多词根组成的词典和一个句子。你需要将句子中的所有继承词用词根替换掉。
 * 如果继承词有许多可以形成它的词根，则用最短的词根替换它。
 * <p>
 * 你需要输出替换之后的句子。
 * <p>
 * 示例 1:
 * <p>
 * 输入: dict(词典) = ["cat", "bat", "rat"]
 * sentence(句子) = "the cattle was rattled by the battery"
 * 输出: "the cat was rat by the bat"
 * 注:
 * <p>
 * 输入只包含小写字母。
 * 1 <= 字典单词数 <=1000
 * 1 <=  句中词语数 <= 1000
 * 1 <= 词根长度 <= 100
 * 1 <= 句中词语长度 <= 1000
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/replace-words
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class A648_ReplaceWords {


    class TrieNode {
        Boolean isWordRoot;
        Character c;
        List<TrieNode> next;

        TrieNode() {
            this.isWordRoot = false;
        }

        TrieNode(Character c) {
            this.c = c;
            this.isWordRoot = false;
        }
    }

    class Trie {

        private Map<Character, TrieNode> data = new HashMap<>();

        public void buildTrie(List<String> dict) {
            //build trie
            for (String d : dict) {
                char head = d.charAt(0);
                TrieNode current = data.get(head);
                if (current == null) {
                    current = new TrieNode(head);
                    data.put(head, current);
                }
                for (char c : d.substring(1).toCharArray()) {
                    TrieNode target;
                    if (current.next == null) {
                        target = new TrieNode(c);
                        current.next = new ArrayList<>();
                        current.next.add(target);
                    } else {
                        target = current.next.stream().filter(n -> n.c == c).findFirst().orElse(null);
                    }
                    if (target == null) {
                        target = new TrieNode(c);
                        current.next.add(target);
                    }
                    current = target;
                }
                current.isWordRoot = true;
            }
        }

        /**
         * 在词根前缀树中查找是否存在
         *
         * @param word
         * @return
         */
        public String handleWord(String word) {
            if (word == null || word.isEmpty()) {
                return "";
            }
            char head = word.charAt(0);
            TrieNode current = data.get(head);
            if (current == null) {
                return word;
            }
            StringBuilder root = new StringBuilder("");
            root.append(current.c);
            if (current.isWordRoot) {
                return root.toString();
            }
            for (char c : word.substring(1).toCharArray()) {
                if (current.next == null || current.next.size() == 0) {
                    return root.toString();
                }
                TrieNode target = current.next.stream().filter(n -> n.c == c).findFirst().orElse(null);
                if (target == null) {
                    return word;
                }
                root.append(c);
                current = target;
                if (current.isWordRoot) {
                    return root.toString();
                }
            }
            if (current.next == null || current.next.size() == 0) {
                return root.toString();
            }
            return word;
        }
    }


    public String replaceWords(List<String> dict, String sentence) {
        if (dict == null || dict.size() == 0) {
            return sentence;
        }
        Trie trie = new Trie();
        trie.buildTrie(dict);
        String[] strArray = sentence.split(" ");
        StringBuilder sb = new StringBuilder("");
        for (int i = 0; i < strArray.length; i++) {
            if (i == strArray.length - 1) {
                sb.append(trie.handleWord(strArray[i]));
            } else {
                sb.append(trie.handleWord(strArray[i]) + " ");
            }
        }
        return sb.toString();
    }

    @Test
    public void test() {
        //["a", "aa", "aaa", "aaaa"]
        // "a aa a aaaa aaa aaa aaa aaaaaa bbb baba ababa"
        List<String> dict = Lists.newArrayList("a", "aa", "aaa", "aaaa");
        String sentence = "a aa a aaaa aaa aaa aaa aaaaaa bbb baba ababa";
        System.out.println("result = " + replaceWords(dict, sentence));
    }
}
