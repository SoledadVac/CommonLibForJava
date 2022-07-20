package com.common.lib.demo.designpatterns.eventbus;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SomeBusinessMessage {
    private String content;
}
