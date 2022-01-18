package leetcode.OrderedMap;

import com.alibaba.fastjson.JSONObject;
import com.common.lib.demo.Student;
import com.google.gson.JsonObject;
import org.junit.Test;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * \* Created: liuhuichao
 * \* Date: 2019/3/13
 * \* Time: 5:33 PM
 * \* Description:Tag=Map
 * \
 * <p>
 * 在考场里，一排有 N 个座位，分别编号为 0, 1, 2, ..., N-1 。
 * <p>
 * 当学生进入考场后，他必须坐在能够使他与离他最近的人之间的距离达到最大化的座位上。如果有多个这样的座位，他会坐在编号最小的座位上。(另外，如果考场里没有人，那么学生就坐在 0 号座位上。)
 * <p>
 * 返回 ExamRoom(int N) 类，它有两个公开的函数：其中，函数 ExamRoom.seat() 会返回一个 int （整型数据），代表学生坐的位置；函数 ExamRoom.leave(int p) 代表坐在座位 p 上的学生现在离开了考场。请确保每次调用 ExamRoom.leave(p) 时都有学生坐在座位 p 上。
 * <p>
 * <p>
 * <p>
 * 示例：
 * <p>
 * 输入：["ExamRoom","seat","seat","seat","seat","leave","seat"]
 * , [[10],[],[],[],[],[4],[]]
 * 输出：[null,0,9,4,2,null,5]
 * 解释：
 * ExamRoom(10) -> null
 * seat() -> 0，没有人在考场里，那么学生坐在 0 号座位上。
 * seat() -> 9，学生最后坐在 9 号座位上。
 * seat() -> 4，学生最后坐在 4 号座位上。
 * seat() -> 2，学生最后坐在 2 号座位上。
 * leave(4) -> null
 * seat() -> 5，学生最后坐在 5 号座位上。
 * <p>
 * <p>
 * 提示：
 * <p>
 * 1 <= N <= 10^9
 * 在所有的测试样例中 ExamRoom.seat() 和 ExamRoom.leave() 最多被调用 10^4 次。
 * 调用 ExamRoom.leave(p) 时需要确保当前有学生坐在座位 p 上。
 */
public class A855_ExamRoom {

    Map<Integer, Integer> seats = new ConcurrentHashMap<>();

    /**
     * 初始化
     *
     * @param N
     */
    public A855_ExamRoom(int N) {
        for (int i = 0; i < N; i++) {
            seats.put(i, -1);
        }
    }

    public int seat() {
        if (seats.get(0) == -1) {
            seats.put(0, 1);
            return 0;
        } else {


        }
        return -1;
    }

    public void leave(int p) {

    }

    @Test
    public void test() {



    }

    public static void main(String[] args) {
       /* List<Student> list = new ArrayList<>();
        Student s1 = new Student();
        s1.setId(1);
        s1.setCheck(false);
        Student s2 = new Student();
        s2.setId(2);
        s2.setCheck(false);
        Student s3 = new Student();
        s3.setId(3);
        s3.setCheck(true);
        Student s4 = new Student();
        s4.setId(4);
        s4.setCheck(false);
        list.add(s1);
        list.add(s2);
        list.add(s3);
        list.add(s4);
        list.sort((c1, c2) -> {
            if (c1.getCheck()) {
                return -1;
            }
            if (c2.getCheck()) {
                return -1;
            }
            return 0;
        });
        System.out.println(JSONObject.toJSONString(list));*/

        List<Student> list = new ArrayList<>();
        Student s1 = new Student();
        s1.setId(1);
        s1.setCheck(false);
        Student s2 = new Student();
        s2.setId(2);
        s2.setCheck(false);
        Student s3 = new Student();
        s3.setId(3);
        s3.setCheck(true);
        Student s4 = new Student();
        s4.setId(4);
        s4.setCheck(false);
        list.add(s1);
        list.add(s2);
        list.add(s3);
        list.add(s4);
        list.sort(Comparator.comparingInt(c -> c.getCheck().hashCode()));
        System.out.println(JSONObject.toJSONString(list));
    }

    /*public class Student {
        public Integer id;
        public Boolean check;

        Student() {
        }

        Student(Integer id, Boolean check) {
            this.id = id;
            this.check = check;
        }
    }*/


}
