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

package com.common.lib.demo.http.client.status;

/**
 * 根据HttpClientConnectionManager的不同实现来定义HttpClient的类型。
 * 目前有2个大类：BASIC and POOLING
 * {@code org.apache.http.impl.conn.BasicHttpClientConnectionManager}
 * 基于单连接，如果没有指定Connection，则自动设置其值为close
 * {@code org.apache.http.impl.conn.PoolingHttpClientConnectionManager}
 * 基于连接池管理，如果没有指定Connection，则自动设置其值为keep-alive
 *
 * @author Xiaohai Zhang
 * @version 20151209
 * @since Dec 9, 2015
 */
public enum HttpClientType {
    BASIC,          // BasicHttpClientConnectionManager
    BASIC_NO_HTTPS, // BasicHttpClientConnectionManager (without https supports)
    POOLING         // PoolingHttpClientConnectionManager
}
