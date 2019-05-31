package leetcode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * \* Created: liuhuichao
 * \* Date: 2019/5/31
 * \* Time: 26:54 PM
 * \* Description:从数据流中移动平均值
 * \
 * Given a stream of integers and a window size, calculate the moving average of all integers in the sliding window.
 * <p>
 * For example,
 * MovingAverage m = new MovingAverage(3);
 * m.next(1) = 1
 * m.next(10) = (1 + 10) / 2
 * m.next(3) = (1 + 10 + 3) / 3
 * m.next(5) = (10 + 3 + 5) / 3
 * <p>
 * 给一个整数流和一个窗口，计算在给定大小的窗口里的数字的平均值。
 * <p>
 * 解法：队列queue，用一个queue记录进入窗口的整数。当流进窗口的整数不足时，计算所有窗口内的数字和返回，当进入窗口的整数多于窗口大小时，移除最先进入窗口的整数，新的整数进入queue，然后计算窗口内的整数和。
 */
public class A346_MovingAverageFromDataStream {

    public class MovingAverage {
        private double previousSum = 0.0;
        private int maxSize;
        private Queue<Integer> currentWindow;

        /**
         * Initialize your data structure here.
         */
        public MovingAverage(int size) {
            currentWindow = new LinkedList<>();
            maxSize = size;
        }

        public double next(int val) {
            if (currentWindow.size() == maxSize) {
                previousSum -= currentWindow.remove();
            }
            currentWindow.add(val);
            previousSum += val;
            return previousSum / currentWindow.size();
        }
    }

}
