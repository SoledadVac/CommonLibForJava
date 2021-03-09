package leetcode.ArrayAndString;

import org.junit.Test;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Stack;

/**
 * \* Created: liuhuichao
 * \* Date: 2021/3/9
 * \* Time: 34:45 上午
 * \* Description: 删除字符串中的所有相邻重复项
 * 给出由小写字母组成的字符串 S，重复项删除操作会选择两个相邻且相同的字母，并删除它们。
 * <p>
 * 在 S 上反复执行重复项删除操作，直到无法继续删除。
 * <p>
 * 在完成所有重复项删除操作后返回最终的字符串。答案保证唯一。
 * <p>
 *  
 * <p>
 * 示例：
 * <p>
 * 输入："abbaca"
 * 输出："ca"
 * 解释：
 * 例如，在 "abbaca" 中，我们可以删除 "bb" 由于两字母相邻且相同，这是此时唯一可以执行删除操作的重复项。之后我们得到字符串 "aaca"，其中又只有 "aa" 可以执行重复项删除操作，所以最后的字符串为 "ca"。
 *  
 * <p>
 * 提示：
 * <p>
 * 1 <= S.length <= 20000
 * S 仅由小写英文字母组成。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/remove-all-adjacent-duplicates-in-string
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * \
 */
public class A1047_RemoveAllAdjacentDuplicatesInString {
    public String removeDuplicates0(String S) {
        StringBuilder result = new StringBuilder();
        if (S.length() == 0) {
            return result.toString();
        }
        Deque queue = new LinkedList<Character>();
        for (int i = 0; i < S.length(); i++) {
            if (queue.isEmpty()) {
                queue.push(S.charAt(i));
                continue;
            }
            if (queue.peek().equals(S.charAt(i))) {
                queue.pop();
            } else {
                queue.push(S.charAt(i));
            }
        }
        while (!queue.isEmpty()) {
            result.append(queue.removeLast());
        }
        return result.toString();
    }

    public String removeDuplicates(String S) {
        StringBuilder result = new StringBuilder();
        if (S.length() == 0) {
            return result.toString();
        }
        for (int i = 0; i < S.length(); i++) {
            if (result.length() == 0) {
                result.append(S.charAt(i));
                continue;
            }
            if (result.charAt(result.length() - 1) == (S.charAt(i))) {
                result.deleteCharAt(result.length() - 1);
            } else {
                result.append(S.charAt(i));
            }
        }
        return result.toString();
    }

    @Test
    public void test() {
        String s = "abbaca"; //ca
        System.out.println(removeDuplicates(s));
    }
}
