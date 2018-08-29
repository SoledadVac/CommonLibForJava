package com.common.lib.demo.algorithm;

import org.junit.Test;

/**
 * \* Created: liuhuichao
 * \* Date: 2018/7/5
 * \* Time: 下午9:15
 * \* Description:青蛙跳问题
 *
 *       **一、问题描述

            一只青蛙一次可以跳上1级台阶，也可以跳上2级。求该青蛙跳上一个n级的台阶总共有多少种跳法。

         **二、算法分析

             因为青蛙一次只能跳上1级台阶或者两级台阶，所以对于第n级台阶来说，青蛙只能从第n-1级台阶或者第n-2级台阶跳上。

             设青蛙跳上第n级台阶有f(n)种跳法，则

             f(n) = f(n-1)+f(n-2)，n>2

             f(2) = 2

             f(1) = 1

         **三、算法设计

             两种解题方案，一，递归法；二，迭代法

             根据递推公式进行求解
 * \
 */
public class FrogClimb {

    @Test
    public void test(){
        int floorNum=3;
        System.out.println("方法为："+jumpFloor(floorNum));

    }

    /**
     * 使用递归方法获取方法数目
     * @param floorNum
     * @return
     */
    private int jumpFloor(int floorNum){
        if(floorNum<=0){
            return 0;
        }
        if(floorNum==1){
            return 1;
        }
        if(floorNum==2){
            return 2;
        }
        return jumpFloor(floorNum-1)+jumpFloor(floorNum-2);
    }

    /**
     * 使用迭代方法获取数目
     * @param floorNum
     * @return
     */
    private int jumpFloor1(int floorNum){
        if(floorNum <= 0) return 0;
        if(floorNum == 1) return 1;
        if(floorNum == 2) return 2;
        int one = 1;
        int two = 2;
        int result =  0;
        for(int i=2; i<floorNum; i++){
            result = one + two;
            one = two;
            two = floorNum;
        }
        return result;
    }


}
