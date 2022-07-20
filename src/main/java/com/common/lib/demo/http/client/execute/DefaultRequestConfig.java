/*
 * SHANGHAI SUNNY EDUCATION, INC. CONFIDENTIAL
 *
 * Copyright 2006-2015 Shanghai Sunny Education, Inc. All Rights Reserved.
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


import com.common.lib.demo.lang.SafeConverter;
import org.apache.http.client.config.RequestConfig;

/**
 * Default {@link RequestConfig} provider.
 *
 * @author Xiaohai Zhang
 * @version 20151209
 * @since Dec 9, 2015
 */
abstract public class DefaultRequestConfig {

    public static final int DEFAULT_CONNECT_TIMEOUT = 5000;
    public static final int DEFAULT_SOCKET_TIMEOUT = 10000;

    public static final RequestConfig DEFAULT;

    static {
        DEFAULT = RequestConfig.custom()
                .setConnectTimeout(DEFAULT_CONNECT_TIMEOUT)
                .setSocketTimeout(DEFAULT_SOCKET_TIMEOUT)
                .build();
    }

    public static RequestConfig getRequestConfig(Integer connectionTimeout,
                                                 Integer socketTimeout) {
        if (connectionTimeout == null && socketTimeout == null) {
            return DEFAULT;
        }
        return RequestConfig.custom()
                .setConnectTimeout(SafeConverter.toInt(connectionTimeout, DEFAULT_CONNECT_TIMEOUT))
                .setSocketTimeout(SafeConverter.toInt(socketTimeout, DEFAULT_SOCKET_TIMEOUT))
                .build();
    }
}
