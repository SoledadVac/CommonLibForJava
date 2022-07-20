package com.common.lib.demo.test;


import com.lmax.disruptor.EventFactory;
import com.lmax.disruptor.EventHandler;
import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.util.DaemonThreadFactory;
import lombok.Data;
import org.junit.Test;

import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.ByteBuffer;
import java.util.concurrent.atomic.AtomicLong;

/**
 * \* Created: liuhuichao
 * \* Date: 2019/10/10
 * \* Time: 11:37 AM
 * \* Description:
 * \
 */
public class TestClass {

    @Test
    public void test() throws InterruptedException {
        AtomicLong counter=new AtomicLong(0);
        int bufferSize = 1024;
        Disruptor<LongEvent> disruptor = new Disruptor<>(LongEvent::new, bufferSize, DaemonThreadFactory.INSTANCE);
        disruptor.handleEventsWith(new LongEventHandler());
        disruptor.start();
        RingBuffer<LongEvent> ringBuffer = disruptor.getRingBuffer();
        ringBuffer.publishEvent((event, sequence, buffer) -> event.setValue(counter.getAndIncrement()));
        Thread.sleep(1000);
    }


    @Data
    public class LongEvent {
        private long value;
    }

    public class LongEventFactory implements EventFactory<LongEvent> {
        @Override
        public LongEvent newInstance() {
            return new LongEvent();
        }
    }

    public class LongEventHandler implements EventHandler<LongEvent> {
        @Override
        public void onEvent(LongEvent event, long sequence, boolean endOfBatch) {
            System.out.println("Event: " + event);
        }
    }


}
