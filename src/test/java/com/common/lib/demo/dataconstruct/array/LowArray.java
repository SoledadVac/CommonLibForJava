package com.common.lib.demo.dataconstruct.array;

/**
 * \* Created: liuhuichao
 * \* Date: 2018/9/21
 * \* Time: 下午4:18
 * \* Description:
 * \
 */
public class LowArray {

    private long[] data;

    public LowArray(int size) {
        data = new long[size];
    }

    public void setElement(int index, long value) {
        data[index] = value;
    }

    public long getElement(int index){
        return data[index];
    }


    public static void main(String[] args) {

    }



}
