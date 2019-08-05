package leetcode.ArrayAndString;

import org.junit.Test;

/**
 * \* Created: liuhuichao
 * \* Date: 2019/4/24
 * \* Time: 4:41 PM
 * \* Description:翻转字符串里的单词
 * 给定一个字符串，逐个翻转字符串中的每个单词。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入: "the sky is blue"
 * 输出: "blue is sky the"
 * 示例 2：
 * <p>
 * 输入: "  hello world!  "
 * 输出: "world! hello"
 * 解释: 输入字符串可以在前面或者后面包含多余的空格，但是反转后的字符不能包括。
 * 示例 3：
 * <p>
 * 输入: "a good   example"
 * 输出: "example good a"
 * 解释: 如果两个单词间有多余的空格，将反转后单词间的空格减少到只含一个。
 * <p>
 * 说明：
 * <p>
 * 无空格字符构成一个单词。
 * 输入字符串可以在前面或者后面包含多余的空格，但是反转后的字符不能包括。
 * 如果两个单词间有多余的空格，将反转后单词间的空格减少到只含一个。
 * <p>
 * <p>
 * 进阶：
 * <p>
 * 请选用 C 语言的用户尝试使用 O(1) 额外空间复杂度的原地解法。
 * \
 */
public class A151_ReverseWordsInAString {
    public String reverseWords(String s) {
        String[] splitStr = s.trim().split(" ");
        StringBuilder sb=new StringBuilder();
        for (int i = splitStr.length - 1; i >= 0; i--) {
            if(splitStr[i].trim().equals("")){
                continue;
            }
            sb.append(splitStr[i]);
            if(i!=0){
                sb.append(" ");
            }
        }
        return sb.toString();
    }

    @Test
    public void test() {
        String source = "the sky is blue";
        System.out.println(reverseWords(source));

    }
}
