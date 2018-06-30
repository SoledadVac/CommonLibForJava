package com.common.lib.demo;

/**
 * \* Created: liuhuichao
 * \* Date: 2018/6/27
 * \* Time: 下午2:10
 * \* Description:
 * \
 */
public class TestClass {
    private long id;
    private String name;


    public TestClass(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public long getId() {
        return id;
    }


    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
