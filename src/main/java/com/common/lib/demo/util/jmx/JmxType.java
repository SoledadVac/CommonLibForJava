package com.common.lib.demo.util.jmx;

import javax.management.ObjectName;

public enum JmxType {
    common,         // default "com.voxlearning.alps:type=common",
    misc,
    application,
    core,
    lang,           // com.voxlearning.alps:type=lang
    serialize,      // serialize related "com.voxlearning.alps:type=serialize"
    http,           // http related "com.voxlearning.alps:type=http"
    config,         // config related "com.voxlearning.alps:type=config"
    runtime,        // runtime related "com.voxlearning.alps:type=runtime"
    monitor,
    management,
    downgrade,
    circuitbreaker,
    cache,          // cache related "com.voxlearning.alps:type=cache"
    couchbase,      // couchbase related "com.voxlearning.alps:type=couchbase"
    ehcache,        // ehcache related "com.voxlearning.alps:type=ehcache"
    hazelcast,
    memcached,      // memcached related "com.voxlearning.alps:type=memcached"
    redis,          // redis related "com.voxlearning.alps:type=redis"
    aerospike,
    dao,
    jdbc,           // jdbc related "com.voxlearning.alps:type=jdbc"
    mysql,
    tidb,
    mongo,          // mongo related "com.voxlearning.alps:type=mongo",
    hbase,
    influxdb,       // influxdb related "com.voxlearning.alps:type=influxdb",
    derby,
    clickhouse,     // clickhouse related "com.voxlearning.alps:type=clickhouse",
    kudu,           // kudu related "com.voxlearning.alps:type=kudu",
    elasticsearch,  // elasticsearch related "com.voxlearning.alps:type=elasticsearch"
    remote,
    dubbo,          // dubbo related "com.voxlearning.alps:type=dubbo"
    hydra,
    ditto,
    dittoClient,
    dittoServer,
    webmvc,         // com.voxlearning.alps:type=webmvc
    email,          // email related "com.voxlearning.alps:type=email"
    queue,
    rabbitmq,       // rabbitmq related "com.voxlearning.alps:type=rabbitmq"
    activemq,       // activemq related "com.voxlearning.alps:type=activemq"
    kafka,          // kafka related "com.voxlearning.alps:type=kafka"
    rocketmq,       // rocketmq related "com.voxlearning.apls:type=rocketmq"
    pubsub,
    storage,
    aliyunoss,      // aliyun oss "com.voxlearning.alps:type=aliyunoss"
    gridfs,
    schedule,
    embedded,
    embed,
    theia,          // for full link monitor
    zookeeper,
    bootstrap;      // bootstrap related "com.voxlearning.alps:type=bootstrap"


    public static final String DOMAIN = "com.voxlearning.alps";

    /**
     * Generate an object name of specified name.
     *
     * @param name the name attribute, must not be null.
     * @return the object name under current type with specified name.
     */
    public ObjectName objectName(String name) {
        return ObjectNameGenerator.generate(this.name(), name);
    }


    /**
     * Get JMX name base string.
     *
     * @return JMX name base string
     */
    public String getJmxNameBase() {
        return DOMAIN + ":type=" + this.name() + ",name=";
    }
}
