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


import com.common.lib.demo.http.client.status.HttpClientType;
import org.apache.http.client.HttpClient;

/**
 * Manager of {@link HttpClient}.
 * Don't forget release the client if type == BASIC
 *
 * @author Xiaohai Zhang
 * @version 20151209
 * @since Dec 9, 2015
 */
public interface HttpClientManager {

  static HttpClientManager instance() {
    return HttpClientManagerImpl.INSTANCE;
  }

  HttpClient getHttpClient(HttpClientType type);
}
