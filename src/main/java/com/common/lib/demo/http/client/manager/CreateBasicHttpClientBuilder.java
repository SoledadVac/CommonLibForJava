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

package com.common.lib.demo.http.client.manager;

import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.BasicHttpClientConnectionManager;
import org.apache.http.protocol.HTTP;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.Args;

import java.util.Objects;

/**
 * Helper for creating basic {@link HttpClientBuilder}.
 *
 * @author Xiaohai Zhang
 * @version 20160331
 * @since Mar 31, 2016
 */
abstract public class CreateBasicHttpClientBuilder {

    public static HttpClientBuilder create(BasicHttpClientConnectionManager connectionManager) {
        Objects.requireNonNull(connectionManager);
        return HttpClients.custom()
                .setConnectionManager(connectionManager)
                .addInterceptorFirst((org.apache.http.HttpRequest request, HttpContext context) -> {
                    Args.notNull(request, "HTTP request");
                    final String method = request.getRequestLine().getMethod();
                    if (method.equalsIgnoreCase("CONNECT")) {
                        return;
                    }
                    if (!request.containsHeader(HTTP.CONN_DIRECTIVE)) {
                        request.addHeader(HTTP.CONN_DIRECTIVE, HTTP.CONN_CLOSE);
                    }
                });
    }
}
