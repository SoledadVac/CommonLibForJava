package leetcode.ArrayAndString;

import com.alibaba.fastjson.JSONObject;
import org.junit.Test;

public class A48_RotateImage {

    public void rotate(int[][] matrix) {
        //  [i,j]  -> [j,n-1-i]
        //  [i,j]  <- [n-1-j,i] <- [n-1-i,n-1-j] <- [j,n-1-i] <- temp
        int length=matrix.length;
        for(int i=0;i<length/2;i++){
            for(int j=0;j<(length+1)/2;j++){
                int temp=matrix[i][j];
                matrix[i][j]=matrix[length-1-j][i];
                matrix[length-1-j][i]=matrix[length-1-i][length-1-j];
                matrix[length-1-i][length-1-j]=matrix[j][length-1-i];
                matrix[j][length-1-i]=temp;
            }
        }
    }

    @Test
    public void test(){
        //Input: matrix = [[1,2,3],[4,5,6],[7,8,9]] Output: [[7,4,1],[8,5,2],[9,6,3]]
        int[][] matrix={
                {1,2,3},
                {4,5,6},
                {7,8,9}
        };
        rotate(matrix);
        System.out.println(JSONObject.toJSONString(matrix));
    }
}
