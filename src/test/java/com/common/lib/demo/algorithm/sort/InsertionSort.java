package com.common.lib.demo.algorithm.sort;

import com.alibaba.fastjson.JSONObject;
import org.junit.Test;

/**
 * \* Created: liuhuichao
 * \* Date: 2021/1/10
 * \* Time: 4:59 下午
 * \* Description:插入排序（Insertion Sort）
 * \\插入排序（Insertion-Sort）的算法描述是一种简单直观的排序算法。
 * 它的工作原理是通过构建有序序列，对于未排序数据，在已排序序列中从后向前扫描，找到相应位置并插入。
 * <p>
 * 3.1 算法描述
 * 一般来说，插入排序都采用in-place在数组上实现。具体算法描述如下：
 * <p>
 * 1,从第一个元素开始，该元素可以认为已经被排序；
 * 2,取出下一个元素，在已经排序的元素序列中从后向前扫描；
 * 3,如果该元素（已排序）大于新元素，将该元素移到下一位置；
 * 4,重复步骤3，直到找到已排序的元素小于或者等于新元素的位置；
 * 5,将新元素插入到该位置后；
 * 6,重复步骤2~5。
 */
public class InsertionSort {
    @Test
    public void test() {
        Integer[] data = {2, 10, 0, 1, 2, 9};
        sort(data);
        System.out.println(JSONObject.toJSONString(data));
    }

    void sort(Integer[] data) {
        //https://www.cnblogs.com/onepixel/articles/7674659.html

    }
}
