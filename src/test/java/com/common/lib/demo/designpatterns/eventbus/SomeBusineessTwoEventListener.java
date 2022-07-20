package com.common.lib.demo.designpatterns.eventbus;

import com.google.common.eventbus.Subscribe;

public class SomeBusineessTwoEventListener {
    @Subscribe
    public void dealSomeBusinessMessage(SomeBusinessMessage businessMessage) {
        //handle message body
        System.out.println(businessMessage.getContent() + "-----");
    }
}
