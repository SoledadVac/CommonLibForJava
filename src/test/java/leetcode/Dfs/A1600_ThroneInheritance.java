package leetcode.Dfs;


import com.alibaba.fastjson.JSONObject;
import com.google.gson.JsonObject;
import org.junit.Test;

import java.util.*;

/**
 * Your ThroneInheritance object will be instantiated and called as such:
 * ThroneInheritance obj = new ThroneInheritance(kingName);
 * obj.birth(parentName,childName);
 * obj.death(name);
 * List<String> param_3 = obj.getInheritanceOrder();
 */
public class A1600_ThroneInheritance {


    class ThroneInheritance {
        Map<String, List<String>> fatherAndSonMap = new HashMap<>();
        String currentKing = null;
        Map<String,String> deathList = new HashMap<>();

        public ThroneInheritance(String kingName) {
            currentKing = kingName;
        }

        public void birth(String parentName, String childName) {
            List<String> sonList = fatherAndSonMap.getOrDefault(parentName, new LinkedList<>());
            sonList.add(childName);
            fatherAndSonMap.put(parentName, sonList);
        }

        public void death(String name) {
            deathList.put(name,"");
        }

        public List<String> getInheritanceOrder() {
            List<String> orderList = new LinkedList<>();
            getInheritance(currentKing, orderList);
            return orderList;
        }

        public void getInheritance(String parent, List<String> orderList) {
            if(!deathList.containsKey(parent)){
                orderList.add(parent);
            }
            List<String> sonList = fatherAndSonMap.get(parent);
            if (sonList == null) {
                return;
            }
            for (String son : sonList) {
                getInheritance(son, orderList);
            }
        }


    }

    @Test
    public void test() {
        /**
         输入
         ["ThroneInheritance","birth","birth","birth","birth","birth","birth","getInheritanceOrder","death","getInheritanceOrder"]
         [["king"],["king","andy"],["king","bob"],["king","catherine"],["andy","matthew"],["bob","alex"],["bob","asha"],
         [null],
         ["bob"],[null]]

         输出
         [null,null,null,null,null,null,null,["king","andy","matthew","bob","alex","asha","catherine"]
         ,null,["king","andy","matthew","catherine"]]

         预期结果
         [null,null,null,null,null,null,null,["king","andy","matthew","bob","alex","asha","catherine"]
         ,null,["king","andy","matthew","alex","asha","catherine"]]

         */
    }

}
