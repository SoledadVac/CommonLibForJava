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

package com.common.lib.demo.http.client.factory;


import com.common.lib.demo.concurrent.ReentrantLocker;
import org.apache.commons.io.IOUtils;
import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.util.ClassUtils;

import java.io.Closeable;
import java.util.IdentityHashMap;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Default {@link HttpClientFactory} implementation.
 *
 * @author Xiaohai Zhang
 * @version 20151209
 * @since Dec 9, 2015
 */

class HttpClientFactoryImpl  implements HttpClientFactory{

  private HttpClientFactoryImpl() {
  }

  static final HttpClientFactoryImpl INSTANCE = new HttpClientFactoryImpl();

  @Override
  public HttpClient build(HttpClientBuilder builder) {
    Objects.requireNonNull(builder, "The HttpClientBuilder is required");
    return builder.build();
  }

}
