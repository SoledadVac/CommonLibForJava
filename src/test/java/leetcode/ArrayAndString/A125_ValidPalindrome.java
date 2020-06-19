package leetcode.ArrayAndString;

import org.junit.Test;

/**
 * \* Created: liuhuichao
 * \* Date: 2020/6/19
 * \* Time: 28:32 下午
 * \* Description: 验证回文串
 * \
 * 给定一个字符串，验证它是否是回文串，只考虑字母和数字字符，可以忽略字母的大小写。
 * <p>
 * 说明：本题中，我们将空字符串定义为有效的回文串。
 * <p>
 * 示例 1:
 * <p>
 * 输入: "A man, a plan, a canal: Panama"
 * 输出: true
 * 示例 2:
 * <p>
 * 输入: "race a car"
 * 输出: false
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/valid-palindrome
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class A125_ValidPalindrome {
    public boolean isPalindrome(String s) {
        if (s.equals("")) {
            return true;
        }
        int indexPrev = 0;
        int indexEnd = s.length() - 1;
        while (indexPrev != indexEnd && indexPrev < s.length() - 1 && indexEnd > 0) {
            char c1 = s.charAt(indexPrev);
            char c2 = s.charAt(indexEnd);
            if (!Character.isLetterOrDigit(c1)) {
                indexPrev++;
                continue;
            }
            if (!Character.isLetterOrDigit(c2)) {
                indexEnd--;
                continue;
            }
            //为字母和数字
            if (c1 == c2 || Character.toLowerCase(c1) == Character.toLowerCase(c2)) {
                indexPrev++;
                indexEnd--;
            } else {
                return false;
            }
        }
        return true;
    }

    @Test
    public void test() {
        String s = "race a car";
        System.out.println(isPalindrome(s));
    }

}
