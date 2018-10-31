package com.common.lib.demo.grammer;

import org.junit.Test;

/**
 * \* Created: liuhuichao
 * \* Date: 2018/7/5
 * \* Time: 下午9:15
 * \* Description: 测试代码类
 * \
 */
public class AutoCloseableTest {

    @Test
    public  void cloneTest() {
        /**use {@code try}-with-resources constructions**/
        try(ObjAutoCloneable lock =new ObjAutoCloneable()){
            //放置
        }catch (Exception e){

        }
    }

    class  ObjAutoCloneable implements AutoCloseable{

        @Override
        public void close() throws Exception {
            System.out.println("这里放置关闭资源的方法");
        }
    }

}
