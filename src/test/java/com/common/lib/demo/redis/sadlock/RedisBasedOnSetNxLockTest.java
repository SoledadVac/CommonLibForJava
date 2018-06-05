package com.common.lib.demo.redis.sadlock;

import com.common.lib.demo.redis.utils.RedisUtil;
import redis.clients.jedis.Jedis;

/**
 * \* Created: liuhuichao
 * \* Date: 2018/6/5
 * \* Time: 下午5:50
 * \* Description:redis悲观锁测试
 * \
 */
public class RedisBasedOnSetNxLockTest {


    /**
     * 顾客线程
     */
   class PessClientThread implements Runnable{

        String key = "prdNum";// 商品主键
        String clientList = "clientList";// // 抢购到商品的顾客列表主键
        String clientName;
        RedisBasedOnSetNxLock redisBasedOnSetNxLock;
        Jedis jedis = null;

        public void init(){
            jedis= RedisUtil.getInstance().getJedis();
            redisBasedOnSetNxLock=new RedisBasedOnSetNxLock(jedis,"",5*1000);
        }

        @Override
       public void run() {

       }
   }

}
