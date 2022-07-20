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

package com.common.lib.demo.http.client.execute;


import com.common.lib.demo.http.client.manager.HttpClientManager;
import com.common.lib.demo.http.client.status.HttpClientType;
import org.apache.http.client.HttpClient;

import java.net.URI;

/**
 * POOLING {@link HttpRequestExecutor} implementation.
 *
 * @author Xiaohai Zhang
 * @version 20151209
 * @since Dec 9, 2015
 */

public class PoolingHttpRequestExecutor extends AbstractHttpRequestExecutor {

  private PoolingHttpRequestExecutor() {
  }

  public static final PoolingHttpRequestExecutor INSTANCE = new PoolingHttpRequestExecutor();

  @Override
  protected HttpClient getHttpClient(URI uri) {
    return HttpClientManager.instance().getHttpClient(HttpClientType.POOLING);
  }
}
