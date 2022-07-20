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
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.protocol.HttpContext;

import java.net.URI;

/**
 * HTTP DELETE request implementation.
 *
 * @author Xiaohai Zhang
 * @version 20160407
 * @since Apr 7, 2016
 */
public class DELETE extends AbstractHttpRequest<DELETE> {

    private final HttpDelete httpDelete;

    public DELETE(HttpClient httpClient, URI uri) {
        super(httpClient, uri);
        httpDelete = new HttpDelete(uri);
    }

    @Override
    protected HttpRequestBase getHttpRequestBase() {
        return httpDelete;
    }

    @Override
    protected HttpResponse doOriginalExecute() throws Exception {
        try {
            return httpClient.execute(httpDelete, (HttpContext) null);
        } catch (Exception ex) {
            if (logException) {
            }
            throw ex;
        }
    }
}
