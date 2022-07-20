/*
 * SHANGHAI SUNNY EDUCATION, INC. CONFIDENTIAL
 *
 * Copyright 2006-2016 Shanghai Sunny Education, Inc. All Rights Reserved.
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

import org.apache.http.client.HttpClient;

import java.net.URI;

/**
 * 基于BASIC的自动选择HttpClient实现，根据URI的schema字段选择。
 *
 * @author Xiaohai Zhang
 * @version 20160113
 * @since Jan 13, 2016
 */

public class AutoBasicHttpRequestExecutor extends AbstractHttpRequestExecutor {

  private AutoBasicHttpRequestExecutor() {
  }

  static final AutoBasicHttpRequestExecutor INSTANCE = new AutoBasicHttpRequestExecutor();

  @Override
  protected HttpClient getHttpClient(URI uri) {
    String schema = uri.getScheme();
    String f = schema == null ? "" : schema.toLowerCase();
    switch (f) {
      case "http": {
        return BasicHttpRequestExecutor.BASIC_NO_HTTPS.getHttpClient(uri);
      }
      case "https": {
        return BasicHttpRequestExecutor.BASIC.getHttpClient(uri);
      }
      default: {
        throw new UnsupportedOperationException("Unsupported URI schema (http | https): " + f);
      }
    }
  }
}
