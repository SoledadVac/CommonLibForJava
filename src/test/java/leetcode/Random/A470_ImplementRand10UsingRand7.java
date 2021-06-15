package leetcode.Random;

import org.junit.Test;

import java.util.Random;

/**
 * \* Created: liuhuichao
 * \* Date: 2021/6/15
 * \* Time: 27:36 下午
 * \* Description: implement random10 using random7
 * \
 */
public class A470_ImplementRand10UsingRand7 {

    public int rand10() {
        int v1 = (rand7() - 1) * 7;
        int v2 = rand7();
        int sum = v1 + v2;
        if (v1 + v2 > 40) {
            return rand10();
        }
        return 1 + (sum - 1) % 10;
    }


    /**
     * generate a int value , between [1,7]
     *
     * @return
     */
    public int rand7() {
        return new Random().nextInt(7) + 1;
    }

    @Test
    public void test() {
        System.out.println();
    }

}
