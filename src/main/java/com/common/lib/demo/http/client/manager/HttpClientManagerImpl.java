/*
 * SHANGHAI SUNNY EDUCATION, INC. CONFIDENTIAL
 *
 * Copyright 2011-2016 Shanghai Sunny Education, Inc. All Rights Reserved.
 *
 * NOTICE: All information contained herein is, and remains the property of
 * Shanghai Sunny Education, Inc. and its suppliers, if any. The intellectual
 * and technical concepts contained herein are proprietary to Shanghai Sunny
 * Education, Inc. and its suppliers and may be covered by patents, patents
 * in process, and are protected by trade secret or copyright law. Dissemination
 * of this information or reproduction of this material is strictly forbidden
 * unless prior written permission is obtained from Shanghai Sunny Education, Inc.
 */

package com.common.lib.demo.http.client.manager;


import com.common.lib.demo.concurrent.ReentrantLocker;
import com.common.lib.demo.http.client.status.HttpClientType;
import com.common.lib.demo.http.client.factory.HttpClientFactory;
import org.apache.http.client.HttpClient;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.BasicHttpClientConnectionManager;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.protocol.RequestConnControl;

/**
 * Default {@link HttpClientManager} implementation.
 * If type is BASIC, create {@link HttpClient} every time and close it after execute method invocation.
 * If type is POOLING, keep it.
 *
 * @author Xiaohai Zhang
 * @version 20151209
 * @since Dec 9, 2015
 */
class HttpClientManagerImpl implements HttpClientManager {

  private static final int DEFAULT_MAX_TOTAL_CONNECTIONS = 100;
  private static final int DEFAULT_MAX_CONNECTIONS_PER_ROUTE = 5;

  static final HttpClientManagerImpl INSTANCE = new HttpClientManagerImpl();

  private final ReentrantLocker locker = new ReentrantLocker();
  private final HttpClientFactory httpClientFactory = HttpClientFactory.instance();

  private final HttpClientBuilder poolingBuilder;
  private HttpClient poolingHttpClient = null;

  private HttpClientManagerImpl() {
    PoolingHttpClientConnectionManager connectionManager = new PoolingHttpClientConnectionManager();
    connectionManager.setMaxTotal(DEFAULT_MAX_TOTAL_CONNECTIONS);
    connectionManager.setDefaultMaxPerRoute(DEFAULT_MAX_CONNECTIONS_PER_ROUTE);
    poolingBuilder = HttpClients.custom()
        .setConnectionManager(connectionManager)
        .addInterceptorFirst(new RequestConnControl());
  }

  @Override
  public HttpClient getHttpClient(HttpClientType type) {
    if (type == null) {
      return null;
    }
    switch (type) {
      case BASIC: {
        BasicHttpClientConnectionManager connectionManager = new BasicHttpClientConnectionManager();
        HttpClientBuilder basicBuilder = CreateBasicHttpClientBuilder.create(connectionManager);
        return httpClientFactory.build(basicBuilder);
      }
      case BASIC_NO_HTTPS: {
        Registry<ConnectionSocketFactory> registry = RegistryBuilder.<ConnectionSocketFactory>create()
            .register("http", PlainConnectionSocketFactory.getSocketFactory())
            .build();
        BasicHttpClientConnectionManager connectionManager = new BasicHttpClientConnectionManager(
            registry, null, null, null);
        HttpClientBuilder basicBuilder = CreateBasicHttpClientBuilder.create(connectionManager);
        return httpClientFactory.build(basicBuilder);
      }
      case POOLING: {
        return locker.withinLock(() -> {
          if (poolingHttpClient != null) {
            return poolingHttpClient;
          }
          poolingHttpClient = httpClientFactory.build(poolingBuilder);
          return poolingHttpClient;
        });
      }
      default: {
        throw new UnsupportedOperationException();
      }
    }
  }

}
