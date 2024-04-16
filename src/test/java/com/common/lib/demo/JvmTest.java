package com.common.lib.demo;


import org.apache.hadoop.yarn.webapp.hamlet.Hamlet;
import org.junit.Test;

import java.beans.Transient;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Collectors;

/**
 * \* Created: liuhuichao
 * \* Date: 2021/4/10
 * \* Time: 7:56 下午
 * \* Description:
 * \
 */
public class JvmTest {

    public void sort(int[] nums) {
        for (int i = 1; i < nums.length; i++) {
            for (int j = i; j > 0; j--) {
                if (nums[j] < nums[j - 1]) {
                    swap(nums, j, j - 1);
                }
            }
        }
    }

    public void swap(int[] nums, int left, int right) {
        int tmp = nums[left];
        nums[left] = nums[right];
        nums[right] = tmp;
    }


    //优化后
    public void sort1(int[] nums) {
        for (int i = 1; i < nums.length; i++) {
            int insertIndex = i;
            int insertValue = nums[i];
            while (insertIndex > 0 && insertValue < nums[insertIndex - 1]) {
                nums[insertIndex] = nums[insertIndex - 1];
                insertIndex--;
            }
            nums[insertIndex] = insertValue;
        }
    }


    public String[] findRelativeRanks(int[] score) {
        String[] answers = new String[score.length];


        return answers;
    }


    public static void main(String[] args) {
        int x = 123;
        //int x = 120;
        //int x = -120;
        //int x = 0;
        System.out.println(reverse(x));
    }

    public static int reverse(int x) {
        int devider = 10;
        int result = 0;










        return result;
    }


}
