package leetcode;

import com.alibaba.fastjson.JSONObject;
import org.junit.Test;

import java.util.*;

/**
 * \* Created: liuhuichao
 * \* Date: 2019/7/4
 * \* Time: 31:19 PM
 * \* Description: 打开转盘锁
 * \
 * 你有一个带有四个圆形拨轮的转盘锁。每个拨轮都有10个数字： '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' 。
 * 每个拨轮可以自由旋转：例如把 '9' 变为  '0'，'0' 变为 '9' 。每次旋转都只能旋转一个拨轮的一位数字。
 * <p>
 * 锁的初始数字为 '0000' ，一个代表四个拨轮的数字的字符串。
 * <p>
 * 列表 deadends 包含了一组死亡数字，一旦拨轮的数字和列表里的任何一个元素相同，这个锁将会被永久锁定，无法再被旋转。
 * <p>
 * 字符串 target 代表可以解锁的数字，你需要给出最小的旋转次数，如果无论如何不能解锁，返回 -1。
 * <p>
 *  
 * <p>
 * 示例 1:
 * <p>
 * 输入：deadends = ["0201","0101","0102","1212","2002"], target = "0202"
 * 输出：6
 * 解释：
 * 可能的移动序列为 "0000" -> "1000" -> "1100" -> "1200" -> "1201" -> "1202" -> "0202"。
 * 注意 "0000" -> "0001" -> "0002" -> "0102" -> "0202" 这样的序列是不能解锁的，
 * 因为当拨动到 "0102" 时这个锁就会被锁定。
 * 示例 2:
 * <p>
 * 输入: deadends = ["8888"], target = "0009"
 * 输出：1
 * 解释：
 * 把最后一位反向旋转一次即可 "0000" -> "0009"。
 * 示例 3:
 * <p>
 * 输入: deadends = ["8887","8889","8878","8898","8788","8988","7888","9888"], target = "8888"
 * 输出：-1
 * 解释：
 * 无法旋转到目标数字且不被锁定。
 * 示例 4:
 * <p>
 * 输入: deadends = ["0000"], target = "8888"
 * 输出：-1
 *  
 * <p>
 * 提示：
 * <p>
 * 死亡列表 deadends 的长度范围为 [1, 500]。
 * 目标数字 target 不会在 deadends 之中。
 * 每个 deadends 和 target 中的字符串的数字会在 10,000 个可能的情况 '0000' 到 '9999' 中产生。
 * <p>
 */
public class A752_OpenTheLock {
    public int openLock(String[] deadends, String target) {
        List<String> deadEndsList = Arrays.asList(deadends);
        if (deadEndsList.contains("0000")) {
            return -1;
        }
        Queue<String> q = new LinkedList<>();
        q.add("0000");
        int step = 0;
        List<String> used = new ArrayList<>();
        while (!q.isEmpty()) {
            step++;
            int size = q.size();
            System.out.println((step) + "=" + JSONObject.toJSONString(q));
            for (int i = 0; i < size; i++) {
                String data = q.poll();
                if (deadEndsList.contains(data)) {
                    continue;
                }
                if (target.equals(data)) {
                    return step - 1;
                }
                char[] charArray = data.toCharArray();
                String n1 = String.valueOf(Character.digit(charArray[0], 9) + 1) + data.substring(1);
                String n2 = data.substring(0, 1) + String.valueOf(Character.digit(charArray[1], 9) + 1) + data.substring(2);
                String n3 = data.substring(0, 2) + String.valueOf(Character.digit(charArray[2], 9) + 1) + data.substring(3);
                String n4 = data.substring(0, 3) + String.valueOf(Character.digit(charArray[3], 9) + 1);
                String n5 = String.valueOf((Character.digit(charArray[0], 10) - 1) >= 0 ? Character.digit(charArray[0], 10) - 1 : 9) + data.substring(1);
                String n6 = data.substring(0, 1) + String.valueOf((Character.digit(charArray[1], 10) - 1) >= 0 ? Character.digit(charArray[1], 10) - 1 : 9) + data.substring(2);
                String n7 = data.substring(0, 2) + String.valueOf((Character.digit(charArray[2], 10) - 1) >= 0 ? Character.digit(charArray[2], 10) - 1 : 9) + data.substring(3);
                String n8 = data.substring(0, 3) + String.valueOf((Character.digit(charArray[3], 10) - 1) >= 0 ? Character.digit(charArray[3], 10) - 1 : 9);
                if (!used.contains(n8) && !deadEndsList.contains(n8)) {
                    q.add(n8);
                    used.add(n8);
                }
                if (!used.contains(n7) && !deadEndsList.contains(n7)) {
                    q.add(n7);
                    used.add(n7);
                }
                if (!used.contains(n6) && !deadEndsList.contains(n6)) {
                    q.add(n6);
                    used.add(n6);
                }
                if (!used.contains(n5) && !deadEndsList.contains(n5)) {
                    q.add(n5);
                    used.add(n5);
                }
                if (!used.contains(n4) && !deadEndsList.contains(n4)) {
                    q.add(n4);
                    used.add(n4);
                }
                if (!used.contains(n3) && !deadEndsList.contains(n3)) {
                    q.add(n3);
                    used.add(n3);
                }
                if (!used.contains(n2) && !deadEndsList.contains(n2)) {
                    q.add(n2);
                    used.add(n2);

                }
                if (!used.contains(n1) && !deadEndsList.contains(n1)) {
                    q.add(n1);
                    used.add(n1);
                }

            }
        }
        return -1;
    }

    @Test
    public void test() {
       /* String[] deadends = {"0201", "0101", "0102", "1212", "2002"};
        String target = "0202";*/
       /* String[] deadends = {"8888"};
        String target = "0009";*/
        String[] deadends = {"8887","8889","8878","8898","8788","8988","7888","9888"};
        String target = "8888";
        int result = openLock(deadends, target);
        System.out.println("result = " + result);
    }

    @Test
    public void test1() {
        System.out.println(Character.digit('0', 9));
        System.out.println((Character.digit('9', 9) + 1));
        System.out.println(String.valueOf((Character.digit('9', 9) + 1)));
    }
}
