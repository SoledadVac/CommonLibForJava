package com.common.lib.demo.mongodb.basic;

import com.alibaba.fastjson.JSONObject;
import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.WriteConcern;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoIterable;
import com.mongodb.client.model.Filters;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * \* Created: liuhuichao
 * \* Date: 2018/9/10
 * \* Time: 下午8:25
 * \* Description: mongodb
 * \
 */
public class BasicOpTest {

    MongoClient mongoClient;

    @Before
    public void initDriver() {
        System.out.println("===============MongoDB connect 初始化========================");
        String ip = "127.0.0.1";
        int port = 27017;
        mongoClient = new MongoClient(ip, port);
        // 大部分用户使用mongodb都在安全内网下，但如果将mongodb设为安全验证模式，就需要在客户端提供用户名和密码：
        // boolean auth = db.authenticate(myUserName, myPassword);
        MongoClientOptions.Builder options = new MongoClientOptions.Builder();
        options.cursorFinalizerEnabled(true);
        options.connectionsPerHost(300);// 连接池设置为300个连接,默认为100
        options.connectTimeout(30000);// 连接超时，推荐>3000毫秒
        options.maxWaitTime(5000); //
        options.socketTimeout(0);// 套接字超时时间，0无限制
        options.threadsAllowedToBlockForConnectionMultiplier(5000);// 线程队列数，如果连接线程排满了队列就会抛出“Out of semaphores to get db”错误。
        options.writeConcern(WriteConcern.SAFE);//
        options.build();
    }

    @After
    public void closeConnect(){
        if (mongoClient != null) {
            mongoClient.close();
            mongoClient = null;
        }
    }

    /**
     * 获取连接
     */
    @Test
    public void getDB() {
        String dbName = "testdb";
        MongoDatabase database = mongoClient.getDatabase(dbName);
        System.out.println(database != null);
    }

    /**
     * 获取collection
     */
    @Test
    public void getCollection() {
        String dbName = "testdb";
        String collectionName = "user";
        MongoCollection<Document> collection = mongoClient.getDatabase(dbName).getCollection(collectionName);
        System.out.println(collection != null);
    }

    /**
     * 获取所有的集合名称
     */
    @Test
    public void getAllCollectionsTest() {
        String dbName = "testdb";
        MongoIterable<String> colls = mongoClient.getDatabase(dbName).listCollectionNames();
        List<String> nameList = new ArrayList<>();
        for (String s : colls) {
            nameList.add(s);
        }
        System.out.println(nameList);
    }

    /**
     * 获取所有的db名称
     */
    @Test
    public void getAllDb() {
        MongoIterable<String> dbIter = mongoClient.listDatabaseNames();
        List<String> nameList = new ArrayList<>();
        for (String db : dbIter) {
            nameList.add(db);
        }
        System.out.println(nameList);
    }

    /**
     * 删除一个db
     */
    public void dropDb(){
        String dbName = "testdb";
        mongoClient.getDatabase(dbName).drop();
    }

    /**
     * 删除一个colllection
     */
    public void dropCollection(){
        String dbName = "testdb";
        String collectionName = "user";
        mongoClient.getDatabase(dbName).getCollection(collectionName).drop();
    }

    /**
     * 根据id查找数据
     */
    @Test
    public void findById(){
        String dbName = "testdb";
        String collectionName = "user";
        String id="5b6068c94b008f4e969ef4fd";
        String name="jay";
        MongoCollection<Document> collection = mongoClient.getDatabase(dbName).getCollection(collectionName);
        Document myDoc = collection.find(Filters.eq("name", name)).first();
        System.out.println(JSONObject.toJSONString(myDoc));
    }

    /**
     * 获取集合数据条数
     */
    @Test
    public void getTotalCount(){
        String dbName = "testdb";
        String collectionName = "user";
        MongoCollection<Document> collection = mongoClient.getDatabase(dbName).getCollection(collectionName);
        long count=collection.count();
        System.out.println(count);
    }

    /**
     * 根据id删除
     */
    @Test
    public void deleteById(){
        String dbName = "testdb";
        String collectionName = "user";
        long count = 0L;
        String id="5b6068c94b008f4e969ef4fd";
        ObjectId _id = null;
        try {
            _id = new ObjectId(id);
        } catch (Exception e) {}
        Bson filter = Filters.eq("_id", _id);
        DeleteResult deleteResult = mongoClient.getDatabase(dbName).getCollection(collectionName).deleteOne(filter);
        count = deleteResult.getDeletedCount();
        System.out.println(count);
    }

    /**
     * 根据id更新数据
     */
    @Test
    public void updateById(){
        String dbName = "testdb";
        String collectionName = "user";
        String id="5b604ac423cc834a5495d780";
        ObjectId _id = null;
        try {
            _id = new ObjectId(id);
        } catch (Exception e) {}
        MongoCollection<Document> collection = mongoClient.getDatabase(dbName).getCollection(collectionName);
        Document newdoc=collection.find(Filters.eq("_id", _id)).first();
        newdoc.append("test",1);
        Bson filter = Filters.eq("_id", _id);
        // coll.replaceOne(filter, newdoc); // 完全替代
        UpdateResult result=collection.updateOne(filter, new Document("$set", newdoc));
        System.out.println(result.getMatchedCount());
    }

    /**
     * 条件查询
     * @param coll
     * @param filter
     * @return
     */
    private MongoCursor<Document> find(MongoCollection<Document> coll, Bson filter) {
        return coll.find(filter).iterator();
    }

    /** 分页查询 */
    private MongoCursor<Document> findByPage(MongoCollection<Document> coll, Bson filter, int pageNo, int pageSize) {
        Bson orderBy = new BasicDBObject("_id", 1);
        return coll.find(filter).sort(orderBy).skip((pageNo - 1) * pageSize).limit(pageSize).iterator();
    }



}
