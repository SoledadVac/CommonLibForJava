package com.common.lib.demo.concurrent;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * \* Created: liuhuichao
 * \* Date: 2018/10/29
 * \* Time: 3:29 PM
 * \* Description:
 * \
 */
@Data
@AllArgsConstructor
public class Person {
    private String name;
    public volatile int age;
}
