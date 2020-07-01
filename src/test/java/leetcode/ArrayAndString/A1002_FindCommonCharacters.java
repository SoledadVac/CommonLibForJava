package leetcode.ArrayAndString;

import com.alibaba.fastjson.JSONObject;
import org.junit.Test;

import java.util.*;

/**
 * \* Created: liuhuichao
 * \* Date: 2020/7/1
 * \* Time: 29:32 下午
 * \* Description: 查找常用字符
 * \
 * 给定仅有小写字母组成的字符串数组 A，返回列表中的每个字符串中都显示的全部字符（包括重复字符）组成的列表。
 * 例如，如果一个字符在每个字符串中出现 3 次，但不是 4 次，则需要在最终答案中包含该字符 3 次。
 * <p>
 * 你可以按任意顺序返回答案。
 * 示例 1：
 * <p>
 * 输入：["bella","label","roller"]
 * 输出：["e","l","l"]
 * 示例 2：
 * <p>
 * 输入：["cool","lock","cook"]
 * 输出：["c","o"]
 *  
 * <p>
 * 提示：
 * <p>
 * 1 <= A.length <= 100
 * 1 <= A[i].length <= 100
 * A[i][j] 是小写字母
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/find-common-characters
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class A1002_FindCommonCharacters {

    /**
     * 效率低
     *
     * @param A
     * @return
     */
    public List<String> commonChars(String[] A) {
        List<String> result = new ArrayList<>();
        Map<Character, Integer> data = new HashMap<>();
        for (int i = 0; i < A.length; i++) {
            char[] chars = A[i].toCharArray();
            if (i == 0) {
                for (char c : chars) {
                    data.put(c, data.getOrDefault(c, 0) + 1);
                }
                continue;
            }
            Iterator<Map.Entry<Character, Integer>> iterator = data.entrySet().iterator();
            while (iterator.hasNext()) {
                Map.Entry<Character, Integer> entry = iterator.next();
                char key = entry.getKey();
                boolean containKey = false;
                Map<Character, Integer> dataTemp = new HashMap<>();
                for (char c : chars) {
                    dataTemp.put(c, dataTemp.getOrDefault(c, 0) + 1);
                    if (c == key) {
                        containKey = true;
                    }
                }
                if (!containKey) {
                    iterator.remove();
                } else {
                    //检测个数
                    data.put(key, Math.min(data.get(key), dataTemp.get(key)));
                }
            }
        }
        for (char key : data.keySet()) {
            for (int i = 0; i < data.get(key); i++) {
                result.add(key + "");
            }
        }
        return result;
    }

    @Test
    public void test() {
        //String[] A = {"bella", "label", "roller"};
        String[] A = {"cool", "lock", "cook"};
        System.out.println(JSONObject.toJSONString(commonChars(A)));
    }

}
