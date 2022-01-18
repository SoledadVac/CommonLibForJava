package com.common.lib.demo.algorithm;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.junit.Test;

import java.util.Arrays;
import java.util.Comparator;


/**
 * \* Created: liuhuichao
 * \* Date: 2018/7/5
 * \* Time: 下午9:15
 * \* Description: 获取数组中第2大的数字
 * \
 */
public class MaxGetTest {

    @Test
    public void getSecondNumTest(){
        int[] sourceData={3,-1,2,8,4,1,99};
        System.out.println("第二大的数字为："+getSecondNum(sourceData));
    }

    @Test
    public void reverseArrayTest() {
       /* int[] source={1,2};
        System.out.println(Arrays.toString(reverseArray(source)));*/
    }

    /**
     * 获取数组中第2大的数字
     * @param sourceData
     * @return
     */
    private int getSecondNum(int[] sourceData){
        if(sourceData==null || sourceData.length==0){
            return -1;
        }
        int max=Integer.MIN_VALUE;
        int secondMax=Integer.MIN_VALUE;
        for(int i=0;i<sourceData.length;i++){
           /* if(i==0){
                max=sourceData[0];
                secondMax=sourceData[0];
            }*/
            if(sourceData[i]>max){
                secondMax=max;
                max=sourceData[i];
            }else{
                if(sourceData[i]>secondMax){
                    secondMax=sourceData[i];
                }
            }
        }
        return secondMax;
    }

    /**
     * 数组倒序 1,2,3,4   /  1,2,3,4,5
     * @param sourceData
     * @return
     */
    private int[] reverseArray(int[] sourceData){
        int[] result=null;
        int left=0;
        int right=sourceData.length-1;
        for(int i=0;i<sourceData.length/2+1;i++){
            if(right-left==1 || right==left){
                break;
            }
            int tmp=sourceData[left];
            sourceData[left]=sourceData[right];
            sourceData[right]=tmp;
            left++;
            right--;

        }
        return sourceData;
    }
    private int getSecondNum(int[] sourceData,int index){
        return 0;

    }
}
