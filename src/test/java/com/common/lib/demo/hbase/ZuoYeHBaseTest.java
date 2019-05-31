package com.common.lib.demo.hbase;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.client.HBaseAdmin;
import org.junit.Test;

/**
 * \* Created: liuhuichao
 * \* Date: 2019/5/29
 * \* Time: 6:22 PM
 * \* Description:
 * \
 */
public class ZuoYeHBaseTest {

    private static Configuration conf = null;

    static {
        conf = HBaseConfiguration.create();
        conf.set("hbase.zookeeper.quorum", "10.200.2.126,10.200.2.125,10.200.2.124");
    }

    @Test
    public void test() throws Exception {
        HBaseAdmin admin = new HBaseAdmin(conf);
        boolean exist = admin.tableExists("aaa");
        System.out.println(exist);
    }

}
