package leetcode.BitManipulation;

import com.alibaba.fastjson.JSONObject;
import com.kenai.jaffl.annotations.In;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Handler;

/**
 * \* Created: liuhuichao
 * \* Date: 2019/9/23
 * \* Time: 35:32 AM
 * \* Description: 重复的DNA序列
 * \
 * 所有 DNA 由一系列缩写为 A，C，G 和 T 的核苷酸组成，例如：“ACGAATTCCG”。
 * 在研究 DNA 时，识别 DNA 中的重复序列有时会对研究非常有帮助。
 * <p>
 * 编写一个函数来查找 DNA 分子中所有出现超多一次的10个字母长的序列（子串）。
 * <p>
 * 示例:
 * <p>
 * 输入: s = "AAAAACCCCCAAAAACCCCCCAAAAAGGGTTT"
 * <p>
 * 输出: ["AAAAACCCCC", "CCCCCAAAAA"]
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/repeated-dna-sequences
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class A187_RepeatedDNASequences {
    public List<String> findRepeatedDnaSequences(String s) {
        List<String> result = new ArrayList<>();
        if (s == null || s.length() < 10) {
            return result;
        }
        Map<Character, Integer> table = new HashMap<>();
        table.put('A', 0); //00
        table.put('C', 1); //01
        table.put('G', 2); //10
        table.put('T', 3); //11
        int[] subStringExist = new int[1 << 20];
        int dataRange = 0;
        int mask = ((1 << 18) - 1);
        for (int i = 0; i < s.length(); i++) {
            dataRange <<= 2; //原来积累的字母向左移动两位，方便低位加入新字母
            dataRange += table.get(s.charAt(i)); //加入新的一个字母
            if (i >= 9) {
                // i 移动到10位之后
                if (subStringExist[dataRange] == 1) {
                    //如果之前加入过了，说明出现重复了
                    result.add(s.substring(i - 9, i + 1));
                }
                subStringExist[dataRange] += 1;//标记又出现了一次
                dataRange &= mask; //后18位保持，其他位置0
            }
        }
        return result;
    }

    @Test
    public void test() {
        String s = "AAAAACCCCCAAAAACCCCCCAAAAAGGGTTT"; //["AAAAACCCCC", "CCCCCAAAAA"]
        System.out.println(JSONObject.toJSONString(findRepeatedDnaSequences(s)));
    }
}
