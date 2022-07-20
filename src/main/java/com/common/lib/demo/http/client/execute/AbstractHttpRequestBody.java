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

import com.alibaba.fastjson.JSONObject;
import com.common.lib.demo.http.client.status.MimeTypes;
import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.message.BasicNameValuePair;

import java.net.URI;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * 带有 body 的 HTTP request。用于支持 POST 和 PUT。
 *
 * @author Xiaohai Zhang
 * @version 20151216
 * @since Dec 16, 2015
 */
abstract public class AbstractHttpRequestBody<T extends AbstractHttpRequestBody<T>>
        extends AbstractHttpRequest<T> {

    /**
     * body的内容使用什么字符集？如果不指定，使用默认的UTF-8
     */
    private Charset contentCharset = Consts.UTF_8;

    private HttpEntity entity = null;
    private String json = null;
    private String xml = null;
    private final List<NameValuePair> parameters = new ArrayList<>();

    protected AbstractHttpRequestBody(HttpClient httpClient, URI uri) {
        super(httpClient, uri);
    }

    /**
     * 可以强行在此处将contentCharset设置为null
     *
     * @param contentCharset content charset to be used
     * @return current instance
     */
    @SuppressWarnings("unchecked")
    public T contentCharset(Charset contentCharset) {
        this.contentCharset = contentCharset;
        return (T) this;
    }

    /**
     * 最高优先级，当使用entity的时候。其余的json, xml, parameters都将被忽略
     *
     * @param entity the http entity
     * @return current instance
     */
    @SuppressWarnings("unchecked")
    public T entity(HttpEntity entity) {
        this.entity = entity;
        return (T) this;
    }

    @SuppressWarnings("unchecked")
    public T json(Object json) {
        if (entity != null) {
            throw new IllegalStateException("HttpEntity already set");
        }
        if (xml != null) {
            throw new IllegalStateException("XML content already set");
        }
        if (!parameters.isEmpty()) {
            throw new IllegalStateException("Parameters already added");
        }
        if (json == null) {
            return (T) this;
        }
        if (json instanceof String) {
            this.json = (String) json;
        } else {
            this.json = JSONObject.toJSONString(json);
        }
        return (T) this;
    }

    @SuppressWarnings("unchecked")
    public T xml(String xml) {
        if (entity != null) {
            throw new IllegalStateException("HttpEntity already set");
        }
        if (json != null) {
            throw new IllegalStateException("JSON content already set");
        }
        if (!parameters.isEmpty()) {
            throw new IllegalStateException("Parameters already added");
        }
        if (xml == null) {
            return (T) this;
        }
        this.xml = xml;
        return (T) this;
    }

    @SuppressWarnings("unchecked")
    public T addParameter(String name, String value) {
        Objects.requireNonNull(name);
        Objects.requireNonNull(value);
        parameters.add(new BasicNameValuePair(name, value));
        return (T) this;
    }

    @SuppressWarnings("unchecked")
    public T addParameter(Map<Object, Object> map) {
        if (map != null) {
            map.entrySet().forEach(e -> {
                String name = e.getKey().toString();
                String value = e.getValue().toString();
                addParameter(name, value);
            });
        }
        return (T) this;
    }

    @Override
    protected HttpResponse doOriginalExecute() throws Exception {
        if (entity == null) {
            if (json != null) {
                ContentType contentType = ContentType.create(MimeTypes.JSON, contentCharset);
                entity = new StringEntity(json, contentType);
            } else if (xml != null) {
                ContentType contentType = ContentType.create(MimeTypes.XML, contentCharset);
                entity = new StringEntity(xml, contentType);
            } else if (!parameters.isEmpty()) {
                entity = new UrlEncodedFormEntity(parameters, contentCharset);
            }
        }
        return doOriginalExecute(entity);
    }

    abstract protected HttpResponse doOriginalExecute(HttpEntity entity) throws Exception;
}
