package leetcode.HashTable;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * \* Created: liuhuichao
 * \* Date: 2019/8/19
 * \* Time: 28:02 PM
 * \* Description:同构字符串
 * \
 * 给定两个字符串 s 和 t，判断它们是否是同构的。
 * <p>
 * 如果 s 中的字符可以被替换得到 t ，那么这两个字符串是同构的。
 * <p>
 * 所有出现的字符都必须用另一个字符替换，同时保留字符的顺序。两个字符不能映射到同一个字符上，但字符可以映射自己本身。
 * <p>
 * 示例 1:
 * <p>
 * 输入: s = "egg", t = "add"
 * 输出: true
 * 示例 2:
 * <p>
 * 输入: s = "foo", t = "bar"
 * 输出: false
 * 示例 3:
 * <p>
 * 输入: s = "paper", t = "title"
 * 输出: true
 * 说明:
 * 你可以假设 s 和 t 具有相同的长度。
 */
public class A205_IsomorphicStrings {


    public boolean isIsomorphic(String s, String t) {
        if (s == null && t == null) {
            return true;
        }
        if (s == null || t == null) {
            return false;
        }
        if (s.length() != t.length()) {
            return false;
        }
        Map<Character, Character> data = new HashMap();
        char[] sChar = s.toCharArray();
        char[] tChar = t.toCharArray();
        int index = 0;
        while (index != s.length()) {
            char sc = sChar[index];
            char tc = tChar[index];
            if (data.containsKey(sc)) {
                if (data.get(sc) != tc) {
                    return false;
                }
            } else {
                if (data.containsValue(tc)) {
                    return false;
                }
                data.put(sc, tc);
            }
            index++;
        }
        return true;
    }

    @Test
    public void test() {
        String s = "egg";
        String t = "add";
        System.out.println("result = " + isIsomorphic(s, t));
    }

}
