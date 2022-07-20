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

import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.HttpClientBuilder;

/**
 * The factory for building (destroying) {@link HttpClient}.
 * The main purpose of this is providing monitor-able {@link HttpClient} enhancement.
 *
 * @author Xiaohai Zhang
 * @version 20151209
 * @see HttpClientBuilder
 * @since Dec 9, 2015
 */
public interface HttpClientFactory {

  static HttpClientFactory instance() {
    return HttpClientFactoryImpl.INSTANCE;
  }

  HttpClient build(HttpClientBuilder builder);

}
