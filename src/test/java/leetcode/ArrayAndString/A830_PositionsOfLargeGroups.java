package leetcode.ArrayAndString;

import com.alibaba.fastjson.JSONObject;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * \* Created: liuhuichao
 * \* Date: 2020/6/16
 * \* Time: 27:30 下午
 * \* Description: 较大分组的位置
 * \
 * 在一个由小写字母构成的字符串 S 中，包含由一些连续的相同字符所构成的分组。
 * <p>
 * 例如，在字符串 S = "abbxxxxzyy" 中，就含有 "a", "bb", "xxxx", "z" 和 "yy" 这样的一些分组。
 * <p>
 * 我们称所有包含大于或等于三个连续字符的分组为较大分组。找到每一个较大分组的起始和终止位置。
 * <p>
 * 最终结果按照字典顺序输出。
 * <p>
 * 示例 1:
 * <p>
 * 输入: "abbxxxxzzy"
 * 输出: [[3,6]]
 * 解释: "xxxx" 是一个起始于 3 且终止于 6 的较大分组。
 * 示例 2:
 * <p>
 * 输入: "abc"
 * 输出: []
 * 解释: "a","b" 和 "c" 均不是符合要求的较大分组。
 * 示例 3:
 * <p>
 * 输入: "abcdddeeeeaabbbcd"
 * 输出: [[3,5],[6,9],[12,14]]
 * 说明:  1 <= S.length <= 1000
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/positions-of-large-groups
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class A830_PositionsOfLargeGroups {
    public List<List<Integer>> largeGroupPositions(String S) {
        List<List<Integer>> result = new ArrayList<>();
        int begin = 0;
        int end = 0;
        char temp = ' ';
        while (end < S.length()) {
            char s = S.charAt(end);
            if (temp == ' ') {
                temp = s;
                end++;
                continue;
            }
            if (s != temp) {
                if (end - 1 - begin >= 2) {
                    List<Integer> item = new ArrayList<>();
                    item.add(begin);
                    item.add(end - 1);
                    result.add(item);
                }
                begin = end;
                temp = s;
            }
            if (end == S.length() - 1) {
                if (end - begin >= 2) {
                    List<Integer> item = new ArrayList<>();
                    item.add(begin);
                    item.add(end);
                    result.add(item);
                }
            }
            end++;
        }
        return result;
    }

    @Test
    public void test() {
        // String s = "abcdddeeeeaabbbcd"; //[[3,5],[6,9],[12,14]]
        //String s = "bababbabaa"; //[]
        //String s = "abbxxxxzzy"; //[3,6]
        String s = "aaa";
        System.out.println(JSONObject.toJSONString(largeGroupPositions(s)));
    }
}
