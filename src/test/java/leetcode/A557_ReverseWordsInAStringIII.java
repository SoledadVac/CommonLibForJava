package leetcode;

import org.junit.Test;

/**
 * \* Created: liuhuichao
 * \* Date: 2019/4/24
 * \* Time: 4:50 PM
 * \* Description:反转字符串中的单词 III
 * 给定一个字符串，你需要反转字符串中每个单词的字符顺序，同时仍保留空格和单词的初始顺序。
 * <p>
 * 示例 1:
 * <p>
 * 输入: "Let's take LeetCode contest"
 * 输出: "s'teL ekat edoCteeL tsetnoc"
 * 注意：在字符串中，每个单词由单个空格分隔，并且字符串中不会有任何额外的空格。
 * \
 */
public class A557_ReverseWordsInAStringIII {
    public String reverseWords(String s) {
        StringBuilder sb = new StringBuilder();
        String[] source = s.split(" ");
        for (int i = 0; i < source.length; i++) {
            sb.append(reverseSingleWord(source[i]));
            if (i != source.length - 1) {
                sb.append(" ");
            }
        }
        return sb.toString();
    }

    String reverseSingleWord(String item) {
        char[] chars = item.toCharArray();
        int begin = 0;
        int end = chars.length - 1;
        while (end < item.length() && end >= begin) {
            char temp = chars[begin];
            chars[begin] = chars[end];
            chars[end] = temp;
            begin++;
            end--;
        }
        return new String(chars);
    }

    @Test
    public void test() {
       /* String item = "LeetCode";
        System.out.println(reverseSingleWord(item));*/
        String s = "Let's take LeetCode contest";
        System.out.println(reverseWords(s));
    }
}
