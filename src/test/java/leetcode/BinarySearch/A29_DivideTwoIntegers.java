package leetcode.BinarySearch;

import org.junit.Test;

public class A29_DivideTwoIntegers {

    public int divide(int dividend, int divisor) {
        // (quotient+1)*divisor > dividend  && quotient*divisor <= dividend
        //  or
        // (quotient+1)*divisor >= dividend  && quotient*divisor < dividend
        int sign=-1;
        if(divisor==0){
            return 0;
        }
        if(divisor==1){
            return dividend;
        }
        if(dividend ==divisor){
            return 1;
        }
        if(dividend==Integer.MIN_VALUE && divisor==-1){
            return Integer.MAX_VALUE;
        }
        if((dividend>0 && divisor>0) || (dividend<0 && divisor<0)){
            sign=1;
        }
        long low=0;
        long high=Math.abs((long)dividend);
        divisor=Math.abs(divisor);
        while (high>=low){
            long quotient=low+(high-low)/2;
            if(matchQuotient(Math.abs((long)dividend),divisor,quotient)){
                long quotientLong= (long) (quotient + 1) * divisor;
                if(quotientLong==Math.abs((long)dividend)){
                    return (int)(quotient+1)*sign;
                }else{
                    return (int)quotient*sign;
                }
            }
            if((quotient+1)*divisor>Math.abs((long)dividend) && quotient*divisor>Math.abs((long)dividend)){
                high=quotient-1;
            }
            if((quotient+1)*divisor<Math.abs((long)dividend) && quotient*divisor<Math.abs((long)dividend)){
                low=quotient+1;
            }
        }
        return 0;
    }

    public boolean matchQuotient(long dividend, long divisor,long quotient){
        return ((quotient+1)*divisor>dividend && quotient*divisor<=dividend)
                || ((quotient+1)*divisor>=dividend && quotient*divisor<dividend);
    }


    public int bsQuotient(int low,int high,int dividend, int divisor){
        int quotient=low+(high-low)/2;
        if(matchQuotient(dividend,divisor,quotient)){
            if((quotient+1)*divisor==dividend){
                return (quotient+1);
            }else{
                return quotient;
            }
        }
        if((quotient+1)*divisor>dividend && quotient*divisor>dividend){
            return bsQuotient(low,quotient-1,dividend,divisor);
        }
        if((quotient+1)*divisor<dividend && quotient*divisor<dividend){
            return bsQuotient(quotient+1,high,dividend,divisor);
        }
        return 0;
    }



    @Test
    public void test(){
        //测试用例:-2147483648
        //			2
        //	测试结果:0
        //	期望结果:-1073741824
        int dividend=-2147483648;
        int divisor=2;
        System.out.println(divide(dividend,divisor));
    }
}
