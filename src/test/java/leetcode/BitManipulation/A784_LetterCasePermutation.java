package leetcode.BitManipulation;

import com.alibaba.fastjson.JSONObject;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * \* Created: liuhuichao
 * \* Date: 2019/9/17
 * \* Time: 28:13 PM
 * \* Description: 字母大小写全排列
 * 给定一个字符串S，通过将字符串S中的每个字母转变大小写，我们可以获得一个新的字符串。返回所有可能得到的字符串集合。
 * <p>
 * 示例:
 * 输入: S = "a1b2"
 * 输出: ["a1b2", "a1B2", "A1b2", "A1B2"]
 * <p>
 * 输入: S = "3z4"
 * 输出: ["3z4", "3Z4"]
 * <p>
 * 输入: S = "12345"
 * 输出: ["12345"]
 * 注意：
 * <p>
 * S 的长度不超过12。
 * S 仅由数字和字母组成。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/letter-case-permutation
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class A784_LetterCasePermutation {

    public List<String> letterCasePermutation(String S) {
        List<String> result = new ArrayList<>();
        dfs(0, S.length(), S.toCharArray(), result);
        return result;
    }

    public void dfs(int index, int length, char[] chars, List<String> result) {
        if (index == length) {
            result.add(new String(chars));
            return;
        }
        dfs(index + 1, length, chars, result);
        if (Character.isLetter(chars[index])) {
            chars[index] ^= (1 << 5);
            dfs(++index, length, chars, result);
        }
    }

    @Test
    public void test() {
        String s = "a1b2";
        System.out.println(JSONObject.toJSONString(letterCasePermutation(s)));
    }
}
