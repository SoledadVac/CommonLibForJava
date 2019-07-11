package leetcode;

import org.junit.Test;

import java.util.Stack;

/**
 * \* Created: liuhuichao
 * \* Date: 2019/7/10
 * \* Time: 31:43 PM
 * \* Description:最小栈
 * \
 * 设计一个支持 push，pop，top 操作，并能在常数时间内检索到最小元素的栈。
 * <p>
 * push(x) -- 将元素 x 推入栈中。
 * pop() -- 删除栈顶的元素。
 * top() -- 获取栈顶元素。
 * getMin() -- 检索栈中的最小元素。
 * 示例:
 * <p>
 * MinStack minStack = new MinStack();
 * minStack.push(-2);
 * minStack.push(0);
 * minStack.push(-3);
 * minStack.getMin();   --> 返回 -3.
 * minStack.pop();
 * minStack.top();      --> 返回 0.
 * minStack.getMin();   --> 返回 -2.
 * <p>
 */
public class A155_MinStack {


    class MinStack {

        private Stack<Integer> data = new Stack<>();
        private Stack<Integer> minStack = new Stack<>();


        /**
         * initialize your data structure here.
         */
        public MinStack() {

        }

        /**
         * 将元素 x 推入栈中
         *
         * @param x
         */
        public void push(int x) {
            data.push(x);
            if (minStack.isEmpty() || minStack.peek() >= x) {
                minStack.push(x);
            }
        }

        /**
         * 删除栈顶的元素
         */
        public void pop() {
            Integer reult = data.pop();
            if (minStack.peek() - reult == 0) {
                minStack.pop();
            }
        }

        /**
         * 获取栈顶元素
         *
         * @return
         */
        public int top() {
            return data.peek();
        }

        public int getMin() {
            return minStack.peek();
        }
    }

    /**
     * Your MinStack object will be instantiated and called as such:
     * MinStack obj = new MinStack();
     * obj.push(x);
     * obj.pop();
     * int param_3 = obj.top();
     * int param_4 = obj.getMin();
     */
    @Test
    public void test() {
        //["MinStack","push","push","push","getMin","pop","getMin"]
        //[[],[0],[1],[0],[],[],[]]
       /* MinStack stack = new MinStack();
        stack.push(0);
        stack.push(1);
        stack.push(0);
        stack.getMin();
        stack.pop();
        stack.getMin();*/

        //["MinStack","push","push","push","push","pop","getMin","pop","getMin","pop","getMin"]
        //[[],[512],[-1024],[-1024],[512],[],[],[],[],[],[]]
        //[null,null,null,null,null,null,-1024,null,-1024,null,512]
       /* MinStack stack = new MinStack();
        stack.push(512);
        stack.push(-1024);
        stack.push(-1024);
        stack.push(512);
        stack.pop();
        System.out.println("getMin=" + stack.getMin());
        stack.pop();
        System.out.println("getMin=" + stack.getMin());
        stack.pop();
        System.out.println("getMin=" + stack.getMin());*/

        //["MinStack","push","push","push","getMin","pop","getMin"]
        //[[],[0],[1],[0],[],[],[]]
        MinStack stack = new MinStack();
        stack.push(0);
        stack.push(1);
        stack.push(0);
        System.out.println("getMin=" + stack.getMin());
        stack.pop();
        System.out.println("getMin=" + stack.getMin());
    }
}
