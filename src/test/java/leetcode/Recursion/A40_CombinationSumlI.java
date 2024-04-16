package leetcode.Recursion;

import com.alibaba.fastjson.JSONObject;
import org.junit.Test;

import java.util.*;

public class A40_CombinationSumlI {

    List<List<Integer>> result=new ArrayList<>();
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        boolean[] used=new boolean[candidates.length];
        Arrays.fill(used,false);
        Arrays.sort(candidates);
        dfs(new ArrayList<Integer>(),used,candidates,target);
        return result;
    }

    void dfs(List<Integer> item,boolean[] used,int[] candidates,int target){
        int sum=item.stream().mapToInt(i->i).sum();
        if(sum==target){
            boolean alreadyContain=false;
            for(List<Integer> r:result){
                if(r.size()==item.size() && item.containsAll(r) && r.containsAll(item) ){
                    alreadyContain=true;
                    break;
                }
            }
            if(!alreadyContain){
                result.add(item);
            }
            return;
        }
        Set<Integer> floorSet=new HashSet<>();
        for(int i=0;i<candidates.length;i++){
            if(used[i] || floorSet.contains(candidates[i])){
                continue;
            }
            if(candidates[i]+sum>target){
                continue;
            }
            floorSet.add(candidates[i]);
            item.add(candidates[i]);
            used[i]=true;
            dfs(new ArrayList<>(item),Arrays.copyOf(used,used.length),candidates,target);
            item.remove(item.size()-1);
        }
    }

    @Test
    public void test(){
        //nput: candidates = [10,1,2,7,6,1,5], target = 8
        // Output: [ [1,1,6], [1,2,5], [1,7], [2,6] ]
        //- int[] candidates={10,1,2,7,6,1,5};
        //- int target=8;

        //测试用例:[4,4,2,1,4,2,2,1,3]
        //			6
        //	测试结果:[[1,1,2,2],[1,1,4],[1,2,3],[2,4]]
        //	期望结果:[[1,1,2,2],[1,1,4],[1,2,3],[2,2,2],[2,4]]
        //int[] candidates={4,4,2,1,4,2,2,1,3};
        //int target=6;


        //测试用例:[4,1,1,4,4,4,4,2,3,5]
        //			10
        //	测试结果:[[1,1,3,5],[1,1,4,4],[1,4,5],[2,3,5],[2,4,4]]
        //	期望结果:[[1,1,3,5],[1,1,4,4],[1,2,3,4],[1,4,5],[2,3,5],[2,4,4]]
        int[] candidates={4,1,1,4,4,4,4,2,3,5};
        int target=10;


        System.out.println(JSONObject.toJSONString(combinationSum2(candidates,target)));
    }
}
