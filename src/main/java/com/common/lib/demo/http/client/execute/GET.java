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

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.protocol.HttpContext;

import java.net.URI;

/**
 * HTTP GET request implementation.
 *
 * @author Xiaohai Zhang
 * @version 20151209
 * @since Dec 9, 2015
 */
public class GET extends AbstractHttpRequest<GET> {

    private final HttpGet httpGet;

    public GET(HttpClient httpClient, URI uri) {
        super(httpClient, uri);
        httpGet = new HttpGet(uri);
    }

    @Override
    protected HttpRequestBase getHttpRequestBase() {
        return httpGet;
    }

    @Override
    protected HttpResponse doOriginalExecute() throws Exception {
        try {
            return httpClient.execute(httpGet, (HttpContext) null);
        } catch (Exception ex) {

            throw ex;
        }
    }
}
