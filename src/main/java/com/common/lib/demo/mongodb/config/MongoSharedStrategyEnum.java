package com.common.lib.demo.mongodb.config;

import java.util.stream.Stream;

/**
 * \* Created: liuhuichao
 * \* Date: 2018/9/11
 * \* Time: 上午11：54
 * \* Description: mongodb 分片策略配置
 * \
 */
public enum MongoSharedStrategyEnum {

    BY_YEAR(0, "根据年分"),
    BY_YEAR_MONTH(1, "根据年月分"),
    BY_YEAR_MONTH_DAY(2, "根据年月日分");

    private final int key;
    private final String value;

    MongoSharedStrategyEnum(Integer key, String value) {
        this.key = key;
        this.value = value;
    }

    public static MongoSharedStrategyEnum parse(int key){
        return Stream.of(values()).filter(item->item.getKey()==key).findFirst().orElse(null);
    }

    public int getKey() {
        return this.key;
    }

    public String getValue() {
        return this.value;
    }
}
