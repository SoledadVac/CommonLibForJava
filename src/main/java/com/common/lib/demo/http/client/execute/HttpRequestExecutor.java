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


import com.common.lib.demo.http.client.status.HttpClientType;

import java.net.URI;

/**
 * HTTP request executor abstraction.
 *
 * @author Xiaohai Zhang
 * @version 20151209
 * @since Dec 9, 2015
 */
public interface HttpRequestExecutor {

    /**
     * Use BASIC as default HTTP request executor.
     *
     * @return http request executor with BASIC (auto) type.
     */
    static HttpRequestExecutor defaultInstance() {
        return AutoBasicHttpRequestExecutor.INSTANCE;
    }

    static HttpRequestExecutor instance(HttpClientType type) {
        if (type == null) {
            type = HttpClientType.BASIC;
        }
        switch (type) {
            case BASIC: {
                return BasicHttpRequestExecutor.BASIC;
            }
            case BASIC_NO_HTTPS: {
                return BasicHttpRequestExecutor.BASIC_NO_HTTPS;
            }
            case POOLING: {
                return PoolingHttpRequestExecutor.INSTANCE;
            }
            default: {
                throw new UnsupportedOperationException();
            }
        }
    }

    default HEAD head(String uri) {
        return head(URI.create(uri));
    }

    HEAD head(URI uri);

    default GET get(String uri) {
        return get(URI.create(uri));
    }

    GET get(URI uri);

    default POST post(String uri) {
        return post(URI.create(uri));
    }

    POST post(URI uri);

    default PUT put(String uri) {
        return put(URI.create(uri));
    }

    PUT put(URI uri);

    default DELETE delete(String uri) {
        return delete(URI.create(uri));
    }

    DELETE delete(URI uri);
}
