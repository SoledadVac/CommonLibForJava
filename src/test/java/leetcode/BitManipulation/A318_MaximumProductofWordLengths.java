package leetcode.BitManipulation;

import org.junit.Test;


/**
 * \* Created: liuhuichao
 * \* Date: 2019/12/24
 * \* Time: 28:53 下午
 * \* Description: 最大单词长度乘积
 * \
 */
public class A318_MaximumProductofWordLengths {

    public int maxProduct(String[] words) {
        int result = 0;
        int[] posFlag = new int[words.length]; //字母标记
        for (int i = 0; i < words.length; i++) {
            char[] datas = words[i].toCharArray();
            for (int j = 0; j < datas.length; j++) {
                int value = datas[j] - 'a';
                posFlag[i] |= 1 << value;
            }
        }
        for (int i = 0; i < words.length; i++) {
            for (int j = i + 1; j < words.length; j++) {
                if ((posFlag[i] & posFlag[j]) == 0) {
                    result = Math.max(result, words[i].length() * words[j].length());
                }
            }
        }
        return result;
    }

    @Test
    public void test() {
        String[] words = {"abcw", "baz", "foo", "bar", "xtfn", "abcdef"};
        System.out.println(maxProduct(words));
    }
}
