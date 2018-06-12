package com.common.lib.demo.redis.sadlock;

import com.common.lib.demo.redis.utils.RedisUtil;
import org.junit.Test;
import redis.clients.jedis.Jedis;

import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * \* Created: liuhuichao
 * \* Date: 2018/6/5
 * \* Time: 下午5:50
 * \* Description:redis悲观锁测试
 * \
 */
public class RedisBasedOnSetNxLockTest {


    @Test
    public void buyGoodsTest(){
        long starTime=System.currentTimeMillis();

        initPrduct();
        initClient();
        printResult();

        long endTime=System.currentTimeMillis();
        long Time=endTime-starTime;
        System.out.println("程序运行时间： "+Time+"ms");
    }

    /**
     * 输出结果
     */
    public  void printResult() {
        Jedis jedis = RedisUtil.getInstance().getJedis();
        Set<String> set = jedis.smembers("clientList");

        int i = 1;
        for (String value : set) {
            System.out.println("第" + i++ + "个抢到商品，" + value + " ");
        }

        RedisUtil.returnResource(jedis);
    }

    /*
        * 初始化顾客开始抢商品
        */
    public  void initClient() {
        ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
        int clientNum = 1000;// 模拟客户数目
        for (int i = 0; i < clientNum; i++) {
            cachedThreadPool.execute(new PessClientThread(i));
        }
        cachedThreadPool.shutdown();

        while (true) {
            if (cachedThreadPool.isTerminated()) {
                System.out.println("所有的线程都结束了！");
                break;
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 初始化商品个数
     */
    public  void initPrduct() {
        int prdNum = 100;// 商品个数
        String key = "prdNum";
        String clientList = "clientList";// 抢购到商品的顾客列表
        Jedis jedis = RedisUtil.getInstance().getJedis();

        if (jedis.exists(key)) {
            jedis.del(key);
        }

        if (jedis.exists(clientList)) {
            jedis.del(clientList);
        }

        jedis.set(key, String.valueOf(prdNum));// 初始化
        RedisUtil.returnResource(jedis);
    }


    /**
     * 顾客线程
     */
   class PessClientThread implements Runnable{

        String key = "prdNum";// 商品主键
        String clientList = "clientList";// // 抢购到商品的顾客列表主键
        String clientName;
        RedisBasedOnSetNxLock redisBasedOnSetNxLock;
        Jedis jedis = null;

        public PessClientThread(int num) {
            clientName = "编号=" + num;
            init();
        }

        public void init(){
            jedis= RedisUtil.getInstance().getJedis();
            redisBasedOnSetNxLock=new RedisBasedOnSetNxLock(jedis,"lock.lock",5*1000);
        }

        @Override
       public void run() {
            try {
                Thread.sleep((int)Math.random()*5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            while (true){
                //先判断缓存是否有商品
                if(Integer.valueOf(jedis.get(key))<= 0) {
                    break;
                }

                //缓存还有商品，取锁，商品数目减去1
                System.out.println("顾客:" + clientName + "开始抢商品");
                if (redisBasedOnSetNxLock.tryLock(3, TimeUnit.SECONDS)) { //等待3秒获取锁，否则返回false
                    int prdNum = Integer.valueOf(jedis.get(key)); //再次取得商品缓存数目
                    if (prdNum > 0) {
                        jedis.decr(key);//商品数减1
                        jedis.sadd(clientList, clientName);// 抢到商品记录一下
                        System.out.println("好高兴，顾客:" + clientName + "抢到商品");
                    } else {
                        System.out.println("悲剧了，库存为0，顾客:" + clientName + "没有抢到商品");
                    }
                    redisBasedOnSetNxLock.unlock();
                    break;
                }
            }
            //释放资源
            redisBasedOnSetNxLock = null;
            RedisUtil.returnResource(jedis);
        }

        }


}
