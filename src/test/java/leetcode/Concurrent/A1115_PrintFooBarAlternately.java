package leetcode.Concurrent;

import java.util.concurrent.Semaphore;

/**
 * \* Created: liuhuichao
 * \* Date: 2019/10/17
 * \* Time: 27:01 PM
 * \* Description: 交替打印FooBar
 * \
 * 我们提供一个类：
 * <p>
 * class FooBar {
 * public void foo() {
 *     for (int i = 0; i < n; i++) {
 *       print("foo");
 *     }
 * }
 * <p>
 * public void bar() {
 *     for (int i = 0; i < n; i++) {
 *       print("bar");
 *     }
 * }
 * }
 * 两个不同的线程将会共用一个 FooBar 实例。其中一个线程将会调用 foo() 方法，另一个线程将会调用 bar() 方法。
 * <p>
 * 请设计修改程序，以确保 "foobar" 被输出 n 次。
 * <p>
 *  
 * <p>
 * 示例 1:
 * <p>
 * 输入: n = 1
 * 输出: "foobar"
 * 解释: 这里有两个线程被异步启动。其中一个调用 foo() 方法, 另一个调用 bar() 方法，"foobar" 将被输出一次。
 * 示例 2:
 * <p>
 * 输入: n = 2
 * 输出: "foobarfoobar"
 * 解释: "foobar" 将被输出两次。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/print-foobar-alternately
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class A1115_PrintFooBarAlternately {
    class FooBar {

        Semaphore semFoo = new Semaphore(1);
        Semaphore semBar = new Semaphore(0);

        private int n;

        public FooBar(int n) {
            this.n = n;
        }

        public void foo(Runnable printFoo) throws InterruptedException {

            for (int i = 0; i < n; i++) {
                semFoo.acquire();
                // printFoo.run() outputs "foo". Do not change or remove this line.
                printFoo.run();
                semBar.release();

            }
        }

        public void bar(Runnable printBar) throws InterruptedException {


            for (int i = 0; i < n; i++) {
                semBar.acquire();
                // printBar.run() outputs "bar". Do not change or remove this line.
                printBar.run();
                semFoo.release();
            }
        }
    }


}
