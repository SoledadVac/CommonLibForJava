package leetcode.BitManipulation;

import org.junit.Test;

/**
 * \* Created: liuhuichao
 * \* Date: 2019/9/17
 * \* Time: 35:08 AM
 * \* Description: 找不同
 * \
 * 给定两个字符串 s 和 t，它们只包含小写字母。
 * <p>
 * 字符串 t 由字符串 s 随机重排，然后在随机位置添加一个字母。
 * <p>
 * 请找出在 t 中被添加的字母。
 * <p>
 *  
 * <p>
 * 示例:
 * <p>
 * 输入：
 * s = "abcd"
 * t = "abcde"
 * <p>
 * 输出：
 * e
 * <p>
 * 解释：
 * 'e' 是那个被添加的字母。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/find-the-difference
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class A389_FindtheDifference {

    public char findTheDifference(String s, String t) {
        int result = 0;
        for (int i = 0; i < t.length(); i++) {
            if (i >= s.length()) {

                result ^= t.charAt(i);
                continue;
            }
            result ^= s.charAt(i) ^ t.charAt(i);

        }
        return (char) result;
    }

    @Test
    public void test() {
        String s = "abcd";
        String t = "abcde";
        System.out.println("result = " + findTheDifference(s, t));
    }

}
