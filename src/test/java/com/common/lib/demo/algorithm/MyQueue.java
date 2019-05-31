package com.common.lib.demo.algorithm;

import java.util.ArrayList;
import java.util.List;

/**
 * \* Created: liuhuichao
 * \* Date: 2019/5/30
 * \* Time: 6:26 PM
 * \* Description: 队列应支持两种操作：入队和出队。入队会向队列追加一个新元素，而出队会删除第一个元素。
 * \
 */
public class MyQueue {
    private List<Integer> data;
    private int pointer;

    public MyQueue() {
        data = new ArrayList<>();
        pointer = 0;
    }

    public boolean isEmpty() {
        return pointer >= data.size();
    }

    /**
     * Get the front item from the queue.
     */
    public int Front() {
        return data.get(pointer);
    }

    /**
     * Insert an element into the queue. Return true if the operation is successful.
     */
    public boolean enQueue(int x) {
        data.add(x);
        return true;
    }


    /**
     * Delete an element from the queue. Return true if the operation is successful.
     */
    public boolean deQueue() {
        if (isEmpty() == true) {
            return false;
        }
        pointer++;
        return true;
    }

}
