package leetcode.ArrayAndString;

import org.junit.Test;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class A43_MultiplyStrings {



    public List<Integer> getIntListFromString(String num){
        List<Integer> result=new LinkedList<>();
        for(int i=0;i<num.length();i++){
            int v=num.charAt(i)-'0';
            result.add(v);
        }
        return result;
    }

    public String multiply(String num1, String num2) {
        if(num1.equals("0") || num2.equals("0")){
            return "0";
        }
        List<Integer> num1List=getIntListFromString(num1);
        List<Integer> num2List=getIntListFromString(num2);

        List<List<Integer>> result=new LinkedList<>();
        for(int index1=num1List.size()-1;index1>=0;index1--){
            int item1=num1List.get(index1);
            List<Integer> temp=new LinkedList<>();
            for(int i=0;i<num1List.size()-index1;i++){
                temp.add(0);
            }
            for(int index2=num2List.size()-1;index2>=0;index2--){
                int item2=num2List.get(index2);
                if(temp.size()==0){
                    int yu=(item1*item2)%10;
                    int moveFur=(item1*item2)/10;
                    temp.add(yu);
                    temp.add(moveFur);
                }else{
                    int last=temp.get(temp.size()-1);
                    int yu=(item1*item2+last)%10;
                    int moveFur=(item1*item2+last)/10;
                    temp.set(temp.size()-1,yu);
                    temp.add(moveFur);
                }
            }
            result.add(temp);
        }
        List<Integer> retResult=new LinkedList<>();
        int last=0;
        for(int i=0;i<result.get(result.size()-1).size();i++){
            int sum=last;
            for(List<Integer> item:result){
                if(i<=item.size()-1){
                    sum+=item.get(i);
                }
            }
            int yu=sum % 10;
            last=sum /10;
            retResult.add(yu);
        }

        if(retResult.get(retResult.size()-1)==0){
            retResult.remove(retResult.size()-1);
        }
        StringBuilder sb=new StringBuilder("");
        for(int i=retResult.size()-1;i>=0;i--){
            sb.append(retResult.get(i));
        }
        return sb.toString();
    }

    @Test
    public void test(){
        //Input: num1 = "2", num2 = "3"
        // Output: "6"
        //String num1="123";
        //String num2="456";

        //测试用例:"498828660196"
        //			"840477629533"
        //	测试结果:"-3269442614257959980"
        //	期望结果:"419254329864656431168468"
        String num1="456";
        String num2="123";
        System.out.println(multiply(num1,num2));
    }


}
