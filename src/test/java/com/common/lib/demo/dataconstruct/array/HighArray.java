package com.common.lib.demo.dataconstruct.array;

/**
 * \* Created: liuhuichao
 * \* Date: 2018/9/21
 * \* Time: 下午4:24
 * \* Description:
 * \
 */
public class HighArray {

    private long[] data;
    private int nElement;

    public void setElement(int index, long value) {
        data[index] = value;
        nElement++;
    }
}
