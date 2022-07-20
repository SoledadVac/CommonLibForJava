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
 * Abstract {@link HttpRequestExecutor} implementation.
 *
 * @author Xiaohai Zhang
 * @version 20151209
 * @since Dec 9, 2015
 */
abstract public class AbstractHttpRequestExecutor implements HttpRequestExecutor {

    abstract protected HttpClient getHttpClient(URI uri);

    @Override
    public HEAD head(URI uri) {
        return new HEAD(getHttpClient(uri), uri);
    }

    @Override
    public GET get(URI uri) {
        return new GET(getHttpClient(uri), uri);
    }

    @Override
    public POST post(URI uri) {
        return new POST(getHttpClient(uri), uri);
    }

    @Override
    public PUT put(URI uri) {
        return new PUT(getHttpClient(uri), uri);
    }

    @Override
    public DELETE delete(URI uri) {
        return new DELETE(getHttpClient(uri), uri);
    }
}
