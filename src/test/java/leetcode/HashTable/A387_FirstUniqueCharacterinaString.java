package leetcode.HashTable;

import org.junit.Test;

import java.util.*;

/**
 * \* Created: liuhuichao
 * \* Date: 2019/8/19
 * \* Time: 29:13 PM
 * \* Description:  字符串中的第一个唯一字符
 * \
 * 给定一个字符串，找到它的第一个不重复的字符，并返回它的索引。如果不存在，则返回 -1。
 * <p>
 * 案例:
 * <p>
 * s = "leetcode"
 * 返回 0.
 * <p>
 * s = "loveleetcode",
 * 返回 2.
 *  
 * <p>
 * 注意事项：您可以假定该字符串只包含小写字母。
 */
public class A387_FirstUniqueCharacterinaString {

    public int firstUniqChar(String s) {
        if (s == null || s.length() < 1) {
            return -1;
        }
        char[] sChars = s.toCharArray();
        Map<Character, Integer> data = new HashMap<>(); // key: char ; value : index
        List<Integer> indexs = new ArrayList<>();
        for (int i = 0; i < sChars.length; i++) {
            if (data.containsKey(sChars[i])) {
                indexs.remove(data.get(sChars[i]));
                data.put(sChars[i], -1);
            } else {
                indexs.add(i);
                data.put(sChars[i], i);
            }
        }
        if (indexs.size() == 0) {
            return -1;
        }
        return indexs.get(0);
    }

    @Test
    public void test() {
        String s = "leetcode";
        System.out.println("result = " + firstUniqChar(s));
    }
}
