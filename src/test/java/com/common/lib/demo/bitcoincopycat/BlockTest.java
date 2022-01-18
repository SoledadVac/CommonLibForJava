package com.common.lib.demo.bitcoincopycat;

import com.alibaba.fastjson.JSONObject;
import com.google.common.base.Charsets;
import com.google.common.hash.Hashing;
import org.junit.Test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * \* Created: liuhuichao
 * \* Date: 2021/11/20
 * \* Time: 6:09 下午
 * \* Description: test block
 * \
 */
public class BlockTest {
    /**
     * 创建区块以及初世区块
     */
    @Test
    public void testNewBlock() {
        //区块链-------
        List<Block> blockChain = new LinkedList<>();
        //先生成个创世区块
        Block god = new Block(1, "1", System.currentTimeMillis(), new ArrayList<>(), 1, "1");
        //将创世区块加入区块链
        blockChain.add(god);

        //挖矿过程模拟------
        //创建一些交易
        List<Transaction> transactionList = new ArrayList<>();
        Transaction t1 = new Transaction();
        Transaction t2 = new Transaction();
        //...
        transactionList.add(t1);
        transactionList.add(t2);

        //创建好关于我这次挖矿收益的系统给我的奖励交易
        Transaction rewardTrans = new Transaction();
        transactionList.add(rewardTrans);
        //获取当前区块链里面最后一个区块
        Block tailBlock = blockChain.get(blockChain.size() - 1);
        String newHashValue;
        int nonce = 2;
        while (true) {
            //计算区块的哈希值
            //hash = SHA256(最后一个区块的Hash+交易记录信息+随机数)
            nonce = 2;
            String token = tailBlock.getHash() + JSONObject.toJSONString(tailBlock.getTransactions()) + nonce;
            newHashValue = Hashing.sha256().newHasher().putString(token, Charsets.UTF_8).hash().toString();
            //校验哈希值
            if (checkHashStandarded(newHashValue)) {
                break;
            }
            nonce++;
        }
        //创建新区块
        Block b2 = new Block(2, newHashValue, System.currentTimeMillis(), transactionList, nonce, "1");
        blockChain.add(b2);
        System.out.println(blockChain);
    }

    public boolean checkHashStandarded(String hash) {
        //这里是一些校验生成hash是不是符合要求的逻辑
        return true;
    }
}
