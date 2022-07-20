package com.common.lib.demo.http;

import com.common.lib.demo.http.client.execute.HttpRequestExecutor;
import org.junit.Test;

public class HttpRequestExecutorTest {
    @Test
    public void test() throws Exception {
        System.out.println(HttpRequestExecutor.defaultInstance().post("https://www.json.cn").execute().getResponseString());
    }
}
