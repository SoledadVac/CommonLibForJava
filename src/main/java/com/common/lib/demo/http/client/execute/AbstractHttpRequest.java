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

import org.apache.commons.math3.util.Pair;
import org.apache.http.Header;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.message.BasicHeader;
import org.slf4j.Logger;

import java.io.Closeable;
import java.net.URI;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Abstract http request implementation.
 *
 * @author Xiaohai Zhang
 * @version 20151209
 * @since Dec 9, 2015
 */
abstract class AbstractHttpRequest<T extends AbstractHttpRequest<T>> {

    protected final HttpClient httpClient;
    protected final URI uri;

    protected boolean logException = true;

    protected Integer connectionTimeout = null;
    protected Integer socketTimeout = null;
    protected RequestConfig requestConfig = null;

    protected final Map<String, String> headers = new LinkedHashMap<>();
    protected final Map<String, Object> params = new LinkedHashMap<>();

    protected AbstractHttpRequest(HttpClient httpClient, URI uri) {
        this.httpClient = Objects.requireNonNull(httpClient);
        this.uri = Objects.requireNonNull(uri);
    }

    @SuppressWarnings("unchecked")
    public T turnOffLogException() {
        logException = false;
        return (T) this;
    }

    // ========================================================================
    // Request config related
    // ========================================================================

    @SuppressWarnings("unchecked")
    public T connectionTimeout(int connectionTimeout) {
        this.connectionTimeout = connectionTimeout;
        return (T) this;
    }

    @SuppressWarnings("unchecked")
    public T socketTimeout(int socketTimeout) {
        this.socketTimeout = socketTimeout;
        return (T) this;
    }

    /**
     * If customized RequestConfig specified, the {@link #connectionTimeout}
     * and {@link #socketTimeout} will be ignored.
     *
     * @param requestConfig request config use be used
     * @return self instance
     */
    @SuppressWarnings("unchecked")
    public T requestConfig(RequestConfig requestConfig) {
        this.requestConfig = requestConfig;
        return (T) this;
    }

    // ========================================================================
    // Request header related
    // ========================================================================

    /**
     * 发现了绿网的服务器有特殊的行为。
     * 如果contentType=application/json，返回json格式的结果
     * 如果contentType=application/json; charset=UTF-8，则返回xml格式的结果
     * 所以有的时候可以通过这个方法指定contentType。这里的contentType的优先级会
     * 高于HttpEntity里的contentType
     * 因此保留这样的方法提供一个自行指定contentType的便捷方式
     *
     * @param contentType the content type
     * @return request itself
     */
    @SuppressWarnings("unchecked")
    public T contentType(String contentType) {
        if (contentType != null) {
            headers(new BasicHeader("Content-Type", contentType));
        }
        return (T) this;
    }

    @SuppressWarnings("unchecked")
    public T authorization(String authorization) {
        if (authorization != null) {
            headers(new BasicHeader("Authorization", authorization));
        }
        return (T) this;
    }

    @SuppressWarnings("unchecked")
    public T userAgent(String userAgent) {
        if (userAgent != null) {
            headers(new BasicHeader("User-Agent", userAgent));
        }
        return (T) this;
    }

    @SuppressWarnings("unchecked")
    public T headers(Header... headers) {
        if (headers != null) {
            for (Header header : headers) {
                this.headers.put(header.getName(), header.getValue());
            }
        }
        return (T) this;
    }

    @SuppressWarnings("unchecked")
    public T headers(Map<String, String> headers) {
        if (headers != null) {
            this.headers.putAll(headers);
        }
        return (T) this;
    }

    // ========================================================================
    // HTTP params related (not URL parameters)
    // ========================================================================

    @SuppressWarnings("unchecked")
    public T params(Pair<String, Object>... params) {
        if (params != null) {
            for (Pair<String, Object> param : params) {
                this.params.put(param.getKey(), param.getValue());
            }
        }
        return (T) this;
    }

    @SuppressWarnings("unchecked")
    public T params(Map<String, Object> params) {
        if (params != null) {
            this.params.putAll(params);
        }
        return (T) this;
    }

    @SuppressWarnings({"unchecked", "deprecation"})
    public T virtualHost(String virtualHost) {
        if (virtualHost != null) {
            this.params.put(org.apache.http.client.params.ClientPNames.VIRTUAL_HOST, new HttpHost(virtualHost));
        }
        return (T) this;
    }

    public AlpsHttpResponse execute() {
        AlpsHttpResponse result = new AlpsHttpResponse(getHttpRequestBase());
        HttpResponse response = null;
        try {
            response = originalExecute();
            result.initialize(response);
        } catch (Exception e) {
            result.setHttpClientException(e);
        }
        return result;

    }

    public HttpResponse originalExecute() throws Exception {
        HttpRequestBase base = getHttpRequestBase();
        Objects.requireNonNull(base);
        setRequestConfigIfNecessary(base);
        setParamsIfNecessary(base);
        setHeadersIfNecessary(base);
        return doOriginalExecute();
    }

    abstract protected HttpResponse doOriginalExecute() throws Exception;

    abstract protected HttpRequestBase getHttpRequestBase();

    protected void setRequestConfigIfNecessary(HttpRequestBase base) {
        RequestConfig requestConfigForUse = requestConfig;
        if (requestConfigForUse == null) {
            requestConfigForUse = DefaultRequestConfig.getRequestConfig(connectionTimeout, socketTimeout);
        }
        base.setConfig(requestConfigForUse);
    }

    @SuppressWarnings("deprecation")
    protected void setParamsIfNecessary(HttpRequestBase base) {
        if (params.isEmpty()) {
            return;
        }
        params.entrySet().forEach(e -> {
            String name = e.getKey();
            Object value = e.getValue();
            base.getParams().setParameter(name, value);
        });
    }

    protected void setHeadersIfNecessary(HttpRequestBase base) {
        if (headers.isEmpty()) {
            return;
        }
        headers.entrySet().forEach(e -> {
            String name = e.getKey();
            String value = e.getValue();
            base.addHeader(name, value);
        });
    }

    /**
     * 有的时候,URI实在太长了。通过syslog无法完整输出，会影响到看不到后面的exception信息
     * 这里，发现URI如果超过512，则截断
     *
     * @param uri uri
     * @return uri string, length &lt;=512
     */
    protected String truncateUriIfNecessary(URI uri) {
        if (uri == null) {
            return null;
        }
        String s = uri.toString();
        int len = s.length();
        if (len <= 512) {
            return s;
        }
        StringBuilder sbuf = new StringBuilder(s);
        sbuf.setLength(512);
        sbuf.append("...");
        sbuf.append("<TRUNCATED,LENGTH=").append(len).append(">");
        return sbuf.toString();
    }
}
