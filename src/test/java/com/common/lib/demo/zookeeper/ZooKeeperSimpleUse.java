package com.common.lib.demo.zookeeper;

import org.apache.zookeeper.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.CountDownLatch;

/**
 * \* Created: liuhuichao
 * \* Date: 2018/6/4
 * \* Time: 下午3:14
 * \* Description: zk simple use
 * \
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ZooKeeperSimpleUse {

    private ZooKeeper zooKeeper;

    private CountDownLatch countDownLatchSemaphore =new CountDownLatch(1);

    private String connectionStrings="localhost:2182";

    private int sessionTimeOut=5000;


    /**
     * init zk
     * @throws Exception
     */
    @Test
    public void initZk() throws Exception{
        zooKeeper=new ZooKeeper(connectionStrings,sessionTimeOut, new Watcher() {
            @Override
            public void process(WatchedEvent watchedEvent) {
                System.out.println("receive watch event:"+watchedEvent);
                if(Event.KeeperState.SyncConnected==watchedEvent.getState()){
                    countDownLatchSemaphore.countDown();//解除等待阻塞
                }
            }
        });
        countDownLatchSemaphore.await();
        System.out.println("zk session established");
    }

    /**
     * create a zk node
     */
    @Test
    public void createNodeTest() throws Exception{
        String path=zooKeeper.create("/tvb","sb".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL);
        System.out.println("path="+path);
    }



}
