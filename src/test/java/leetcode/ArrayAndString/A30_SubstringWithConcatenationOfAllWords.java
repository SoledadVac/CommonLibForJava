package leetcode.ArrayAndString;

import com.alibaba.fastjson.JSONObject;
import org.junit.Test;

import java.util.*;

public class A30_SubstringWithConcatenationOfAllWords {

    public List<Integer> findSubstring(String s, String[] words) {
        List<Integer> result=new ArrayList<>();
        //map=word:count
        Map<String,Integer> wordCountMap=new HashMap<>();
        for(String word:words){
            wordCountMap.put(word,wordCountMap.getOrDefault(word,0)+1);
        }
        int singleWordLength=words[0].length();
        int allWordsLength=singleWordLength*words.length;
        for(int i=0;i<=s.length()-allWordsLength;i++){
            Map<String,Integer> sourceMap=new HashMap<>();
            int from=i;
            String tempS=s;
            boolean containNotInArrayWord=false;
            while (from-i<allWordsLength){
                String cut=tempS.substring(from,from+singleWordLength);
                if(!wordCountMap.containsKey(cut)){
                    containNotInArrayWord=true;
                    break;
                }
                sourceMap.put(cut,sourceMap.getOrDefault(cut,0)+1);
                from=from+singleWordLength;
            }
            if(containNotInArrayWord){
                continue;
            }
            //判断俩map是否一致
            Set<String> wordSet=new HashSet<>();
            wordSet.addAll(wordCountMap.keySet());
            wordSet.addAll(sourceMap.keySet());
            boolean mapMatch=true;
            for(String w:wordSet){
                if(wordCountMap.get(w)==null){
                    mapMatch=false;
                    break;
                }
                if(!wordCountMap.get(w).equals(sourceMap.get(w))){
                    mapMatch=false;
                    break;
                }
            }
            if(mapMatch){
                result.add(i);
            }
        }
        return result;
    }


    @Test
    public void test(){
        //Input:
        //  s = "barfoothefoobarman",
        //  words = ["foo","bar"]
        //Output: [0,9]
        //String s="barfoothefoobarman";
        //String[] words={"foo","bar"};


        //测试用例:"wordgoodgoodgoodbestword"
        //			["word","good","best","good"]
        //	测试结果:[]
        //	期望结果:[8]
        String s="wordgoodgoodgoodbestword";
        String[] words={"word","good","best","good"};
        System.out.println(JSONObject.toJSONString(findSubstring(s,words)));
    }
}
