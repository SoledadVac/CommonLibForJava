package com.common.lib.demo.designpatterns.eventbus;

import com.google.common.eventbus.EventBus;
import org.junit.Test;

public class GuavaEventBusTest {

    @Test
    public void test() {
        EventBus eventBus = new EventBus("test");
        eventBus.register(new SomeBusinessEventListener());
        eventBus.register(new SomeBusineessTwoEventListener());
        eventBus.post(new SomeBusinessMessage("这里是条新消息"));
    }
}
